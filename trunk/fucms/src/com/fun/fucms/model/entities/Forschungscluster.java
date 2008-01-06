package com.fun.fucms.model.entities;

import com.fun.fucms.model.Entity;

public class Forschungscluster extends Entity {

	private static final String TABLE="Forschungscluster";
	
	private static final String[] sTypes=initTypes(TABLE);
	private static String[] sFields=initFields(TABLE);
	private static final String KEY=sFields[0];
	
	public Forschungscluster() {
		super();
	}

	public String[] getFields() {
		return Forschungscluster.sFields;
	}

	public String getKey() {
		return Forschungscluster.KEY;
	}

	public String getTable() {
		return Forschungscluster.TABLE;
	}

	public String[] getTypes() {
		return Forschungscluster.sTypes;
	}
	
	public Entity getNewInstance() {
		return new Forschungscluster();
	}
}
