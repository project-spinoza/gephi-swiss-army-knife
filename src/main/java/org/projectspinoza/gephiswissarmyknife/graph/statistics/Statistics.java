package org.projectspinoza.gephiswissarmyknife.graph.statistics;

import org.gephi.graph.api.GraphModel;
import org.gephi.statistics.plugin.ClusteringCoefficient;
import org.gephi.statistics.plugin.ConnectedComponents;
import org.gephi.statistics.plugin.Degree;
import org.gephi.statistics.plugin.EigenvectorCentrality;
import org.gephi.statistics.plugin.GraphDensity;
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
  
  public Statistics() {}
  
  public void connectedComponents(){
    connectedComponents = new ConnectedComponents();
    connectedComponents.execute(this.graphModel);
  }
  
  public void avgClusterCoeficients(){
    clusteringCoefficient = new ClusteringCoefficient();
    clusteringCoefficient.execute(this.graphModel);
  }
  
  public void averageDegree(){
    degree = new Degree();
    degree.execute(this.graphModel);
  }
  
  public void eigenVectorCentrality(){
    eigenvectorCentrality = new EigenvectorCentrality();
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
    hits.execute(this.graphModel);
  }

  public void modularityClass (){
    modularity = new Modularity();
    modularity.execute(this.graphModel);
  }
  
  public void pageRank(){
    pagerank = new PageRank();
    pagerank.execute(this.graphModel);
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

}
