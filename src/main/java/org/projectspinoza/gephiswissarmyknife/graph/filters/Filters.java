package org.projectspinoza.gephiswissarmyknife.graph.filters;


import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualNumberFilter;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.graph.api.Column;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;

public class Filters {
  
  
  private GraphModel graphModel;
 
  private Column attributeColumn;
  private EqualStringFilter EqualStrfilter;
  private EqualNumberFilter EqualNumberfilter;
  
  public EqualNumberFilter getEqualNumberfilter() {
    return EqualNumberfilter;
	
}

public void setEqualNumberfilter(EqualNumberFilter equalNumberfilter) {
	EqualNumberfilter = equalNumberfilter;
}

public EqualStringFilter getEqualStrfilter() {
    return EqualStrfilter;
  }

  public void setEqualStrfilter(EqualStringFilter equalStrfilter) {
    EqualStrfilter = equalStrfilter;
  }

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
   * Function: filters Graph Nodes whose id matches the specified ID
   * 
   * OR
   * 
   * Contains the specified ID when 'useRegex' is set to true.
   * 
   * */
  public void idNodeFilter(String id, boolean useRegex) {
    this.attributeColumn = graphModel.getNodeTable().getColumn("id");
    this.EqualStrfilter.setColumn(attributeColumn);
    this.EqualStrfilter.setPattern(id);
    this.EqualStrfilter.setUseRegex(useRegex);

    Graph graph = this.graphModel.getGraph();
    Node[] nodes = graph.getNodes().toArray();
    boolean nodeMatched = false;
    for (int i = 0; i < nodes.length; i++) {
      nodeMatched = this.EqualStrfilter.evaluate(graph, nodes[i]);
      /*
       * See if node Matched then Remove it.
       */
      if (!nodeMatched) {
        graph.removeNode(nodes[i]);
      }
    }
  }
  
  public void idEdgeFilter (String id, boolean useRegex) {
	 this.attributeColumn = graphModel.getEdgeTable().getColumn("id");
	 this.EqualStrfilter.setColumn(attributeColumn);
	 this.EqualStrfilter.setPattern(id);
	 this.EqualStrfilter.setUseRegex(useRegex);
	 Graph graph = this.graphModel.getGraph();
	    Edge[] edges = graph.getEdges().toArray();
	    boolean edgeMatched = false;
	    for (int i = 0; i < edges.length; i++) {
	      edgeMatched = this.EqualStrfilter.evaluate(graph, edges[i]);
	      /*
	       * See if node Matched then Remove it.
	       */
	      if (!edgeMatched) {
	        graph.removeEdge(edges[i]);
	      }
	    }
  }
  
  public void labelNodeFilter (String label, boolean useRegex) {
	  this.attributeColumn = graphModel.getNodeTable().getColumn("label");
	  this.EqualStrfilter.setColumn(attributeColumn);
	  this.EqualStrfilter.setPattern(label);
	  this.EqualStrfilter.setUseRegex(useRegex);
	  Graph graph = this.graphModel.getGraph();
	  Node[] nodes = graph.getNodes().toArray();
	  boolean nodeMatched = false;
	  for (int i = 0; i < nodes.length; i++) {
		  nodeMatched = this.EqualStrfilter.evaluate(graph, nodes[i]);
		  /*
		   * See if node Matched then Remove it.
		   */
		  if (!nodeMatched) {
		     graph.removeNode(nodes[i]);
		  }
	  }
  }
  
  public void labelEdgeFilter (String label, boolean useRegex) {
		this.attributeColumn = graphModel.getEdgeTable().getColumn("label");
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(label);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < edges.length; i++) {
			edgeMatched = this.EqualStrfilter.evaluate(graph, edges[i]);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeEdge(edges[i]);
			}
		}
  }
  
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
  
  public void equalStringNodeFilter (String column, String str,boolean useRegex) {
		this.attributeColumn = graphModel.getNodeTable().getColumn(column);
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(str);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Node[] nodes = graph.getNodes().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < nodes.length; i++) {
			edgeMatched = this.EqualStrfilter.evaluate(graph, nodes[i]);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeNode(nodes[i]);
			}
		}
}
  
  public void equalStringEdgeFilter (String column, String str,boolean useRegex) {
		this.attributeColumn = graphModel.getEdgeTable().getColumn(column);
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(str);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < edges.length; i++) {
			edgeMatched = this.EqualStrfilter.evaluate(graph, edges[i]);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeEdge(edges[i]);
			}
		}
  }
  
 
  
  public GraphModel getGraphModel() {
    return graphModel;
  }


  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  
}
