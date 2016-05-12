package org.projectspinoza.gephiswissarmyknife.graph.filters;


import java.awt.Color;

import org.gephi.filters.api.Range;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.filters.plugin.edge.EdgeWeightBuilder.EdgeWeightFilter;
import org.gephi.filters.plugin.edge.SelfLoopFilterBuilder.SelfLoopFilter;
import org.gephi.filters.plugin.graph.DegreeRangeBuilder.DegreeRangeFilter;
import org.gephi.filters.plugin.graph.EgoBuilder.EgoFilter;
import org.gephi.filters.plugin.graph.GiantComponentBuilder.GiantComponentFilter;
import org.gephi.filters.plugin.graph.InDegreeRangeBuilder.InDegreeRangeFilter;
import org.gephi.filters.plugin.graph.KCoreBuilder.KCoreFilter;
import org.gephi.filters.plugin.graph.MutualDegreeRangeBuilder.MutualDegreeRangeFilter;
import org.gephi.filters.plugin.graph.OutDegreeRangeBuilder.OutDegreeRangeFilter;
import org.gephi.graph.api.Column;
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
  
  
  /*
   * 
   * Filters edges within specified range.
   * 
   * */
  public Graph edgeWeightBuilder(Range range, boolean removeEdge){
    EdgeWeightFilter edgeWeightFilter =new EdgeWeightFilter();
    this.graph = this.graphModel.getGraph();
    edgeWeightFilter.init(this.graph);
    edgeWeightFilter.setRange(range);
    Edge[] edges = this.graph.getEdges().toArray();
    boolean boolInRange=false;
    for(int i=0;i<edges.length;i++){
      boolInRange = edgeWeightFilter.evaluate(this.graph, edges[i]);
      if(boolInRange){
          if (removeEdge) {
            this.graph.removeEdge(edges[i]);
          } else {
            edges[i].setColor(Color.red);
          }   
      }
    }
    return this.graph;
  }
  
  /*
   * 
   * Degree range filter
   * 
   * */
  public Graph degreeRangeFilter(Range range, boolean remove){
    boolean boolInRange =false;
    DegreeRangeFilter degreeFilter = new DegreeRangeFilter();
    this.graph = this.graphModel.getGraph();
    degreeFilter.init(this.graph);
    degreeFilter.setRange(range);
    Node[] nodes = this.graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = degreeFilter.evaluate(this.graph, nodes[i]);
      if(boolInRange == true){
        if (remove){
          this.graph.removeNode(nodes[i]);
        }else{
          nodes[i].setColor(Color.red);
        }
      }
    }
    return this.graph;
  }
  
  /*
   * 
   * indegree range filter
   * 
   * */
  public Graph inDegreeRangeFilter(Range range, boolean remove){
    boolean boolInRange =false;
    this.graph = this.graphModel.getGraph();
    InDegreeRangeFilter inDegreeRangeFilter = new InDegreeRangeFilter();
    inDegreeRangeFilter.init(this.graph);
    inDegreeRangeFilter.setRange(range);
    Node[] nodes = this.graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = inDegreeRangeFilter.evaluate(this.graph, nodes[i]);
      if(boolInRange == true){
        if (remove){
          this.graph.removeNode(nodes[i]);
        }else{
          nodes[i].setColor(Color.red);
        }
      }
    }
    return this.graph;
  }
  
  /*
   * 
   * outdegree range filter
   * 
   * */
  public Graph outDegreeRangeFilter(Range range, boolean remove){
    boolean boolInRange =false;
    this.graph = this.graphModel.getGraph();
    OutDegreeRangeFilter outDegreeRangeFilter = new OutDegreeRangeFilter();
    outDegreeRangeFilter.init(this.graph);
    outDegreeRangeFilter.setRange(range);
    Node[] nodes = this.graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = outDegreeRangeFilter.evaluate(this.graph, nodes[i]);
      if(boolInRange == true){
        if (remove){
          this.graph.removeNode(nodes[i]);
        }else{
          nodes[i].setColor(Color.red);
        }
      }
    }
    return this.graph;
  }
  
  
  /*
   * 
   * ego network filter
   * 
   * */
  public Graph egoFilter(String id, boolean boolSelfloop,int depth){
    EgoFilter egoFilter = new EgoFilter();
    this.graph = this.graphModel.getGraph();
    egoFilter.setPattern(id);
    egoFilter.setDepth(depth);
    egoFilter.setSelf(boolSelfloop);
    this.graph = egoFilter.filter(this.graph);
    return this.graph;
  }

  /*
   * 
   * giant component filter
   * 
   * */
  public Graph giantComponentFilter(boolean remove){
    GiantComponentFilter giantCompfilter = new GiantComponentFilter();
    this.graph = this.graphModel.getGraph();
    boolean boolStronglyConnected= false;
    giantCompfilter.init(graph);
    Node[] nodes = graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolStronglyConnected = giantCompfilter.evaluate(this.graph, nodes[i]);
      if(boolStronglyConnected){
        if (remove){
          this.graph.removeNode(nodes[i]);
        }else{
          nodes[i].setColor(Color.red);
        }
      }
    }
    return this.graph;
  }
  
  
  /*
   * 
   * k core filter
   * 
   * */
  public Graph kCoreFilter(int kvalue){
    KCoreFilter kCorefilter = new KCoreFilter();
    this.graph = this.graphModel.getGraph();
    kCorefilter.setK(kvalue);
    kCorefilter.filter(this.graph);
    return this.graph;
  }
  
  
  /*
   * 
   * Self Loop Filter
   * 
   * */
  public Graph edgeSelfLoop(boolean remove){
    SelfLoopFilter filter =new SelfLoopFilter();
    this.graph = this.graphModel.getGraph();
    boolean boolIsSelfLoop=false;
    filter.init(this.graph);
    Edge[] edges = this.graph.getEdges().toArray();
    
    for(int i=0;i<edges.length;i++){
      boolIsSelfLoop = filter.evaluate(this.graph, edges[i]);
      if(boolIsSelfLoop){
        if (remove) {
          this.graph.removeEdge(edges[i]);
        } else {
          edges[i].setColor(Color.red);
          edges[i].setWeight(edges[i].getWeight());
        } 
      }
    }
    return this.graph;
  }
  
  /*
   * 
   * mutual degree filter
   * 
   * */
  public Graph mutualDegreeRange(Range range, boolean remove){
    MutualDegreeRangeFilter filter = new MutualDegreeRangeFilter();
    this.graph = this.graphModel.getGraph();
    boolean boolInRange= false;
    filter.init(this.graph);
    filter.setRange(range);
    Node[] nodes = this.graph.getNodes().toArray();
    for(int i=0;i<nodes.length;i++){
      boolInRange = filter.evaluate(this.graph, nodes[i]);
      if(boolInRange){
        if (remove){
          this.graph.removeNode(nodes[i]);
        }else{
          nodes[i].setColor(Color.red);
        }
      }
    }
    return this.graph;
  }
  
  /********************************************************************************
   *                           FOR FUTURE USE ONLY                                *
   ********************************************************************************/
  
  /*
   * for future use only
   * 

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

  public void equalNumberNodeFilter (String column, Number num) {
		attributeColumn = graphModel.getNodeTable().getColumn(column);
		EqualNumberfilter.setColumn(attributeColumn);
		EqualNumberfilter.setMatch(num);
		Graph graph = this.graphModel.getGraph();
		Node[] nodes = graph.getNodes().toArray();
		boolean nodeMatched = false;
		for (int i = 0; i < nodes.length; i++) {
			nodeMatched = this.EqualNumberfilter.evaluate(graph, nodes[i]);
			
			 //See if node Matched then Remove it.
			 
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

			 // See if node Matched then Remove it.

			if (!edgeMatched) {
				graph.removeEdge(edges[i]);
			}
		}
}
  */ 
  
  public GraphModel getGraphModel() {
    return graphModel;
  }


  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  
}
