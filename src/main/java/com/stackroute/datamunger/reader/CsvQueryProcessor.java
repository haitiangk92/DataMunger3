package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	private BufferedReader bReader;
	private ArrayList<String> lines;
	private int currentRow;

	public CsvQueryProcessor() {
		bReader = null;
		lines = null;
		currentRow = 0;
	}

	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		bReader = new BufferedReader(new FileReader(fileName));
		currentRow = 1;
		lines = new ArrayList<>();

		try {
			String line = bReader.readLine();

			while (line != null) {
				lines.add(line);
				line = bReader.readLine();
			}
		} catch (IOException e) {
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
		String[] headers = null;

		try {
			// read the first line
			String headline = lines.get(0);
			headers = headline.split(",");
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}

		// populate the header object with the String array containing the header names
		return new Header(headers);
	}

	/**
	 * getDataRow() method will be used in the upcoming assignments
	 */

	@Override
	public String getDataRow() {
		if (currentRow >= lines.size()) {
			return "EOF";
		}

		return lines.get(currentRow++);
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
		// Read off the first line
		int numFields = lines.get(0).length();
		String[] definitions = new String[numFields];

		try {
			String[] dataLine = lines.get(1).split(",");

			for (int i = 0; i < numFields; i++) {
				try {
					Integer.parseInt(dataLine[i]);
					definitions[i] = "Integer";
				} catch (Exception e) {
					try {
						Double.parseDouble(dataLine[i]);
						definitions[i] = "Double";
					} catch (Exception ex) {
						definitions[i] = (i >= dataLine.length || dataLine[i].equals("")) ? "Unknown" : "String";
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}
		return new DataTypeDefinitions(definitions);
	}
}
