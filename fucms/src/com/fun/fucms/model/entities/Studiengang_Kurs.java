package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Studiengang_Kurs extends Entity {

	private static final String TABLE="Studiengang_Kurs";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Studiengang_Kurs() {
		super();
	}

	public String[] getFields() {
		return Studiengang_Kurs.sFields;
	}

	public String getKey() {
		return Studiengang_Kurs.KEY;
	}

	public String getTable() {
		return Studiengang_Kurs.TABLE;
	}

	public String[] getTypes() {
		return Studiengang_Kurs.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Studiengang_Kurs();
	}
}