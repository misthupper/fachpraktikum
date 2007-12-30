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
		File templateFile = new File(Configuration.getInhaltsDirectory().getAbsolutePath() +
				"/Bspinhalt.html");
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile), ENCODING));
			String s = br.readLine();
			while (s != null) {
				sb.append(s+"\n");
				s = br.readLine();
			}
			br.close();
			String ergebnis = parser(sb.toString());

			return sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	
	private static String parser(String pHTML){
		String pseudoHTML = pHTML;
		String translatedHTML = "";
		
		Integer markerStart=0;
		Integer markerTabellenStart=0;
		Integer markerIDStart=0;
		Integer markerEnde=0;
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
				//translatedHTML = pseudoHtml.substring(0, markerStart-1);
				//mTabellenName = pseudoHtml.substring(markerStart+11, );
				
				mMarkerString = pseudoHTML.substring(markerStart+11, markerEnde-1);
				
				String[] mToken = mMarkerString.split("\\.");
				if (mToken.length == 3){
					// Drei Token - Korrekter Aufbau
					mSQLString = "select * from " + mToken[0] + " where id = " + mToken[1];
					//mSQLString = "Select * from Person where id=1";
			        System.out.println(mSQLString);
			         ResultSet rs = Context.getInstance().executeQuery(mSQLString);
			         rs.first();
			         String ergebnis = rs.getString(mToken[2]);
			         System.out.println("Ergebnis: " + ergebnis);
				} else {
					// Fehler
			        System.out.println("Fehler:"+mToken.length);
				}

			    markerStart=pseudoHTML.indexOf("<!-- FUCMS.",markerEnde);
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
	
			return "";
		
	}	

}
