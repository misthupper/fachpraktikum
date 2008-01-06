package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person_Kurs extends Entity {

	private static final String TABLE="Person_Kurs";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Person_Kurs() {
		super();
	}

	public String[] getFields() {
		return Person_Kurs.sFields;
	}

	public String getKey() {
		return Person_Kurs.KEY;
	}

	public String getTable() {
		return Person_Kurs.TABLE;
	}

	public String[] getTypes() {
		return Person_Kurs.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Person_Kurs();
	}
}


