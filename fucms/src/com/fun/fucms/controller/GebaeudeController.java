package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.gui.entities.GebaeudeFrame;
import com.fun.fucms.model.Gebaeude;
import com.fun.fucms.model.TableMediator;

public class GebaeudeController implements ActionListener {
	
	public static final String ACTION_OK="action_ok";
	public static final String ACTION_CANCEL="action_cancel";
	
	private GebaeudeFrame mGebaeude;
	
	public GebaeudeController(GebaeudeFrame e) {
		mGebaeude = e;
		
	}

	public void actionPerformed(ActionEvent ae) {
		String actionCommand = ae.getActionCommand();
		MainFrame.log("action: " + actionCommand);
		Gebaeude g = new Gebaeude();
		if (TableMediator.getRowByKey(g,5)) MainFrame.log(g.toString());
		ArrayList<Object> keys = TableMediator.getKeys(g);
		for (Object o: keys) {
			MainFrame.log("key: " + o.toString());
		}
	}

}
