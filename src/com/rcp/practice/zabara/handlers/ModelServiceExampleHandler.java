package com.rcp.practice.zabara.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

import com.rcp.practice.zabara.parts.CompositePart;

public class ModelServiceExampleHandler {

    @Execute
    public void execute(MApplication application, EModelService service) {
        
        List<CompositePart> parts = service.findElements(application, "com.rcp.practice.zabara.part.compositepart",
                CompositePart.class, null);
        System.out.println(parts);



    }

    // example for search by ID
    private void findPart(MApplication application, EModelService service) {
        List<MPart> parts = service.findElements(application, "mypart",
                MPart.class, null);
        System.out.println("Found part(s) : " + parts.size());

    }

    // example for search by type
    private void findParts(MApplication application,
            EModelService service) {
        List<MPart> parts = service.findElements(application, null,
                MPart.class, null);
        System.out.println("Found parts(s) : " + parts.size());

    }

    // example for search by tag
    private void findObjectsByTag(MApplication application,
            EModelService service) {
        List<String> tags = new ArrayList<>();
        tags.add("justatag");
        List<MUIElement> elementsWithTags = service.findElements(application,
                null, null, tags);
        System.out.println("Found parts(s) : " + elementsWithTags.size());
    }
}