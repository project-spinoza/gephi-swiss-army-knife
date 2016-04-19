package org.projectspinoza.gephiswissarmyknife.server;



import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;

import java.io.File;
import java.util.HashMap;

import org.gephi.graph.api.Graph;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.projectspinoza.gephiswissarmyknife.configurations.ServerConfig;
import org.projectspinoza.gephiswissarmyknife.dto.DtoConfig;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.GraphGenerator;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.LayoutsWrap;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.StatisticsWrap;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.utils.Utils;

import com.google.inject.Inject;

public class GraphServer {

  private ServerConfig configHolder;
  private Vertx vertx;
  private HttpServer server;
  private Router router;
  private LayoutsWrap layoutsWrap;
  private StatisticsWrap statisticsWrap;
  private Graph gephiGraph;
  private SigmaGraph sigmaGraph;
  private DtoConfig dtoConfig;
  private GephiGraph gephiGraphWs;
  

  public GraphServer() {
    setVertx(Vertx.vertx());
    router = Router.router(vertx);
    this.gephiGraphWs = new GephiGraph();
    dtoConfig = DtoConfig.getInstance();
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
     * Upload Files Directory
     * */
   // router.route().handler(BodyHandler.create().setUploadsDirectory(System.getProperty("./uploads")));

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
    
    router.getWithRegex("/gsak.*").method(HttpMethod.GET).handler(routingContext -> {
      routingContext.response().sendFile("public/index.html");
    });
    
    router.getWithRegex("/layout.*").method(HttpMethod.GET).handler(routingContext -> {
      this.layoutsWrap.applyLayout(routingContext.request().params());
      responseSigmaGraph (GephiGraph.getGraphModel().getGraphVisible(), routingContext);
    });

    
    /*
     * Graph statistics Route
     * 
     * */
    router.getWithRegex("/statistics.*").method(HttpMethod.GET).handler(routingContext -> {
      String resp = this.statisticsWrap.applyStatistics(routingContext.request().params());
      routingContext.response().end(resp);
    });
    
    /*
     * Get graph from uploaded file
     * 
     * */
    router.getWithRegex("/extractGraph.*").method(HttpMethod.GET).handler(routingContext -> {
      this.gephiGraph = gephiGraphWs.loadGraph("uploads/"+dtoConfig.getGraphfileName(), EdgeDirectionDefault.DIRECTED);
      responseSigmaGraph(this.gephiGraph, routingContext);
    });
    
    /*
     * Upload graph file route
     * 
     * */
    router.getWithRegex("/graphFileUpload.*").method(HttpMethod.POST).handler(routingContext -> {
      uploadGraphFile(routingContext, true);
    });

    /*
     * general file Upload route
     * 
     * */
    router.getWithRegex("/fileUpload.*").method(HttpMethod.POST).handler(routingContext -> {
      uploadGraphFile(routingContext, false);
      //TEMP to be removed
      dtoConfig.setDataSource("inputfile");
      dtoConfig.setSearchValue("data");
      GraphGenerator graphGen = new GraphGenerator();
      graphGen.setGraphModel(GephiGraph.getGraphModel());
      graphGen.createGraph();
    });
    
    /*
     * remove uploaded file route
     * 
     * */
    router.getWithRegex("/removeUpload.*").method(HttpMethod.GET).handler(routingContext -> {
      File uploadDel = new File ("uploads/"+ routingContext.request().getParam("file"));
      if (uploadDel.exists()) {
        uploadDel.delete();
      }
      routingContext.response().end();
    });
    
    /*
     * search
     * 
     * */
    router.getWithRegex("/search.*").method(HttpMethod.POST).handler(routingContext -> {
      
    });
    
    /*
     * Temp route for demo
     * */
//    router.getWithRegex("/ajax.*").method(HttpMethod.GET).handler(routingContext -> {
//      GephiGraph gephiGraph = new GephiGraph();
//      this.gephiGraph = gephiGraph.loadGraph(Main.graphfile, EdgeDirectionDefault.DIRECTED);
//      Map <String, String> yifanParams = new HashMap<String, String>();
//      yifanParams.put("quadtreeMaxLevel", "10.0");
//      yifanParams.put("theta", "1.2");
//      yifanParams.put("optimalDistance", "100.0");
//      yifanParams.put("relativeStrength", "0.2");
//      yifanParams.put("initialStepSize", "20.0");
//      yifanParams.put("stepRatio", "0.95");
//      yifanParams.put("adaptiveCooling", "true");
//      yifanParams.put("convergenceThreshold", "1.0E-4");
//      //YifanLayout
//      new YifanHu().applyLayout(yifanParams);
//      responseSigmaGraph(this.gephiGraph, routingContext);
//    });
//    
  }
  
  
  
  private void responseSigmaGraph (Graph graph, RoutingContext routingContext) {
    this.sigmaGraph = Utils.toSigmaGraph(this.gephiGraph );
    HashMap<String, Object> result = new HashMap<String, Object>();
    result.put("nodes", this.sigmaGraph);
    routingContext.response().end(new JsonObject(result).toString());
  }
  

  private void uploadGraphFile(RoutingContext routingContext, boolean isGraphFile) {

    File uploadsDir = new File("uploads");
    try {
      if (!uploadsDir.exists()) {
        uploadsDir.mkdir();
      }

      routingContext.request().setExpectMultipart(true);

      routingContext.request().uploadHandler(
          new Handler<HttpServerFileUpload>() {
            @Override
            public void handle(final HttpServerFileUpload upload) {
              upload.exceptionHandler(new Handler<Throwable>() {
                @Override
                public void handle(Throwable event) {
                }
              });
              upload.endHandler(new Handler<Void>() {
                @Override
                public void handle(Void event) {
                  routingContext.request().response().end("1");
                }
              });
              upload.streamToFileSystem("uploads/" + upload.filename());
              if (isGraphFile) {
                dtoConfig.setGraphfileName(upload.filename());
              } else {
                dtoConfig.setTextfileName(upload.filename());
              }
            }
          });

    } catch (SecurityException se) {
      System.out.println("Permission denied to create uploads directroy.");
    }
  }
  
  
  
  public HttpServer getServer() {
    return server;
  }

  public void setServer(HttpServer server) {
    this.server = server;
  }

  public ServerConfig getConfigHolder() {
    return configHolder;
  }

  @Inject
  public void setConfigHolder(ServerConfig configHolder) {
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

  public DtoConfig getDtoConfig() {
    return dtoConfig;
  }

  public void setDtoConfig(DtoConfig dtoConfig) {
    this.dtoConfig = dtoConfig;
  }

}
