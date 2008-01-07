package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.AboutFrame;
import com.fun.fucms.gui.EditFrame;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.gui.entities.EntityFrame;
import com.fun.fucms.model.CMSBenutzer;
import com.fun.fucms.model.PageTreeModel.TreeNode;
import com.fun.fucms.model.entities.Gebaeude;
import com.fun.fucms.model.entities.Person;
import com.fun.fucms.sql.SQLUtils;

public class MainController implements ActionListener, TreeSelectionListener {
	
	public static final String sCREATE_TABLES = "createTables";
	public static final String sEXECUTE_SINGLE_SQLFILE = "executeSingleFile";
	public static final String sOPEN_EMPLOYEE = "openEmployeeFrame";
	public static final String sOPEN_GEBAEUDE = "openGebaeudeFrame";
	public static final String sOPEN_CMSBENUZER = "openCMSBenutzerFrame";
	public static final String sOPEN_ABOUT = "openAboutFrame";
	public static final String sOPEN_EDIT = "openEditFrame";
	public static final String sOPEN_NEWPAGE = "openNewEditFrame";
		
	private Context mContext;
	private JFrame jFrame;
	
	public MainController(JFrame jFrame) throws EvilException {
		mContext = Context.getInstance();
//		InhaltsHelper h = new InhaltsHelper(mContext);
//		Entity.testAllEntityTypes();
//		WebsiteGenerator wg = new WebsiteGenerator(9);
		this.jFrame = jFrame;
	}
	
		/**
	 * dispatch actions from the main frame
	 */
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
			EntityFrame gebaeudeFrame = new EntityFrame(new Gebaeude());
		} else if (actionCommand.equals(sOPEN_CMSBENUZER)) {
			EntityFrame cmsBenutzerFrame = new EntityFrame(new CMSBenutzer());
		} else if (actionCommand.equals(sOPEN_ABOUT)) {
			AboutFrame af = new AboutFrame(jFrame);			
		} else if (actionCommand.equals(sOPEN_EDIT)) {
			if (MainFrame.getPageTreeModel().getSelectedTreeNode()!= null){
				TreeNode tn = MainFrame.getPageTreeModel().getSelectedTreeNode();
				int x = tn.getId();
				System.out.println("ID des selektierten Knoten "+x);
				EditFrame ef = new EditFrame(x);
			}
			else {
				JOptionPane.showMessageDialog(null, "Bitte wählen Sie eine Seite aus.", "Achtung!", JOptionPane.CANCEL_OPTION);
			}
				
		} else if (actionCommand.equals(sOPEN_NEWPAGE)) {
			int x,y;
			//selektierte ID ermitteln
			TreeNode tn = MainFrame.getPageTreeModel().getSelectedTreeNode();
			x = tn.getId();
			//selektierte ebene ermitteln
			try {
				ResultSet rs = Context.getInstance().executeQuery("select VATERSEITEID from VERSION where ID="+x);
				rs.first();
				y = rs.getInt("VATERSEITEID");
				
				Context.getInstance().executeQuery("INSERT INTO Version " +
						"(id, vaterseiteID, path, hauptseiteninhaltid, seitenleisteninhaltid) " +
						"VALUES (10, "+x+", 'testEintragNeu', 2, 1)");
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (EvilException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
//			EditFrame ef = new EditFrame();
			
		}
	}
	
	/**
	 * open file chooser dialog and execute choosen file as sql statements
	 *
	 */
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



	public void valueChanged(TreeSelectionEvent e) {
		TreeNode tn = (TreeNode) e.getPath().getLastPathComponent();
		// Speichert den selektierten TreeNode im PageTreeModel für spätere Abfragen
		MainFrame.getPageTreeModel().setSelectedTreeNode(tn);
		//MainFrame.log(tn.toString());
	}

}
