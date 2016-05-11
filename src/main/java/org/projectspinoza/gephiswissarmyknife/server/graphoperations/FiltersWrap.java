package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;

import org.gephi.filters.api.Range;
import org.gephi.graph.api.GraphModel;
import org.projectspinoza.gephiswissarmyknife.graph.filters.Filters;

import com.google.inject.Inject;

public class FiltersWrap {

  private Filters filters;
  private GraphModel graphModel;
  
  public FiltersWrap() {
    
  }
  
  public void applyFilter (MultiMap params, boolean remove){

    filters.setGraphModel(this.graphModel);
    int lowerLmt,upperLmt;
    switch (params.get("filterId")) {
     case "edgeIdStr":
       if (params.contains("useregex")) {
         filters.edgeColFilter("id", params.get("edgeid"), true , remove);
       } else {
         filters.edgeColFilter("id", params.get("edgeid"), false , remove);
       }
       break;
     case "nodeIdStr":
       if (params.contains("useregex")) {
         filters.nodeColFilter("id", params.get("nodeid"), true , remove);
       } else {
         filters.nodeColFilter("id", params.get("nodeid"), false , remove);
       }
       break;
     case "edgeLabelStr":
       if (params.contains("useregex")) {
         filters.edgeColFilter("label", params.get("edgelabel"), true , remove);
       } else {
         filters.edgeColFilter("label", params.get("edgelabel"), false , remove);
       }
       break;
     case "nodeLabelStr":
       if (params.contains("useregex")) {
         filters.nodeColFilter("label", params.get("nodelabel"), true , remove);
       } else {
         filters.nodeColFilter("label", params.get("nodelabel"), false , remove);
       }
       break;
     case "weightFloatEdge":
       filters.edgeWeightBuilder(new Range(0d,Double.parseDouble(params.get("weightFloatEdge"))), remove);
       break;
     case "degreeRange":
       lowerLmt = Integer.parseInt(params.get("degreeRange").split(",")[0]);
       upperLmt = Integer.parseInt(params.get("degreeRange").split(",")[1]);
       filters.degreeRangeFilter(new Range(lowerLmt, upperLmt), remove);
       break;
     case "indegreeRange":
       lowerLmt = Integer.parseInt(params.get("inDegreerange").split(",")[0]);
       upperLmt = Integer.parseInt(params.get("inDegreerange").split(",")[1]);
       filters.inDegreeRangeFilter(new Range(lowerLmt, upperLmt), remove);
       break;
     case "outdegreeRange":
       lowerLmt = Integer.parseInt(params.get("outDegreerange").split(",")[0]);
       upperLmt = Integer.parseInt(params.get("outDegreerange").split(",")[1]);
       filters.outDegreeRangeFilter(new Range(lowerLmt, upperLmt), remove);
       break;
     case "egoNetwork":
       if (params.contains("withself_ego")){
         filters.egoFilter(params.get("node_id_ego"), true, Integer.parseInt(params.get("egoDepth").toString()));
       }else {
         filters.egoFilter(params.get("node_id_ego"), false, Integer.parseInt(params.get("egoDepth").toString()));
       }
       break;
     case "giantCompFilter":
       filters.giantComponentFilter(remove);
       break;
     }
  }

  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

  public Filters getFilters() {
    return filters;
  }

  @Inject
  public void setFilters(Filters filters) {
    this.filters = filters;
  }

}
