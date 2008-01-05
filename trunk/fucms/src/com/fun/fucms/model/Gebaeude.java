package com.fun.fucms.model;


public class Gebaeude extends Entity {

	private static final String TABLE="gebaeude";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Gebaeude() {
		super();
	}

	public String[] getFields() {
		return Gebaeude.sFields;
	}

	public String getKey() {
		return Gebaeude.KEY;
	}

	public String getTable() {
		return Gebaeude.TABLE;
	}

	public String[] getTypes() {
		return Gebaeude.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Gebaeude();
	}



}
