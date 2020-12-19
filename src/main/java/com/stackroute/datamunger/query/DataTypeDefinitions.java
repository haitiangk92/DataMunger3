package com.stackroute.datamunger.query;

public class DataTypeDefinitions {
	
	private String[] dataTypes;
	
	public DataTypeDefinitions() {
		dataTypes = null;
	}
	
	public DataTypeDefinitions(String[] dataTypes) {
		this.dataTypes = dataTypes;
	}

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */

	public String[] getDataTypes() {
		return dataTypes;
	}
}
