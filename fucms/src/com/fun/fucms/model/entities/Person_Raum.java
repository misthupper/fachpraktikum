package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Person_Raum extends Entity {

	private static final String TABLE="Person_Raum";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Person_Raum() {
		super();
	}

	public String[] getFields() {
		return Person_Raum.sFields;
	}

	public String getKey() {
		return Person_Raum.KEY;
	}

	public String getTable() {
		return Person_Raum.TABLE;
	}

	public String[] getTypes() {
		return Person_Raum.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Person_Raum();
	}
}


