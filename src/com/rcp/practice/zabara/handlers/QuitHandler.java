package com.rcp.practice.zabara.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.rcp.practice.zabara.jface.ModelProvider;
import com.rcp.practice.zabara.jface.Person;

/**
 * Handler, that save current data to the file, when application will be close.
 * 
 * @author SZabara
 *
 */
public class QuitHandler {
    @Execute
    public void execute(IWorkbench workbench, Shell shell) {
        if (MessageDialog.openConfirm(shell, "Confirmation", "Do you want to exit?")) {
            URL url = null;
            try {
                url = new URL("platform:/plugin/com.rcp.practice.zabara/database.txt");
                FileWriter tfw = new FileWriter(new File(FileLocator.resolve(url).toURI()).getAbsoluteFile());
                BufferedWriter tbw = new BufferedWriter(tfw);

                for (Person person : ModelProvider.INSTANCE.getPersons()) {
                    tbw.write(person.getName() + "," + person.getGroup() + "," + person.isSwtDone());
                    tbw.newLine();
                }
                tbw.flush();

                tbw.close();
            } catch (IOException ex) {
                System.err.println("Some problem with writing file. Changes was not saved.");
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            workbench.close();
        }
    }
}
