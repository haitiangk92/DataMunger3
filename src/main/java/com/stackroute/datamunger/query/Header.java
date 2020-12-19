package com.stackroute.datamunger.query;

public class Header {
	
	private String[] headers;
	
	public Header() {
		headers = null;
	}
	
	public Header(String[] headers) {
		this.headers = headers;
	}

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the headers.
	 */
	
	public String[] getHeaders() {
		return headers;
	}

}
