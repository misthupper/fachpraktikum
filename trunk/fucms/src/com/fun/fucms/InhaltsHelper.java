package com.fun.fucms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fun.fucms.EvilException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.AssertionError;

import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.model.*;


public class InhaltsHelper {

	protected static Context mContext;
	private static String ENCODING = "ISO-8859-1";
	private static String sInhalt = getInhalt();

	public InhaltsHelper(Context context) {
		mContext = context;
	}

	private static String getInhalt()
	// Erst mal über eine DAtei laden - später aus der Datenbank
	{
		String ergebnis="";
		File templateFile = new File(Configuration.getInhaltsDirectory().getAbsolutePath() +
		"/Senatsbeauftragter.html");
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile), ENCODING));
			String s = br.readLine();
			while (s != null) {
				sb.append(s+"\n");
				s = br.readLine();
			}
			br.close();
			ergebnis = parser(sb.toString());

			return sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ergebnis;
	}


	private static String parser(String pHTML){
		String pseudoHTML = pHTML;

		int markerStart=0;
		int markerEnde=0;
		String mMarkerString="";
		String mSQLString="";

		//Person mPerson = new Person();
		EntityTableModel mTable = new EntityTableModel(new Person());
		//mPerson.setStringValue(1, "name");
		//String mName = mPerson.getStringValue("name");

		//System.out.println(mTable.getValueAt(1,1));

		try {
			markerStart=pseudoHTML.indexOf("<!-- FUCMS.");
			if (markerStart !=-1) 
				markerEnde = pseudoHTML.indexOf("-->",markerStart); 
			else
				markerEnde = -1;


			while (markerEnde !=-1) {

				mMarkerString = pseudoHTML.substring(markerStart+11, markerEnde-1);

				String[] mToken = mMarkerString.split("\\.");
				if (mToken.length == 3){
					// Drei Token - Korrekter Aufbau
					
					//if (Entity.isEntityOrRelation(mToken[0])) throw AssertionError("Warning: "+mToken[0]+ " ist keine Entity oder Relation");
					assert !Entity.isEntityOrRelation(mToken[0]) : "Warning: "+mToken[0]+ " ist keine Entity oder Relation";
					
					mSQLString = "select * from " + mToken[0] + " where id = " + mToken[1];
					ResultSet rs = Context.getInstance().executeQuery(mSQLString);
					rs.first();
					String ergebnis = rs.getString(mToken[2]);
					pseudoHTML = pseudoHTML.substring(0, markerStart) + ergebnis.trim() + pseudoHTML.substring(markerEnde+3,pseudoHTML.length());
				} 
				else {
					// Fehler zu viele oder zu wenige Token
					MainFrame.log("Fehler: Ersetzungsmarke '"+mMarkerString+"' hat "+mToken.length+" Token.");
				}

				markerStart=pseudoHTML.indexOf("<!-- FUCMS.",0);
				if (markerStart !=-1) {
					markerEnde=pseudoHTML.indexOf("-->",markerStart);

				} 
				else markerEnde =-1;
			}
		}  catch (SQLException e) {
			MainFrame.log(e.getMessage());
		}
		catch (EvilException e2) {
			MainFrame.log(e2.getMessage());
		}
		// MainFrame.log(pseudoHTML);
		return pseudoHTML;

	}	

}
