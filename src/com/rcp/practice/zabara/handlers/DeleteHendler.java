 
package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.rcp.practice.zabara.parts.DeletePersonDialog;
import com.rcp.practice.zabara.parts.TableViewerPart;

public class DeleteHendler {
	@Execute
	public void execute(EPartService partService) {
	    MPart tableviewerPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
      TableViewerPart tableViewerPart2 = (TableViewerPart) tableviewerPart.getObject();
      if (tableViewerPart2.getTableViewerAdapter().getCurrentPerson() != null) {
      new DeletePersonDialog(tableViewerPart2.getTableViewerAdapter()).open();
      }
	}
		
}