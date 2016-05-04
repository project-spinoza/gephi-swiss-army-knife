package org.projectspinoza.gephiswissarmyknife.utils;

import io.vertx.core.json.JsonObject;

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

  public GraphBackup() {
    if (nodesMap == null && edgesMap == null) {
      nodesMap = new HashMap<Integer, JsonObject>();
      edgesMap = new HashMap<Integer, JsonObject>();
    }
  }

  public void saveGraph(Graph graph) {

    int Counter = 0;
    JsonObject attrJson;

    //clear existing graph
    nodesMap.clear();
    edgesMap.clear();
    
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
      nodesMap.put(Counter++, attrJson);
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
      edgesMap.put(Counter++, attrJson);
    }
  }

  public Graph retrieveGraph(GraphModel graphModel) {
    Graph graph = graphModel.getGraph();

    graph.clear();

    for (Entry<Integer, JsonObject> enSet : nodesMap.entrySet()) {
      Node node = graphModel.factory().newNode(enSet.getValue().getValue("id"));
      for (Entry<String, Object> attSet : enSet.getValue().getMap().entrySet()) {
        if (!attSet.getKey().equalsIgnoreCase("id")
            && !attSet.getKey().equalsIgnoreCase("x")
            && !attSet.getKey().equalsIgnoreCase("y")) {
          node.setAttribute(attSet.getKey(), attSet.getValue());
        }
        if (attSet.getKey().equalsIgnoreCase("x")) {
          node.setX((float) attSet.getValue());
        }
        if (attSet.getKey().equalsIgnoreCase("y")) {
          node.setY((float) attSet.getValue());
        }
      }
      graph.addNode(node);
    }
    for (Entry<Integer, JsonObject> enSet : edgesMap.entrySet()) {
      Map<String, Object> map = enSet.getValue().getMap();

      Edge edge = graphModel.factory().newEdge(map.get("id"),
          graph.getNode(map.get("source")), graph.getNode(map.get("target")),
          1, Double.parseDouble(map.get("weight").toString()), true);
      for (Entry<String, Object> attSet : map.entrySet()) {
        if (!attSet.getKey().equalsIgnoreCase("id")
            && !attSet.getKey().equalsIgnoreCase("source")
            && !attSet.getKey().equalsIgnoreCase("target")
            && !attSet.getKey().equalsIgnoreCase("size")) {
          edge.setAttribute(attSet.getKey(), attSet.getValue());
        }
      }
      graph.addEdge(edge);
    }

    return graph;
  }

}
