package com.fun.fucms.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fun.fucms.controller.MainController;

public class MainMenu extends JMenuBar {
	
	// Web Menue
	private static final String sMENU1 = "DB";
	//private static final String sMENU1_ITEM1 = "DB löschen";  
	private static final String sMENU1_ITEM1 = "DB neu anlegen"; 
	private static final String sMENU1_ITEM2 = "Einzelnes SQL script ausführen"; 
	
	private static final String sMENU2 = "Entities";
	private static final String sMENU2_ITEM1 = "Mitarbeiter bearbeiten";
	private static final String sMENU2_ITEM2 = "Gebaeude bearbeiten";
	
	private static final String sMENU3 = "Benutzerverwaltung";
	private static final String sMENU3_ITEM1 = "CMS Benutzer";
	
	private static final String sMENU4 = "Hilfe";
	private static final String sMENU4_ITEM1 = "About FUCMS";
	
	private static final String sMENU5 = "Seitenverwaltung";
	private static final String sMENU5_ITEM1 = "Seite bearbeiten";
	private static final String sMENU5_ITEM2 = "Neue Seite anlegen";
	private static final String sMENU5_ITEM3 = "Seite loeschen";
//	private static final String sMENU5_ITEM4 = "Seitenleiste bearbeiten";
	
	private static final String sMENU6 = "Webseitengenerierung";
	private static final String sMENU6_ITEM1 = "Seite generieren";
	private static final String sMENU6_ITEM2 = "Alle Seiten neu generieren";
	
	private static final String sMENU7 = "Refresh";
	private static final String sMENU7_ITEM1 = "Baumstruktur neu laden";

    JMenu mMenu1 = new JMenu(sMENU1);
    JMenu mMenu2 = new JMenu(sMENU2);
    JMenu mMenu3 = new JMenu(sMENU3);
    JMenu mMenu4 = new JMenu(sMENU4);
    JMenu mMenu5 = new JMenu(sMENU5);
    JMenu mMenu6 = new JMenu(sMENU6);
    JMenu mMenu7 = new JMenu(sMENU7);
    
    JMenuItem mMenu1Item1 = new JMenuItem(sMENU1_ITEM1);
    JMenuItem mMenu1Item2 = new JMenuItem(sMENU1_ITEM2);
    
    JMenuItem mMenu2Item1 = new JMenuItem(sMENU2_ITEM1);
    JMenuItem mMenu2Item2 = new JMenuItem(sMENU2_ITEM2);
    
    JMenuItem mMenu3Item1 = new JMenuItem(sMENU3_ITEM1);
    
    JMenuItem mMenu4Item1 = new JMenuItem(sMENU4_ITEM1);
    
    JMenuItem mMenu5Item1 = new JMenuItem(sMENU5_ITEM1);
    JMenuItem mMenu5Item2 = new JMenuItem(sMENU5_ITEM2);
    JMenuItem mMenu5Item3 = new JMenuItem(sMENU5_ITEM3);
//    JMenuItem mMenu5Item4 = new JMenuItem(sMENU5_ITEM4);
    
    JMenuItem mMenu6Item1 = new JMenuItem(sMENU6_ITEM1);
    JMenuItem mMenu6Item2 = new JMenuItem(sMENU6_ITEM2);
    
    JMenuItem mMenu7Item1 = new JMenuItem(sMENU7_ITEM1);
    
    public MainMenu() {
    	mMenu1.add(mMenu1Item1);
    	mMenu1Item1.setActionCommand(MainController.sCREATE_TABLES);
    	mMenu1.add(mMenu1Item2);
    	mMenu1Item2.setActionCommand(MainController.sEXECUTE_SINGLE_SQLFILE);
    	this.add(mMenu1);
    	
    	mMenu5.add(mMenu5Item1);
    	mMenu5Item1.setActionCommand(MainController.sOPEN_EDIT);
    	mMenu5.add(mMenu5Item2);
    	mMenu5Item2.setActionCommand(MainController.sOPEN_NEWPAGE);
    	mMenu5.add(mMenu5Item3);
    	mMenu5Item3.setActionCommand(MainController.sDELETE_PAGE);
//    	mMenu5.add(mMenu5Item4);
    	mMenu5Item1.setActionCommand(MainController.sOPEN_EDIT);
    	this.add(mMenu5);
    	
    	mMenu2.add(mMenu2Item1);
    	mMenu2Item1.setActionCommand(MainController.sOPEN_EMPLOYEE);
    	mMenu2.add(mMenu2Item2);
    	mMenu2Item2.setActionCommand(MainController.sOPEN_GEBAEUDE);
    	this.add(mMenu2);
    	
    	mMenu3.add(mMenu3Item1);
    	mMenu3Item1.setActionCommand(MainController.sOPEN_CMSBENUZER);
    	this.add(mMenu3);
    	
    	mMenu6.add(mMenu6Item1);
    	mMenu6.add(mMenu6Item2);
    	mMenu6Item1.setActionCommand(MainController.sGENERATE_WEBSITE);
    	mMenu6Item2.setActionCommand(MainController.sGENERATE_WEBSITES);
    	this.add(mMenu6);
    	
    	mMenu7.add(mMenu7Item1);
    	mMenu7Item1.setActionCommand(MainController.sTREE_REFRESH);
    	this.add(mMenu7);
    	
    	mMenu4.add(mMenu4Item1);
    	mMenu4Item1.setActionCommand(MainController.sOPEN_ABOUT);
    	this.add(mMenu4);
    }
    
    public void setActionListener(ActionListener al) {
    	mMenu1Item1.addActionListener(al);
    	mMenu1Item2.addActionListener(al);
    	mMenu2Item1.addActionListener(al);
    	mMenu2Item2.addActionListener(al);
    	mMenu3Item1.addActionListener(al);
    	mMenu4Item1.addActionListener(al);
    	mMenu5Item1.addActionListener(al);
    	mMenu5Item2.addActionListener(al);
    	mMenu5Item3.addActionListener(al);
//    	mMenu5Item4.addActionListener(al);
    	mMenu6Item1.addActionListener(al);
    	mMenu6Item2.addActionListener(al);
    	mMenu7Item1.addActionListener(al);
    }

}
