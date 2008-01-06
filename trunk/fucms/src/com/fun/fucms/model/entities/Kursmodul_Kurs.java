package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Kursmodul_Kurs extends Entity {

	private static final String TABLE="Kursmodul_Kurs";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Kursmodul_Kurs() {
		super();
	}

	public String[] getFields() {
		return Kursmodul_Kurs.sFields;
	}

	public String getKey() {
		return Kursmodul_Kurs.KEY;
	}

	public String getTable() {
		return Kursmodul_Kurs.TABLE;
	}

	public String[] getTypes() {
		return Kursmodul_Kurs.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Kursmodul_Kurs();
	}
}
