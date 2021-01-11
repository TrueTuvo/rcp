
package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.rcp.practice.zabara.jface.CreateNewPersonDialog;
import com.rcp.practice.zabara.parts.TableViewerPart;

/**
 * Handler, what will be use for creating new person. Opens a new Shell with new users form
 * 
 * @author SZabara
 *
 */
public class NewHandler {
    @Execute
    public void execute(EPartService partService) {
        MPart mPart = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
        TableViewerPart tableViewerPart = (TableViewerPart) mPart.getObject();
        new CreateNewPersonDialog(tableViewerPart.getTableViewerAdapter()).open();
    }

}