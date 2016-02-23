package org.projectspinoza.gephiswissarmyknife.sigma.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.gephi.graph.api.Attributes;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;

public class SigmaGraph implements Serializable{
    
    private static final long serialVersionUID = -6468681022457314329L;
    
    private Set<SigmaNode> nodes;
    private Set<SigmaEdge> edges;
    
    public SigmaGraph(){
        this.nodes = new HashSet<SigmaNode>();
        this.edges = new HashSet<SigmaEdge>();
    }
    
    public static SigmaGraph buildGraph (Graph graph){
      
      Node[] nodes = graph.getNodes().toArray();
      
      SigmaNode sigmaNode;
      
      for (Node node : nodes) {
        sigmaNode = new SigmaNode(node.getId()+"");
        
        
        
      }
      
      return null;
    }
    
    
    
    
    public Set<SigmaNode> getNodes() {
        return nodes;
    }
    public void setNodes(Set<SigmaNode> nodes) {
        this.nodes = nodes;
    }
    public Set<SigmaEdge> getEdges() {
        return edges;
    }
    public void setEdges(Set<SigmaEdge> edges) {
        this.edges = edges;
    }
    public SigmaNode getNode(String nodeId){
        SigmaNode other = new SigmaNode(nodeId);
        for(SigmaNode node : nodes){
            if(node.equals(other)){
                return node;
            }
        }
        return null;
    }
    public SigmaEdge getEdge(String source, String target){
        for(SigmaEdge edge : edges){
            if(edge.getSource().equals(source) && edge.getTarget().equals(target)){
                return edge;
            }
        }
        return null;
    }
    public boolean addNode(SigmaNode node){
        this.nodes.add(node);
        return true;
    }
    public boolean addEdge(SigmaEdge edge){
        this.edges.add(edge);
        return true;
    }
    
    @Override
    public String toString() {
        return "SigmaGraph [nodes=" + nodes + ", edges=" + edges + "]";
    }
}
