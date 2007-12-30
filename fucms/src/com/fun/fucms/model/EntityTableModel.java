package com.fun.fucms.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EntityTableModel extends AbstractTableModel {
	
	private Entity mEntity;
	private int mRows, mColumns;
	private ArrayList<Object> mKeys;
	private ArrayList<Object> mEntities;
	
	public void update() {
		TableMediator.updateEntityTableModell(this);
	}
	
	public void fillData(int rows, int columns,
			ArrayList<Object> keys, ArrayList<Object> entities) {
		mRows = rows;
		mColumns = columns;
		mKeys = keys;
		mEntities = entities;
	}
	

	public EntityTableModel(Entity e) {
		mEntity = e;
		update();
	}

	public int getColumnCount() {
		return mColumns;
	}

	public int getRowCount() {
		return mRows;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return mEntities.get(columnIndex + (rowIndex*(mColumns)));
	}
	
	public Entity getEntity(int row) {
		Entity entity = mEntity.getNewInstance();
		Object key = mKeys.get(row -1);
		TableMediator.getRowByKey(entity, key);
		return entity;
	}
	
	public Entity getNewEntity() {
		Entity entity = mEntity.getNewInstance();
		return entity;
	}

}
