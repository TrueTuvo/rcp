 
package com.rcp.practice.zabara.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import com.rcp.practice.zabara.parts.CompositePart;
import com.rcp.practice.zabara.parts.Person;
import com.rcp.practice.zabara.parts.TableViewerPart;
import com.rcp.practice.zabara.parts.Utils;

public class CancelHandler {
    
    @Inject
    ESelectionService selectionService;
    
	@Execute
	public void execute(EPartService partService) {
      MPart tableviewerPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
      TableViewerPart tableViewerPart2 = (TableViewerPart) tableviewerPart.getObject();
      
      MPart mPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.compositepart");
      CompositePart compositePart = (CompositePart) mPart.getObject();

	    if (tableViewerPart2.getTableViewerAdapter().getCurrentPerson() != null) {
          Utils.removeChangesPersonData(compositePart.getMainComposite(), (Person)selectionService.getSelection());
          tableViewerPart2.getTableViewerAdapter().getViewer().refresh();
      }
	}
		
}