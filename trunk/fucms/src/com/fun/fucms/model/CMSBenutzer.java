package com.fun.fucms.model;



public class CMSBenutzer extends Entity {
	
	public static final String[] sTypes = {
		TableMediator.SQL_TYPE_INTEGER,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING,
		TableMediator.SQL_TYPE_STRING
		};
	
	public static final String[] sFields= {
		"id",
		"grad",
		"name",
		"vorname",
		"telefon",
		"email",
		"rechte",
		};
	
	private static final String TABLE="cmsbenutzer";
	private static final String KEY=sFields[0];
	
	
	public CMSBenutzer() {
		super();
	}
	
	


	public String[] getFields() {
		return CMSBenutzer.sFields;
	}

	public String getKey() {
		return CMSBenutzer.KEY;
	}

	public String getTable() {
		return CMSBenutzer.TABLE;
	}

	public String[] getTypes() {
		return CMSBenutzer.sTypes;
	}

	public Entity getNewInstance() {
		return new CMSBenutzer();
	}



}
