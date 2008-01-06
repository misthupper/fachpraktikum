package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Kurs extends Entity {

	private static final String TABLE="Kurs";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Kurs() {
		super();
	}

	public String[] getFields() {
		return Kurs.sFields;
	}

	public String getKey() {
		return Kurs.KEY;
	}

	public String getTable() {
		return Kurs.TABLE;
	}

	public String[] getTypes() {
		return Kurs.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Kurs();
	}
}
