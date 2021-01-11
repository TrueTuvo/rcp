 
package com.rcp.practice.zabara.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import com.rcp.practice.zabara.jface.Person;
import com.rcp.practice.zabara.jface.Utils;
import com.rcp.practice.zabara.parts.CompositePart;
import com.rcp.practice.zabara.parts.TableViewerPart;
/**
 * Handler that canceling change in a person
 * @author SZabara
 *
 */
public class CancelHandler {
    
    @Inject
    ESelectionService selectionService;
    
	@Execute
	public void execute(EPartService partService) {
      MPart mPartT = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
      TableViewerPart tableViewerPart = (TableViewerPart) mPartT.getObject();
      
      MPart mPartC = (MPart) partService.findPart("com.rcp.practice.zabara.part.compositepart");
      CompositePart compositePart = (CompositePart) mPartC.getObject();

	    if (tableViewerPart.getTableViewerAdapter().getCurrentPerson() != null) {
          Utils.removeChangesPersonData(compositePart.getMainComposite(), (Person)selectionService.getSelection());
          tableViewerPart.getTableViewerAdapter().getViewer().refresh();
      }
	}
		
}