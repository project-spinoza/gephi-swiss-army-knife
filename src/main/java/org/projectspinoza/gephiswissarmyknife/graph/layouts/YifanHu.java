package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

public class YifanHu {

  private YifanHuLayout yifanHuLayout;
  private GraphModel graphModel;
  
  public YifanHu() {
  }
  
  
  /*
   * @return void
   * wraper public method for Yifan Hu layout
   * 
   * */
  public void applyLayout(Map<String, String> layoutParams ) {
    this.yifanHuLayout = (this.yifanHuLayout == null)? new YifanHuLayout(null,new StepDisplacement(1f)): this.yifanHuLayout;
    this.yifanHuLayout.setGraphModel(GephiGraph.getGraphModel());
    this.yifanHuLayout.resetPropertiesValues();
    yifanHuAlgo(layoutParams);
  }
  
  private void yifanHuAlgo (Map<String, String> layoutParams) {
    
    this.yifanHuLayout.setQuadTreeMaxLevel((int) Double.parseDouble(layoutParams.get("quadtreeMaxLevel")));
    this.yifanHuLayout.setBarnesHutTheta(Float.parseFloat(layoutParams.get("theta")));
    this.yifanHuLayout.setOptimalDistance(Float.parseFloat(layoutParams.get("optimalDistance")));
    this.yifanHuLayout.setRelativeStrength(Float.parseFloat(layoutParams.get("relativeStrength")));
    this.yifanHuLayout.setInitialStep(Float.parseFloat(layoutParams.get("initialStepSize")));
    this.yifanHuLayout.setStepRatio(Float.parseFloat(layoutParams.get("stepRatio")));
    if (layoutParams.containsKey("adaptiveCooling")) {
      this.yifanHuLayout.setAdaptiveCooling(Boolean.parseBoolean(layoutParams.get("adaptiveCooling")));      
    }
    this.yifanHuLayout.setConvergenceThreshold(Float.parseFloat(layoutParams.get("convergenceThreshold")));

    this.yifanHuLayout.initAlgo();
    for (int i = 0; i < 100 && this.yifanHuLayout.canAlgo(); i++) {
      this.yifanHuLayout.goAlgo();
    }
    this.yifanHuLayout.endAlgo();
  }

  public YifanHuLayout getYifanHuLayout() {
    return yifanHuLayout;
  }

  public void setYifanHuLayout(YifanHuLayout yifanHuLayout) {
    this.yifanHuLayout = yifanHuLayout;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }
}
