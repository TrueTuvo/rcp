package com.rcp.practice.zabara.parts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.IWindowCloseHandler;

public class ExampleWindowCloseAddon implements IWindowCloseHandler
{
    @Inject
    @Optional
    public void startupComplete(@UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) MApplication application,
            EModelService modelService)
    {
        MWindow window = (MWindow) modelService.find("com.rcp.practice.zabara.window.main", application);
        window.getContext().set(IWindowCloseHandler.class, this);
    }

    @Override
    public boolean close(MWindow window)
    {
        try {
            FileWriter tfw = new FileWriter(new File("C:\\luxoft\\database.txt"));
            BufferedWriter tbw = new BufferedWriter(tfw);

            for (Person person : ModelProvider.INSTANCE.getPersons()) {
                tbw.write(person.getName() + "," + person.getGroup() + "," + person.isSwtDone());
                tbw.newLine();
                tbw.flush();
            }

            tbw.close();
        } catch (IOException ex) {
            System.err.println("Some problem with writing file. Changes was not saved.");
        }
        return true;
    }

}