package com.fun.fucms.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EntityTableModel extends AbstractTableModel {
	
	private Entity mEntity;
	

	public EntityTableModel(Entity e) {
		mEntity = e;
	}

	public int getColumnCount() {
		return mEntity.getFields().length;
	}

	public int getRowCount() {
		return TableMediator.getKeys(mEntity).size()+1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		int row = rowIndex;
		if (row == 0) {
			return mEntity.getFields()[columnIndex];
		} else {
			ArrayList<Object> keys = TableMediator.getKeys(mEntity);
			Object key = keys.get(--row);
			TableMediator.getRowByKey(mEntity, key);
			return mEntity.mValues[columnIndex];
		}
	}

}
