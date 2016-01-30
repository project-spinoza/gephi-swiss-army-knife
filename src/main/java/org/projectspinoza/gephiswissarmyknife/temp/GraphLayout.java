package org.projectspinoza.gephiswissarmyknife.temp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;


public class GraphLayout {
	
	private static Logger log = LogManager.getLogger(GraphLayout.class);
	
	public GraphLayout() {
	}
	
	public void applayLayouts(GraphModel graphModel, String layout){
		
		String layoutAlgorithmName = layout;
		if (layoutAlgorithmName.equals("YifanHuLayout")) {
			log.info("\nLayout: YifanHuLayout");
			yifanHuLayout(graphModel);
			
		} else if (layoutAlgorithmName.equals("ForceAtlasLayout")) {
			log.info("\nLayout: ForceAtlasLayout");
			forceAtlasLayout(graphModel);
			
		} else if (layoutAlgorithmName.equals("FruchtermanReingold")) {
			
			log.info("\nLayout: FruchtermanReingold");
			fruchtermanReingoldLayout(graphModel);
		}
	}
	public void yifanHuLayout (GraphModel graphModel){
		
		YifanHuLayout layout = new YifanHuLayout(null,new StepDisplacement(1f));
		
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();
		layout.setOptimalDistance((float)260);
		layout.initAlgo();
		
		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
			layout.goAlgo();
		}
		
		layout.endAlgo();
	}
	
	public void forceAtlasLayout (GraphModel graphModel){
	
		ForceAtlasLayout layout = new ForceAtlasLayout(null);
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();

		layout.setSpeed(1.0);
		layout.setConverged(true);
		layout.setInertia(0.1);
		layout.setGravity(1.0);
		layout.setMaxDisplacement(10.0);

		layout.initAlgo();
		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
			layout.goAlgo();
		}
		layout.endAlgo();
	}
	
	public void fruchtermanReingoldLayout (GraphModel graphModel){
		
		FruchtermanReingold layout = new FruchtermanReingold(null);
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();

		layout.setArea((float)1000.0);
		layout.setSpeed(1.0);
		layout.setGravity(10.0);

		layout.initAlgo();
		
		for (int i = 0; i < 100 && layout.canAlgo(); i++) {
			layout.goAlgo();
		}
		
		layout.endAlgo();
	}

}
