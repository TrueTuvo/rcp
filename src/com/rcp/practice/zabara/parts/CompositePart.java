 
package com.rcp.practice.zabara.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.rcp.practice.zabara.composite.attachments.MainComposite;
import com.rcp.practice.zabara.handlers.NewHandler;



public class CompositePart {
    private MainComposite mainComposite;
    
    public MainComposite getMainComposite() {
        return mainComposite;
    }

    @Inject
    ESelectionService selectionService;
	
	@PostConstruct
	public void postConstruct(Composite parent, ECommandService commandService, EHandlerService handleService) {
	     mainComposite = new MainComposite(parent, SWT.NONE); 
	    Person person = (Person) selectionService.getSelection();
	    if (person != null) {
	        mainComposite.getNameTextField().setText(person.getName());
	        mainComposite.getGroupTextField().setText(String.valueOf(person.getGroup()));
	         mainComposite.getSwtCheckdone().setSelection(person.isSwtDone());
	         
	    }
	    mainComposite.getNewButton().addSelectionListener(new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
              ParameterizedCommand cmd =
                      commandService.createCommand("com.rcp.practice.zabara.command.new", null);

                  handleService.executeHandler(cmd);
          
          }
      });
	    mainComposite.getSaveButton().addSelectionListener(new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
              ParameterizedCommand cmd =
                      commandService.createCommand("org.eclipse.ui.file.save", null);

                  handleService.executeHandler(cmd);
          
          }
      });

      mainComposite.getResetButton().addSelectionListener(new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
              ParameterizedCommand cmd =
                      commandService.createCommand("com.rcp.practice.zabara.command.cancel", null);

                  handleService.executeHandler(cmd);
          
          }
      });
      
      mainComposite.getDeleteButton().addSelectionListener(new SelectionAdapter() {
          public void widgetSelected(SelectionEvent e) {
              ParameterizedCommand cmd =
                      commandService.createCommand("com.rcp.practice.zabara.command.delete", null);

                  handleService.executeHandler(cmd);
          
          }
      });
	}
}