package com.fun.fucms.model;


public class Gebaeude extends Entity {
	
	public static final String[] sTypes = {
		TableMediator.SQL_TYPE_INTEGER,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_INTEGER,
		//TableMediator.SQL_TYPE_STRING,
		//TableMediator.SQL_TYPE_STRING,
		//TableMediator.SQL_TYPE_STRING,
		};
	
	public static final String[] sFields= {
		"id",
		"strasse",
		"hausnummer",
		//"titel",
		//"telefon",
		//"email"
		};
	
	private static final String TABLE="gebaeude";
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



}
