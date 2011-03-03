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
		
		// Strip #text nodes
		/*
		for(int i = 0; i < numbers.getLength(); i++) {
			NodeList numberChildren = numbers.item(i).getChildNodes();
			
			if(numbers.item(i).getNodeName() == "#text") {
				Node rem = numbers.item(i);
				tree.removeChild(rem);
			}
		}
		*/
		
		for(int i = 0; i < numbers.getLength(); i++) {
			System.out.println(numbers.item(i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		NumberParser numParser = new NumberParser();
		
		String testCase = "five";
		if(numParser.isNumber(testCase)) {
			System.out.println(testCase + " is a number");
		} else System.out.println(testCase + " is not a number");
		
		/*
	      // first of all we request out 
	      // DOM-implementation through factory above
	      // then we have to create document-loader:
	      loader = factory.newDocumentBuilder();
	
	      // loading a DOM-tree...
	      Document document = loader.parse(xmlFilename);
	      // at last, we get a root element:
	      tree = document.getDocumentElement();
	      System.out.println(tree);
	      System.out.println("root has children: " + tree.hasChildNodes());
	      
	      NodeList nodes = tree.getChildNodes();
	      int l = nodes.getLength();
	      System.out.println("Number of children: " + l);
	      for(int i = 0; i < l; i++) {
	    	  Node node = nodes.item(i);
	    	  if(node.getNodeName() == "number") {
	    		  Element number = (Element) node;
	    		  NodeList numNodes = number.getChildNodes();
	    		  for(int j = 0; j < numNodes.getLength(); j++) {
	    			  if(numNodes.item(j).getNodeName() == "text") {
	    				  Element numVal = (Element) numNodes.item(j);
	    				  System.out.println(numVal.getTextContent());
	    			  }
	    		  }
	    	  }
	      }
	      */
		      
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
