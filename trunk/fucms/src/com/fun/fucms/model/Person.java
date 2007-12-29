package com.fun.fucms.model;


public class Person extends Entity {
	
	public static final String[] sTypes = {
		TableMediator.SQL_TYPE_INTEGER,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		//TableMediator.SQL_TYPE_STRING,
		//TableMediator.SQL_TYPE_STRING,
		};
	
	public static final String[] sFields= {
		"id",
		"name",
		"vorname",
		"titel",
		//"telefon",
		//"email"
		};
	
	private static final String TABLE="person";
	private static final String KEY=sFields[0];
	
	
	public Person() {
		super();
	}
	
	


	public String[] getFields() {
		return Person.sFields;
	}

	public String getKey() {
		return Person.KEY;
	}

	public String getTable() {
		return Person.TABLE;
	}

	public String[] getTypes() {
		return Person.sTypes;
	}



}
