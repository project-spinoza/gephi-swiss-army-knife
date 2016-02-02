package org.projectspinoza.gephiswissarmyknife.server.requesthandlers;

import java.util.Map;

import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Rotation;

import spark.Request;
import spark.Response;

import com.google.inject.Inject;

public class LayoutsWrap {
	
	public static enum LayoutsId {
		LAYOUT_ROTATE,
	}
	
	Rotation layoutRotate;
	
	public LayoutsWrap() {}
	
	
	/*
	 * Initialize all layout with basic configurations
	 * */
	protected void init () {
		this.layoutRotate.setGraphModel(GephiGraph.getGraphModel());
	}

	/*
	 * if request is forwarded from Spark server
	 * */
	public void applyLayout (Request request, Response response){
		
		request.queryParams("layout");
		switch (request.queryParams("layout")) {
		case "rotation":
			this.layoutRotate.setAngle(Double.parseDouble(request.queryParams("angle").toString()));
			this.layoutRotate.rotate();
			break;
		default:
			break;
		}
	}
	
	
	/*
	 * For stand alone rotation operations
	 * */
	public void applyLayout (LayoutsId layoutId, Map<String, Object> layoutParams) {
		
		switch (layoutId) {
		case LAYOUT_ROTATE:
			this.layoutRotate.setAngle(Double.parseDouble(layoutParams.get("angle").toString()));
			this.layoutRotate.rotate();
			break;
			
		default:
			break;
		}
	}

	public Rotation getLayoutRotate() {
		return layoutRotate;
	}

	@Inject
	public void setLayoutRotate(Rotation layoutRotate) {
		this.layoutRotate = layoutRotate;
	}
	
}
