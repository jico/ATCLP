package edu.stockton;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

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
	
	
}
