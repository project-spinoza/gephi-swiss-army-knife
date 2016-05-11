package org.projectspinoza.gephiswissarmyknife.graph.statistics;

import java.util.Arrays;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.ConnectedComponents;
import org.gephi.statistics.plugin.Degree;
import org.gephi.statistics.plugin.EigenvectorCentrality;
import org.gephi.statistics.plugin.GraphDensity;
import org.gephi.statistics.plugin.GraphDistance;
import org.gephi.statistics.plugin.Hits;
import org.gephi.statistics.plugin.Modularity;
import org.gephi.statistics.plugin.PageRank;
import org.gephi.statistics.plugin.WeightedDegree;

import com.google.inject.Singleton;

@Singleton
public class Statistics {
  
  private GraphModel graphModel;
  private ConnectedComponents connectedComponents;
  private ClusteringCoefficient clusteringCoefficient;
  private Degree degree;
  private EigenvectorCentrality eigenvectorCentrality;
  private GraphDensity graphDensity;
  private WeightedDegree weightedDegree;
  private Hits hits;
  private Modularity modularity;
  private PageRank pagerank;
  private GraphDistance graphDistance;
  
  
  public Statistics() {}
  
  public void graphDistance () {
    graphDistance = new GraphDistance();
    graphDistance.execute(this.graphModel);
  }
  
  public void connectedComponents(){
    connectedComponents = new ConnectedComponents();
    connectedComponents.execute(this.graphModel);
  }
  
  public void avgClusterCoeficients(){
    clusteringCoefficient = new ClusteringCoefficient();
    clusteringCoefficient.setDirected(false);
    clusteringCoefficient.execute(this.graphModel);
  }
  
  public void averageDegree(){
    degree = new Degree();
    degree.execute(this.graphModel);
  }
  
  public void eigenVectorCentrality(){
    eigenvectorCentrality = new EigenvectorCentrality();
    eigenvectorCentrality.setNumRuns(100);
    eigenvectorCentrality.execute(this.graphModel);
  }
  
  public double graphDensity(){
    graphDensity = new GraphDensity();
    graphDensity.execute(this.graphModel);
    return graphDensity.getDensity();
  }
  
  public void averageWeightedDegree(){
    weightedDegree = new WeightedDegree();
    weightedDegree.execute(this.graphModel);
  }
  
  public void calculateHits(){
    hits = new Hits();
    hits.setEpsilon(1.0E-4);
    hits.execute(this.graphModel);
  }

  public void modularityClass (){
    modularity = new Modularity();
    modularity.setRandom(true);
    modularity.setUseWeight(true);
    modularity.setResolution(1.0);
    modularity.execute(this.graphModel);
  }
  
  public void pageRank(){
    pagerank = new PageRank();
    pagerank.setEpsilon(0.001);
    pagerank.setProbability(0.85);
    pagerank.setUseEdgeWeight(false);
    pagerank.execute(this.graphModel);
  }
  
  public String calculateMaxDegrees (){
    averageDegree();
    Graph graph = this.graphModel.getGraph();
    DirectedGraph dgraph = (DirectedGraph) graph;
    
    
    double[] degree = new double[graph.getNodeCount()];
    double[] indegree = new double[graph.getNodeCount()];
    double[] outdegree = new double[graph.getNodeCount()];
    int mdegree = 0;
   
    int counter = 0;
    
    NodeIterable nodes = dgraph.getNodes();
    for (Node node : nodes){
      degree[counter] = Double.parseDouble(node.getAttribute("degree").toString());
      indegree[counter] = Double.parseDouble(node.getAttribute("indegree").toString());
      outdegree[counter] = Double.parseDouble(node.getAttribute("outdegree").toString());
      for (Edge e : dgraph.getOutEdges(node)) {
        if (dgraph.getMutualEdge(e) != null) {
          mdegree++;
        }
      }
      counter++;
    }
    Arrays.sort(degree);
    Arrays.sort(indegree);
    Arrays.sort(outdegree);
    
    String respJson = "{\"degree\":"+"\""+(int)degree[0]+","+(int)degree[degree.length-1]+"\","+
                        "\"indegree\":"+"\""+(int)indegree[0]+","+(int)indegree[indegree.length-1]+"\","+
                        "\"outdegree\":"+"\""+(int)outdegree[0]+","+(int)outdegree[outdegree.length-1]+"\","+
                        "\"mutualdegree\":"+"\""+0+","+mdegree+
                        "\"}";
    return respJson;
  }
  
  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }
  
  public ConnectedComponents getConnectedComponents() {
    return connectedComponents;
  }

  public void setConnectedComponents(ConnectedComponents connectedComponents) {
    this.connectedComponents = connectedComponents;
  }

  public ClusteringCoefficient getClusteringCoefficient() {
    return clusteringCoefficient;
  }

  public void setClusteringCoefficient(ClusteringCoefficient clusteringCoefficient) {
    this.clusteringCoefficient = clusteringCoefficient;
  }

  public Degree getDegree() {
    return degree;
  }

  public void setDegree(Degree degree) {
    this.degree = degree;
  }

  public EigenvectorCentrality getEigenvectorCentrality() {
    return eigenvectorCentrality;
  }

  public void setEigenvectorCentrality(EigenvectorCentrality eigenvectorCentrality) {
    this.eigenvectorCentrality = eigenvectorCentrality;
  }

  public GraphDensity getGraphDensity() {
    return graphDensity;
  }

  public void setGraphDensity(GraphDensity graphDensity) {
    this.graphDensity = graphDensity;
  }

  public WeightedDegree getWeightedDegree() {
    return weightedDegree;
  }

  public void setWeightedDegree(WeightedDegree weightedDegree) {
    this.weightedDegree = weightedDegree;
  }

  public Hits getHits() {
    return hits;
  }

  public void setHits(Hits hits) {
    this.hits = hits;
  }

  public Modularity getModularity() {
    return modularity;
  }

  public void setModularity(Modularity modularity) {
    this.modularity = modularity;
  }

  public PageRank getPagerank() {
    return pagerank;
  }

  public void setPagerank(PageRank pagerank) {
    this.pagerank = pagerank;
  }

  public GraphDistance getGraphDistance() {
    return graphDistance;
  }

  public void setGraphDistance(GraphDistance graphDistance) {
    this.graphDistance = graphDistance;
  }

}
