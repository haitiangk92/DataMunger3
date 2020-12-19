package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private BufferedReader bReader;

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		bReader = new BufferedReader(new FileReader(fileName));
		try {
			bReader.mark(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file. Note: Return type of the method will be
	 * Header
	 */

	@Override
	public Header getHeader() throws IOException {
		//Reset to beginning of buffer
		bReader.reset();
		
		// read the first line
		String headline = bReader.readLine();

		// populate the header object with the String array containing the header names
		return new Header(headline.split(","));
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */

	@Override
	public void getDataRow() {
	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. Note: Return Type of the method will be
	 * DataTypeDefinitions
	 */

	@Override
	public DataTypeDefinitions getColumnType() throws IOException {
		//Reset to beginning of buffer
		bReader.reset();
		
		//Read off the first line
		int numFields = bReader.readLine().split(",").length;
		String[] dataLine = bReader.readLine().split(",");
		
		String[] defs = new String[numFields];
		
		for(int i = 0; i < numFields; i++) {
			try {
				Integer.parseInt(dataLine[i]);
				defs[i] = "Integer";
			}catch(Exception e) {
				try {
				Double.parseDouble(dataLine[i]);
				defs[i] = "Double";
				}catch(Exception ex) {
					defs[i] = (i >= dataLine.length || dataLine[i].equals("")) ? "Unknown":"String";
				}
			}
		}
		
		return new DataTypeDefinitions(defs);
	}
}
