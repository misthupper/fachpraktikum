package com.fun.fucms.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


/**
 * Holds the data for the table inside of the EntityFrame
 * @author rod
 *
 */
public class EntityTableModel extends AbstractTableModel {
	
	public Entity mEntity;
	private int mRows, mColumns;
	private ArrayList<Object> mKeys;
	private ArrayList<Object> mEntities;
	
	/**
	 * reloads the data of the table from the database
	 *
	 */
	public void update() {
		TableMediator.updateEntityTableModell(this);
	}
	
	/**
	 * enables the TableMediator to fill in the data from the database
	 * @param rows
	 * @param columns
	 * @param keys
	 * @param entities
	 */
	public void fillData(int rows, int columns,
			ArrayList<Object> keys, ArrayList<Object> entities) {
		mRows = rows;
		mColumns = columns;
		mKeys = keys;
		mEntities = entities;
	}
	

	/**
	 * Constructor for a given Entity
	 * @param e
	 */
	public EntityTableModel(Entity e) {
		mEntity = e;
		update();
	}

	/**
	 * returns the number of columns
	 */
	public int getColumnCount() {
		return mColumns;
	}

	/**
	 * returns the number of rows
	 */
	public int getRowCount() {
		return mRows;
	}

	/**
	 * returns one value in the table
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return mEntities.get(columnIndex + (rowIndex*(mColumns)));
	}
	
	/**
	 * creates an Entity for the given row. The Entity is filled from the database.
	 * @param row
	 * @return
	 */
	public Entity getEntity(int row) {
		assert row>0 : "row muss größer 0 sein!";
		Entity entity = mEntity.getNewInstance();
		Object key = mKeys.get(row -1);
		TableMediator.getRowByKey(entity, key);
		return entity;
	}
	
	/**
	 * creates a new empty Entity of the same type as the initial Entity
	 * @return
	 */
	public Entity getNewEntity() {
		Entity entity = mEntity.getNewInstance();
		return entity;
	}

}
