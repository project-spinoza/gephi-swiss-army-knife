package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;

import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.statistics.Statistics;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class StatisticsWrap {

  private Statistics statistics;
  
  public StatisticsWrap() {
  }

  /*
   * For stand alone Statistics operations
   */
  public void applyStatistics(MultiMap layoutParams) {
    switch (layoutParams.get("statistics")) {
    case "averageDegree":
      this.statistics.setGraphModel(GephiGraph.getGraphModel());
      this.statistics.averageDegree();
      break;
    default:
      break;
    }
  }

  
  public Statistics getStatistics() {
    return statistics;
  }

  @Inject
  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }
  
  
  
}
