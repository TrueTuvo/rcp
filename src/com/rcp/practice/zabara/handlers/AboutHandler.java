package com.rcp.practice.zabara.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.rcp.practice.zabara.jface.AboutApplicationDialog;
/**
 * Handler, that opens new window with additional information
 * @author SZabara
 *
 */
public class AboutHandler {
	@Execute
	public void execute(EPartService partService) {
      new AboutApplicationDialog().open();
	}
}
