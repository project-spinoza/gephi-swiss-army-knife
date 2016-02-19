package org.projectspinoza.gephiswissarmyknife.graph;

import java.io.File;

import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.ProjectController;
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
  private ProjectController projectController;
  private Workspace workspace;

  public GraphImporter() {
    this.importController = Lookup.getDefault().lookup(ImportController.class);
    this.projectController = Lookup.getDefault().lookup(ProjectController.class);
    init();
  }

  public boolean importGraph(Container container, String graphFile,
      EdgeDefault edgesType) {

    try {

      File file = new File(graphFile);
      container = this.importController.importFile(file);
      container.getLoader().setEdgeDefault(edgesType);
      this.importController.process(container, new DefaultProcessor(),
          this.workspace);
      return (container.verify());

    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  private void init() {
    this.projectController.newProject();
    setWorkspace(this.projectController.getCurrentWorkspace());
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
