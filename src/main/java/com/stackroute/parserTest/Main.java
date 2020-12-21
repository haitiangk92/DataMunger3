package com.stackroute.parserTest;

import com.stackroute.datamunger.reader.CsvQueryProcessor;
import com.stackroute.datamunger.query.parser.QueryParser;
import com.stackroute.datamunger.query.parser.Restriction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.stackroute.datamunger.query.parser.QueryParameter;

public class Main {

	public static void main(String[] args) {

		String query = "select team1, team2, venue from data/ipl.csv where win_by_runs > 40 and win_by_wickets > 3";
		QueryParser parser = new QueryParser();
		QueryParameter parameters = parser.parseQuery(query);
		CsvQueryProcessor reader = null;
		List<String> fields = null;
		String[] headers = null;
		String[] types = null;

		try {
			reader = new CsvQueryProcessor(parameters.getFileName());
			fields = parameters.getFields();
			headers = reader.getHeader().getHeaders();
			types = reader.getColumnType().getDataTypes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HashMap<String, Integer> fieldsIdx = new HashMap<>();

		for (String field : fields) {
			for (int i = 0; i < headers.length; i++) {
				if (headers[i].equals(field)) {
					fieldsIdx.put(field, i);
				}
			}
		}

		List<Restriction> restrictions = parameters.getRestrictions();
		HashMap<String, HashMap<Restriction, Integer>> restrictionIdx = new HashMap<>();

		for (Restriction restriction : restrictions) {
			for (int i = 0; i < headers.length; i++) {
				String conditionField = restriction.getName();
				if (conditionField.equals(headers[i])) {
					HashMap<Restriction, Integer> map = new HashMap<>();
					map.put(restriction, i);
					restrictionIdx.put(conditionField,map);
				}
			}
		}

		System.out.println(fieldsIdx);
		System.out.println(restrictionIdx);
		
		
		List<String[]> results = new ArrayList<>();
		
		String row = reader.getDataRow();
		
		while(!row.equals("EOF")){
			String[] split = row.split(",");
			
			
		}
		

	}

}
