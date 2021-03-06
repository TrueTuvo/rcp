package com.rcp.practice.zabara.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.rcp.practice.zabara.jface.ModelProvider;
import com.rcp.practice.zabara.jface.Person;
import com.rcp.practice.zabara.jface.Utils;
import com.rcp.practice.zabara.parts.CompositePart;
import com.rcp.practice.zabara.parts.TableViewerPart;

/**
 * Handler, that will be use for saving person to the tableViewer
 * 
 * @author SZabara
 *
 */
public class SaveHandler {

    @Inject
    ESelectionService selectionService;

    @CanExecute
    public boolean canExecute(EPartService partService) {
        if (partService != null) {
            if (selectionService.getSelection() != null) {
                return true;
            }
        }
        return false;
    }

    @Execute
    public void execute(EPartService partService) {
        String name = null;
        int group = 0;
        boolean swtDone = false;
        MPart mPartC = (MPart) partService.findPart("com.rcp.practice.zabara.part.compositepart");
        CompositePart compositePart = (CompositePart) mPartC.getObject();
        try {
            name = compositePart.getMainComposite().getNameTextField().getText();
            group = Integer.parseInt(compositePart.getMainComposite().getGroupTextField().getText());
            swtDone = compositePart.getMainComposite().getSwtCheckdone().getSelection();
        } catch (NumberFormatException ignore) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Incoorect input",
                    "Your input was incorrect. Please, put the correct data");
        }
        if (Utils.isValidData(name, group)) {
            Person selectionPerson = (Person) selectionService.getSelection();
            MPart mPartT = (MPart) partService.findPart("com.rcp.practice.zabara.part.tableviewer");
            TableViewerPart tableViewerPart = (TableViewerPart) mPartT.getObject();
            if (tableViewerPart.getTableViewerAdapter().getCurrentPerson() == null) {

                MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Information",
                        "Please, choose a row for saving data");
            } else {
                for (Person availablePerson : ModelProvider.INSTANCE.getPersons()) {
                    if (selectionPerson.equals(availablePerson)) {
                        Utils.updatePersonData(availablePerson, name, group, swtDone);
                        tableViewerPart.getTableViewerAdapter().getViewer().refresh();
                    }
                }
            }
        }
    }
}