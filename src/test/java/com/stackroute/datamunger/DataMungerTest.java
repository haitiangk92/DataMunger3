package com.stackroute.datamunger;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
import com.stackroute.datamunger.reader.CsvQueryProcessor;

public class DataMungerTest {

	private static CsvQueryProcessor reader;

	@BeforeAll
	public static void init() throws FileNotFoundException {
		reader = new CsvQueryProcessor("data/ipl.csv");
	}

	@AfterAll
	public static void close() throws FileNotFoundException {
		
	}

	@Test
	public void testRetrieveHeader() throws IOException {
		Assertions.assertTrue(reader.getHeader().getClass() == Header.class);
	}

	@Test
	public void testRetrieveHeaderFailure() throws IOException {
		
	}

	@Test
	public void testRetrieveDataTypes() throws IOException {
		Assertions.assertEquals(DataTypeDefinitions.class, reader.getColumnType().getClass());
	}

	@Test
	public void testRetrieveDataTypesFailure(){
	}

	@Test
	public void testFileNotFound() throws FileNotFoundException{
		
	}

	@Test
	public void testNotNullHeader() {
	}

	@Test
	public void testNotNullDataTypes() {
	}

	private void display(String testCaseName, String result){
		
	}

}