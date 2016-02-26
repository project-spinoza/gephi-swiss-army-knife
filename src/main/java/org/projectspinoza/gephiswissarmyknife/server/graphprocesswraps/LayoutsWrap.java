package org.projectspinoza.gephiswissarmyknife.server.graphprocesswraps;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.ForceAtlas;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Rotation;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Scale;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LayoutsWrap {

  private Rotation layoutRotate;
  private Scale layoutScale;
  private ForceAtlas forceAtlas;
  private GraphModel graphModel;

  public LayoutsWrap() {

  }

  /*
   * For stand alone rotation operations
   */
  public void applyLayout(String layoutIdStr, Map<String, String> layoutParams) {

    switch (layoutIdStr) {
    case "rotation":
      this.layoutRotate.setGraphModel(getGraphModel());
      this.layoutRotate.setAngle(Double.parseDouble(layoutParams.get("angle")));
      this.layoutRotate.rotate();
      break;
    case "scale":
      this.layoutScale.setGraphModel(getGraphModel());
      this.layoutScale.setScale(Double.parseDouble(layoutParams.get("scale")));
      this.layoutScale.scale();
      break;
    case "force_atlas":
      this.forceAtlas.setGraphModel(getGraphModel());
      this.forceAtlas.applayLayout(layoutParams);
      break;
    default:
      break;
    }
  }

  public Rotation getLayoutRotate() {
    return layoutRotate;
  }

  @Inject
  public void setLayoutRotate(Rotation layoutRotate) {
    this.layoutRotate = layoutRotate;
  }

  public Scale getLayoutScale() {
    return layoutScale;
  }

  @Inject
  public void setLayoutScale(Scale layoutScale) {
    this.layoutScale = layoutScale;
  }

  public ForceAtlas getForceAtlas() {
    return forceAtlas;
  }

  @Inject
  public void setForceAtlas(ForceAtlas forceAtlas) {
    this.forceAtlas = forceAtlas;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

}
