package org.projectspinoza.gephiswissarmyknife.graph;

import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.openide.util.Lookup;

import com.google.inject.Inject;

/*
 * Gets Graph Model for the data 
 * imported by GraphImporter.class
 * 
 * */

public class GephiGraph {

  private static GraphModel graphModel;
  private GraphImporter graphImporter;
  private Container container;
  private Graph graph;


  public GephiGraph() {
    this.setGraphModel(Lookup.getDefault().lookup(GraphController.class).getGraphModel());
    this.getGraphImporter();
  }

  /*
   * Loads graph from specified file/source after successful loading graphModel
   * and Container values are set and can be retrieved using getter setter
   */

  public Graph loadGraph(String graphFile, EdgeDirectionDefault edgeType) {

    boolean graphImported = false;

    switch (edgeType) {
    case DIRECTED:
      graphImported = this.graphImporter.importGraph(this.container, graphFile,EdgeDirectionDefault.DIRECTED);
      this.graph = (graphImported == true) ? graphModel.getDirectedGraph(): null;
      System.out.println("Nodes: " + graph.getNodeCount());
      System.out.println("Edges: " + graph.getEdgeCount());

      break;

    case UNDIRECTED:
      graphImported = this.graphImporter.importGraph(this.container, graphFile,
          EdgeDirectionDefault.UNDIRECTED);
      this.graph = (graphImported == true) ? graphModel.getUndirectedGraph()
          : null;
      break;

    case MIXED:
      graphImported = this.graphImporter.importGraph(this.container, graphFile,
          EdgeDirectionDefault.MIXED);
      this.graph = (graphImported == true) ? graphModel.getGraph() : null;
      break;
    default:
      break;
    }
    return this.graph;
  }

  public Container getContainer() {
    return container;
  }

  public void setContainer(Container container) {
    this.container = container;
  }

  public GraphImporter getGraphImporter() {
    return graphImporter;
  }

  @Inject
  public void setGraphImporter(GraphImporter graphImporter) {
    this.graphImporter = graphImporter;
  }

  public static GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    GephiGraph.graphModel = graphModel;
  }

  public Graph getGraph() {
    return graph;
  }

  public void setGraph(Graph graph) {
    this.graph = graph;
  }
}
