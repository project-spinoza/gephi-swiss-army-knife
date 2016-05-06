package org.projectspinoza.gephiswissarmyknife.graph.filters;


import java.awt.Color;
import java.util.Arrays;
import java.util.Comparator;

import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualNumberFilter;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.filters.plugin.edge.EdgeWeightBuilder.EdgeWeightFilter;
import org.gephi.filters.plugin.edge.SelfLoopFilterBuilder.SelfLoopFilter;
import org.gephi.filters.plugin.graph.EgoBuilder.EgoFilter;
import org.gephi.filters.plugin.graph.GiantComponentBuilder.GiantComponentFilter;
import org.gephi.filters.plugin.graph.InDegreeRangeBuilder.InDegreeRangeFilter;
import org.gephi.filters.plugin.graph.KCoreBuilder.KCoreFilter;
import org.gephi.filters.plugin.graph.MutualDegreeRangeBuilder.MutualDegreeRangeFilter;
import org.gephi.filters.plugin.graph.OutDegreeRangeBuilder.OutDegreeRangeFilter;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.EdgeIterable;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;

public class Filters {
  
  
  private GraphModel graphModel;
 
  private Column attributeColumn;
  private EqualStringFilter<Node> equalStrFilterNode;
  private EqualStringFilter<Edge> equalStrFilterEdge;
  private Graph graph;
  private EqualNumberFilter<?> EqualNumberfilter;
  

  public Filters() {
  //  setAttributable(Lookup.getDefault().lookup(Attributable.class));
  }
  
  public Filters (GraphModel graphModel) {
    this();
    this.setGraphModel(graphModel);
  }
  
  
  

  /*
   * @return void
   * 
   * Type: Filter
   * 
   * Function: filters/Colors Graph Nodes whose id matches the specified ID
   * 
   * OR
   * 
   * Contains the specified ID when 'useRegex' is set to true.
   * 
   * */
  public void nodeColFilter (String column, String id, boolean useRegex, boolean removeNode) {
    this.attributeColumn = graphModel.getNodeTable().getColumn(column);
    this.equalStrFilterNode = new AttributeEqualBuilder.EqualStringFilter.Node(attributeColumn);
    this.equalStrFilterNode.setColumn(attributeColumn);
    this.equalStrFilterNode.setPattern(id);
    this.equalStrFilterNode.setUseRegex(useRegex);

    this.graph = this.graphModel.getGraph();
    Node[] nodes = graph.getNodes().toArray();
    boolean nodeMatched = false;
    for (int i = 0; i < nodes.length; i++) {
      
      //regex exp are not working properly so we are doing it manually
      if(useRegex){
        if (nodes[i].getAttribute(this.attributeColumn).toString().contains(id)){
          applyFilterNode(nodes[i]);
        }
      }else {
        nodeMatched = this.equalStrFilterNode.evaluate(graph, nodes[i]);
        /*
         * See if node Matched then Remove it.
         */
        if (nodeMatched) {
          if (removeNode){
            graph.removeNode(nodes[i]);
            continue;
          }
          applyFilterNode(nodes[i]);
        }
      }
    }
  }
  
  private void applyFilterNode (Node node){
    EdgeIterable edges = graph.getEdges(node);
    for (Edge e : edges){
      e.setColor(Color.red);
      e.setWeight(e.getWeight()+5);
    }
    node.setColor(Color.red);
    node.setSize(10f);
  }
  
  public void edgeColFilter(String column, String id, boolean useRegex,boolean removeEdge) {
    
    this.attributeColumn = graphModel.getEdgeTable().getColumn(column);
    this.equalStrFilterEdge = new AttributeEqualBuilder.EqualStringFilter.Edge(
        attributeColumn);
    this.equalStrFilterEdge.setColumn(attributeColumn);
    this.equalStrFilterEdge.setPattern(id);
    this.equalStrFilterEdge.setUseRegex(useRegex);
    this.graph = this.graphModel.getGraph();
    Edge[] edges = graph.getEdges().toArray();
    boolean edgeMatched = false;
    for (int i = 0; i < edges.length; i++) {
      
      // regex exp are not working properly so we are doing it manually
      if (useRegex) {
        if (edges[i].getAttribute(this.attributeColumn).toString().contains(id)) {
          applyFilterEdge(edges[i]);
        }
      } else {

        edgeMatched = this.equalStrFilterEdge.evaluate(graph, edges[i]);
        /*
         * See if node Matched.
         */
        if (edgeMatched) {
          if (removeEdge) {
            graph.removeEdge(edges[i]);
          } else {
            applyFilterEdge(edges[i]);
          }
        }
      }
    }
  }
  
  private void applyFilterEdge (Edge edge){
    edge.setColor(Color.red);
    edge.setWeight(edge.getWeight()+10);
    edge.getSource().setColor(Color.red);
    edge.getTarget().setColor(Color.red);
  }
  
