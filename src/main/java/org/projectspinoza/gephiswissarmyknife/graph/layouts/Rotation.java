package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.rotate.RotateLayout;

import com.google.inject.Singleton;

@Singleton
public class Rotation {

  private double angle;
  protected GraphModel graphModel;
  private RotateLayout rotateLayout;

  public Rotation() {
  }

  public void rotate() {
    this.rotateLayout = (this.rotateLayout == null) ? new RotateLayout(null,
        getAngle()) : rotateLayout;
    this.rotateLayout.setGraphModel(graphModel);
    this.rotateLayout.setAngle(getAngle());
    for (int i = 0; i < 100 && this.rotateLayout.canAlgo(); i++) {
      this.rotateLayout.goAlgo();
    }
    this.rotateLayout.endAlgo();
  }

  public RotateLayout getRotateLayout() {
    return rotateLayout;
  }

  public void setRotateLayout(RotateLayout rotateLayout) {
    this.rotateLayout = rotateLayout;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public Double getAngle() {
    return angle;

  }
}
