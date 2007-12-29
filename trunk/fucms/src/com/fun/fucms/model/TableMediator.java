package com.fun.fucms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.gui.MainFrame;

public class TableMediator {
	
	public static final String SQL_TYPE_INTEGER = "INTEGER";
	public static final String SQL_TYPE_STRING = "STRING";
	
	protected int mIntegerCount = 0;
	protected int mStringCount = 0;
	protected int[] mIntegers;
	protected String[] mStrings;
	
	protected TableMediator(String[] types) {
		for (String type : types) {
			if (type.equals(SQL_TYPE_INTEGER)) {
				mIntegerCount++;
			} else if (type.equals(SQL_TYPE_STRING)) {
				mStringCount++;
			}
		}
		mIntegers = new int[mIntegerCount];
		mStrings = new String[mStringCount];
	}
	
	public static boolean getRowByKey(Entity e, Object key) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + e.getTable()+" where ");
		sb.append(e.getKey() + "=");
		if (e.getKeyType().equals(SQL_TYPE_STRING)) sb.append("'");
		sb.append(key.toString());
		if (e.getKeyType().equals(SQL_TYPE_STRING)) sb.append("'");
		try {
			ResultSet rs = Context.getInstance().executeQuery(sb.toString());
			if (rs.first()) {
				for (int i=0; i < e.getTypes().length; i++) {
					if (e.getTypes()[i].equals(TableMediator.SQL_TYPE_INTEGER)) {
						e.setIntValue(i, rs.getInt(e.getFields()[i]));
					} else if (e.getTypes()[i].equals(TableMediator.SQL_TYPE_STRING)) {
						e.setStringValue(i, rs.getString(e.getFields()[i]));
					}
				}
			}
			rs.close();
			return true;
		} catch (SQLException e1) {
			MainFrame.log(e1.getMessage());
		} catch (EvilException e2) {
			MainFrame.log(e2.getMessage());
		}
		return false;
	}
	
	public static ArrayList<Object> getKeys(Entity e) {
		ArrayList<Object> keys = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select "+ e.getKey()+" from " + e.getTable());
		try {
			ResultSet rs = Context.getInstance().executeQuery(sb.toString());
			while (rs.next()) {
				if (e.getKeyType().equals(TableMediator.SQL_TYPE_INTEGER)) {
					keys.add(new Integer(rs.getInt(e.getKey())));
				} else if (e.getKeyType().equals(TableMediator.SQL_TYPE_STRING)) {
					keys.add(rs.getString(e.getKey()));
				}
			}
			rs.close();
		} catch (SQLException e1) {
			MainFrame.log(e1.getMessage());
		} catch (EvilException e2) {
			MainFrame.log(e2.getMessage());
		}
		return keys;
	}

}