  public Graph removePercentageNodes(String column, double threshhold, String columnValue) {
    
    this.attributeColumn = graphModel.getEdgeTable().getColumn(column);
    this.graph = this.graphModel.getGraph();
    Node[] nodesN = sortgraphBasedonColoumnValue(this.getGraphModel().getGraphVisible(), attributeColumn);
    int nodestoberemoved = (int) Math.ceil(nodesN.length * threshhold);
    for (int i = 0; i < nodestoberemoved; i++) {
      graph.removeNode(nodesN[i]);
    }
    return graph;
  } 
  
  private Node[] sortgraphBasedonColoumnValue(Graph graph, Column column) {
    Node[] nodesN = graph.getNodes().toArray();
    if (column != null) {
      Arrays.sort(nodesN, new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
          Number n1 = (Number) o1.getAttribute(column);
          Number n2 = (Number) o2.getAttribute(column);
          if (n1.doubleValue() < n2.doubleValue()) {
            return -1;
          } else if (n1.doubleValue() > n2.doubleValue()) {
            return 1;
          } else {
            return 0;
          }
        }
      });
    }
    return nodesN;
  }

  
  
  /*
   * 
   * TO BE REFINED
   * */
  public Graph edgeSelfLoop(Graph graph){
    SelfLoopFilter filter =new SelfLoopFilter();
    boolean boolIsSelfLoop=false;
    filter.init(graph);
    Edge[] edges = graph.getEdges().toArray();
    
    for(int i=0;i<edges.length;i++){
      boolIsSelfLoop = filter.evaluate(graph, edges[i]);
      if(boolIsSelfLoop){
        continue;
      }
      else{
        graph.removeEdge(edges[i]);
      }
    }
    return graph;
  }
  
  /*
   * 
   * TO BE REFINED.
   * */
  public Graph edgeWeightBuilder(Graph graph,Range range){
    EdgeWeightFilter filter =new EdgeWeightFilter();
    filter.init(graph);
    filter.setRange(range);
    Edge[] edges = graph.getEdges().toArray();
    boolean boolInRange=false;
    for(int i=0;i<edges.length;i++){
      boolInRange = filter.evaluate(graph, edges[i]);
      if(boolInRange){
        continue;
      }
      else{
        graph.removeEdge(edges[i]);
      }
    }
    return graph;
  }
  
  public Graph kCoreFilter(Graph graph,int kvalue){
    KCoreFilter filter = new KCoreFilter();
    filter.setK(kvalue);
    filter.filter(graph);
    return graph;
  }
  
  public Graph mutualDegreeRange(Graph graph, Range range){
    MutualDegreeRangeFilter filter = new MutualDegreeRangeFilter();
    boolean boolInRange= false;
    filter.init(graph);
    filter.setRange(range);
    Node[] nodes = graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = filter.evaluate(graph, nodes[i]);
      if(boolInRange){
        
        continue;
      }
      else{
        System.out.println("Node to be removed :" + nodes[i]);
      }
    }
    
    return graph;
  }
  
  
  public Graph giantcomponentFilter(Graph graph){
    GiantComponentFilter filter = new GiantComponentFilter();
    boolean boolStronglyConnected= false;
    filter.init(graph);
    Node[] nodes = graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolStronglyConnected = filter.evaluate(graph, nodes[i]);
      if(boolStronglyConnected == true){
        continue;
      }
      else{
        graph.removeNode(nodes[i]);
      }
    }
    return graph;
  }
  
  public Graph inDegreeRangeFilter(Graph graph, Range range){
    boolean boolInRange =false;
    //DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
    InDegreeRangeFilter indegreerangeFilter = new InDegreeRangeFilter();
    indegreerangeFilter.init(graph);
    indegreerangeFilter.setRange(range);
    Node[] nodes = graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = indegreerangeFilter.evaluate(graph, nodes[i]);
      if(boolInRange == true){
        continue;
      }
      else{
        graph.removeNode(nodes[i]);
      }
    }
    return graph;
  }
  
  public Graph outDegreeRangeFilter(Graph graph, Range range){
    boolean boolInRange =false;
    int count=0;
    //DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
    OutDegreeRangeFilter outdegreerangeFilter = new OutDegreeRangeFilter();
    outdegreerangeFilter.init(graph);
    outdegreerangeFilter.setRange(range);
    Node[] nodes = graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = outdegreerangeFilter.evaluate(graph, nodes[i]);
      if(boolInRange == true){
        continue;
      }
      else{
        count++;
        System.out.println("Nodes outDegree :\t"+ count + "\t" + ((DirectedGraph)graph).getOutDegree(nodes[i]));
        graph.removeNode(nodes[i]);
      }
    }
    return graph;
  }
  
  public Graph egoFilter(Graph graph,String pattern, boolean boolSelfloop,int depth){
    EgoFilter filter = new EgoFilter();
    filter.setPattern(pattern);
    filter.setDepth(depth);
    filter.setSelf(boolSelfloop);
    graph = filter.filter(graph);
    return graph;
  }


