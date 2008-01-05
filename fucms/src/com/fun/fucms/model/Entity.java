package com.fun.fucms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.sql.*;

/**
 * An Entity holds the information of one DB Record. Entity must be extended to use it. 
 * @author rod
 *
 */
public abstract class Entity {
	
	public abstract String[] getFields();
	public abstract String getKey();
	public abstract String getTable();
	public abstract String[] getTypes();
	public abstract Entity getNewInstance();
	
	protected Object[] mValues;	
	
	static protected ArrayList<String> mEntityTypes = loadEntityTypes(); 
	static protected ArrayList<String> mRelationTypes = loadRelationTypes(); 
	
	protected Entity() {
		/*if (getFields().length != getTypes().length) {
			throw new IllegalStateException(getTable() + " is not defined well(1)!");
		};*/
		loadEntityTypes();
		initEmptyEntity();
	}
	
	private void initEmptyEntity() {
		mValues=new Object[getTypes().length];
		for (int i=0; i < getTypes().length; i++) {
			if (getTypes()[i].equals(TableMediator.SQL_TYPE_INTEGER)) {
				mValues[i] = new Integer(0);
			} else if (getTypes()[i].equals(TableMediator.SQL_TYPE_STRING)) {
				mValues[i] = "";
			}
		}
	}
	
