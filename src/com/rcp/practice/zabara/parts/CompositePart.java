 
package com.rcp.practice.zabara.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import com.rcp.practice.zabara.composite.attachments.MainComposite;



@SuppressWarnings("restriction")
public class CompositePart {
    private MainComposite mainComposite;
    
    public MainComposite getMainComposite() {
        return mainComposite;
    }

    @Inject
    ESelectionService selectionService;
    @SuppressWarnings("restriction")
    ECommandService commandService;
    @SuppressWarnings("restriction")
    EHandlerService handleService;
	
	@PostConstruct
	public void postConstruct(Composite parent) {
	     mainComposite = new MainComposite(parent, SWT.NONE); 
	    Person person = (Person) selectionService.getSelection();
	    if (person != null) {
	        mainComposite.getNameTextField().setText(person.getName());
	        mainComposite.getGroupTextField().setText(String.valueOf(person.getGroup()));
	         mainComposite.getSwtCheckdone().setSelection(person.isSwtDone());
	         
	    }
	    mainComposite.getNewButton().addSelectionListener(new SelectionAdapter() {
          @SuppressWarnings("restriction")
        @Override
          public void widgetSelected(SelectionEvent e) {
              try {
                  ParameterizedCommand cmd =
                          commandService.createCommand("com.rcp.practice.zabara.command.new");
                  
                  handleService.executeHandler(cmd);
              } catch (Exception ex) {
                  throw new RuntimeException("command with id \"com.rcp.practice.zabara.command.new\" not found");
              }

          }
      });


		
	}
	
	
}