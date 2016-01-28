package org.projectspinoza.gephiswissarmyknife.Server;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.projectspinoza.gephiswissarmyknife.configurations.ConfigurationHolder;

import com.google.inject.Inject;

import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import freemarker.template.Configuration;

/*
 * Spark Embedded server wrapper class.
 * 
 * */
public class GsakServer {
	
	@SuppressWarnings("unused")
	private static Logger log = LogManager.getLogger(GsakServer.class);
	
	private ConfigurationHolder configHolder;
    private FreeMarkerEngine freeMarkerEngine;
    private Configuration freeMarkerConfiguration;
    
	@Inject
	public GsakServer(ConfigurationHolder cHolder, FreeMarkerEngine fMarkerEngine, Configuration fMarkerConfig) {
		this.configHolder = cHolder;
		this.freeMarkerEngine =  fMarkerEngine;
		this.freeMarkerConfiguration = fMarkerConfig;
		init();
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
	
	
	private void getServerInstance () {
		Spark.getInstance();
	}

	
	private void setTemplateDirectory () {
		Spark.staticFileLocation(this.configHolder.getWebroot());
	}
	
	
	public void deployGsakRoutes(){

		/*
		 * Base Route GSAK
		 * 
		 * */
		
		Spark.get("/", (request, response) ->{
			return "Welcome to Gephi Swiss Army Knife<br/>"
					+ "Graph Server:"
					+ "<br/>"
					+ "<a href='http://localhost:9090/graph'>http://localhost:9090/graph</a>";
		});
		
		
		/*
		 * Main GSAK Graph Route
		 * */
        Spark.get("/graph", (request, response) -> {
                response.status(200);
                response.type("text/html");
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("title", "Gephi Swiss Army Knife");
                return freeMarkerEngine.render(new ModelAndView(attributes, "public/graph.html"));
        });
        
	}
	

	private void init() {
        freeMarkerConfiguration.setClassForTemplateLoading(GsakServer.class, "/");
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
	}
}
