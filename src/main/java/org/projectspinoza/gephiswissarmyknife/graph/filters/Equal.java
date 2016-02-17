package org.projectspinoza.gephiswissarmyknife.graph.filters;

import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.filters.plugin.attribute.AttributeEqualBuilder.EqualStringFilter;
import org.gephi.graph.api.Attributable;
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
   * 
   * 
   * */
  public void idNodeFilter(String id, boolean useRegex) {
    this.attributeColumn = attributeModel.getNodeTable().getColumn("id");
    this.EqualStrfilter = new EqualStringFilter(attributeColumn);
    this.EqualStrfilter.addProperty(String.class, "match");
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

  }
  
  public void labelNodeFilter (String label, boolean useRegex) {

  }
  
  public void labelEdgeFilter (String label, boolean useRegex) {

  }
  
  public void weightFilter (float weight) {
    
  }
  
  public void equalNumberFilter (String column, Number num) {
    
  }

  public void equalStringFilter (String column, String str) {
    
  }
  
  public void id (GraphDim dim, Number idNum) {
    this.attributeColumn = null;
    
    switch (dim) {
    case NODES:
      this.attributeColumn = attributeModel.getNodeTable().getColumn("id");
      break;
      
    case EDGES:
      this.attributeColumn = attributeModel.getEdgeTable().getColumn("id");
      break;

    default:
      break;
    }
  }
  
  public void id (GraphDim dim, String idString) {
    
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
