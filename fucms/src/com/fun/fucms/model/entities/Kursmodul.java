package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Kursmodul extends Entity {

	private static final String TABLE="Kursmodul";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Kursmodul() {
		super();
	}

	public String[] getFields() {
		return Kursmodul.sFields;
	}

	public String getKey() {
		return Kursmodul.KEY;
	}

	public String getTable() {
		return Kursmodul.TABLE;
	}

	public String[] getTypes() {
		return Kursmodul.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Kursmodul();
	}
}
