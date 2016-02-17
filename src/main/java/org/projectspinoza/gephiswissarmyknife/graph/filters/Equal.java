package org.projectspinoza.gephiswissarmyknife.graph.filters;

import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualNumberFilter;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.graph.api.Attributable;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.openide.util.Lookup;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph.GraphDim;

public class Equal {
  
  
  private GraphModel graphModel;
  private AttributeModel attributeModel;
  private AttributeColumn attributeColumn;
  private Attributable attributable;
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

  public Equal() {
    setAttributable(Lookup.getDefault().lookup(Attributable.class));
  }
  
  public Equal (GraphModel graphModel, AttributeModel attributeModel) {
    this();
    this.setGraphModel(graphModel);
    this.setAttributeModel(attributeModel);
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
    this.attributeColumn = attributeModel.getNodeTable().getColumn("id");
    this.EqualStrfilter = new EqualStringFilter(attributeColumn);
    this.EqualStrfilter.setColumn(attributeColumn);
    this.EqualStrfilter.setPattern(id);
    this.EqualStrfilter.setUseRegex(useRegex);

    Graph graph = this.graphModel.getGraph();
    Node[] nodes = graph.getNodes().toArray();
    boolean nodeMatched = false;
    for (int i = 0; i < nodes.length; i++) {
      attributable = (Attributable) nodes[i];
      nodeMatched = this.EqualStrfilter.evaluate(graph, attributable);
      /*
       * See if node Matched then Remove it.
       */
      if (!nodeMatched) {
        graph.removeNode(nodes[i]);
      }
    }
  }
  
  public void idEdgeFilter (String id, boolean useRegex) {
	 this.attributeColumn = attributeModel.getEdgeTable().getColumn("id");
	 this.EqualStrfilter = new EqualStringFilter(attributeColumn);
	 this.EqualStrfilter.setColumn(attributeColumn);
	 this.EqualStrfilter.setPattern(id);
	 this.EqualStrfilter.setUseRegex(useRegex);
	 Graph graph = this.graphModel.getGraph();
	    Edge[] edges = graph.getEdges().toArray();
	    boolean edgeMatched = false;
	    for (int i = 0; i < edges.length; i++) {
	      attributable = (Attributable) edges[i];
	      edgeMatched = this.EqualStrfilter.evaluate(graph, attributable);
	      /*
	       * See if node Matched then Remove it.
	       */
	      if (!edgeMatched) {
	        graph.removeEdge(edges[i]);
	      }
	    }
  }
  
  public void labelNodeFilter (String label, boolean useRegex) {
	  this.attributeColumn = attributeModel.getNodeTable().getColumn("label");
	  this.EqualStrfilter = new EqualStringFilter(attributeColumn);
	  this.EqualStrfilter.setColumn(attributeColumn);
	  this.EqualStrfilter.setPattern(label);
	  this.EqualStrfilter.setUseRegex(useRegex);
	  Graph graph = this.graphModel.getGraph();
	  Node[] nodes = graph.getNodes().toArray();
	  boolean nodeMatched = false;
	  for (int i = 0; i < nodes.length; i++) {
		  attributable = (Attributable) nodes[i];
		  nodeMatched = this.EqualStrfilter.evaluate(graph, attributable);
		  /*
		   * See if node Matched then Remove it.
		   */
		  if (!nodeMatched) {
		     graph.removeNode(nodes[i]);
		  }
	  }
  }
  
  public void labelEdgeFilter (String label, boolean useRegex) {
		this.attributeColumn = attributeModel.getEdgeTable().getColumn("label");
		this.EqualStrfilter = new EqualStringFilter(attributeColumn);
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(label);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < edges.length; i++) {
			attributable = (Attributable) edges[i];
			edgeMatched = this.EqualStrfilter.evaluate(graph, attributable);
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
		attributeColumn = attributeModel.getNodeTable().getColumn(column);
		EqualNumberfilter = new EqualNumberFilter(attributeColumn);
		EqualNumberfilter.setColumn(attributeColumn);
		EqualNumberfilter.setMatch(num);
		Graph graph = this.graphModel.getGraph();
		Node[] nodes = graph.getNodes().toArray();
		boolean nodeMatched = false;
		for (int i = 0; i < nodes.length; i++) {
			attributable = (Attributable) nodes[i];
			nodeMatched = this.EqualNumberfilter.evaluate(graph, attributable);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!nodeMatched) {
				graph.removeNode(nodes[i]);
			}
		}
  }
  
  public void equalNumberEdgeFilter (String column, Number num) {
		attributeColumn = attributeModel.getEdgeTable().getColumn(column);
		EqualNumberfilter = new EqualNumberFilter(attributeColumn);
		boolean edgeMatched = false;
		EqualNumberfilter.setColumn(attributeColumn);
		EqualNumberfilter.setMatch(num);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		for (int i = 0; i < edges.length; i++) {
			attributable = (Attributable) edges[i];
			edgeMatched = this.EqualNumberfilter.evaluate(graph, attributable);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeEdge(edges[i]);
			}
		}
}
  
  public void equalStringNodeFilter (String column, String str,boolean useRegex) {
		this.attributeColumn = attributeModel.getNodeTable().getColumn(column);
		this.EqualStrfilter = new EqualStringFilter(attributeColumn);
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(str);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Node[] nodes = graph.getNodes().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < nodes.length; i++) {
			attributable = (Attributable) nodes[i];
			edgeMatched = this.EqualStrfilter.evaluate(graph, attributable);
			/*
			 * See if node Matched then Remove it.
			 */
			if (!edgeMatched) {
				graph.removeNode(nodes[i]);
			}
		}
}
  
  public void equalStringEdgeFilter (String column, String str,boolean useRegex) {
		this.attributeColumn = attributeModel.getEdgeTable().getColumn(column);
		this.EqualStrfilter = new EqualStringFilter(attributeColumn);
		this.EqualStrfilter.setColumn(attributeColumn);
		this.EqualStrfilter.setPattern(str);
		this.EqualStrfilter.setUseRegex(useRegex);
		Graph graph = this.graphModel.getGraph();
		Edge[] edges = graph.getEdges().toArray();
		boolean edgeMatched = false;
		for (int i = 0; i < edges.length; i++) {
			attributable = (Attributable) edges[i];
			edgeMatched = this.EqualStrfilter.evaluate(graph, attributable);
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


  public AttributeModel getAttributeModel() {
    return attributeModel;
  }

  public void setAttributeModel(AttributeModel attributeModel) {
    this.attributeModel = attributeModel;
  }


  public Attributable getAttributable() {
    return attributable;
  }

  public void setAttributable(Attributable attributable) {
    this.attributable = attributable;
  }
  
}
