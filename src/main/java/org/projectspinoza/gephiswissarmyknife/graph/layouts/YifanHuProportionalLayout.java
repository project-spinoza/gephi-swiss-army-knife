package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.force.yifanHu.YifanHuProportional;

public class YifanHuProportionalLayout {

  private YifanHuProportional yifanHuPropertional;
  private GraphModel graphModel;
  private YifanHuLayout yifanHuLayout;
  
  public YifanHuProportionalLayout() {
  }
  
  /*
   * @return void
   * wraper public method for Yifan Hu Proportional layout
   * 
   * */
  public void applyLayout(Map<String, String> layoutParams ) {
    this.yifanHuPropertional = (this.yifanHuPropertional == null)? new YifanHuProportional(): this.yifanHuPropertional;
    this.yifanHuLayout = this.yifanHuPropertional.buildLayout();
    this.yifanHuLayout.setGraphModel(this.getGraphModel());
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
      this.yifanHuLayout.setAdaptiveCooling(Boolean.parseBoolean(layoutParams.get("numThreads")));      
    }
    this.yifanHuLayout.setConvergenceThreshold(Float.parseFloat(layoutParams.get("convergenceThreshold")));

    this.yifanHuLayout.initAlgo();
    for (int i = 0; i < 100 && this.yifanHuLayout.canAlgo(); i++) {
      this.yifanHuLayout.goAlgo();
    }
    this.yifanHuLayout.endAlgo();
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  public YifanHuProportional getYifanHuPropertional() {
    return yifanHuPropertional;
  }

  public void setYifanHuPropertional(YifanHuProportional yifanHuPropertional) {
    this.yifanHuPropertional = yifanHuPropertional;
  }
}
