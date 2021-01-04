package com.rcp.practice.zabara.parts;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


/**
 * 
 * Every time, when user try to delete person, must confirm action in dialog window
 * 
 * @author SZabara
 */
public class DeletePersonDialog extends Dialog {

    private final TableViewerAdapter tableViewerAdapter;

    public DeletePersonDialog(TableViewerAdapter tableViewer) {
        super(Display.getCurrent().getActiveShell());
        this.tableViewerAdapter = tableViewer;
    }

    @Override
    protected Control createDialogArea(Composite parent) {

           Composite container = (Composite) super.createDialogArea(parent);
           Label label = new Label(container, SWT.FILL);
           label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
           label.setText(
                   String.format("Do you really want to delete %s person?", tableViewerAdapter.getCurrentPerson().getName()));

           return container;

    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Delete Person");

    }

    @Override
    protected void okPressed() {
        Person person = (Person) tableViewerAdapter.getViewer().getStructuredSelection().getFirstElement();
        tableViewerAdapter.delete(person);
        super.okPressed();
    }

    @Override
    protected Point getInitialSize() {
        return new Point(450, 300);
    }
}
