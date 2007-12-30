package com.fun.fucms.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import com.fun.fucms.Context;
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

}
