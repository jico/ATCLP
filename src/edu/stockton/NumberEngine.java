package edu.stockton;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class NumberEngine {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document numbersDoc;
	private static Element tree;
	private static NodeList numbersList;
	private static String xmlFilename = "numbers.xml";
	
	private static HashMap numbers;
	
	public NumberEngine() throws Exception {
		loader = factory.newDocumentBuilder();
		numbersDoc = loader.parse(xmlFilename);
		tree = numbersDoc.getDocumentElement();
		numbersList = tree.getChildNodes();
		
		numbers = new HashMap();
		for(int i = 0; i < numbersList.getLength(); i++) {
			NodeList numberNodes = numbersList.item(i).getChildNodes();
			
			// Number constructor params
			int value = -1;
			int weight = -1;
			String text = "";
			
			// Retrieve number data 
			for(int j = 0; j < numberNodes.getLength(); j++) {
				Element numberNode = (Element) numberNodes.item(j);
				String xmlTag = numberNode.getTagName();
				
				if(xmlTag.equalsIgnoreCase("value")) value = Integer.parseInt(numberNode.getTextContent());
				if(xmlTag.equalsIgnoreCase("weight")) weight = Integer.parseInt(numberNode.getTextContent());
				if(xmlTag.equalsIgnoreCase("text")) text = numberNode.getTextContent();

			}
			
			Number number = new Number(text, value, weight);
			numbers.put(text, number);
			
			
		}
	}
	
	
	/**
	 * Checks to see if a word represents a number.
	 * @param s The string of text
	 * @return True if the word represents a number, false otherwise
	 */
	public static boolean isNumber(String s) {
		return numbers.containsKey(s);
	}
	
	public static String toNumeric(String text) {
		String[] tokens = text.split("\\-|\\ ");

		LinkedList<String> numberTokens = new LinkedList<String>();
		for(String token : tokens) numberTokens.add(token);
		ListIterator<String> cursor = numberTokens.listIterator();
		
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
		Number number = (Number) numbers.get(s);
		return number.getValue();
		
	}
	
	/**
	 * Returns the weight of the word string
	 * @param s String of the number word
	 * @return integer weight value of the string, returns -1 if string is NAN
	 */
	public static int getWeight(String s) {
		Number number = (Number) numbers.get(s);
		return number.getWeight();
	}

	
}
