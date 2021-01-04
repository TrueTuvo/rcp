package com.rcp.practice.zabara.parts;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.rcp.practice.zabara.composite.attachments.InputFields;
import com.rcp.practice.zabara.composite.attachments.SWTDoneCheckLine;


/**
 * 
 * Every time, when user try to add new person, must fill empty fields confirm action in dialog window
 * 
 * @author SZabara
 */
public class CreateNewPersonDialog extends Dialog {

    private TableViewerAdapter myTableViewer;

    private InputFields inputFields;

    private SWTDoneCheckLine swtDoneCheckLine;

    public CreateNewPersonDialog(TableViewerAdapter tableViewer) {
        super(Display.getCurrent().getActiveShell());
        this.myTableViewer = tableViewer;
    }

    @Override
    protected Control createDialogArea(Composite parent) {

        Composite container = (Composite) super.createDialogArea(parent);
        inputFields = new InputFields(container, SWT.NONE);
        swtDoneCheckLine = new SWTDoneCheckLine(container, SWT.NONE);

        return container;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Selection dialog");
    }

    @Override
    protected void okPressed() {

        String name = null;

        int group = 0;

        boolean swtDone = false;

        try {
            name = inputFields.getNameTextField().getText();
            group = Integer.parseInt(inputFields.getGroupTextField().getText());
            swtDone = swtDoneCheckLine.getSwtDoneButton().getSelection();
        } catch (NumberFormatException e) {
            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), "Incorrect input",
                    "Your input was empty. Please, put the correct data");
        }

        if (Utils.isValidData(name, group)) {
            Person person = new Person(name, group, swtDone);
            myTableViewer.add(person);
            super.okPressed();

        }
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}