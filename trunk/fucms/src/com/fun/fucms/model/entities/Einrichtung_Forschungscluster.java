package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Einrichtung_Forschungscluster extends Entity {

	private static final String TABLE="Einrichtung_Forschungscluster";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Einrichtung_Forschungscluster() {
		super();
	}

	public String[] getFields() {
		return Einrichtung_Forschungscluster.sFields;
	}

	public String getKey() {
		return Einrichtung_Forschungscluster.KEY;
	}

	public String getTable() {
		return Einrichtung_Forschungscluster.TABLE;
	}

	public String[] getTypes() {
		return Einrichtung_Forschungscluster.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Einrichtung_Forschungscluster();
	}
}
