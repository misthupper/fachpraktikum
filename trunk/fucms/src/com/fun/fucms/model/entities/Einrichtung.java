package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Einrichtung extends Entity {

	private static final String TABLE="Einrichtung";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Einrichtung() {
		super();
	}

	public String[] getFields() {
		return Einrichtung.sFields;
	}

	public String getKey() {
		return Einrichtung.KEY;
	}

	public String getTable() {
		return Einrichtung.TABLE;
	}

	public String[] getTypes() {
		return Einrichtung.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Einrichtung();
	}
}
