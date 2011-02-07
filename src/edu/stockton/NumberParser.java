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
	 * Converts a string of numbers in English word format to numeric format.
	 * @param text The string to convert.
	 * @return The corresponding number string representation.
	 */
	public static String toNum(String text) throws JSONException {
		String[] keys = text.split("\\-|\\ ");
		LinkedList numbers = new LinkedList();
		for(String k : keys) numbers.add(k);
		ListIterator cursor = numbers.listIterator();
		
		String numString = "";
		while(cursor.hasNext()) {
			String current = (String) cursor.next();
			current = current.toLowerCase();
			if(current.equalsIgnoreCase("hundred")) {
				if(cursor.hasNext()) {
					String next = (String) cursor.next();
					int nextVal = (int) numDictionary.getJSONObject(next).getInt("value") * numDictionary.getJSONObject(next).getInt("weight");
					if(nextVal < 10) {
						numString += "0";
						numString += nextVal;
					} else if (nextVal < 20) {
						numString += nextVal; 
					} else if (!cursor.hasNext()) {
						numString += nextVal;
					} else cursor.previous();
				} else {
					numString += "00";
				}
			} else {
				JSONObject currentVal = (JSONObject) numDictionary.getJSONObject(current);
				numString += currentVal.getInt("value");
			}
			
		}
		
		return numString;
	}
}
