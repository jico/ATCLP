package edu.stockton;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.json.JSONObject;
import org.w3c.dom.*;

public class NumberParser {
	private static DocumentBuilder builder;
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document numbersDoc;
	private static Element tree;
	private static NodeList numbers;
	private static String xmlFilename = "numbers.xml";
	
	public NumberParser() throws Exception {
		loader = factory.newDocumentBuilder();
		numbersDoc = loader.parse(xmlFilename);
		tree = numbersDoc.getDocumentElement();
		numbers = tree.getChildNodes();
	}
	
	/**
	 * Checks to see if a word represents a number.
	 * @param s The string of text
	 * @return True if the word represents a number, false otherwise
	 */
	public static boolean isNumber(String s) {
		for(int i = 0; i < numbers.getLength(); i++) {
			Element number = (Element) numbers.item(i);
			if(s.equals(number.getFirstChild().getTextContent())) return true;
		}
		return false;
	}
	
	public static String toNumeric(String text) {
		String[] tokens = text.split("\\-|\\ ");

		LinkedList<String> numbers = new LinkedList<String>();
		for(String token : tokens) numbers.add(token);
		ListIterator<String> cursor = numbers.listIterator();
		
		String numericString = "";
		while(cursor.hasNext()) {
			String current = cursor.next();
			current = current.toLowerCase();

			if(current.equalsIgnoreCase("hundred")) {
				/*
				if(cursor.hasNext()) {
					String next = cursor.next();
					int nextVal = numDictionary.getJSONObject(next).getInt("value");
					int nextWeight = numDictionary.getJSONObject(next).getInt("weight");
					int nextNum = nextVal * nextWeight;
					if(nextNum < 10) numString += "0" + nextNum;
					else if(nextNum < 20 || !cursor.hasNext()) numString += nextNum; 
					else cursor.previous();
				} else {
					numString += "00";
				}
				*/
			} else {
				/*
				JSONObject currentObj = numDictionary.getJSONObject(current);
				numString += currentObj.getInt("value");
				int currentWeight = currentObj.getInt("weight");
				if(currentWeight != 1) {
					if(cursor.hasNext()) {
						String next = cursor.next();
						JSONObject nextObj = numDictionary.getJSONObject(next);
						int nextWeight = nextObj.getInt("weight");
						if(nextWeight != 1) numString += "0";
						cursor.previous();
					} else numString += "0";
				}
				*/
			}
		}
		
		return numericString;
	}
	
	/**
	 * Returns the value (not actual numeric representation) of the string
	 * as specified in numbers.xml document
	 * @param s String of the number word
	 * @return integer value of number word
	 */
	public static int getValue(String s) {
		Element number = getElement(s);
		NodeList numberChildren = number.getChildNodes();
		String value = numberChildren.item(1).getTextContent();
		return Integer.parseInt(value);
	}
	
	/**
	 * Finds the node of the given word representation of a number
	 * and returns it as an Element
	 * @param number The word representation of a number
	 * @return Element of the passed number
	 */
	public static Element getElement(String number) {
		Element numberElement = null;
		for(int i = 0; i < numbers.getLength(); i++) {
			Node numberNode = numbers.item(i);
			if(number.equalsIgnoreCase(numberNode.getFirstChild().getTextContent())) {
				return numberElement = (Element) numberNode;
			}
		}
		return numberElement;
	}
	
	
	
	
}
