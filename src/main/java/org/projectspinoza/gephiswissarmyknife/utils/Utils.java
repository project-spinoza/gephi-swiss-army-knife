package org.projectspinoza.gephiswissarmyknife.utils;

import java.awt.Color;
import java.util.Map;

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
  public enum graphOperations {
    LOADGRAPH, FILTER
  }
  
  public static SigmaGraph toSigmaGraph(Graph graph, Map<String, Object> settings){
      SigmaGraph sigmaGraph = new SigmaGraph();
      createSigmaNodes(graph, sigmaGraph, settings);
      createSigmaEdges(graph, sigmaGraph, settings);
      log.debug("SigmaGraph[nodes: {}, edges: {}]", sigmaGraph.getNodes().size(), sigmaGraph.getEdges().size());
      return sigmaGraph;
  }
  
  private static void createSigmaNodes(Graph graph, SigmaGraph sigmaGraph, Map<String, Object> settings){
      String nodeSizeBy = settings.get("nsb").toString();
      Node[] nodeArray = graph.getNodes().toArray();
      for (int i = 0; i < nodeArray.length; i++) {

          Node n = nodeArray[i];
          String id = (String) n.getId();
          String label = (String) n.getLabel();
          double x = n.x();
          double y = n.y();
          double size = n.size();
              
          if (nodeSizeBy.equals("pr")) {
                  size = (Double) nodeArray[i].getAttribute("pagerank");
          } else if (nodeSizeBy.equals("exp_pr")) {
                  size = (Double) nodeArray[i].getAttribute("pagerank");
                  size = Math.exp(size);
          }
          String color = "rgb(" + (int) (n.r() * 255) + "," + (int) (n.g() * 255) + "," + (int) (n.b() * 255) + ")";
          
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
  
  private static void createSigmaEdges(Graph graph, SigmaGraph sigmaGraph, Map<String, Object> settings){
      EdgeColor colorMixer = new EdgeColor(EdgeColor.Mode.MIXED);
      Edge[] edgeArray = graph.getEdges().toArray();

      for (int i = 0; i < edgeArray.length; i++) {
          Edge e = edgeArray[i];
          String sourceId = (String) e.getSource().getId();
          String targetId = (String) e.getTarget().getId();

          SigmaEdge sigmaEdge = new SigmaEdge(sourceId, targetId);
          sigmaEdge.setSize(e.getWeight());
          String color = "rgb(" + (int) (e.r() * 255) + "," + (int) (e.g() * 255) + "," + (int) (e.b() * 255) + ")";
          
          if (settings.get("ecb").toString().equals("mix")) {
              if (e.r() == -1 || e.g() == -1 || e.b() == -1) {
                  Color result = colorMixer.getColor(null, e.getSource().getColor(), e.getTarget().getColor());
                  color = "rgb(" + result.getRed() + "," + result.getGreen() + "," + result.getBlue() + ")";
              }
          }
          sigmaEdge.setColor(color);
          sigmaGraph.addEdge(sigmaEdge);
      }
  }
}
