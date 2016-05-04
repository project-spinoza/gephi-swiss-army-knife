package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.projectspinoza.gephiswissarmyknife.graph.statistics.Statistics;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class StatisticsWrap {

  private Statistics statistics;
  JsonObject rootRespJson;
  private GraphModel graphModel;

  public StatisticsWrap() {
  }

  /*
   * For stand alone Statistics operations
   */
  public String applyStatistics(MultiMap layoutParams) {
    String responseJson = "";
    JsonObject root;
    this.statistics.setGraphModel(this.graphModel);
    
    switch (layoutParams.get("statistics")) {
    case "averageDegree":
      this.statistics.averageDegree();
      responseJson = averageDegreeStatistics(this.graphModel.getGraphVisible(), false);
      break;
      
    case "averageWeightedDegree":
      this.statistics.averageWeightedDegree();
      responseJson = averageDegreeStatistics(this.graphModel.getGraphVisible(), true);
      break;
    case "graphDensity":
      double gDensity = this.statistics.graphDensity();
      rootRespJson = new JsonObject();
      rootRespJson.put("density", "Density: " + gDensity);
      responseJson = rootRespJson.toString();
      break;
    case "Modularity":
      this.statistics.modularityClass(); //calculate modularity with default params
      responseJson = colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "modularity_class");
      break;
    case "pageRank":
      this.statistics.pageRank(); //calculate pagerank with default params
      responseJson = colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "pageranks");
      break;
    case "connectedComponents":
      this.statistics.connectedComponents();
      responseJson = connectedComponentsStatistics(this.graphModel.getGraphVisible());
      break;
      
    case "avgClusterCoeficients":
      this.statistics.avgClusterCoeficients();
      responseJson = colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "clustering");
      break;
      
    case "eigenVectorCentrality":
      this.statistics.eigenVectorCentrality();
      responseJson = colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "eigencentrality");
      break;
      
    case "avgPathLength":
      this.statistics.graphDistance();
      root = new JsonObject();
      JsonObject eccentricity = new JsonObject(colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "eccentricity")).getJsonObject("eccentricity");
      JsonObject closnesscentrality = new JsonObject(colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "closnesscentrality")).getJsonObject("closnesscentrality");
      JsonObject betweenesscentrality = new JsonObject(colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "betweenesscentrality")).getJsonObject("betweenesscentrality");
      root.put("eccentricity", eccentricity);
      root.put("closnesscentrality", closnesscentrality);
      root.put("betweenesscentrality", betweenesscentrality);
      responseJson = root.toString();
      break;
    case "hits":
      this.statistics.calculateHits();
      root = new JsonObject();
      JsonObject authority = new JsonObject(colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "authority")).getJsonObject("authority");
      JsonObject hub = new JsonObject(colValNodesStatisticsJson(this.graphModel.getGraphVisible(), "hub")).getJsonObject("hub");
      root.put("authority", authority);
      root.put("hub", hub);      
      responseJson = root.toString();
      break;
    default:
      break;
    }
    return responseJson;
  }
  
  
  /*
   * @return String JSON. Counts nodes for modularity class
   * 
   */
  public String colValNodesStatisticsJson(Graph graph, String col) {
    Map<String, Object> colMap = new HashMap<String, Object>();

    for (Node n : graph.getNodes()) {
      String mKey = n.getAttribute(col).toString();
      if (colMap.containsKey(mKey)) {
        int d = Integer.parseInt(colMap.get(mKey).toString());
        colMap.put(mKey, d + 1);
      } else {
        colMap.put(mKey, 1);
      }
    }
    this.rootRespJson = new JsonObject();
    this.rootRespJson.put(col, new JsonObject(colMap));
    return this.rootRespJson.toString();
  }

  public String connectedComponentsStatistics(Graph graph) {
    Map <Integer, Integer> componentsMap = new HashMap<Integer, Integer>();
    Map <String, Integer> componentsMapFinal = new HashMap<String, Integer>();
    double stronglyConnected = 0;
    
    //calculating the weekly connected components.
    for (Node node : this.graphModel.getGraphVisible().getNodes()) {
      int componentId = Integer.parseInt(node.getAttribute("componentnumber").toString());
      if (componentsMap.containsKey(componentId)) {
        int val = componentsMap.get(componentId);
        componentsMap.put(componentId, val+1);
      }else {
        componentsMap.put(componentId, 1);
      }
      
      if (Double.parseDouble(node.getAttribute("strongcompnum").toString()) != 0d){
        stronglyConnected++;
      }
    }
    for (Entry<Integer, Integer> entityOut : componentsMap.entrySet()) {
      int valComp = entityOut.getValue();
      int counter = 1;
      for (Entry<Integer, Integer> entityInn : componentsMap.entrySet()) {
        if ((valComp == entityInn.getValue())&& (entityOut.getKey() != entityInn.getKey()) && !componentsMapFinal.containsKey(String.valueOf(valComp))) {
          counter++;
        }
      }
      componentsMapFinal.put(String.valueOf(valComp), counter);
    }
   rootRespJson = new JsonObject();
   rootRespJson.put("connected_components", componentsMapFinal);
   rootRespJson.put("weekly_connected_components", componentsMap.size());
   rootRespJson.put("strongly_connected_components", stronglyConnected);
   return rootRespJson.toString();
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

    rootRespJson = new JsonObject();
    rootRespJson.put("degree", new JsonObject(degreeMap));
    rootRespJson.put("indegree", new JsonObject(inDegreeMap));
    rootRespJson.put("outdegree", new JsonObject(outDegreeMap));
    
    if (isWeighted) {
      rootRespJson.put("avgdegree", "Average Weighted Degree: "+ sumDegree / graph.getNodeCount());      
    }else {
      rootRespJson.put("avgdegree", "Average Degree: "+ sumDegree / graph.getNodeCount());
    }
    
    return rootRespJson.toString();
  }

  public Statistics getStatistics() {
    return statistics;
  }

  @Inject
  public void setStatistics(Statistics statistics) {
    this.statistics = statistics;
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

}
