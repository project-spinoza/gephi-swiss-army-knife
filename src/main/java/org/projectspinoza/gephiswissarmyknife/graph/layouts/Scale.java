package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.scale.ScaleLayout;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;

import com.google.inject.Singleton;

@Singleton
public class Scale {

  private double scale;
  protected GraphModel graphModel;
  private ScaleLayout sclaeLayout;

  public Scale() {
  }

  public void scale() {
    this.sclaeLayout = (this.sclaeLayout == null) ? new ScaleLayout(null,
        getScale()) : sclaeLayout;
    this.sclaeLayout.setGraphModel(GephiGraph.getGraphModel());
    this.sclaeLayout.setScale(getScale());
    this.sclaeLayout.goAlgo();
    this.sclaeLayout.endAlgo();
  }

  public ScaleLayout getSclaeLayout() {
    return sclaeLayout;
  }

  public void setSclaeLayout(ScaleLayout sclaeLayout) {
    this.sclaeLayout = sclaeLayout;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  public Double getScale() {
    return scale;

  }
}
