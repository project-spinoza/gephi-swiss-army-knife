package org.projectspinoza.gephiswissarmyknife.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.HashMap;
import java.util.Map;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.projectspinoza.gephiswissarmyknife.Main;
import org.projectspinoza.gephiswissarmyknife.configurations.ConfigurationHolder;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.YifanHu;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.LayoutsWrap;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.StatisticsWrap;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.utils.Utils;

import com.google.inject.Inject;

public class GraphServer {

  private ConfigurationHolder configHolder;
  private Vertx vertx;
  private HttpServer server;
  private Router router;
  private LayoutsWrap layoutsWrap;
  private StatisticsWrap statisticsWrap;
  private Graph gephiGraph;
  private SigmaGraph sigmaGraph;
  

  public GraphServer() {
    setVertx(Vertx.vertx());
    router = Router.router(vertx);
  }

  /*
   * @return void
   * 
   * Basic HTTP server on pre-configured host/port.
   * 
   * @Param 
   * 
   * deployRoutes: set to false if HTTP server deployment is required
   * instead of GSAK graph Routes.
   * 
   * */
  public void deployServer(boolean deployRoutes) {
    if (deployRoutes) {this.deployGsakRoutes();}
    server = vertx.createHttpServer().requestHandler(router::accept);
    this.server.listen(this.configHolder.getPort(), this.configHolder.getHost());
  }

  /*
   * @return void
   * 
   * Basic HTTP server on specified host/port.
   * 
   * @Param 
   * 
   * deployRoutes: set to false if HTTP server deployment is required
   * instead of GSAK graph Routes.
   * 
   * */
  public void deployServer(String host, int port, boolean deployRoutes) {
    if (deployRoutes) {this.deployGsakRoutes();}
    server = vertx.createHttpServer().requestHandler(router::accept);
    this.server.listen(port, host);    
  }
  
