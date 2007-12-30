package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.InhaltsHelper;
import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.gui.entities.EntityFrame;
import com.fun.fucms.model.Person;
import com.fun.fucms.gui.entities.GebaeudeFrame;
import com.fun.fucms.sql.SQLUtils;

public class MainController implements ActionListener {
	
	public static final String sCREATE_TABLES = "createTables";
	public static final String sEXECUTE_SINGLE_SQLFILE = "executeSingleFile";
	public static final String sOPEN_EMPLOYEE = "openEmployeeFrame";
	public static final String sOPEN_GEBAEUDE = "openGebaeudeFrame";
	
	private Context mContext;
	
	public MainController() throws EvilException {
		mContext = Context.getInstance();
        // InhaltsHelper h = new InhaltsHelper(mContext);
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		MainFrame.log("action: " + actionCommand);
		if (actionCommand.equals(sCREATE_TABLES)) {
			SQLUtils.executeSQLStmsInDirectory(mContext, Configuration.getSQLDirectory());
		} else if (actionCommand.equals(sEXECUTE_SINGLE_SQLFILE)) {
			excuteSingleSQLFile();
		} else if (actionCommand.equals(sOPEN_EMPLOYEE)) {
			EntityFrame employeeFrame = new EntityFrame(new Person());
		} else if (actionCommand.equals(sOPEN_GEBAEUDE)) {
			GebaeudeFrame gebaeudeFrame = new GebaeudeFrame();
		}
	}
	
	public void excuteSingleSQLFile() {
        JFileChooser chooser = new JFileChooser(Configuration.getSQLDirectory());
        chooser.setDialogTitle("Select file");
        int returnVal = chooser.showOpenDialog(MainFrame.sInstance);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	File file = chooser.getSelectedFile();
        	MainFrame.log("file choosen: "+file.getAbsolutePath());
        	SQLUtils.executeSQLStatementsFromFile(mContext, file);
        } 
	}

}
