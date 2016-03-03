package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

import com.google.inject.Singleton;

@Singleton
public class ForceAtlas {

  private ForceAtlasLayout forceAtlasLayout;
  private GraphModel graphModel;


  public ForceAtlas()  {
  }

  /*
   * @return void
   * Wrapper function for force atlas layouts..
   * */
  public void applyLayout(Map<String, String> layoutParams) {
    this.forceAtlasLayout = (this.forceAtlasLayout == null) ? new ForceAtlasLayout(null) : this.forceAtlasLayout;
    this.forceAtlasLayout.setGraphModel(GephiGraph.getGraphModel());
    this.forceAtlasLayout.resetPropertiesValues();
    forceAtlas(layoutParams);
  }

  private void forceAtlas(Map<String, String> layoutParams) {

    this.forceAtlasLayout.setInertia(Double.parseDouble(layoutParams.get("intertia")));
    this.forceAtlasLayout.setRepulsionStrength(Double.parseDouble(layoutParams.get("repulsion_strength")));
    this.forceAtlasLayout.setAttractionStrength(Double.parseDouble(layoutParams.get("attraction_strength")));
    this.forceAtlasLayout.setMaxDisplacement(Double.parseDouble(layoutParams.get("max_displacement")));
    this.forceAtlasLayout.setAdjustSizes(Boolean.parseBoolean(layoutParams.get("adjust_sizes")));
    this.forceAtlasLayout.setGravity(Double.parseDouble(layoutParams.get("gravity")));
    this.forceAtlasLayout.setSpeed(Double.parseDouble(layoutParams.get("speed")));
    this.forceAtlasLayout.setConverged(true);
    this.forceAtlasLayout.initAlgo();
    for (int i = 0; i < 100 && this.forceAtlasLayout.canAlgo(); i++) {
      this.forceAtlasLayout.goAlgo();
    }
    this.forceAtlasLayout.endAlgo();
  }

  
  /*
   * for test purpose ==> testing layout without UI form elements
   * */
//  private void forceAtlasTemp(Map<String, String> layoutParams) {
//    
//    this.forceAtlasLayout = (this.forceAtlasLayout == null) ? new ForceAtlasLayout(null) : this.forceAtlasLayout;
//    this.forceAtlasLayout.setGraphModel(getGraphModel());
//    this.forceAtlasLayout.resetPropertiesValues();
//    this.forceAtlasLayout.setInertia(0.1);
//    this.forceAtlasLayout.setRepulsionStrength(200.0);
//    this.forceAtlasLayout.setAttractionStrength(10.0);
//    this.forceAtlasLayout.setMaxDisplacement(10.0);
//    this.forceAtlasLayout.setAdjustSizes(true);
//    this.forceAtlasLayout.setGravity(30.0);
//    this.forceAtlasLayout.setSpeed(1.0);
//    this.forceAtlasLayout.setConverged(true);
//    this.forceAtlasLayout.initAlgo();
//    for (int i = 0; i < 100 && this.forceAtlasLayout.canAlgo(); i++) {
//      this.forceAtlasLayout.goAlgo();
//    }
//    this.forceAtlasLayout.endAlgo();
//  }
//  
  public ForceAtlasLayout getForceAtlasLayout() {
    return forceAtlasLayout;
  }

  public void setForceAtlasLayout(ForceAtlasLayout forceAtlasLayout) {
    this.forceAtlasLayout = forceAtlasLayout;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

}