  private void deployGsakRoutes() {
    
    // static resources CSS/JS files
    router.getWithRegex(".*/css/.*|.*/js/.*|.*/images/.*|.*/assets/.*").handler(
        StaticHandler.create("public").setCachingEnabled(false));
    

    /*
     * GSAK specific Routes
     * 
     * '/' GSAK Welcome Screen.
     * 
     * '/graph' GSAK main graph UI.
     * 
     * */
    router.getWithRegex("/").method(HttpMethod.GET).handler(request -> {
      request.response().end("Welcome to Twitter-Grapher");
    });
    
    router.getWithRegex("/graph.*").method(HttpMethod.GET).handler(routingContext -> {
      routingContext.response().sendFile("public/index.html");
    });
    
    router.getWithRegex("/layout.*").method(HttpMethod.GET).handler(routingContext -> {
      this.layoutsWrap.applyLayout(routingContext.request().params());
      responseSigmaGraph (GephiGraph.getGraphModel().getGraphVisible(), routingContext);
    });

    
    /*
     * Graph statistics Route
     * */
    router.getWithRegex("/statistics.*").method(HttpMethod.GET).handler(routingContext -> {
      this.statisticsWrap.applyStatistics(routingContext.request().params());
      String resp = responseGraphStatistics(GephiGraph.getGraphModel().getGraphVisible(), routingContext);
      routingContext.response().end(resp);
    });
    
    
    /*
     * Temp route for demo
     * */
    router.getWithRegex("/ajax.*").method(HttpMethod.GET).handler(routingContext -> {
      GephiGraph gephiGraph = new GephiGraph();
      this.gephiGraph = gephiGraph.loadGraph(Main.graphfile, EdgeDirectionDefault.DIRECTED);
      Map <String, String> yifanParams = new HashMap<String, String>();
      yifanParams.put("quadtreeMaxLevel", "10.0");
      yifanParams.put("theta", "1.2");
      yifanParams.put("optimalDistance", "100.0");
      yifanParams.put("relativeStrength", "0.2");
      yifanParams.put("initialStepSize", "20.0");
      yifanParams.put("stepRatio", "0.95");
      yifanParams.put("adaptiveCooling", "true");
      yifanParams.put("convergenceThreshold", "1.0E-4");
      //YifanLayout
      new YifanHu().applyLayout(yifanParams);
      responseSigmaGraph(this.gephiGraph, routingContext);
    });
    
  }
  
  
  /*
   * @return String JSON.
   * Counts nodes for each degree, indegree, and outdegree value
   * 
   * */
  private String responseGraphStatistics (Graph graph, RoutingContext routingContext) {
    Map <String, Object> degreeMap = new HashMap<String, Object>();
    Map <String, Object> inDegreeMap = new HashMap<String, Object>();
    Map <String, Object> outDegreeMap = new HashMap<String, Object>();
    
    double sumDegree = 0;
    
    for (Node n : graph.getNodes()) {
      String dKey = n.getAttribute("degree").toString();
      String indKey = n.getAttribute("indegree").toString();
      String outdKey = n.getAttribute("outdegree").toString();
      
      sumDegree = sumDegree + Double.parseDouble(dKey);
      
      //Degree Calculation
      if (degreeMap.containsKey(dKey)){
        int d = Integer.parseInt(degreeMap.get(dKey).toString());
        degreeMap.put(dKey, d+1);
      }else {
        degreeMap.put(dKey, 1);
      }
      
      //In-Degree Calculation
      if (inDegreeMap.containsKey(indKey)){
        int id = Integer.parseInt(inDegreeMap.get(indKey).toString());
        inDegreeMap.put(indKey, id+1);
      }else {
        inDegreeMap.put(indKey, 1);
      }
      
      //Out-Degree Calculation
      if (outDegreeMap.containsKey(outdKey)){
        int od = Integer.parseInt(outDegreeMap.get(outdKey).toString());
        outDegreeMap.put(outdKey, od+1);
      }else {
        outDegreeMap.put(outdKey, 1);
      }
    }

    JsonObject root = new JsonObject();
    root.put("degree", new JsonObject(degreeMap));
    root.put("indegree", new JsonObject(inDegreeMap));
    root.put("outdegree", new JsonObject(outDegreeMap));
    root.put("avgdegree", sumDegree/graph.getNodeCount());
    return root.toString();
  }
  
  
  private void responseSigmaGraph (Graph graph, RoutingContext routingContext) {
    this.sigmaGraph = Utils.toSigmaGraph(this.gephiGraph );
    HashMap<String, Object> result = new HashMap<String, Object>();
    result.put("nodes", this.sigmaGraph);
    routingContext.response().end(new JsonObject(result).toString());
  }
  

  public HttpServer getServer() {
    return server;
  }

  public void setServer(HttpServer server) {
    this.server = server;
  }

  public ConfigurationHolder getConfigHolder() {
    return configHolder;
  }

  @Inject
  public void setConfigHolder(ConfigurationHolder configHolder) {
    this.configHolder = configHolder;
  }

  public Vertx getVertx() {
    return vertx;
  }

  public void setVertx(Vertx vertx) {
    this.vertx = vertx;
  }

  public LayoutsWrap getLayoutsWrap() {
    return layoutsWrap;
  }

  @Inject
  public void setLayoutsWrap(LayoutsWrap layoutsWrap) {
    this.layoutsWrap = layoutsWrap;
  }

  public Graph getGephiGraph() {
    return gephiGraph;
  }

  public void setGephiGraph(Graph gephiGraph) {
    this.gephiGraph = gephiGraph;
  }

  public SigmaGraph getSigmaGraph() {
    return sigmaGraph;
  }

  public void setSigmaGraph(SigmaGraph sigmaGraph) {
    this.sigmaGraph = sigmaGraph;
  }

  public StatisticsWrap getStatisticsWrap() {
    return statisticsWrap;
  }

  @Inject
  public void setStatisticsWrap(StatisticsWrap statisticsWrap) {
    this.statisticsWrap = statisticsWrap;
  }

}
