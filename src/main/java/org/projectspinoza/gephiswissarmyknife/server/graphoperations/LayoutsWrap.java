package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;

import org.gephi.graph.api.GraphModel;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.ForceAtlas;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.FruchtermanReingoldLayout;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.LabelAdjustLayout;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.NoverLap;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.OpenOrd;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Random;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Rotation;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Scale;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.YifanHu;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.YifanHuProportionalLayout;
import org.projectspinoza.gephiswissarmyknife.utils.Utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LayoutsWrap {

  private Rotation layoutRotate;
  private Scale layoutScale;
  private ForceAtlas forceAtlas;
  private FruchtermanReingoldLayout fruchtermanReingoldLayout;
  private LabelAdjustLayout labelAdjust;
  private NoverLap noverLapLayout;
  private OpenOrd openOrd;
  private Random randomLayout;
  private YifanHu yifanHuLayout;
  private YifanHuProportionalLayout yifanHuProportionalLayout;
  private GraphModel graphModel;

  public LayoutsWrap() {
  }

  /*
   * For stand alone Layout operations
   */
  public void applyLayout(MultiMap layoutParams) {

    switch (layoutParams.get("layout")) {
    case "Rotation":
      this.layoutRotate.setGraphModel(getGraphModel());
      this.layoutRotate.setAngle(Double.parseDouble(layoutParams.get("angle")));
      this.layoutRotate.rotate();
      break;
    case "Scale":
      this.layoutScale.setGraphModel(getGraphModel());
      this.layoutScale.setScale(Double.parseDouble(layoutParams.get("scale")));
      this.layoutScale.scale();
      break;
    case "ForceAtlas":
      this.forceAtlas.setGraphModel(this.graphModel);
      this.forceAtlas.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break; 
    case "FruchtermanReingold":
      this.fruchtermanReingoldLayout.setGraphModel(this.graphModel);
      this.fruchtermanReingoldLayout.setGraphModel(this.graphModel);
      this.fruchtermanReingoldLayout.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break;
    case "LabelAdjust":
      this.labelAdjust.setGraphModel(this.graphModel);
      this.labelAdjust.setGraphModel(this.graphModel);
      this.labelAdjust.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break;
    case "NoverLap":
      this.noverLapLayout.setGraphModel(this.graphModel);
      this.noverLapLayout.setGraphModel(this.graphModel);
      this.noverLapLayout.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break;
    case "OpenOrd":
      this.openOrd.setGraphModel(this.graphModel);
      this.openOrd.setGraphModel(this.graphModel);
      this.openOrd.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break;
    case "RandomLayout":
      this.randomLayout.setGraphModel(this.graphModel);
      this.randomLayout.setGraphModel(this.graphModel);
      this.randomLayout.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break;
    case "YafinHu":
      this.yifanHuLayout.setGraphModel(this.graphModel);
      this.yifanHuLayout.setGraphModel(this.graphModel);
      this.yifanHuLayout.applyLayout(Utils.multiMapToHashMap(layoutParams));
      break; 
    case "YafinHuProportional":
      this.yifanHuProportionalLayout.setGraphModel(this.graphModel);
      this.yifanHuProportionalLayout.setGraphModel(this.graphModel);
      this.yifanHuProportionalLayout.applyLayout(Utils.multiMapToHashMap(layoutParams));
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

  public FruchtermanReingoldLayout getFruchtermanReingoldLayout() {
    return fruchtermanReingoldLayout;
  }

  @Inject
  public void setFruchtermanReingoldLayout(FruchtermanReingoldLayout fruchtermanReingoldLayout) {
    this.fruchtermanReingoldLayout = fruchtermanReingoldLayout;
  }

  public LabelAdjustLayout getLabelAdjust() {
    return labelAdjust;
  }

  @Inject
  public void setLabelAdjust(LabelAdjustLayout labelAdjust) {
    this.labelAdjust = labelAdjust;
  }

  public NoverLap getNoverLapLayout() {
    return noverLapLayout;
  }

  @Inject
  public void setNoverLapLayout(NoverLap noverLapLayout) {
    this.noverLapLayout = noverLapLayout;
  }

  public OpenOrd getOpenOrd() {
    return openOrd;
  }
  
  @Inject
  public void setOpenOrd(OpenOrd openOrd) {
    this.openOrd = openOrd;
  }

  public Random getRandomLayout() {
    return randomLayout;
  }

  @Inject
  public void setRandomLayout(Random randomLayout) {
    this.randomLayout = randomLayout;
  }

  public YifanHu getYifanHuLayout() {
    return yifanHuLayout;
  }

  @Inject
  public void setYifanHuLayout(YifanHu yifanHuLayout) {
    this.yifanHuLayout = yifanHuLayout;
  }

  public YifanHuProportionalLayout getYifanHuProportionalLayout() {
    return yifanHuProportionalLayout;
  }

  @Inject
  public void setYifanHuProportionalLayout(YifanHuProportionalLayout yifanHuProportionalLayout) {
    this.yifanHuProportionalLayout = yifanHuProportionalLayout;
  }

}
