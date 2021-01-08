package com.rcp.practice.zabara.parts;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
public class AboutApplicationDialog extends MessageDialog {


    public AboutApplicationDialog() {
        super(Display.getCurrent().getActiveShell(), "About", Utils.getImageByPath(), "My message", MessageDialog.INFORMATION, new String[] {"Ok"},0);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
           parent.setLayout(new GridLayout(2,false));
           Label label = new Label(parent, SWT.LEFT);
           Image image= Utils.getImageByPath();
           
           label.setImage(image);
           label = new Label(parent, SWT.LEFT);
           label.setText("The  product is demo" + System.lineSeparator() + "(c) 2013");        
           return parent;

    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("About");
        Display display = Display.getCurrent();
        Rectangle screenSize = display.getPrimaryMonitor().getBounds();
        newShell.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
        newShell.setLayout(new FillLayout(SWT.VERTICAL));
        newShell.setLocation((screenSize.width - newShell.getBounds().width) / 2,
                (screenSize.height - newShell.getBounds().height) / 2);

    }

    @Override
    protected void okPressed() {

        super.okPressed();
    }

    @Override
    protected Point getInitialSize() {
        return new Point(300,240);
    }
}
