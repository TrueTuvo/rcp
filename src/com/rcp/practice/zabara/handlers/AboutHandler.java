package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;


import com.rcp.practice.zabara.parts.AboutApplicationDialog;
import com.rcp.practice.zabara.parts.TableViewerPart;

public class AboutHandler {
	@Execute
	public void execute(EPartService partService) {
      new AboutApplicationDialog().open();
	}
}
