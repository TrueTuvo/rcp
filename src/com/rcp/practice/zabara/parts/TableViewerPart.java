package com.rcp.practice.zabara.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.UIEvents.Part;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;

import com.rcp.practice.zabara.composite.attachments.MainComposite;

public class TableViewerPart {

    private TableViewerAdapter tableViewerAdapter;
    
    public TableViewerAdapter getTableViewerAdapter() {
        return tableViewerAdapter;
    }

    @Inject
    ESelectionService selectionService;

    @PostConstruct
    public void createComposite(Composite parent, EModelService service, MApplication application) {
        tableViewerAdapter = new TableViewerAdapter();
        tableViewerAdapter.createPartControl(parent);
        tableViewerAdapter.getViewer().addSelectionChangedListener(event -> {
            IStructuredSelection selection = tableViewerAdapter.getViewer().getStructuredSelection();
            selectionService.setSelection(selection.getFirstElement());  
            Person selectedPerson = (Person)selectionService.getSelection();
            MPart part = (MPart) service.find("com.rcp.practice.zabara.part.compositepart",  application);
            CompositePart compositePart = (CompositePart) part.getObject();
            MainComposite mainComposite = compositePart.getMainComposite();
            mainComposite.getNameTextField().setText(selectedPerson.getName());
            mainComposite.getGroupTextField().setText(String.valueOf(selectedPerson.getGroup()));
            mainComposite.getSwtCheckdone().setSelection(selectedPerson.isSwtDone());

        });
    }

    @Focus
    public void setFocus() {
        tableViewerAdapter.getViewer().getTable().setFocus();
    }

}