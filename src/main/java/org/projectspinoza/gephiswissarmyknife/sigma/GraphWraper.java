package org.projectspinoza.gephiswissarmyknife.sigma;

import java.util.HashSet;
import java.util.Map;

import org.gephi.graph.api.Graph;

import uk.ac.ox.oii.sigmaexporter.model.GraphElement;

import com.google.inject.ImplementedBy;

@ImplementedBy(SigmaGraph.class)
public interface GraphWraper {

  public void build(Graph graph, Map<String, Object> settings);

  public HashSet<GraphElement> getNodes();

  public HashSet<GraphElement> getEdges();

}
