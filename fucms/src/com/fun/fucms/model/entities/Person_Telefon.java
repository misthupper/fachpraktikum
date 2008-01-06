package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person_Telefon extends Entity {

	private static final String TABLE="Person_Telefon";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Person_Telefon() {
		super();
	}

	public String[] getFields() {
		return Person_Telefon.sFields;
	}

	public String getKey() {
		return Person_Telefon.KEY;
	}

	public String getTable() {
		return Person_Telefon.TABLE;
	}

	public String[] getTypes() {
		return Person_Telefon.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Person_Telefon();
	}
}

