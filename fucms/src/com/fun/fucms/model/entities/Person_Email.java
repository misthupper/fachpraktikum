package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person_Email extends Entity {

	private static final String TABLE="Person_Email";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Person_Email() {
		super();
	}

	public String[] getFields() {
		return Person_Email.sFields;
	}

	public String getKey() {
		return Person_Email.KEY;
	}

	public String getTable() {
		return Person_Email.TABLE;
	}

	public String[] getTypes() {
		return Person_Email.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Person_Email();
	}
}

