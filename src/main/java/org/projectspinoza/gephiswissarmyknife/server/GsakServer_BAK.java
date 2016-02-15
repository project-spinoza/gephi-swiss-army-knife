package org.projectspinoza.gephiswissarmyknife.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.gephiswissarmyknife.configurations.ConfigurationHolder;
import org.projectspinoza.gephiswissarmyknife.server.requests.ServerRequests;

import spark.Spark;

import com.google.inject.Inject;

/*
 * Spark Embedded server wrapper class.
 * 
 * */
public class GsakServer_BAK {

  @SuppressWarnings("unused")
  private static Logger log = LogManager.getLogger(GsakServer_BAK.class);

  private ConfigurationHolder configHolder;
  private ServerRequests responseHandler;

  @Inject
  public GsakServer_BAK(ConfigurationHolder cHolder) {
    this.configHolder = cHolder;
  }

  public void deployServer() {
    Spark.ipAddress(this.configHolder.getHost());
    Spark.port(this.configHolder.getPort());
    setTemplateDirectory();
    getServerInstance();
  }

  public void deployServer(String host, int port) {
    this.configHolder.setHost(host);
    this.configHolder.setPort(port);
    deployServer();
  }

  private void getServerInstance() {
    Spark.getInstance();
  }

  private void setTemplateDirectory() {
    Spark.staticFileLocation(this.configHolder.getWebroot());
  }

  public void deployGsakRoutes() {

    /*
     * Base Route GSAK
     */
    Spark.get("/", (request, response) -> {
      return this.responseHandler.indexResponse(request, response);
    });

    /*
     * Main GSAK Graph Route
     */
    Spark.get("/graph", (request, response) -> {
      return this.responseHandler.gsakResponse(request, response);
    });

    /*
     * To be removed in next phase
     */
    Spark.get("/ajax", (request, response) -> {
      return this.responseHandler.ajax(request, response);
    });

    /*
     * To be removed in next phase
     */
    Spark.get("/layout", (request, response) -> {
      return this.responseHandler.layout(request, response);
    });

  }

  public ServerRequests getResponseHandler() {
    return responseHandler;
  }

  @Inject
  public void setResponseHandler(ServerRequests responseHandler) {
    this.responseHandler = responseHandler;
  }
}
