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
			//MainFrame.log(e.toString());
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
	
	public static void saveEntity(Entity entity) {
		StringBuffer sb = new StringBuffer();
		if (keyExists(entity)) {
			deleteEntity(entity);
		}
		sb.append("INSERT INTO " + entity.getTable() + " (");
		for (String field : entity.getFields()) {
			sb.append(field + ", ");
		}
		sb = new StringBuffer(sb.toString().substring(0,
				sb.toString().length() - 2));
		sb.append(") VALUES ( ");
		for (int i = 0; i < entity.getFields().length; i++) {
			if (entity.getTypes()[i].equals(SQL_TYPE_INTEGER)) {
				sb.append(entity.getValueAsString(i) + " ,");
			} else if (entity.getTypes()[i].equals(SQL_TYPE_STRING)) {
				sb.append("'" + entity.getValueAsString(i).trim() + "' ,");
			}
		}
		sb = new StringBuffer(sb.toString().substring(0,
				sb.toString().length() - 2));
		sb.append(")");
		try {
			Context.getInstance().executeUpdate(sb.toString());
		} catch (SQLException e) {
			MainFrame.log(e.getMessage());
		} catch (EvilException e) {
			MainFrame.log(e.getMessage());
		}
	}
	
	public static void deleteEntity(Entity entity) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM " + entity.getTable() + " WHERE ");
		sb.append(entity.getKey() + " = ");
		if (entity.getKeyType().equals(SQL_TYPE_STRING)) {
			sb.append("'");
		}
		sb.append(entity.getKeyValue().toString().trim());
		if (entity.getKeyType().equals(SQL_TYPE_STRING)) {
			sb.append("'");
		}
		try {
			Context.getInstance().executeUpdate(sb.toString());
		} catch (SQLException e) {
			MainFrame.log(e.getMessage());
		} catch (EvilException e) {
			MainFrame.log(e.getMessage());
		}
	}
	
	public static boolean keyExists(Entity entity) {
		String key= entity.getKeyValue().toString();
		ArrayList<Object> keys = getKeys(entity);
		for (Object o : keys) {
			if (o.toString().equals(key)) {
				return true;
			} 
		} 
		return false;
	}


}
