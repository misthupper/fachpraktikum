package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person extends Entity {

	private static final String TABLE="person";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];

	public Person() {
		super();
	}

	public String[] getFields() {
		return Person.sFields;
	}

	public String getKey() {
		return Person.KEY;
	}

	public String getTable() {
		return Person.TABLE;
	}

	public String[] getTypes() {
		return Person.sTypes;
	}

	public Entity getNewInstance() {
		return new Person();
	}
}
