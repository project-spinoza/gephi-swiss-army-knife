package org.projectspinoza.gephiswissarmyknife.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

import org.projectspinoza.gephiswissarmyknife.configurations.ConfigurationHolder;

import com.google.inject.Inject;

public class GraphServer {

  private ConfigurationHolder configHolder;
  private Vertx vertx;
  private HttpServer server;
  private Router router;

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
    router.getWithRegex(".*/css/.*|.*/js/.*|.*/images/.*").handler(
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

}
