package org.projectspinoza.gephiswissarmyknife.utils;

import io.vertx.core.json.JsonObject;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;

import com.google.inject.Singleton;

@Singleton
public class GraphBackup {

  private static Map<Integer, JsonObject> nodesMap;
  private static Map<Integer, JsonObject> edgesMap;
  private static Map<Integer, JsonObject> nodesMapFilter;
  private static Map<Integer, JsonObject> edgesMapFilter;

  public GraphBackup() {
    if (nodesMap == null && edgesMap == null) {
      nodesMap = new HashMap<Integer, JsonObject>();
      edgesMap = new HashMap<Integer, JsonObject>();
    }
    if (nodesMapFilter == null && edgesMapFilter == null) {
      nodesMapFilter = new HashMap<Integer, JsonObject>();
      edgesMapFilter = new HashMap<Integer, JsonObject>();
    }
  }

  public void saveGraph(Graph graph, boolean isFilterGraph) {

    int Counter = 0;
    JsonObject attrJson;

    //clear existing graph
    if (!isFilterGraph){
      nodesMap.clear();
      edgesMap.clear();
    }else {
      nodesMapFilter.clear();
      edgesMapFilter.clear();
    }
    
    /*
     * Nodes Iteration
     */
    for (Node node : graph.getNodes()) {
      attrJson = new JsonObject();
      for (String attrKey : node.getAttributeKeys()) {
        attrJson.put(attrKey, node.getAttribute(attrKey));
      }
      attrJson.put("x", node.x());
      attrJson.put("y", node.y());
      attrJson.put("cR", node.r());
      attrJson.put("cG", node.g());
      attrJson.put("cB", node.b());
      attrJson.put("size", node.size());

      if (!isFilterGraph){
        nodesMap.put(Counter++, attrJson);
      }else {
        nodesMapFilter.put(Counter++, attrJson);
      }
      
    }

    Counter = 0;

    /*
     * Edges Iteration
     */
    for (Edge edge : graph.getEdges()) {
      attrJson = new JsonObject();
      for (String attrKey : edge.getAttributeKeys()) {
        attrJson.put(attrKey, edge.getAttribute(attrKey));
      }
      attrJson.put("source", edge.getSource().getId());
      attrJson.put("target", edge.getTarget().getId());
      attrJson.put("cR", edge.r());
      attrJson.put("cG", edge.g());
      attrJson.put("cB", edge.b());
      attrJson.put("size", edge.getWeight());
      if (!isFilterGraph){
        edgesMap.put(Counter++, attrJson);
      }else {
        edgesMapFilter.put(Counter++, attrJson);
      }
    }
  }

  public Graph retrieveGraph(GraphModel graphModel, boolean isFilterGraph) {
    Graph graph = graphModel.getGraph();

    graph.clear();
    Map<Integer, JsonObject> nMap;
    Map<Integer, JsonObject> eMap;
    if (!isFilterGraph){
      nMap = nodesMap;
      eMap = edgesMap;
    }else {
      nMap = nodesMapFilter;
      eMap = edgesMapFilter;
    }
    
    for (Entry<Integer, JsonObject> enSet : nMap.entrySet()) {
      Map<String, Object> map = enSet.getValue().getMap();
      Node node = graphModel.factory().newNode(enSet.getValue().getValue("id"));
      for (Entry<String, Object> attSet : map.entrySet()) {
        if (!attSet.getKey().equalsIgnoreCase("id")
            && !attSet.getKey().equalsIgnoreCase("x")
            && !attSet.getKey().equalsIgnoreCase("y")
            && !attSet.getKey().equalsIgnoreCase("cR")
            && !attSet.getKey().equalsIgnoreCase("size")
            && !attSet.getKey().equalsIgnoreCase("cG")
            && !attSet.getKey().equalsIgnoreCase("cB")) {
          node.setAttribute(attSet.getKey(), attSet.getValue());
        }
        if (attSet.getKey().equalsIgnoreCase("x")) {
          node.setX((float) attSet.getValue());
        }
        if (attSet.getKey().equalsIgnoreCase("y")) {
          node.setY((float) attSet.getValue());
        }
      }
      node.setSize((float) Double.parseDouble(map.get("size").toString()));
      node.setColor(new Color((float)Double.parseDouble(map.get("cR").toString()),
                              (float)Double.parseDouble(map.get("cG").toString()),
                              (float)Double.parseDouble(map.get("cB").toString())));
      graph.addNode(node);
    }
    for (Entry<Integer, JsonObject> enSet : eMap.entrySet()) {
      Map<String, Object> map = enSet.getValue().getMap();
      Edge edge = graphModel.factory().newEdge(map.get("id"),
          graph.getNode(map.get("source")), graph.getNode(map.get("target")),
          1, Double.parseDouble(map.get("weight").toString()), true);
      for (Entry<String, Object> attSet : map.entrySet()) {
        if (!attSet.getKey().equalsIgnoreCase("id")
            && !attSet.getKey().equalsIgnoreCase("source")
            && !attSet.getKey().equalsIgnoreCase("target")
            && !attSet.getKey().equalsIgnoreCase("size")
            && !attSet.getKey().equalsIgnoreCase("cR")
            && !attSet.getKey().equalsIgnoreCase("cG")
            && !attSet.getKey().equalsIgnoreCase("cB")) {
          edge.setAttribute(attSet.getKey(), attSet.getValue());
        }
      }
      edge.setWeight(Double.parseDouble(map.get("size").toString()));
      edge.setColor(new Color((float)Double.parseDouble(map.get("cR").toString()),
            (float)Double.parseDouble(map.get("cG").toString()),
            (float)Double.parseDouble(map.get("cB").toString())));
      graph.addEdge(edge);
    }

    return graph;
  }

}
