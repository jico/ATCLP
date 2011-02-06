package edu.stockton;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	 * @param text English word of number
	 * @return True if the English word represents a number, false otherwise.
	 */
	public static boolean isNumber(String text) {
		if(numDictionary.has(text)) return true;
		else return false;
	}
}
