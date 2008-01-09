package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Medien extends Entity {

	private static final String TABLE="Medien";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Medien() {
		super();
	}

	public String[] getFields() {
		return Medien.sFields;
	}

	public String getKey() {
		return Medien.KEY;
	}

	public String getTable() {
		return Medien.TABLE;
	}

	public String[] getTypes() {
		return Medien.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Medien();
	}
}