//  public void labelNodeFilter (String label, boolean useRegex) {
//	  this.attributeColumn = graphModel.getNodeTable().getColumn("label");
//	  this.equalStrFilter.setColumn(attributeColumn);
//	  this.equalStrFilter.setPattern(label);
//	  this.equalStrFilter.setUseRegex(useRegex);
//	  Graph graph = this.graphModel.getGraph();
//	  Node[] nodes = graph.getNodes().toArray();
//	  boolean nodeMatched = false;
//	  for (int i = 0; i < nodes.length; i++) {
//		  nodeMatched = this.equalStrFilter.evaluate(graph, nodes[i]);
//		  /*
//		   * See if node Matched then Remove it.
//		   */
//		  if (!nodeMatched) {
//		     graph.removeNode(nodes[i]);
//		  }
//	  }
//  }
  
//  public void labelEdgeFilter (String label, boolean useRegex) {
//		this.attributeColumn = graphModel.getEdgeTable().getColumn("label");
//		this.equalStrFilter.setColumn(attributeColumn);
//		this.equalStrFilter.setPattern(label);
//		this.equalStrFilter.setUseRegex(useRegex);
//		Graph graph = this.graphModel.getGraph();
//		Edge[] edges = graph.getEdges().toArray();
//		boolean edgeMatched = false;
//		for (int i = 0; i < edges.length; i++) {
//			edgeMatched = this.equalStrFilter.evaluate(graph, edges[i]);
//			/*
//			 * See if node Matched then Remove it.
//			 */
//			if (!edgeMatched) {
//				graph.removeEdge(edges[i]);
//			}
//		}
//  }
  
  public void weightFilter (float weight) {
    
  }
  
  public void equalNumberNodeFilter (String column, Number num) {
		attributeColumn = graphModel.getNodeTable().getColumn(column);
		EqualNumberfilter.setColumn(attributeColumn);
		EqualNumberfilter.setMatch(num);
		Graph graph = this.graphModel.getGraph();
		Node[] nodes = graph.getNodes().toArray();
		boolean nodeMatched = false;
		for (int i = 0; i < nodes.length; i++) {
			nodeMatched = this.EqualNumberfilter.evaluate(graph, nodes[i]);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!nodeMatched) {
				graph.removeNode(nodes[i]);
			}
		}
  }
  
  public void equalNumberEdgeFilter (String column, Number num) {
		attributeColumn = graphModel.getEdgeTable().getColumn(column);
		boolean edgeMatched = false;
		EqualNumberfilter.setColumn(attributeColumn);
		EqualNumberfilter.setMatch(num);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		for (int i = 0; i < edges.length; i++) {
			edgeMatched = this.EqualNumberfilter.evaluate(graph, edges[i]);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeEdge(edges[i]);
			}
		}
}
  
//  public void equalStringNodeFilter (String column, String str,boolean useRegex) {
//		this.attributeColumn = graphModel.getNodeTable().getColumn(column);
//		this.equalStrFilter.setColumn(attributeColumn);
//		this.equalStrFilter.setPattern(str);
//		this.equalStrFilter.setUseRegex(useRegex);
//		Graph graph = this.graphModel.getGraph();
//		Node[] nodes = graph.getNodes().toArray();
//		boolean edgeMatched = false;
//		for (int i = 0; i < nodes.length; i++) {
//			edgeMatched = this.equalStrFilter.evaluate(graph, nodes[i]);
//			/*
//			 * See if node Matched then Remove it.
//			 */
//			if (!edgeMatched) {
//				graph.removeNode(nodes[i]);
//			}
//		}
//}
//  
//  public void equalStringEdgeFilter (String column, String str,boolean useRegex) {
//		this.attributeColumn = graphModel.getEdgeTable().getColumn(column);
//		this.equalStrFilter.setColumn(attributeColumn);
//		this.equalStrFilter.setPattern(str);
//		this.equalStrFilter.setUseRegex(useRegex);
//		Graph graph = this.graphModel.getGraph();
//		Edge[] edges = graph.getEdges().toArray();
//		boolean edgeMatched = false;
//		for (int i = 0; i < edges.length; i++) {
//			edgeMatched = this.equalStrFilter.evaluate(graph, edges[i]);
//			/*
//			 * See if node Matched then Remove it.
//			 */
//			if (!edgeMatched) {
//				graph.removeEdge(edges[i]);
//			}
//		}
//  }
  
 
  
  public GraphModel getGraphModel() {
    return graphModel;
  }


  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  
}
