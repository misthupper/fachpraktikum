package com.fun.fucms.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.gui.MainFrame;

public class SQLUtils {
	
	public static void executeSQLStmsInDirectory(Context context, File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File sqlfile : files) {
				if (! sqlfile.getName().endsWith(".sql") || sqlfile.isDirectory()) continue;
				executeSQLStatementsFromFile(context, sqlfile);
			}

		}
		
	}
	
	public static void executeSQLStatementsFromFile(Context context, File sqlfile) {

		BufferedReader br = null;
		try {
			StringBuffer sb = new StringBuffer();
			br = new BufferedReader(new FileReader(sqlfile));
			String s = br.readLine();
			while (s != null) {
				if (s.trim().endsWith(";")) {
					s = s.replace(";", " ");
					sb.append(s);
					Statement stmt = context.getConnection().createStatement();
					try {
						MainFrame.log("executing: " + sb.toString());
						//stmt.executeQuery(sb.toString());
						stmt.executeUpdate(sb.toString());
						stmt.close();
					} catch (SQLException e) {
						int errorCode = e.getErrorCode();
						// 942 = ErrorCode von Oracle falls Tabelle nicht exstiert
                        // -204 = ErrorCode von DB2 falls Tabelle nicht exstiert
						if ( (errorCode != 942) && (errorCode != -204)) {
							MainFrame.log("SQL error code: " + e.getErrorCode() );
							throw e;
						}
					}
					sb = new StringBuffer();
				} else {
					sb.append(s);
				}
				s = br.readLine();
			}
			MainFrame.log("executed " + sqlfile.getAbsolutePath());
		} catch (IOException e) {
			MainFrame.log("Error SU001 : for file " + sqlfile.getName() + " "+e.getMessage());
		} catch (SQLException e) {
			MainFrame.log("Error SU002 : for file " + sqlfile.getName() + " "+e.getMessage());
			MainFrame.log("Error SU002 : for file " + sqlfile.getName() + " "+e.getErrorCode());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		
	}

	/**
	 * loads, inits returns an ArrayList<T> from the Database Table mTable
	 * @return
	 * @param sVariable the ArrayList<T> to be initialized
	 * @param mTable the database table from with the ArrayList<T> is loaded
	 * @param mAttribute is the attribute to be read into the Array
	 * @param indexAttribute is the index attribute to check 
	 * @param id is the id of the 
	 */	
		public static <T> ArrayList<T> arrayFromDB(ArrayList<T> sVariable, String mTable, String mAttribute, String indexAttribute, String id)
	{
		try {
			if (sVariable == null) // load/initialize only once
			{
				String query = "select * from "+mTable;
				if (!(indexAttribute=="*" || indexAttribute=="" || id=="*" || id=="")){
					query += " where "+indexAttribute+" = "+id; };
				
				// System.out.println(query);
				ResultSet rs = Context.getInstance().executeQuery(query);
	
				// initialize and load mEntityTypes
				sVariable = new ArrayList<T>(); 
				for (boolean isRow=rs.first(); isRow ;isRow=rs.next()) {
					if (mAttribute.equals("*") || mAttribute.equals("")){
					// case 2-dimensional array: 1st = rows, 2nd=columns
					// only works if <T>.toString() is properly set 
						{ assert (sVariable.getClass() == new ArrayList<ArrayList<Object>>().getClass()) : ""; }
						ArrayList<Object> o = new ArrayList<Object>();
						for (int i=1; i <= rs.getMetaData().getColumnCount(); i++){
							// System.out.println(rs.getMetaData().getColumnName(i)+" "+rs.getMetaData().getColumnTypeName(i).toString().trim()+" "+rs.getMetaData().getPrecision(i));	
							o.add(rs.getObject(i).toString().trim());
						}
						sVariable.add((T) o);
					}
					else {
					// case 1 dimensional array (one column)
						Object o=rs.getObject(mAttribute);
						if (o instanceof String) {
								String s =(String)o;
								o= s.trim();
						} 
						sVariable.add((T) o);
					}
				};
				// Debug Print				
				// for (int i=0; i< sVariable.size(); i++) {
				//	System.out.println(i+ " " + sVariable.get(i).toString());
				// };
			};
			
		}
		catch (SQLException e) {
			MainFrame.log(e.getMessage());}
		catch (EvilException e2) {
			MainFrame.log(e2.getMessage());}

		return sVariable;
	};
	
}
