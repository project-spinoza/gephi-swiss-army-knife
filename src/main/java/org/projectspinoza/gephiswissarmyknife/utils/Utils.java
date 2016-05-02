package org.projectspinoza.gephiswissarmyknife.utils;

import io.vertx.core.MultiMap;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.preview.types.EdgeColor;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaEdge;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaGraph;
import org.projectspinoza.gephiswissarmyknife.sigma.model.SigmaNode;

public class Utils {
  private static Logger log = LogManager.getLogger(Utils.class);
  
  public static SigmaGraph toSigmaGraph(Graph graph){
      SigmaGraph sigmaGraph = new SigmaGraph();
      createSigmaNodes(graph, sigmaGraph);
      createSigmaEdges(graph, sigmaGraph);
      log.debug("SigmaGraph[nodes: {}, edges: {}]", sigmaGraph.getNodes().size(), sigmaGraph.getEdges().size());
      return sigmaGraph;
  }
  
  private static void createSigmaNodes(Graph graph, SigmaGraph sigmaGraph){
//      String nodeSizeBy = settings.get("nsb").toString();
      Node[] nodeArray = graph.getNodes().toArray();      
      for (int i = 0; i < nodeArray.length; i++) {

          Node n = nodeArray[i];
          String id = (String) n.getId();
          String label = (String) n.getLabel();
          double x = n.x();
          double y = n.y();
          double size = n.size()+10;
          
          int R = (int)(Math.random()*256);
          int G = (int)(Math.random()*256);
          int B= (int)(Math.random()*256);
          String color = "rgb(" + R + "," + G + "," + B + ")";
          
          /*
           * Skipping origin nodes colors
           * 
           * */
//          String color = "rgb(" + (int) (n.r()) + "," + (int) (n.g()) + "," + (int) (n.b()) + ")";
          
          SigmaNode sigmaNode = new SigmaNode(id);
          sigmaNode.setLabel(label);
          sigmaNode.setX(x);
          sigmaNode.setY(y);
          sigmaNode.setSize(size);
          sigmaNode.setColor(color);

          for (String attrKey : n.getAttributeKeys()) {
              Object attrValue = n.getAttribute(attrKey);
              sigmaNode.addAttribute(attrKey, attrValue);
              if(attrValue == null){continue;}
              if(attrKey.equalsIgnoreCase("Id") || attrKey.equalsIgnoreCase("Label") || attrKey.equalsIgnoreCase("uid")){
                  continue;
              }
              sigmaNode.addAttribute(attrKey, attrValue);
          }
          sigmaGraph.addNode(sigmaNode);
      }
  } 
  
  private static void createSigmaEdges(Graph graph, SigmaGraph sigmaGraph){
      EdgeColor colorMixer = new EdgeColor(EdgeColor.Mode.MIXED);
      Edge[] edgeArray = graph.getEdges().toArray();

      for (int i = 0; i < edgeArray.length; i++) {
          Edge e = edgeArray[i];
          String sourceId = (String) e.getSource().getId();
          String targetId = (String) e.getTarget().getId();

          SigmaEdge sigmaEdge = new SigmaEdge(sourceId, targetId);
          sigmaEdge.setSize(e.getWeight());
          sigmaEdge.setLabel(e.getLabel());
          /*
           * To be used when graph setting need to be passed from UI
           * 
           * */
//        String color = "rgb(" + (int) (e.r()) + "," + (int) (e.g()) + "," + (int) (e.b()) + ")";

          
          /*
           * Temp edge colors
           * */
           String color = "rgb(" + 205 + "," + 220 + "," + 213 + ")";
       
              if (e.r() == -1 || e.g() == -1 || e.b() == -1) {
                  Color result = colorMixer.getColor(null, e.getSource().getColor(), e.getTarget().getColor());
                  color = "rgb(" + result.getRed() + "," + result.getGreen() + "," + result.getBlue() + ")";
              }
          sigmaEdge.setColor(color);
          sigmaGraph.addEdge(sigmaEdge);
      }
  }
  
  public static Map<String, String> multiMapToHashMap (MultiMap mMap) {
    Map <String, String> map = new HashMap<String, String>();
    for (Entry<String, String> entity : mMap.entries()) {
      map.put(entity.getKey(), entity.getValue());
    }
    return map;
  }
}
