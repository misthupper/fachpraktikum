package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person_Einrichtung extends Entity {

	private static final String TABLE="Person_Einrichtung";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Person_Einrichtung() {
		super();
	}

	public String[] getFields() {
		return Person_Einrichtung.sFields;
	}

	public String getKey() {
		return Person_Einrichtung.KEY;
	}

	public String getTable() {
		return Person_Einrichtung.TABLE;
	}

	public String[] getTypes() {
		return Person_Einrichtung.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Person_Einrichtung();
	}
}

