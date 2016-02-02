package org.projectspinoza.gephiswissarmyknife.server.requesthandlers;

import java.util.Map;

import org.projectspinoza.gephiswissarmyknife.graph.GephiGraph;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Rotation;
import org.projectspinoza.gephiswissarmyknife.graph.layouts.Scale;

import spark.Request;
import spark.Response;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LayoutsWrap {
	
	private Rotation layoutRotate;
	private Scale layoutScale;
	
	
	public LayoutsWrap() {

	}
	
	/*
	 * Initialize all layout with basic configurations
	 * */
	protected void init () {
		this.layoutRotate.setGraphModel(GephiGraph.getGraphModel());
		this.layoutScale.setGraphModel(GephiGraph.getGraphModel());
	}
	
	/*
	 * For stand alone rotation operations
	 * */
	public void applyLayout (String layoutIdStr, Map<String, String> layoutParams) {
		
		switch (layoutIdStr) {
		case "rotation":
			this.layoutRotate.setAngle(Double.parseDouble(layoutParams.get("angle")));
			this.layoutRotate.rotate();
			break;
		case "scale":
			this.layoutScale.setScale(Double.parseDouble(layoutParams.get("scale")));
			this.layoutScale.scale();
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


	public Scale getLayoutScale() {
		return layoutScale;
	}

	@Inject
	public void setLayoutScale(Scale layoutScale) {
		this.layoutScale = layoutScale;
	}
	
}
