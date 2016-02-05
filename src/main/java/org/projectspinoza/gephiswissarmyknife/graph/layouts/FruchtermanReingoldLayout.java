package org.projectspinoza.gephiswissarmyknife.graph.layouts;

import java.util.Map;

import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;

import com.google.inject.Singleton;

@Singleton
public class FruchtermanReingoldLayout {

  private FruchtermanReingold fruchtermanReingold;
  private GraphModel graphModel;
  
  public FruchtermanReingoldLayout() {
  }
  
  /*
   * @return void
   * wraper public method for frchtermanreingold layout
   * 
   * */
  public void applayLahout( Map<String, String> layoutParams ) {
    this.fruchtermanReingold = (this.fruchtermanReingold == null)? new FruchtermanReingold(null): this.fruchtermanReingold;
    this.fruchtermanReingold.setGraphModel(this.graphModel);
    this.fruchtermanReingold.resetPropertiesValues();
    fruchtermanReigGold(layoutParams);
  }
  
  private void fruchtermanReigGold (Map<String, String> layoutParams) {
    
    this.fruchtermanReingold.setArea((float)Double.parseDouble(layoutParams.get("area")));
    this.fruchtermanReingold.setSpeed(Double.parseDouble(layoutParams.get("speed")));
    this.fruchtermanReingold.setGravity(Double.parseDouble(layoutParams.get("gravity")));
    
    this.fruchtermanReingold.initAlgo();
    for (int i = 0; i < 100 && this.fruchtermanReingold.canAlgo(); i++) {
      this.fruchtermanReingold.goAlgo();
    }
    this.fruchtermanReingold.endAlgo();
  }

  public FruchtermanReingold getFruchtermanReingold() {
    return fruchtermanReingold;
  }

  public void setFruchtermanReingold(FruchtermanReingold fruchtermanReingold) {
    this.fruchtermanReingold = fruchtermanReingold;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }
  
}
