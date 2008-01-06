package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Raum extends Entity {

	private static final String TABLE="Raum";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Raum() {
		super();
	}

	public String[] getFields() {
		return Raum.sFields;
	}

	public String getKey() {
		return Raum.KEY;
	}

	public String getTable() {
		return Raum.TABLE;
	}

	public String[] getTypes() {
		return Raum.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Raum();
	}
}