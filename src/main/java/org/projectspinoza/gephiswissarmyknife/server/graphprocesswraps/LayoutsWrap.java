package org.projectspinoza.gephiswissarmyknife.server.graphprocesswraps;

import io.vertx.core.MultiMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.gephi.graph.api.GraphModel;
import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.ForceAtlas;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.FruchtermanReingoldLayout;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.LabelAdjustLayout;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.NoverLap;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Rotation;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Scale;

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
  private GraphModel graphModel;

  public LayoutsWrap() {
    this.graphModel = GephiGraph.getGraphModel();
  }

  /*
   * For stand alone rotation operations
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
      this.forceAtlas.applyLayout(multiMapToHashMap(layoutParams));
      break; 
    case "FruchtermanReingold":
      this.fruchtermanReingoldLayout.setGraphModel(GephiGraph.getGraphModel());
      this.fruchtermanReingoldLayout.applyLayout(multiMapToHashMap(layoutParams));
      break;
    case "LabelAdjust":
      this.labelAdjust.setGraphModel(GephiGraph.getGraphModel());
      this.labelAdjust.applyLayout(multiMapToHashMap(layoutParams));
      break;
    case "NoverLap":
      this.noverLapLayout.setGraphModel(GephiGraph.getGraphModel());
      this.noverLapLayout.applyLayout(multiMapToHashMap(layoutParams));
      break;
    default:
      break;
    }
    
  }

  private Map<String, String> multiMapToHashMap (MultiMap mMap) {
    Map <String, String> map = new HashMap<String, String>();
    for (Entry<String, String> entity : mMap.entries()) {
      map.put(entity.getKey(), entity.getValue());
    }
    return map;
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

}
