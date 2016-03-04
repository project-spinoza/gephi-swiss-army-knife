package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.random.RandomLayout;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

public class Random {

  private RandomLayout randomLayout;
  private GraphModel graphModel;
  
  public Random() {
  }
  
  public void applyLayout (Map <String, String> layoutParams) {
    this.randomLayout = (this.randomLayout == null) ? new RandomLayout(null, 0.0) : this.randomLayout;
    this.randomLayout.setGraphModel(GephiGraph.getGraphModel());
    this.randomLayout.resetPropertiesValues();
    randomLayoutAlgo(layoutParams);
  }
  
  private void randomLayoutAlgo(Map <String, String> layoutParams) {
    this.randomLayout.setConverged(true);
    this.randomLayout.setSize(Double.parseDouble(layoutParams.get("space_size")));
    this.randomLayout.initAlgo();
    for (int i = 0; i < 100 & this.randomLayout.canAlgo() ; i++ ) {
      this.randomLayout.goAlgo();
    }
    this.randomLayout.endAlgo();
  }

  public RandomLayout getRandomLayout() {
    return randomLayout;
  }

  public void setRandomLayout(RandomLayout randomLayout) {
    this.randomLayout = randomLayout;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }
}
