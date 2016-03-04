package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.openord.OpenOrdLayout;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

public class OpenOrd {

  private OpenOrdLayout openOrdLayout;
  private GraphModel graphModel;
  
  public OpenOrd() {
  
  }
  
  /*
   * @return void
   * wraper public method for noverLap layout
   * 
   * */
  public void applyLayout(Map<String, String> layoutParams ) {
    this.openOrdLayout = (this.openOrdLayout == null)? new OpenOrdLayout(null): this.openOrdLayout;
    this.openOrdLayout.setGraphModel(GephiGraph.getGraphModel());
    this.openOrdLayout.resetPropertiesValues();
    openOrdAlgo(layoutParams);
  }
  
  private void openOrdAlgo (Map<String, String> layoutParams) {
    
    this.openOrdLayout.setLiquidStage(Integer.parseInt(layoutParams.get("liquid")));
    this.openOrdLayout.setExpansionStage(Integer.parseInt(layoutParams.get("expansion")));
    this.openOrdLayout.setCooldownStage(Integer.parseInt(layoutParams.get("cooldown")));
    this.openOrdLayout.setCrunchStage(Integer.parseInt(layoutParams.get("crunch")));
    this.openOrdLayout.setSimmerStage(Integer.parseInt(layoutParams.get("simmer")));
    this.openOrdLayout.setEdgeCut(Float.parseFloat(layoutParams.get("edgeCut")));
    this.openOrdLayout.setNumThreads(Integer.parseInt(layoutParams.get("numThreads")));
    this.openOrdLayout.setNumIterations(Integer.parseInt(layoutParams.get("numIterations")));
    this.openOrdLayout.setRandSeed(Long.parseLong(layoutParams.get("randomSeed")));
    this.openOrdLayout.setRealTime(Float.parseFloat(layoutParams.get("fixedTime")));
    
    this.openOrdLayout.initAlgo();
    for (int i = 0; i < 100 && this.openOrdLayout.canAlgo(); i++) {
      this.openOrdLayout.goAlgo();
    }
    this.openOrdLayout.endAlgo();
  }
  
  

  public GraphModel getGraphModel() {
    return graphModel;
  }
  
  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  public OpenOrdLayout getOpenOrdLayout() {
    return openOrdLayout;
  }

  public void setOpenOrdLayout(OpenOrdLayout openOrdLayout) {
    this.openOrdLayout = openOrdLayout;
  }
}
