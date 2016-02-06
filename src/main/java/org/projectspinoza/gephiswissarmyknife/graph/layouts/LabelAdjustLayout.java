package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.labelAdjust.LabelAdjust;

public class LabelAdjustLayout {
  
  private LabelAdjust labelAdjust;
  private GraphModel graphModel;
  
  public LabelAdjustLayout() {
    
  }
  
  public void applyLayout (Map<String, String> layoutParams) {
    this.labelAdjust = (this.labelAdjust == null) ? new LabelAdjust(null): this.labelAdjust;
    this.labelAdjust.setGraphModel(this.graphModel);
    this.labelAdjust.resetPropertiesValues();
    layoutLabelAdust(layoutParams);
    
  }
  
  private void layoutLabelAdust(Map<String, String> layoutParams) {
    this.labelAdjust.setSpeed(Double.parseDouble(layoutParams.get("speed")));
    this.labelAdjust.setAdjustBySize(true);
    this.labelAdjust.setConverged(true);
    this.labelAdjust.initAlgo();
    for (int i = 0; i < 100 && this.labelAdjust.canAlgo(); i++) {
      this.labelAdjust.goAlgo();
    }
    this.labelAdjust.endAlgo();
  }

  public LabelAdjust getLabelAdjust() {
    return labelAdjust;
  }

  public void setLabelAdjust(LabelAdjust labelAdjust) {
    this.labelAdjust = labelAdjust;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }
  
}
