package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.noverlap.NoverlapLayout;

public class NoverLap {

  private NoverlapLayout noverlapLayout;
  private GraphModel graphModel;

  public NoverLap() {
  }
  
  /*
   * @return void
   * wraper public method for noverLap layout
   * 
   * */
  public void applyLayout(Map<String, String> layoutParams ) {
    this.noverlapLayout = (this.noverlapLayout == null)? new NoverlapLayout(null): this.noverlapLayout;
    this.noverlapLayout.setGraphModel(this.getGraphModel());
    this.noverlapLayout.resetPropertiesValues();
    OpenOrdAlgo (layoutParams);
  }
  
  private void OpenOrdAlgo (Map<String, String> layoutParams) {
    
    this.noverlapLayout.setRatio(Double.parseDouble(layoutParams.get("ratio")));
    this.noverlapLayout.setSpeed(Double.parseDouble(layoutParams.get("speed")));
    this.noverlapLayout.setMargin(Double.parseDouble(layoutParams.get("margin")));
    this.noverlapLayout.initAlgo();
    for (int i = 0; i < 100 && this.noverlapLayout.canAlgo(); i++) {
      this.noverlapLayout.goAlgo();
    }
    this.noverlapLayout.endAlgo();
  }
  
  public NoverlapLayout getNoverlapLayout() {
    return noverlapLayout;
  }
  
  public void setNoverlapLayout(NoverlapLayout noverlapLayout) {
    this.noverlapLayout = noverlapLayout;
  }
  
  public GraphModel getGraphModel() {
    return graphModel;
  }
  
  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

}
