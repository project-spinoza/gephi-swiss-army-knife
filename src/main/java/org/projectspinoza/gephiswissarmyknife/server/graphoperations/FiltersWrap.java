package org.projectspinoza.gephiswissarmyknife.server.graphoperations;

import io.vertx.core.MultiMap;

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
