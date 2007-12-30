package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.model.Person;
import com.fun.fucms.model.TableMediator;

public class EmployeeController implements ActionListener {
	
	public static final String ACTION_OK="action_ok";
	public static final String ACTION_CANCEL="action_cancel";
	
	//private EmployeeFrame mEmployee;
	
	//public EmployeeController(EmployeeFrame e) {
	//	mEmployee = e;
		
	//}

	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		MainFrame.log("action: " + actionCommand);
		Person p = new Person();
		if (TableMediator.getRowByKey(p,5)) MainFrame.log(p.toString());
		ArrayList<Object> keys = TableMediator.getKeys(p);
		for (Object o: keys) {
			MainFrame.log("key: " + o.toString());
		}
	}

}
