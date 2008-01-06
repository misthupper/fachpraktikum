package com.fun.fucms.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fun.fucms.Context;
import com.fun.fucms.EvilException;
import com.fun.fucms.gui.MainFrame;
import com.fun.fucms.model.*;
import com.fun.fucms.model.entities.*;

/**
 * Some static methods for reading and updating Entitities
 * @author rod
 *
 */
public class TableMediator {
	
	public static final String SQL_TYPE_INTEGER = "INTEGER";
	public static final String SQL_TYPE_STRING = "STRING";
	
	/**
	 * fills Entity e with record for the given key
	 * @param e Entity, values will be overwritten
	 * @param key value of the key 
	 * @return false if there was an exception
	 */
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
	
	/**
	 * returns an ArrayList with all values of the key field for the given entity
	 * @param e the Entity
	 * @return ArrayList<Object> filled with keys
	 */
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
	
	/**
	 * save given entity, makes an insert. If key already exists, makes a delete before.
	 * @param entity
	 */
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
	
	/**
	 * delete record with key given in entity
	 * @param entity
	 */
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
	
	/**
	 * return true if key from entity already exists in DB, false otherwise
	 * @param entity
	 * @return
	 */
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
	
	/**
	 * fills EntityTableModel with data of the whole table
	 * @param etm EntityTableModel
	 */
	public static void updateEntityTableModell(EntityTableModel etm) {
		
		Entity entity = etm.getNewEntity();
		
		int rows=1;
		int columns=0;
		ArrayList<Object> keys = new ArrayList<Object>();
		ArrayList<Object> entities = new ArrayList<Object>();
		
		for (String field : entity.getFields()) {
			entities.add(field);
			columns++;
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from " + etm.getNewEntity().getTable());
		try {
			ResultSet rs = Context.getInstance().executeQuery(sb.toString());
			while (rs.next()) {
				rows++;
				for (int i=0; i < entity.getTypes().length; i++) {
					Object o = null;
					if (entity.getTypes()[i].equals(TableMediator.SQL_TYPE_INTEGER)) {
						o = new Integer(rs.getInt(entity.getFields()[i]));
					} else if (entity.getTypes()[i].equals(TableMediator.SQL_TYPE_STRING)) {
						o = rs.getString(entity.getFields()[i]);
					}
					entities.add(o);
					if (entity.getFields()[i].equals(entity.getKey())) {
						keys.add(o);
					}
				}
			}
			rs.close();
			etm.fillData(rows, columns, keys, entities);
		} catch (SQLException e1) {
			MainFrame.log(e1.getMessage());
		} catch (EvilException e2) {
			MainFrame.log(e2.getMessage());
		}
	}

	public static Entity createEntitybyName(String name){
		assert (Entity.isEntityOrRelation(name)) : "Tabellenname ist keine Entity oder Relation!";
		Entity e=null;
		name = name.toUpperCase().trim();
		if (name.equals("Kursmodul_Kurs".toUpperCase())) e=new Kursmodul_Kurs();
		else if (name.equals("Person_Kurs".toUpperCase())) e=new Person_Kurs();
		else if (name.equals("Studiengang_Kurs".toUpperCase())) e=new Studiengang_Kurs();
		else if (name.equals("Einrichtung_Forschungscluster".toUpperCase())) e=new Einrichtung_Forschungscluster();
		else if (name.equals("Person_Einrichtung".toUpperCase())) e=new Person_Einrichtung();
		else if (name.equals("Abschlussarbeit".toUpperCase())) e=new Abschlussarbeit();
		else if (name.equals("Forschungscluster".toUpperCase())) e=new Forschungscluster();
		else if (name.equals("Person_Raum".toUpperCase())) e=new Person_Raum();
		else if (name.equals("Person_Telefon".toUpperCase())) e=new Person_Telefon();
		else if (name.equals("Person_Email".toUpperCase())) e=new Person_Email();
		else if (name.equals("Person".toUpperCase())) e=new Person();
		else if (name.equals("Raum".toUpperCase())) e=new Raum();
		else if (name.equals("Gebaeude".toUpperCase())) e=new Gebaeude();
		else if (name.equals("Kurs".toUpperCase())) e=new Kurs();
		else if (name.equals("Kursmodul".toUpperCase())) e=new Kursmodul();
		else if (name.equals("Studiengang".toUpperCase())) e=new Studiengang();
		else if (name.equals("Einrichtung".toUpperCase())) e=new Einrichtung();
		else {
			assert false: "Tabellenname ist keine Entity oder ist nicht implementiert!";
		}
			
		return e;
	};
}
