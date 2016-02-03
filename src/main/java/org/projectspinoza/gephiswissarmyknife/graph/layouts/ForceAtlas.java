package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;

public class ForceAtlas {

  private ForceAtlasLayout forceAtlasLayout;
  private GraphModel graphModel;

  public ForceAtlas() {

  }

  public void applayLayout(Map<String, String> layoutParams) {
    forceAtlas(layoutParams);
  }

  private void forceAtlas(Map<String, String> layoutParams) {
    this.forceAtlasLayout = (this.forceAtlasLayout == null) ? new ForceAtlasLayout(
        null) : this.forceAtlasLayout;
    this.forceAtlasLayout.setGraphModel(getGraphModel());
    this.forceAtlasLayout.setInertia(Double.parseDouble(layoutParams
        .get("intertia")));
    this.forceAtlasLayout.setRepulsionStrength(Double.parseDouble(layoutParams
        .get("repulsion_strength")));
    this.forceAtlasLayout.setAttractionStrength(Double.parseDouble(layoutParams
        .get("attraction_strength")));
    this.forceAtlasLayout.setMaxDisplacement(Double.parseDouble(layoutParams
        .get("max_displacement")));
    this.forceAtlasLayout.setAdjustSizes(Boolean.parseBoolean(layoutParams
        .get("adjust_sizes")));
    this.forceAtlasLayout.setGravity(Double.parseDouble(layoutParams
        .get("gravity")));
    this.forceAtlasLayout
        .setSpeed(Double.parseDouble(layoutParams.get("speed")));
    this.forceAtlasLayout.setConverged(true);
    this.forceAtlasLayout.initAlgo();
    for (int i = 0; i < 100 && this.forceAtlasLayout.canAlgo(); i++) {
      this.forceAtlasLayout.goAlgo();
    }
    this.forceAtlasLayout.endAlgo();
  }

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
