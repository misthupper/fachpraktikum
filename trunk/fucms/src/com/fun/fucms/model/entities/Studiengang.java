package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Studiengang extends Entity {

	private static final String TABLE="Studiengang";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Studiengang() {
		super();
	}

	public String[] getFields() {
		return Studiengang.sFields;
	}

	public String getKey() {
		return Studiengang.KEY;
	}

	public String getTable() {
		return Studiengang.TABLE;
	}

	public String[] getTypes() {
		return Studiengang.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Studiengang();
	}
}