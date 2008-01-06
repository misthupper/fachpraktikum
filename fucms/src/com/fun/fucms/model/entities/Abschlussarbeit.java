package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Abschlussarbeit extends Entity {

	private static final String TABLE="Abschlussarbeit";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Abschlussarbeit() {
		super();
	}

	public String[] getFields() {
		return Abschlussarbeit.sFields;
	}

	public String getKey() {
		return Abschlussarbeit.KEY;
	}

	public String getTable() {
		return Abschlussarbeit.TABLE;
	}

	public String[] getTypes() {
		return Abschlussarbeit.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Abschlussarbeit();
	}
}
