 
package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.rcp.practice.zabara.parts.CreateNewPersonDialog;
import com.rcp.practice.zabara.parts.TableViewerPart;

public class NewHandler {
	@Execute
	public void execute(EPartService partService) {
	    MPart tableviewerPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
      TableViewerPart tableViewerPart2 = (TableViewerPart) tableviewerPart.getObject();
	    new CreateNewPersonDialog(tableViewerPart2.getTableViewerAdapter()).open();
	}
		
}