	private int getFieldNo(String field) {
		for (int i=0; i < getFields().length; i++) {
			if (getFields()[i].equals(field)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * return the data of this record as string (for debugging purposes)
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < getTypes().length; i++) {
			sb.append(getFields()[i] + " : " + mValues[i].toString() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * get the type of the key field 
	 * The possible values are defined in TableMediator 
	 * @return type of the key field 
	 */
	public String getKeyType() {
		for (int i=0; i<getFields().length ; i++) {
			if (getFields()[i].equals(getKey())) {
				return getTypes()[i];
			}
		}
		throw new IllegalStateException(getTable() + " is not defined well(2)!");
	}
	
	/**
	 * returns the name and the value of the key field
	 * @return
	 */
	public String getKeyString() {
		String s = getKey() + " : " + getValueAsString(getKey());
		return s;
	}
	
	/**
	 * returns the key of this record
	 * @return
	 */
	public Object getKeyValue() {
		if (getKeyType().equals(TableMediator.SQL_TYPE_INTEGER)) {
			return new Integer(getIntValue(getKey()));
		} else if (getKeyType().equals(TableMediator.SQL_TYPE_STRING)) {
			return getStringValue(getKey());
		}
		return null;
	}
	
	/**
	 * sets a int value in this Entity
	 * @param fieldNo no of the field
	 * @param value
	 */
	public void setIntValue(int fieldNo, int value) {
		mValues[fieldNo] = new Integer(value);
	}
	
	/**
	 * sets a string (char) field in this Entity
	 * @param fieldNo no of the field
	 * @param value
	 */
	public void setStringValue(int fieldNo, String value) {
		mValues[fieldNo] = value;
	}
	
	/**
	 * get an int value from this Entity
	 * @param fieldNo no of the field
	 * @return
	 */
	public int getIntValue(int fieldNo) {
		return ((Integer) mValues[fieldNo]).intValue();
	}
	
	/**
	 * get an String value from this Entity
	 * @param fieldNo no of the field
	 * @return
	 */
	public String getStringValue(int fieldNo) {
		return (String) mValues[fieldNo];
	}
	
	/**
	 * sets an int value in this Entity
	 * @param field name of the field (column)
	 * @param value
	 */
	public void setIntValue(String field, int value) {
		mValues[getFieldNo(field)] = new Integer(value);
	}
	
	/**
	 * sets an String value in this Entity
	 * @param field name of the field (column)
	 * @param value
	 */
	public void setStringValue(String field, String value) {
		mValues[getFieldNo(field)] = value;
	}
	
	/**
	 * get int value from Entity
	 * @param field name of the field
	 * @return
	 */
	public int getIntValue(String field) {
		return ((Integer) mValues[getFieldNo(field)]).intValue();
	}
	
	/**
	 * get String value from Entity
	 * @param field name of the field
	 * @return
	 */
	public String getStringValue(String field) {
		return (String) mValues[getFieldNo(field)];
	}
	
	/**
	 * returns the value of the given field as String (even for integer values)
	 * @param fieldNo
	 * @return
	 */
	public String getValueAsString(int fieldNo) {
		String s = "";
		if (mValues[fieldNo] instanceof Integer) {
			s = ((Integer) mValues[fieldNo]).toString();
		} else if (mValues[fieldNo] instanceof String) {
			s = (String) mValues[fieldNo];
		}
		return s;
	}
	
	/**
	 * returns the value of the given field as String (even for integer values)
	 * @param field name of the field
	 * @return
	 */
	public String getValueAsString(String field) {
		String s = "";
		if (mValues[getFieldNo(field)] instanceof Integer) {
			s = ((Integer) mValues[getFieldNo(field)]).toString();
		} else if (mValues[getFieldNo(field)] instanceof String) {
			s = (String) mValues[getFieldNo(field)];
		}
		return s;
	}
	
	/**
	 * set the Value for the given field. If the field is of the type int
	 * (char), the value String is converted before. 
	 * 
	 * @param fieldNo
	 * @param value
	 */
	public void setValueAsString(int fieldNo, String value) {
		if (getTypes()[fieldNo].equals(TableMediator.SQL_TYPE_INTEGER)) {
			int intValue = Integer.parseInt(value.trim());
			setIntValue(fieldNo,intValue);
		} else if (getTypes()[fieldNo].equals(TableMediator.SQL_TYPE_STRING)) {
			setStringValue(fieldNo,value);
		}
	}
	
	/**
	 * set the Value for the given field. If the field is of the type int
	 * (char), the value String is converted before. 
	 * 
	 * @param field the name of the field
	 * @param value
	 */
	public void setValueAsString(String field, String value) {
		if (getTypes()[getFieldNo(field)].equals(TableMediator.SQL_TYPE_INTEGER)) {
			int intValue = Integer.parseInt(value.trim());
			setIntValue(getFieldNo(field),intValue);
		} else if (getTypes()[getFieldNo(field)].equals(TableMediator.SQL_TYPE_STRING)) {
			setStringValue(getFieldNo(field),value);
		}
	}

	/**
	 * Initialize entity type names from existing database
	 * 
	 * @param table
	 */
	protected static final String[] initTypes(String table)
	{
		String [] types=null;
		try {
				String query = "select * from "+table;
				ResultSet rs = Context.getInstance().executeQuery(query);
				assert (rs!=null) : "Tabelle existiert nicht: "+table;
				
				int length = rs.getMetaData().getColumnCount();
				// initialize and load mEntityTypes
				types = new String [length];
				for (int i=1; i <= length; i++)
				{
					String s = rs.getMetaData().getColumnTypeName(i).toUpperCase().trim();
					if (s.equals("CHAR")){
						types[i-1]=TableMediator.SQL_TYPE_STRING;
					}else if (s.equals("NUMBER")){
						types[i-1]=TableMediator.SQL_TYPE_INTEGER;
					}else {
						assert (false) : "unsupported database format:"+s; 
					}
				}
		}
		catch (SQLException e) {
			MainFrame.log(e.getMessage());}
		catch (EvilException e2) {
			MainFrame.log(e2.getMessage());}
		
		return types;
	}

	/**
	 * Initialize entity field names from existing database
	 * 
	 * @param table
	 */
	protected static final String[] initFields(String table)
	{
		String [] fields=null;
		try {
				String query = "select * from "+table;
				ResultSet rs = Context.getInstance().executeQuery(query);
				assert (rs!=null) : "Tabelle existiert nicht: "+table;
				
				int length = rs.getMetaData().getColumnCount();
				// initialize and load mEntityTypes
				fields = new String [length];
				for (int i=1; i <= length; i++)	{
					fields[i-1] = rs.getMetaData().getColumnName(i).toUpperCase().trim();
				}
		}
		catch (SQLException e) {
			MainFrame.log(e.getMessage());}
		catch (EvilException e2) {
			MainFrame.log(e2.getMessage());}
		
		return fields;
	}
	
	
	/**
	 * Check is the given name is an Entity or a Relation within the database schema
	 * 
	 * @param name
	 */
	public static Boolean isEntityOrRelation(String name)
	{
		return isEntity(name) || isRelation(name);
	};
	
	/**
	 * Check is the given name is an Entity within the database schema
	 * 
	 * @param name
	 */
	public static boolean isEntity(String name)
	{
		boolean isEntity = false;
		
		// Force loading of Entities, if not already done
		mEntityTypes = loadEntityTypes(); 
		
		for (int i=0; i< mEntityTypes.size(); i++) {
			if (mEntityTypes.get(i).toUpperCase().trim().equals(name.toUpperCase().trim())) {isEntity = true;};
		};
		return isEntity;
	};
	
	/**
	 * Check is the given name is a Relation within the database schema
	 * 
	 * @param name
	 */
	public static boolean isRelation(String name)
	{
		boolean isRelation = false;
		
		// Force loading of Relations, if not already done
		mRelationTypes = loadRelationTypes(); 
		
		for (int i=0; i< mRelationTypes.size(); i++) {
			if (mRelationTypes.get(i).equals(name)) {isRelation = true;};
		};
		return isRelation;
	};
	
	/**
	 * loads, inits and returns the static ArrayList<String> mEntityTypes with all the
	 * Entities from the database schema
	 * @return
	 */
	
	private static ArrayList<String> loadEntityTypes()
	{
		mEntityTypes = initFromDB(mEntityTypes, "FUCMS_Entities", "name");
		return mEntityTypes;
	}

	/**
	 * loads, inits and returns the static ArrayList<String> mRelationTypes with all the
	 * Relatons from the database schema
	 * @return
	 */
	
	private static ArrayList<String> loadRelationTypes()
	{
		mRelationTypes = initFromDB(mRelationTypes, "FUCMS_Relations", "name");
		return mRelationTypes;
	}

	/**
	 * loads, inits returns an ArrayList<T> from the Database Table mTable
	 * @return
	 * @param sVariable the ArrayList<T> to be initialized
	 * @param mTable the database table from with the ArrayList<T> is loaded
	 * @param mAttribute is the attribute to be read into the Array
	 */	
		private static <T> ArrayList<T> initFromDB(ArrayList<T> sVariable, String mTable, String mAttribute)
	{
		return SQLUtils.arrayFromDB(sVariable, mTable , mAttribute, "*", "*" );
	};
}
