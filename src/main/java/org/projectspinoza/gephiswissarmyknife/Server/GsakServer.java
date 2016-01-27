package org.projectspinoza.gephiswissarmyknife.Server;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;


public class GsakServer {
	
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger(GsakServer.class);
	
	private String host = "0.0.0.0";
	private int port = 9090;
	private String webroot = "/public";
	
	public GsakServer() {}
	
	public void deployServer() {
		Spark.ipAddress(getHost());
		Spark.port(getPort());
		setTemplateDirectory();
		getServerInstance();
	}

	public void deployServer(String host, int port) {
		Spark.ipAddress(host);
		Spark.port(port);
		setTemplateDirectory();
		getServerInstance();
	}
	
	private void getServerInstance () {
		Spark.getInstance();
	}

	private void setTemplateDirectory () {
		Spark.staticFileLocation(getWebroot());
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getWebroot() {
		return webroot;
	}

	public void setWebroot(String webroot) {
		this.webroot = webroot;
	}
	
	public void deployRoutes(){
		
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        freeMarkerConfiguration.setClassForTemplateLoading(GsakServer.class, "/");
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        
        Spark.get("/graph", (request, response) -> {
                response.status(200);
                response.type("text/html");
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("title", "Gephi Swiss Army Knife");
                return freeMarkerEngine.render(new ModelAndView(attributes, "public/graph.html"));
            
        });
	}
}
