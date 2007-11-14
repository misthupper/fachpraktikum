package com.fun.fucms.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.fun.fucms.Context;

public class SQLUtils {
	
	public static void executeSQLStmsInDirectory(Context context, File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			for (File sqlfile : files) {
				if (! sqlfile.getName().endsWith(".sql") || sqlfile.isDirectory()) continue;
				try {
					StringBuffer sb = new StringBuffer();
					BufferedReader br = new BufferedReader(new FileReader(sqlfile));
					String s = br.readLine();
					while (s != null) {
						if (s.trim().endsWith(";")) {
							s = s.replace(";", " ");
							sb.append(s);
							Statement stmt = context.getConnection().createStatement();
							try { 
							stmt.executeQuery(sb.toString());
							stmt.close();
							} catch (SQLException e) {
								if (e.getErrorCode() != 942) {
									throw e;
								}
							}
							sb = new StringBuffer();
						} else {
							sb.append(s);
						}
						s = br.readLine();
					}
					System.out.println("executed " + sqlfile.getAbsolutePath());
				} catch (IOException e) {
					System.out.println("Error SU001 : for file " + sqlfile.getName() + " "+e.getMessage());
				} catch (SQLException e) {
					System.out.println("Error SU002 : for file " + sqlfile.getName() + " "+e.getMessage());
					System.out.println("Error SU002 : for file " + sqlfile.getName() + " "+e.getErrorCode());
				}
			}
		}
		
	}

}
