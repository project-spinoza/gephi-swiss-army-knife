package org.projectspinoza.gephiswissarmyknife.Server;

import java.util.HashMap;
import java.util.Map;

import org.gephi.io.importer.api.EdgeDefault;
import org.json.JSONObject;
import org.projectspinoza.gephiswissarmyknife.Main;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.sigma.GraphWraper;
import org.projectspinoza.gephiswissarmyknife.sigma.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.temp.GraphLayout;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import freemarker.template.Configuration;


@Singleton
public class ResponseHandler {
	
    private FreeMarkerEngine freeMarkerEngine;
    private Configuration freeMarkerConfiguration;
    private GephiGraph gephiGraph;
	
    @Inject
	public ResponseHandler(FreeMarkerEngine fMarkerEngine, Configuration fMarkerConfig) {

		this.freeMarkerEngine =  fMarkerEngine;
		this.freeMarkerConfiguration = fMarkerConfig;
		init();
	}
    
	public Object indexResponse (Request request, Response response) {
		return "Welcome to Gephi Swiss Army Knife<br/>"
				+ "Graph Server:"
				+ "<br/>"
				+ "<a href='http://localhost:9090/graph'>http://localhost:9090/graph</a>";
	}
	
	/*
	 * TO BE REFINED
	 * */
	
	public Object gsakResponse (Request request, Response response) {
        response.status(200);
        response.type("text/html");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("title", "Gephi Swiss Army Knife"); 
        return freeMarkerEngine.render(new ModelAndView(attributes, "public/graph.html"));
	}
	
	
	public Object layout (Request request, Response response) {
		
		GraphLayout gLayout = new GraphLayout();
	
//		System.out.println(request.queryParams("layout"));
		
		gLayout.applayLayouts(this.gephiGraph.getGraphModel(), request.queryParams("layout"));
		
//		YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
//		layout.setGraphModel(gephiGraph.getGraphModel());
//		layout.resetPropertiesValues();
//		layout.setOptimalDistance(200f);
//		layout.initAlgo();
//
//		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
//			layout.goAlgo();
//		}
//		layout.endAlgo();
		
		
		
		
        GraphWraper graphSigma = new SigmaGraph();        
        graphSigma.build(this.gephiGraph.getGraph(), returnGraphsettings());
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("nodes", graphSigma);
		//System.out.println(new JSONObject(result).toString());
		return new JSONObject(result).toString();

	}
	
	
	public Object ajax (Request request, Response response) {
		this.gephiGraph.loadGraph(Main.graphfile, EdgeDefault.DIRECTED); 
        GraphWraper graphSigma = new SigmaGraph();        
        graphSigma.build(this.gephiGraph.getGraph(), returnGraphsettings());
        Map<String, Object> result = new HashMap<String, Object>();
		result.put("nodes", graphSigma);
		//System.out.println(new JSONObject(result).toString());
		return new JSONObject(result).toString();
	}
	
	
	public Map<String, Object> returnGraphsettings(){
		  Map<String, Object>  layoutAlgo = new HashMap<String, Object>();
		  Map<String, Object> graphsettings = new HashMap<String, Object>();
		  layoutAlgo.put( "name", "YifanHuLayout");
		  layoutAlgo.put("it", 100);
		  layoutAlgo.put( "distance", 260);
		  graphsettings.put( "nct", 0);
		  graphsettings.put( "prt", 0);
		  graphsettings.put("neighborcountrange", 0);
		  graphsettings.put( "ncb", "cluster");
		  graphsettings.put( "nsb", "pr");
		  graphsettings.put( "ecb", "mix");
		  graphsettings.put( "et", "curve");
		  graphsettings.put( "bk_color", "#000");
		  graphsettings.put("la", layoutAlgo);
		  graphsettings.put("eventsEnabled", true);
		  graphsettings.put("doubleClickEnabled", false);
		  graphsettings.put("enableEdgeHovering", true);
		  graphsettings.put("singleHover", true);
		  graphsettings.put("edgeHoverColor", "edge");
		  graphsettings.put("edgeHoverColor", "default");
		  graphsettings.put("defaultEdgeHoverColor", "#777");
		  graphsettings.put("edgeHoverSizeRatio", 10);
		  graphsettings.put("edgeColor", "default");
		  graphsettings.put("defaultHoverLabelBGColor", "#fff");
		  graphsettings.put("defaultEdgeColor", "rgb(205, 220, 213)");
		  graphsettings.put("minEdgeSize", 0.2);
		  graphsettings.put("labelThreshold", 3);
		  graphsettings.put("defaultLabelColor", "#fff");
		  graphsettings.put("animationsTime", 1000);
		  graphsettings.put("borderSize", 2);
		  graphsettings.put("outerBorderSize", 3);
		  graphsettings.put("defaultNodeOuterBorderColor","rgb(72,227,236)");
		  graphsettings.put("edgeHoverHighlightNodes", "circle");
		  graphsettings.put("sideMargin", 10);
		  graphsettings.put("edgeHoverExtremities", true);
		  graphsettings.put("scalingMode", "outside");
		  graphsettings.put("enableCamera", true);
		  
		  return graphsettings;
		 }
	
	
	private void init() {
        freeMarkerConfiguration.setClassForTemplateLoading(GsakServer.class, "/");
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
	}
	
	
	public GephiGraph getGephiGraph() {
		return gephiGraph;
	}

	@Inject
	public void setGephiGraph(GephiGraph gephiGraph) {
		this.gephiGraph = gephiGraph;
	}
	
}
