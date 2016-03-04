package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import java.util.HashMap;
import java.util.Map;

import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class ServerRequests {
//	
//    private FreeMarkerEngine freeMarkerEngine;
//    private Configuration freeMarkerConfiguration;
    private GephiGraph gephiGraph;
    private LayoutsWrap layoutsWrap;
//    private GraphWraper graphSigma;
	

//	@Inject
//	public ServerRequests(FreeMarkerEngine fMarkerEngine, Configuration fMarkerConfig, LayoutsWrap layoutsWrap) {
//		this.freeMarkerEngine =  fMarkerEngine;
//		this.freeMarkerConfiguration = fMarkerConfig;
//		this.layoutsWrap = layoutsWrap;
//		init();
//	}
    
//	public Object indexResponse (Request request, Response response) {
//		return "Welcome to Gephi Swiss Army Knife<br/>"
//				+ "Graph Server:"
//				+ "<br/>"
//				+ "<a href='http://localhost:9090/graph'>http://localhost:9090/graph</a>";
//	}
	
	
	/*
	 * graph base page
	 * */
//	public Object gsakResponse (Request request, Response response) {
//        response.status(200);
//        response.type("text/html");
//        return freeMarkerEngine.render(new ModelAndView(null, "public/graph.html"));
//	}
	
	
	/*
	 * Gsak HTTP request path for handling Layouts Requests
	 * 
	 * */
//	public Object layout (Request request, Response response) {
//	  this.layoutsWrap.setGraphModel(GephiGraph.getGraphModel());
//		layoutsWrap.applyLayout(request.queryParams("layout"), request.params());
//		return getSigmaGraph(this.gephiGraph.getGraph());
//	}
	
	
	/*
	 * 
	 * Convert Gephi graph to sigma graph 
	 * @return Graph String
	 * 
	 * */
//	protected String getSigmaGraph(Graph graph) {
//        graphSigma.build(graph, returnGraphsettings());
//        Map<String, Object> result = new HashMap<String, Object>();
//		result.put("nodes", graphSigma);
//		return new JSONObject(result).toString();
//	}
	
	
	/*
	 * TO BE REMOVED IN NEAR FUTURE
	 * */
//	public Object ajax (Request request, Response response) {
//		this.gephiGraph.loadGraph(Main.graphfile, EdgeDefault.DIRECTED); 
//        GraphWraper graphSigma = new SigmaGraph();        
//        graphSigma.build(this.gephiGraph.getGraph(), returnGraphsettings());
//        Map<String, Object> result = new HashMap<String, Object>();
//		result.put("nodes", graphSigma);
//		//System.out.println(new JSONObject(result).toString());
//		return new JSONObject(result).toString();
//	}
	
	
	public static Map<String, Object> returnGraphsettings(){
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
		  graphsettings.put( "ec", "mix");
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
	
	
//	private void init() {
////        freeMarkerConfiguration.setClassForTemplateLoading(GsakServer_BAK.class, "/");
////        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
//	}
	
	
	public GephiGraph getGephiGraph() {
		return gephiGraph;
	}

	@Inject
	public void setGephiGraph(GephiGraph gephiGraph) {
		this.gephiGraph = gephiGraph;
	}

    public LayoutsWrap getLayoutsWrap() {
		return layoutsWrap;
	}
    
  @Inject
	public void setLayoutsWrap(LayoutsWrap layoutsWrap) {
		this.layoutsWrap = layoutsWrap;
	}

//	public GraphWraper getGraphSigma() {
//		return graphSigma;
//	}
//	
//	@Inject
//	public void setGraphSigma(GraphWraper graphSigma) {
//		this.graphSigma = graphSigma;
//	}
}