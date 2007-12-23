package com.fun.fucms.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fun.fucms.controller.MainController;

public class MainMenu extends JMenuBar {
	
	// Web Menue
	private static final String sMENU1 = "Web";
	//private static final String sMENU1_ITEM1 = "DB löschen";  
	private static final String sMENU1_ITEM1 = "DB neu anlegen"; 
	private static final String sMENU1_ITEM2 = "Einzelnes SQL script ausführen"; 
	
	private static final String sMENU2 = "Entities";
	private static final String sMENU2_ITEM1 = "Mitarbeiter bearbeiten";  
	
    JMenu mMenu1 = new JMenu(sMENU1);
    JMenu mMenu2 = new JMenu(sMENU2);
    
    JMenuItem mMenu1Item1 = new JMenuItem(sMENU1_ITEM1);
    JMenuItem mMenu1Item2 = new JMenuItem(sMENU1_ITEM2);
    
    JMenuItem mMenu2Item1 = new JMenuItem(sMENU2_ITEM1);
    
    public MainMenu() {
    	mMenu1.add(mMenu1Item1);
    	mMenu1Item1.setActionCommand(MainController.sCREATE_TABLES);
    	mMenu1.add(mMenu1Item2);
    	mMenu1Item2.setActionCommand(MainController.sEXECUTE_SINGLE_SQLFILE);
    	this.add(mMenu1);
    	
    	mMenu2.add(mMenu2Item1);
    	mMenu2Item1.setActionCommand(MainController.sOPEN_EMPLOYEE);
    	this.add(mMenu2);
    }
    
    public void setActionListener(ActionListener al) {
    	mMenu1Item1.addActionListener(al);
    	mMenu1Item2.addActionListener(al);
    	mMenu2Item1.addActionListener(al);
    }

}
