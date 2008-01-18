package com.fun.fucms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.AssertionError;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fun.fucms.EvilException;
import com.fun.fucms.conf.Configuration;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.model.*;
import com.fun.fucms.sql.*;


public class InhaltsParser {

	protected static Context mContext;
	private static String ENCODING = "ISO-8859-1";

	public InhaltsParser(Context context) {
		mContext = context;
	}



	public static String parse(String pHTML){
		String pseudoHTML = pHTML;

		int markerStart=0;
		int markerEnde=0;
		String mMarkerString="";
		String mSQLString="";
		String mTable;
		String mAttribute;
		String mID;
		String s="";
		
		//Person mPerson = new Person();
		//EntityTableModel mTable = new EntityTableModel(new Person());
		//mPerson.setStringValue(1, "name");
		//String mName = mPerson.getStringValue("name");

		//System.out.println(mTable.getValueAt(1,1));

		//try {
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
					mTable = mToken[0];
					mAttribute = mToken[2];
					mID = mToken[1];
					
					//if (Entity.isEntityOrRelation(mTable) throw AssertionError("Warning: "+mTable+ " ist keine Entity oder Relation");
					assert Entity.isEntityOrRelation(mTable) : "Warning: "+mTable+ " ist keine Entity oder Relation";

					if (mAttribute.equals("*") || mAttribute.equals("")) {
						ArrayList<ArrayList<String>> result= null;
						result = SQLUtils.arrayFromDB(result, mTable, "*", "id", mID );
					}
					else{
						// Fall: (mID.length>1 mID == "*" || mID == "")
						ArrayList<String> result= null;
						result = SQLUtils.arrayFromDB(result, mTable, mAttribute, "id", mID );
						if (result.size()==1) // SQL-Ergebnis besteht aus einem Datensatz
							{s=result.get(0).toString();}
						else if (result.size()>1) {  // SQL-Ergebnis besteht aus mehreren Datenrecords
							s="<TABLE>"; 
								for (int i=0; i<result.size(); i++) {
									s+= "<tr>"+ result.get(i).toString()+ "</tr>";
								};
							s+="</TABLE>";
						} 
					}
				}	
				else {
					// Fehler zu viele oder zu wenige Token
					MainFrame.log("Fehler: Ersetzungsmarke '"+mMarkerString+"' hat "+mToken.length+" Token.");
				}
				pseudoHTML = pseudoHTML.substring(0, markerStart) + s + pseudoHTML.substring(markerEnde+3,pseudoHTML.length());	
				
				// Bedingungen fuer while-Schleife
				markerStart=pseudoHTML.indexOf("<!-- FUCMS.",0);
				if (markerStart !=-1) {
					markerEnde=pseudoHTML.indexOf("-->",markerStart);

				} 
				else markerEnde =-1;
			}
		/*}  catch (SQLException e) {
			MainFrame.log(e.getMessage());
		}
		catch (EvilException e2) {
			MainFrame.log(e2.getMessage());
		}
		*/
		MainFrame.log(pseudoHTML);
		return pseudoHTML;
	}	
}
