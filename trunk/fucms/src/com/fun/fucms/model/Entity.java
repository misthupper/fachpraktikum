package com.fun.fucms.model;


public abstract class Entity {
	
	public abstract String[] getFields();
	public abstract String getKey();
	public abstract String getTable();
	public abstract String[] getTypes();
	public abstract Entity getNewInstance();
	
	protected Object[] mValues;	
	
	protected Entity() {
		if (getFields().length != getTypes().length) {
			throw new IllegalStateException(getTable() + " is not defined well(1)!");
		}
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
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < getTypes().length; i++) {
			sb.append(getFields()[i] + " : " + mValues[i].toString() + "\n");
		}
		return sb.toString();
	}
	
	public String getKeyType() {
		for (int i=0; i<getFields().length ; i++) {
			if (getFields()[i].equals(getKey())) {
				return getTypes()[i];
			}
		}
		throw new IllegalStateException(getTable() + " is not defined well(2)!");
	}
	
	public String getKeyString() {
		String s = getKey() + " : " + getValueAsString(getKey());
		return s;
	}
	
	public Object getKeyValue() {
		if (getKeyType().equals(TableMediator.SQL_TYPE_INTEGER)) {
			return new Integer(getIntValue(getKey()));
		} else if (getKeyType().equals(TableMediator.SQL_TYPE_STRING)) {
			return getStringValue(getKey());
		}
		return null;
	}
	
	public void setIntValue(int fieldNo, int value) {
		mValues[fieldNo] = new Integer(value);
	}
	
	public void setStringValue(int fieldNo, String value) {
		mValues[fieldNo] = value;
	}
	
	public int getIntValue(int fieldNo) {
		return ((Integer) mValues[fieldNo]).intValue();
	}
	
	public String getStringValue(int fieldNo) {
		return (String) mValues[fieldNo];
	}
	
	public void setIntValue(String field, int value) {
		mValues[getFieldNo(field)] = new Integer(value);
	}
	
	public void setStringValue(String field, String value) {
		mValues[getFieldNo(field)] = value;
	}
	
	public int getIntValue(String field) {
		return ((Integer) mValues[getFieldNo(field)]).intValue();
	}
	
	public String getStringValue(String field) {
		return (String) mValues[getFieldNo(field)];
	}
	
	public String getValueAsString(int fieldNo) {
		String s = "";
		if (mValues[fieldNo] instanceof Integer) {
			s = ((Integer) mValues[fieldNo]).toString();
		} else if (mValues[fieldNo] instanceof String) {
			s = (String) mValues[fieldNo];
		}
		return s;
	}
	
	public String getValueAsString(String field) {
		String s = "";
		if (mValues[getFieldNo(field)] instanceof Integer) {
			s = ((Integer) mValues[getFieldNo(field)]).toString();
		} else if (mValues[getFieldNo(field)] instanceof String) {
			s = (String) mValues[getFieldNo(field)];
		}
		return s;
	}
	
	public void setValueAsString(int fieldNo, String value) {
		if (getTypes()[fieldNo].equals(TableMediator.SQL_TYPE_INTEGER)) {
			int intValue = Integer.parseInt(value.trim());
			setIntValue(fieldNo,intValue);
		} else if (getTypes()[fieldNo].equals(TableMediator.SQL_TYPE_STRING)) {
			setStringValue(fieldNo,value);
		}
	}
	
	public void setValueAsString(String field, String value) {
		if (getTypes()[getFieldNo(field)].equals(TableMediator.SQL_TYPE_INTEGER)) {
			int intValue = Integer.parseInt(value.trim());
			setIntValue(getFieldNo(field),intValue);
		} else if (getTypes()[getFieldNo(field)].equals(TableMediator.SQL_TYPE_STRING)) {
			setStringValue(getFieldNo(field),value);
		}
	}

}
