 
package com.rcp.practice.zabara.handlers;

import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class TableViewerHandler {
	@Execute
	public void execute(MApplication application, EModelService service) {
		
	    List<MPart> parts = service.findElements(application, null,
              MPart.class, null);
	    System.out.println(parts.toString());
	}
		
}