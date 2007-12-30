package com.fun.fucms.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class EntityTableModel extends AbstractTableModel {
	
	private Entity mEntity;
	private int mRows, mColumns;
	private Object[] mEntities;
	
	private void update() {
		ArrayList<Object> keys = TableMediator.getKeys(mEntity);
		mRows = keys.size() +1;
		mColumns = mEntity.getFields().length;
		mEntities = new Object[mRows * mColumns];
		for (int row = 0 ; row < mRows ; row++) {
			for (int column = 0 ; column < mColumns ; column++) {
				if (row == 0) {
					mEntities[column] = mEntity.getFields()[column];
				} else {
					TableMediator.getRowByKey(mEntity, keys.get(row - 1));
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

}
