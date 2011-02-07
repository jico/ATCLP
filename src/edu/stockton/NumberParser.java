package edu.stockton;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.JSONException;

/**
 * Number parsing and identifying utility.
 * 
 * @author Jico Baligod
 * @version 0.1a
 *
 */
public class NumberParser {
	private static JSONObject numDictionary;
	private static String numSourcePath = "numbersJSON.txt";
	
	public NumberParser() throws FileNotFoundException, JSONException {
		// Initialize numbers dictionary
		FileReader sourceReader = new FileReader(numSourcePath);
		Scanner jsonIn = new Scanner(sourceReader);
		String jsonNumbers = "";
		while(jsonIn.hasNext()) jsonNumbers += jsonIn.next();
		numDictionary = new JSONObject(jsonNumbers);
	}
	
	/**
	 * Determines whether a string of text of natural language is
	 * a number.
	 * @param text English word of number.
	 * @return True if the English word represents a number, false otherwise.
	 */
	public static boolean isNumber(String text) {
		if(numDictionary.has(text)) return true;
		else return false;
	}
	
	/**
	 * Converts a string of English worded numbers to an integer.
	 * @param text The string to convert.
	 * @return The corresponding integer representation.
	 */
	public static int toInt(String text) throws JSONException {
		String[] keys = text.split("\\-|\\ ");
		LinkedList numbers = new LinkedList();
		for(String k : keys) numbers.add(k);
		ListIterator cursor = numbers.listIterator();
		
		int processedNum = 0;
		while(cursor.hasNext()) {
			String current = (String) cursor.next();
			current = current.toLowerCase();
			if(current.equalsIgnoreCase("hundred")) {
				processedNum *= 100;
			} else {
				JSONObject currentVal = (JSONObject) numDictionary.getJSONObject(current);
				processedNum += currentVal.getInt("value") * currentVal.getInt("weight");
			}
			
		}
		
		return processedNum;
	}
}
