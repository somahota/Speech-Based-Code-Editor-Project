package org.brown.speechtocode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.swt.widgets.Composite;

public class SampleView extends ViewPart {
	
	public SampleView() {
		// TODO Auto-generated constructor stub
	}
	
	public void createPartControl(org.eclipse.swt.widgets.Composite parent) {
		Label label = new Label(parent, SWT.LEFT); 
		label.setText( "Insert Text" ); 
	}

}
