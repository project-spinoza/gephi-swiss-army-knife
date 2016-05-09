package org.projectspinoza.gephiswissarmyknife.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.YifanHu;
import org.projectspinoza.gephiswissarmyknife.utils.DataImporter;

public class GraphGenerator {

  private GraphModel graphModel;
  
  Map<String, List<Object>> nodesMap;
  Map<String, List<Object>> edgesMap;
  
  public GraphGenerator() {
    nodesMap = new HashMap<String, List<Object>>();
    edgesMap = new HashMap<String, List<Object>>();
  }
  
  @SuppressWarnings("unchecked")
  public Graph createGraph() {
    List<String> tweets = null;
    try {
      tweets = (List<String>) new DataImporter().importGraphData();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // clear the existing graph
    this.graphModel.getGraph().clear();
    if (tweets == null || tweets.isEmpty()) {
      System.out.println("Graph cannot be generated. i.e. No data found related to given Keywords.!");
      return this.graphModel.getGraph(); 
    }
    for (String tweet : tweets) {
      List<String[]> edges = buildEdges(tweet);
      hashTagGraph(edges);
    }
    
    for (Entry<String, List<Object>> entity : nodesMap.entrySet()) {
      this.graphModel.getGraph().addNode((Node) entity.getValue().get(0));
    }
    for (Entry<String, List<Object>> entity : edgesMap.entrySet()) {
      this.graphModel.getGraph().addEdge((Edge) entity.getValue().get(0));
    }
    nodesMap.clear();
    edgesMap.clear();
    
    /*
     * Default Layout
     * 
     * */
    Map<String, String> yifanParams = new HashMap<String, String>();
    yifanParams.put("quadtreeMaxLevel", "10.0");
    yifanParams.put("theta", "1.2");
    yifanParams.put("optimalDistance", "100.0");
    yifanParams.put("relativeStrength", "0.2");
    yifanParams.put("initialStepSize", "20.0");
    yifanParams.put("stepRatio", "0.95");
    yifanParams.put("adaptiveCooling", "true");
    yifanParams.put("convergenceThreshold", "1.0E-4");
    // YifanLayout
    YifanHu yafinHuLayout = new YifanHu();
    yafinHuLayout.setGraphModel(this.getGraphModel());
    yafinHuLayout.applyLayout(yifanParams);
    
    return this.graphModel.getGraph();
  }
	
	private void hashTagGraph (List<String[]> nodes) {    
	  for (String[] node : nodes) {
	     
      node[0] =  node[0].trim().replaceAll("\\s+","").toLowerCase(); node[1] = node[1].trim().replaceAll("\\s+","").toLowerCase();
	    Node source = null, target = null; Edge edge = null;
	    
	    if (nodesMap.containsKey(node[0])) {
	      List <Object> list = nodesMap.get(node[0]);
	      source = (Node) list.get(0);
        float size = (float) (Double.parseDouble(list.get(1).toString())+1);
	      source.setSize(size);
	      list.add(0,source);
	      list.add(1,size);
	      nodesMap.put(node[0], list);
	    }else {
	       source = graphModel.factory().newNode(node[0]);
	       source.setLabel(node[0]);
         source.setX((float) (Math.random() * ( 200 - 0 )));
         source.setY((float) (Math.random() * ( 200 - 0 )));
	       source.setSize(3f);
	       List <Object> list = new ArrayList<Object>();
	       list.add(0,source);
	       list.add(1,3);
	       nodesMap.put(node[0], list);
	    }
	    
      if (nodesMap.containsKey(node[1])) {
        List <Object> list = nodesMap.get(node[1]);
        target = (Node) list.get(0);
        float size = (float) (Double.parseDouble(list.get(1).toString())+1);
        target.setSize(size);
        list.add(0,target);
        list.add(1,size);
        nodesMap.put(node[1], list);
      }else {
        target = graphModel.factory().newNode(node[1]);
        target.setLabel(node[1]);
        target.setX((float) (Math.random() * ( 200 - 0 )));
        target.setY((float) (Math.random() * ( 200 - 0 )));
        target.setSize(3f);
        List <Object> list = new ArrayList<Object>();
        list.add(0,target);
        list.add(1,3);
        nodesMap.put(node[1], list);
     }
      if (edgesMap.containsKey(node[0]+"-"+node[1])) {
        List <Object> list = edgesMap.get(node[0]+"-"+node[1]);
        edge = (Edge) list.get(0);
        Double weight = Double.parseDouble(list.get(1).toString())+0.5;
        edge.setWeight(weight);
        list.add(0,edge);
        list.add(1,weight);
        edgesMap.put(node[0]+"-"+node[1], list);
      }else {
        edge = graphModel.factory().newEdge(source, target,1, true);
        edge.setLabel(node[0]+"-"+node[1]);
        edge.setWeight(1f);
        List <Object> list = new ArrayList<Object>();
        list.add(0,edge);
        list.add(1,1);
        edgesMap.put(node[0]+"-"+node[1], list);
     }
	  }
	}

	private List<String[]> buildEdges(String tweet) {

		List<String[]> edges = new ArrayList<String[]>();

		String parts[] = tweet.replaceAll("/[^A-Za-z0-9 #]/", " ")
				.replace("\n", " ").replace("\r", " ")
				.replaceAll("\\P{Print}", " ").split(" ");

		Set<String> tags = new HashSet<String>();

		for (String part : parts) {
			part = part.trim();
			if (part.length() < 2)
				continue;
			if (part.equals("#rt"))
				continue;

			if (part.startsWith("#")) {
				// . splits hashtags of type: #tag1#tag2...
				if ((part.length() - part.replace("#", "").length()) > 1) {
					String[] subParts = part.split("#");
					for (String sb : subParts) {
						sb = sb.replaceAll("[^a-zA-Z0-9_-]", "").trim();
						sb = sb.replace("\\s", "");
						if (sb.length() > 1) {
							tags.add(sb);
						}
					}
					continue;
				}
				part = part.replaceAll("[^a-zA-Z0-9_-]", "").trim();
				part = part.replace("\\s", "");
				if (part.length() > 1) {
					tags.add(part);
				}
			}
		}

		List<String> taglist = new ArrayList<String>();
		taglist.addAll(tags);
		Collections.sort(taglist);
		if (taglist.size() < 2)
			return edges;

		for (int i = 0; i < taglist.size() - 1; i++) {
			for (int j = i + 1; j < taglist.size(); j++) {
				String edge[] = new String[2];
				edge[0] = taglist.get(i);
				edge[1] = taglist.get(j);
				edges.add(edge);
			}
		}

		return edges;
	}
	
  public GraphModel getGraphModel() {
    return graphModel;
  }

  public void setGraphModel(GraphModel graphModel) {
    this.graphModel = graphModel;
  }

}
