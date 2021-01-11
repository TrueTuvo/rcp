 
package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.rcp.practice.zabara.jface.DeletePersonDialog;
import com.rcp.practice.zabara.parts.TableViewerPart;

/**
 * Handler, that removes Person from app.
 * @author SZabara
 *
 */
public class DeleteHendler {
	@Execute
	public void execute(EPartService partService) {
	    MPart mPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
      TableViewerPart tableViewerPart = (TableViewerPart) mPart.getObject();
      if (tableViewerPart.getTableViewerAdapter().getCurrentPerson() != null) {
      new DeletePersonDialog(tableViewerPart.getTableViewerAdapter()).open();
      }
	}	
}