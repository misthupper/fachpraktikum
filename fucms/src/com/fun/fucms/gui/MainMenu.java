package com.fun.fucms.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fun.fucms.controller.MainController;

public class MainMenu extends JMenuBar {
	
	// Web Menue
	private static final String sMENU1 = "Web";
	//private static final String sMENU1_ITEM1 = "DB l�schen";  
	private static final String sMENU1_ITEM1 = "DB neu anlegen"; 
	private static final String sMENU1_ITEM2 = "Einzelnes SQL script ausf�hren"; 
	
	private static final String sMENU2 = "Entities";
	private static final String sMENU2_ITEM1 = "Mitarbeiter bearbeiten";
	private static final String sMENU2_ITEM2 = "Gebaeude bearbeiten";
	
	private static final String sMENU3 = "Benutzerverwaltung";
	private static final String sMENU3_ITEM1 = "CMS Benutzer";
	//private static final String sMENU3_ITEM2 = " ";
	
    JMenu mMenu1 = new JMenu(sMENU1);
    JMenu mMenu2 = new JMenu(sMENU2);
    JMenu mMenu3 = new JMenu(sMENU3);
    
    JMenuItem mMenu1Item1 = new JMenuItem(sMENU1_ITEM1);
    JMenuItem mMenu1Item2 = new JMenuItem(sMENU1_ITEM2);
    
    JMenuItem mMenu2Item1 = new JMenuItem(sMENU2_ITEM1);
    JMenuItem mMenu2Item2 = new JMenuItem(sMENU2_ITEM2);
    
    JMenuItem mMenu3Item1 = new JMenuItem(sMENU3_ITEM1);
    //JMenuItem mMenu3Item2 = new JMenuItem(sMENU3_ITEM2);
    
    public MainMenu() {
    	mMenu1.add(mMenu1Item1);
    	mMenu1Item1.setActionCommand(MainController.sCREATE_TABLES);
    	mMenu1.add(mMenu1Item2);
    	mMenu1Item2.setActionCommand(MainController.sEXECUTE_SINGLE_SQLFILE);
    	this.add(mMenu1);
    	
    	mMenu2.add(mMenu2Item1);
    	mMenu2Item1.setActionCommand(MainController.sOPEN_EMPLOYEE);
    	mMenu2.add(mMenu2Item2);
    	mMenu2Item2.setActionCommand(MainController.sOPEN_GEBAEUDE);
    	this.add(mMenu2);
    	
    	mMenu3.add(mMenu3Item1);
    	mMenu3Item1.setActionCommand(MainController.sOPEN_CMSBENUZER);
    	this.add(mMenu3);
    }
    
    public void setActionListener(ActionListener al) {
    	mMenu1Item1.addActionListener(al);
    	mMenu1Item2.addActionListener(al);
    	mMenu2Item1.addActionListener(al);
    	mMenu2Item2.addActionListener(al);
    	mMenu3Item1.addActionListener(al);
    }

}
