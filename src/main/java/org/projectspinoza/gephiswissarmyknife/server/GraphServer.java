package org.projectspinoza.gephiswissarmyknife.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.HashMap;

import org.gephi.graph.api.Graph;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.projectspinoza.gephiswissarmyknife.Main;
import org.projectspinoza.gephiswissarmyknife.configurations.ConfigurationHolder;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.server.graphprocesswraps.LayoutsWrap;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.utils.Utils;

import com.google.inject.Inject;

public class GraphServer {

  private ConfigurationHolder configHolder;
  private Vertx vertx;
  private HttpServer server;
  private Router router;
  private LayoutsWrap layoutsWrap;
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
     * Temp route for demo
     * */
    router.getWithRegex("/ajax.*").method(HttpMethod.GET).handler(routingContext -> {
      GephiGraph gephiGraph = new GephiGraph();
      this.gephiGraph = gephiGraph.loadGraph(Main.graphfile, EdgeDirectionDefault.DIRECTED);
      responseSigmaGraph(this.gephiGraph, routingContext);
    });
    
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

}
