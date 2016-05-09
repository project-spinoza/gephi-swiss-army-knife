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
import org.gephi.graph.api.GraphModel;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.projectspinoza.gephiswissarmyknife.configurations.ServerConfig;
import org.projectspinoza.gephiswissarmyknife.dto.DtoConfig;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.GraphGenerator;
import org.projectspinoza.gephiswissarmyknife.graph.statistics.Statistics;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.FiltersWrap;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.LayoutsWrap;
import org.projectspinoza.gephiswissarmyknife.server.graphoperations.StatisticsWrap;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.utils.DataImporter;
import org.projectspinoza.gephiswissarmyknife.utils.GraphBackup;
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
  private GraphGenerator graphGen;
  private DataImporter dataImporter;
  private GraphBackup graphBackup;
  private FiltersWrap filters;
  private Statistics statistics;
  
  public GraphServer() {
    setVertx(Vertx.vertx());
    router = Router.router(vertx);
    this.dtoConfig = new DtoConfig();
    this.dataImporter = new DataImporter();
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
    
    /*
     *  static resources CSS/JS files
     *  
     * */
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
      request.response().end("Welcome to <a href=\"/gsak\">Twitter-Grapher.</a>");
    });
    
    /*
     *  base UI route
     *  
     * */
    router.getWithRegex("/gsak.*").method(HttpMethod.GET).handler(routingContext -> {
      this.gephiGraphWs = new GephiGraph();
      this.gephiGraphWs.setGraphModel(GraphModel.Factory.newInstance());
      routingContext.response().sendFile("public/index.html");
    });
    
    /*
     * graph Layouts route
     *  
     * */
    router.getWithRegex("/layout.*").method(HttpMethod.GET).handler(routingContext -> {
      this.layoutsWrap.setGraphModel(this.gephiGraphWs.getGraphModel());
      this.layoutsWrap.applyLayout(routingContext.request().params());
      responseSigmaGraph (gephiGraphWs.getGraphModel().getGraphVisible(), routingContext, false);
    });

    
    /*
     * Graph statistics Route
     * 
     * */
    router.getWithRegex("/statistics.*").method(HttpMethod.GET).handler(routingContext -> {
      this.statisticsWrap.setGraphModel(this.gephiGraphWs.getGraphModel());
      String resp = this.statisticsWrap.applyStatistics(routingContext.request().params());
      routingContext.response().end(resp);
    });
    
    /*
     * Get graph from uploaded file
     * 
     * */
    router.getWithRegex("/extractGraph.*").method(HttpMethod.GET).handler(routingContext -> {
      this.gephiGraphWs = new GephiGraph();
      this.gephiGraph = gephiGraphWs.loadGraph("uploads/"+dtoConfig.getGraphfileName(), EdgeDirectionDefault.DIRECTED);
      this.graphBackup.saveGraph(this.gephiGraph);
      responseSigmaGraph(this.gephiGraph, routingContext, false);
    });
    
    /*
     * Original/unmodified/initial Graph request route
     * 
     * */
    router.getWithRegex("/originalGraph.*").method(HttpMethod.GET).handler(routingContext -> {
      this.gephiGraph = this.graphBackup.retrieveGraph(gephiGraphWs.getGraphModel());
      responseSigmaGraph(this.gephiGraph, routingContext, false);
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
     * Database connections
     * 
     * */
    router.getWithRegex("/connectDB.*").method(HttpMethod.POST).handler(routingContext -> {
      if (routingContext.request().getParam("dBServer").equalsIgnoreCase("mysql")){
        if (routingContext.request().getParam("dbAction").equalsIgnoreCase("connect")){
          dtoConfig.setMysqlDatabaseName(routingContext.request().getParam("database"));
          dtoConfig.setMysqlTableName(routingContext.request().getParam("dbtable"));
          dtoConfig.setMysqlDataColumnName(routingContext.request().getParam("dbtableCol"));
          dtoConfig.setMysqlHost(routingContext.request().getParam("dbhost"));
          dtoConfig.setMysqlPort(Integer.parseInt(routingContext.request().getParam("dbport")));
          dtoConfig.setMysqlUserName(routingContext.request().getParam("dbuser"));
          dtoConfig.setMysqlUserPassword(routingContext.request().getParam("dbpass"));
          boolean resp =this.dataImporter.connectMysql();
          routingContext.response().end(""+resp);
        }else {
          this.dataImporter.disconnectMysql();
          routingContext.response().end("true");
        }
      }else if (routingContext.request().getParam("dBServer").equalsIgnoreCase("mongodb")){
        if (routingContext.request().getParam("dbAction").equalsIgnoreCase("connect")){
          dtoConfig.setMongodbDatabaseName(routingContext.request().getParam("database"));
          dtoConfig.setMongodbCollectionName(routingContext.request().getParam("dbcollection"));
          dtoConfig.setMongodbFieldName(routingContext.request().getParam("dbfield"));
          dtoConfig.setMongodbHost(routingContext.request().getParam("dbhost"));
          dtoConfig.setMongodbPort(Integer.parseInt(routingContext.request().getParam("dbport")));
          dtoConfig.setMongdbUserName(routingContext.request().getParam("dbuser"));
          dtoConfig.setMongodbUserPassword(routingContext.request().getParam("dbpass"));
          boolean resp =this.dataImporter.connectMongoDb();
          routingContext.response().end(""+resp);
        }else {
          this.dataImporter.disconnectMongoDb();
          routingContext.response().end("true");
        }
      }else if (routingContext.request().getParam("dBServer").equalsIgnoreCase("elasticsearch")){
        if (routingContext.request().getParam("dbAction").equalsIgnoreCase("connect")){
          dtoConfig.setElasticsearchHost(routingContext.request().getParam("es_host"));
          dtoConfig.setElasticsearchPort(Integer.parseInt(routingContext.request().getParam("es_port")));
          dtoConfig.setElasticsearchClusterName(routingContext.request().getParam("es_cluster"));
          dtoConfig.setElasticsearchIndexType(routingContext.request().getParam("es_type"));
          dtoConfig.setElasticsearchIndex(routingContext.request().getParam("es_index"));
          boolean resp =this.dataImporter.connectElasticsearch();
          routingContext.response().end(""+resp);
        }else {
          this.dataImporter.disconnectElasticsearch();
          routingContext.response().end("true");
        }
      }else {
        routingContext.response().end("Unknown DB server.!");
      }

    });
    
    
    /*
     * selection filters
     * 
     * */
    router.getWithRegex("/selectFilter.*").method(HttpMethod.GET).handler(routingContext -> {
      filters.setGraphModel(this.gephiGraphWs.getGraphModel());
      filters.applyFilter(routingContext.request().params(), false);
      responseSigmaGraph(this.gephiGraph, routingContext, false);
    });
    
    /*
     * filter ranges for degree, indegree, outdegree
     * 
     * */
    router.getWithRegex("/filterRanges.*").method(HttpMethod.GET).handler(routingContext -> {
      this.statistics.setGraphModel(this.gephiGraphWs.getGraphModel());
      String resp = this.statistics.calculateMaxDegrees();
      routingContext.response().end(resp);
    });
    
    /*
     * search
     * 
     * */
    router.getWithRegex("/search.*").method(HttpMethod.GET).handler(routingContext -> {
      dtoConfig.setDataSource(routingContext.request().getParam("datasource"));
      dtoConfig.setSearchValue(routingContext.request().getParam("searchStr"));
      this.graphGen.setGraphModel(gephiGraphWs.getGraphModel());
      this.gephiGraph = this.graphGen.createGraph();
      this.graphBackup.saveGraph(this.gephiGraph);
      responseSigmaGraph(this.gephiGraph, routingContext, true);
   });   
  }
  
  
  
  private void responseSigmaGraph (Graph graph, RoutingContext routingContext, boolean autoColor) {
    this.sigmaGraph = Utils.toSigmaGraph(this.gephiGraph, autoColor);
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

  @Inject
  public void setDtoConfig(DtoConfig dtoConfig) {
    this.dtoConfig = dtoConfig;
  }

  public DataImporter getDataImporter() {
    return dataImporter;
  }

  @Inject
  public void setDataImporter(DataImporter dataImporter) {
    this.dataImporter = dataImporter;
  }

  public GraphGenerator getGraphGen() {
    return graphGen;
  }

  @Inject
  public void setGraphGen(GraphGenerator graphGen) {
    this.graphGen = graphGen;
  }

  public GraphBackup getGraphBackup() {
    return graphBackup;
  }
  
  @Inject
  public void setGraphBackup(GraphBackup graphBackup) {
    this.graphBackup = graphBackup;
  }

  public FiltersWrap getFilters() {
    return filters;
  }

  @Inject
  public void setFilters(FiltersWrap filters) {
    this.filters = filters;
  }

  public Statistics getStatistics() {
    return statistics;
  }

  @Inject
  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

}
