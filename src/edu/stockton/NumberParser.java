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
			if(s.equalsIgnoreCase(number.getFirstChild().getTextContent())) return true;
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
				
				if(cursor.hasNext()) {
					String next = cursor.next();
					int nextVal = getValue(next);
					int nextWeight = getWeight(next);
					int nextNum = nextVal * nextWeight;
					if(nextNum < 10) numericString += "0" + nextNum;
					else if(nextNum < 20 || !cursor.hasNext()) numericString += nextNum; 
					else cursor.previous();
				} else {
					numericString += "00";
				}
				
			} else {
				numericString += getValue(current);
				int currentWeight = getWeight(current);
				if(currentWeight != 1) {
					if(cursor.hasNext()) {
						String next = cursor.next();
						int nextWeight = getWeight(next);
						if(nextWeight != 1) numericString += "0";
						cursor.previous();
					} else numericString += "0";
				}
				
			}
		}
		
		return numericString;
	}
	
	/**
	 * Returns the value (not actual numeric representation) of the string
	 * as specified in numbers.xml document
	 * @param s String of the number word
	 * @return integer value of number word, returns -1 if string is NAN
	 */
	public static int getValue(String s) {
		Element number = getElement(s);
		if(number == null) return -1;
		NodeList numberChildren = number.getChildNodes();
		String value = numberChildren.item(1).getTextContent();
		return Integer.parseInt(value);
	}
	
	/**
	 * Returns the weight of the word string
	 * @param s String of the number word
	 * @return integer weight value of the string, returns -1 if string is NAN
	 */
	public static int getWeight(String s) {
		Element number = getElement(s);
		if(number == null) return -1;
		NodeList numberChildren = number.getChildNodes();
		String value = numberChildren.item(2).getTextContent();
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
