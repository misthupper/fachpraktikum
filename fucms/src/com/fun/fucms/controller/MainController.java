package com.fun.fucms.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.WebsiteGenerator;
import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.AboutFrame;
import com.fun.fucms.gui.EditFrame;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.gui.PageTree;
import com.fun.fucms.gui.entities.EntityFrame;
import com.fun.fucms.model.CMSBenutzer;
import com.fun.fucms.model.PageTreeModel;
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
	public static final String sGENERATE_WEBSITE = "generateWebsite";
	public static final String sGENERATE_WEBSITES = "generateAllWebsites";
	public static final String sTREE_REFRESH = "refreshTree";
	public static final String sDELETE_PAGE = "deletePage";

		
	private Context mContext;
	private JFrame jFrame;
	
	public MainController(JFrame jFrame) throws EvilException {
		mContext = Context.getInstance();
		this.jFrame = jFrame;
	}
	
	/**
	 * dispatch actions from the main frame
	 */
	public void actionPerformed(ActionEvent e) {
		int treenode_id, vaterseite_id, last_id, new_id=0,
		hauptSeitenInhaltID, seitenLeistenInhaltID;
		
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
				//System.out.println("ID des selektierten Knoten "+x);
				EditFrame ef = new EditFrame(x);
			}
			else {
				JOptionPane.showMessageDialog(null, "Bitte waehlen Sie eine Seite aus.", "Achtung!", JOptionPane.CANCEL_OPTION);
			}
				
		} else if (actionCommand.equals(sGENERATE_WEBSITE)) {
			if (MainFrame.getPageTreeModel().getSelectedTreeNode()!= null){
				TreeNode tn = MainFrame.getPageTreeModel().getSelectedTreeNode();
				int x = tn.getId();
				//System.out.println("ID des selektierten Knoten "+x);
				WebsiteGenerator.generateWebsite(x);
				System.out.println("Webseite generiert!");
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Bitte waehlen Sie eine Seite aus.", "Achtung!", JOptionPane.CANCEL_OPTION);
			}
				
		}else if (actionCommand.equals(sGENERATE_WEBSITES)) {
			ResultSet rs;
			try {
				rs = Context.getInstance().executeQuery("select * from Version");
				int [] websiteIDs = new int [200];
				int counter = 0;
				while (rs.next()){
					websiteIDs[counter] = rs.getInt("id");
					System.out.println("Wert eingelesen: " + rs.getInt("id"));
					counter++;
				}
				rs.close();
				System.out.println(counter);
				
				for (int i=0; i < counter; i++){
					WebsiteGenerator.generateWebsite(websiteIDs[i]);
	        		System.out.println("Webseite " + websiteIDs[i] + " generiert!");
	        		// schliesst und startet die Verbindung neu um "maximum open cursors exceeded" error der Datenbank zu verhindern
	        		mContext.close();
					mContext.start();
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (EvilException e1) {
				e1.printStackTrace();
			}
				
		} else if (actionCommand.equals(sOPEN_NEWPAGE)) {
			//selektierte ID ermitteln
			TreeNode tn = MainFrame.getPageTreeModel().getSelectedTreeNode();
			if(tn== null) {
				JOptionPane.showMessageDialog(null, "Bitte waehlen Sie eine Seite aus.", "Achtung!", JOptionPane.CANCEL_OPTION);
				return;
			}
			
			treenode_id = tn.getId();
			String websiteName = "test";
			websiteName = JOptionPane.showInputDialog(null,"Name der neuen Webseite","Webseitenname",JOptionPane.PLAIN_MESSAGE);
            if(websiteName==null || websiteName.length()==0){
            	JOptionPane.showMessageDialog(null, "Kein Seitenname eingegeben, bitte versuchen Sie es erneut","Oops...",JOptionPane.ERROR_MESSAGE);
            } else {
            
			try {
				//VaterID holen
				//ResultSet rs = Context.getInstance().executeQuery("select VATERSEITEID from VERSION where ID="+treenode_id);
				//rs.first();
				//vaterseite_id = rs.getInt("VATERSEITEID");
				vaterseite_id = treenode_id;//Neue Seite wird Unterseite der selektierten Seite.
				
				//letzte id holen zum inkrementieren
				ResultSet rs_id = Context.getInstance().executeQuery(
						"select ID from VERSION order by ID DESC");
				rs_id.first();
				last_id = rs_id.getInt("ID");
				new_id = last_id + 1;
				
				ResultSet rs_hauptSeitenInhaltID = Context.getInstance().executeQuery(
						"select hauptseiteninhaltid from version" +
						" order by hauptseiteninhaltid desc");
				rs_hauptSeitenInhaltID.first();
				hauptSeitenInhaltID = rs_hauptSeitenInhaltID.getInt("HAUPTSEITENINHALTID");
				System.out.println("max. HauptseitenID: "+hauptSeitenInhaltID);
				
				ResultSet rs_seitenLeistenInhaltID = Context.getInstance().executeQuery("" +
						"select SEITENLEISTEINHALTID from version order by SEITENLEISTEINHALTID desc");
				rs_seitenLeistenInhaltID.first();
				seitenLeistenInhaltID = rs_seitenLeistenInhaltID.getInt("SEITENLEISTEINHALTID");
				System.out.println("max. SeitenleistenID: "+seitenLeistenInhaltID);
				
				int high;
				if(hauptSeitenInhaltID > seitenLeistenInhaltID)
					high=hauptSeitenInhaltID;
				else
					high=seitenLeistenInhaltID;
				
				//neue Seite anlegen
				Context.getInstance().executeQuery("INSERT INTO Version " +
						"(id, vaterseiteID, path, titel, autor, format, statusid, hauptseiteninhaltID, seitenleisteInhaltID) " +
						"VALUES ("+new_id+", "+vaterseite_id+", '" + websiteName + "', '" + websiteName + "'," +
								" 1, 1, 9, "+(high+1)+","+(high+2)+")");
				
				//Inhalt zur Seite anlegen
				Context.getInstance().executeQuery(
						"INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) " +
						"VALUES ("+(high+1)+", 'Text', 'Hier kommt der HauptleistenInhalt hinein')");
				Context.getInstance().executeQuery(
						"INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) " +
						"VALUES ("+(high+2)+", 'Text', 'Hier kommt der SeitenleistenInhalt hinein')");
				
				//wieder id holen und inkrementieren
//				ResultSet rs_inhaltID = Context.getInstance().executeQuery("" +
//				"select ID from INHALT order by ID desc");
//				rs_inhaltID.first();
//				int inhaltID = rs_inhaltID.getInt("ID");
//				System.out.println("max. inhaltID: "+inhaltID);
//				
//				int high;
//				if(hauptSeitenInhaltID > seitenLeistenInhaltID)
//					high=hauptSeitenInhaltID;
//				else
//					high=seitenLeistenInhaltID;
//				
//				if(inhaltID != high) {
//					Context.getInstance().executeQuery(
//							"INSERT INTO INHALT (ID, INHALTSTYP, INHALTSTEXT) " +
//							"VALUES ("+(hauptSeitenInhaltID+1)+", 'Text', 'Hier kommt der Inhalt hinein')");
//				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (EvilException e1) {
				e1.printStackTrace();
			}
			EditFrame ef = new EditFrame(new_id);
            }
			MainFrame.getPageTreeModel().update(null);
			
		} else if (actionCommand.equals(sTREE_REFRESH)) {
			//TODO Baumstruktur neu laden
			PageTreeModel ptm = MainFrame.getPageTreeModel();
			//ptm.update(new java.util.Date());
			MainFrame mf = (MainFrame) jFrame;
			PageTree pt = mf.getPageTree();
			MainFrame.getPageTreeModel().update(null);
			
		} else if (actionCommand.equals(sDELETE_PAGE)) {
			TreeNode tn = MainFrame.getPageTreeModel().getSelectedTreeNode();
			int x = tn.getId();
//			System.out.println(x);
			
			try {
				ResultSet rs = Context.getInstance().executeQuery("" +
						"select HAUPTSEITENINHALTID, SEITENLEISTEINHALTID from Version where id='"+x+"'");
				rs.first();
				int hauptseiteninhaltID = rs.getInt("HAUPTSEITENINHALTID");
				int seitenleisteninhaltID = rs.getInt("SEITENLEISTEINHALTID");
//				System.out.println(hauptseiteninhaltID+" "+seitenleisteninhaltID);
				
				//die seite mit der id loschen und inhalte loeschen
				Context.getInstance().executeQuery(
						"DELETE FROM Version WHERE ID='"+x+"'");
				// pruefen, ob Inhalt von einer anderen Seite verwendet wird (und dann nicht loeschen!)
				rs = Context.getInstance().executeQuery("select HauptseiteninhaltID, SeitenleisteinhaltID from Version where id<>" + x + "and HauptseiteninhaltID =" + hauptseiteninhaltID);
				if (!rs.first()){
					Context.getInstance().executeQuery(
							"DELETE FROM Inhalt WHERE ID='"+hauptseiteninhaltID+"'");
					System.out.println("Hauptseiteninhalt geloescht");
				}
					
				rs = Context.getInstance().executeQuery("select HauptseiteninhaltID, SeitenleisteinhaltID from Version where id<>" + x + "and SeitenleisteinhaltID =" + seitenleisteninhaltID);
				if (!rs.first()){
					Context.getInstance().executeQuery(
							"DELETE FROM Inhalt WHERE ID='"+seitenleisteninhaltID+"'");
					System.out.println("Seitenleisteninhalt geloescht");
				}
					
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (EvilException e1) {
				e1.printStackTrace();
			}
			MainFrame.getPageTreeModel().update(null);
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
		// Speichert den selektierten TreeNode im PageTreeModel fuer spaetere Abfragen
		MainFrame.getPageTreeModel().setSelectedTreeNode(tn);
		//MainFrame.log(tn.toString());
	}

}
