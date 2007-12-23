package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.gui.entities.EmployeeFrame;

public class EmployeeController implements ActionListener {
	
	public static final String ACTION_OK="action_ok";
	public static final String ACTION_CANCEL="action_cancel";
	
	private EmployeeFrame mEmployee;
	
	public EmployeeController(EmployeeFrame e) {
		mEmployee = e;
		
	}

	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		MainFrame.log("action: " + actionCommand);

	}

}
