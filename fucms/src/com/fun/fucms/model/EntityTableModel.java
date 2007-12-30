package com.fun.fucms.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.fun.fucms.gui.MainFrame;

public class EntityTableModel extends AbstractTableModel {
	
	private Entity mEntity;
	private int mRows, mColumns;
	private ArrayList<Object> mKeys;
	private Object[] mEntities;
	
	public void update() {
		mKeys = TableMediator.getKeys(mEntity);
		mRows = mKeys.size() +1;
		mColumns = mEntity.getFields().length;
		mEntities = new Object[mRows * mColumns];
		for (int row = 0 ; row < mRows ; row++) {
			if (row > 0) {
				TableMediator.getRowByKey(mEntity, mKeys.get(row - 1));
			}
			for (int column = 0 ; column < mColumns ; column++) {
				if (row == 0) {
					mEntities[column] = mEntity.getFields()[column];
				} else {
					mEntities[column + (row*(mColumns))] = mEntity.mValues[column];
				}
			}
		}
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
		return mEntities[columnIndex + (rowIndex*(mColumns))];
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
