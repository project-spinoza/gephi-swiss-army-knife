package org.projectspinoza.gephiswissarmyknife.graph;

import java.io.File;

import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import com.google.inject.Singleton;

/*
 * Import data from standard gephi graph fromats
 * e.g. .gml, .dot, .json, .gdf etc.
 * 
 * */

@Singleton
public class GraphImporter {

  private ImportController importController;
  private Workspace workspace;

  public GraphImporter() {
     this.importController = Lookup.getDefault().lookup(ImportController.class);
  }

  public boolean importGraph(Container container, String graphFile, EdgeDirectionDefault edgeType) {

    try {

      File file = new File(graphFile);
      container = this.importController.importFile(file);
      container.getLoader().setEdgeDefault(edgeType);
      this.importController.process(container, new DefaultProcessor(),
          this.workspace);
      return (container.verify());

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  public ImportController getImportController() {
    return importController;
  }

  public void setImportController(ImportController importController) {
    this.importController = importController;
  }

  public Workspace getWorkspace() {
    return workspace;
  }

  public void setWorkspace(Workspace workspace) {
    this.workspace = workspace;
  }

}
