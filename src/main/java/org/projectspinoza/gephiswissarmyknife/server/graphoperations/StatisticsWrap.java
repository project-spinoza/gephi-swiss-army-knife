package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
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
  public String applyStatistics(MultiMap layoutParams) {
    String responseJson = "";
    switch (layoutParams.get("statistics")) {
    case "averageDegree":
      this.statistics.setGraphModel(GephiGraph.getGraphModel());
      this.statistics.averageDegree();
      responseJson = averageDegreeStatistics(GephiGraph.getGraphModel().getGraphVisible(), false);
      break;
      
    case "averageWeightedDegree":
      this.statistics.setGraphModel(GephiGraph.getGraphModel());
      this.statistics.averageWeightedDegree();
      responseJson = averageDegreeStatistics(GephiGraph.getGraphModel().getGraphVisible(), true);
      break;
      
    default:
      break;
    }
    
    return responseJson;
  }

  /*
   * @return String JSON. Counts nodes for each degree, indegree, and outdegree
   * value
   */
  public String averageDegreeStatistics(Graph graph, boolean isWeighted) {
    
    Map<String, Object> degreeMap = new HashMap<String, Object>();
    Map<String, Object> inDegreeMap = new HashMap<String, Object>();
    Map<String, Object> outDegreeMap = new HashMap<String, Object>();
    String degreeCol;
    String indegreeCol;
    String outdegreeCol;

    if (!isWeighted) {
      degreeCol = "degree";
      indegreeCol = "indegree";
      outdegreeCol = "outdegree";
    }else {
      degreeCol = "weighted degree";
      indegreeCol = "weighted indegree";
      outdegreeCol = "weighted outdegree";      
    }
    
    double sumDegree = 0;

    for (Node n : graph.getNodes()) {
      
      String dKey = n.getAttribute(degreeCol).toString();
      String indKey = n.getAttribute(indegreeCol).toString();
      String outdKey = n.getAttribute(outdegreeCol).toString();

      sumDegree = sumDegree + Double.parseDouble(dKey);

      // Degree Calculation
      if (degreeMap.containsKey(dKey)) {
        int d = Integer.parseInt(degreeMap.get(dKey).toString());
        degreeMap.put(dKey, d + 1);
      } else {
        degreeMap.put(dKey, 1);
      }
      
      // In-Degree Calculation
      if (inDegreeMap.containsKey(indKey)) {
        int id = Integer.parseInt(inDegreeMap.get(indKey).toString());
        inDegreeMap.put(indKey, id + 1);
      } else {
        inDegreeMap.put(indKey, 1);
      }

      // Out-Degree Calculation
      if (outDegreeMap.containsKey(outdKey)) {
        int od = Integer.parseInt(outDegreeMap.get(outdKey).toString());
        outDegreeMap.put(outdKey, od + 1);
      } else {
        outDegreeMap.put(outdKey, 1);
      }
    }

    JsonObject root = new JsonObject();
    root.put("degree", new JsonObject(degreeMap));
    root.put("indegree", new JsonObject(inDegreeMap));
    root.put("outdegree", new JsonObject(outDegreeMap));
    
    if (isWeighted) {
      root.put("avgdegree", "Average Weighted Degree: "+ sumDegree / graph.getNodeCount());      
    }else {
      root.put("avgdegree", "Average Degree: "+ sumDegree / graph.getNodeCount());
    }
    
    return root.toString();
    
  }

  public Statistics getStatistics() {
    return statistics;
  }

  @Inject
  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

}
