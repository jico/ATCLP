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
	
	public NumberParser() throws Exception {
		loader = factory.newDocumentBuilder();
	}
	
	public static void main(String[] args) throws Exception {
		try {
		      // first of all we request out 
		      // DOM-implementation through factory above
		      // then we have to create document-loader:
		      DocumentBuilder loader = factory.newDocumentBuilder();

		      // loading a DOM-tree...
		      Document document = loader.parse("src/numbers.xml");
		      // at last, we get a root element:
		      Element tree = document.getDocumentElement();
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
		      
		    } catch (IOException ex) {
		      // any IO errors occur:
		      handleError(ex);
		    } catch (SAXException ex) {
		      // parse errors occur:
		      handleError(ex);
		    } catch (ParserConfigurationException ex) {
		      // document-loader cannot be created which,
		      // satisfies the configuration requested
		      handleError(ex);
		    } catch (FactoryConfigurationError ex) {
		      // DOM-implementation is not available 
		      // or cannot be instantiated:
		      handleError(ex);
		    }
	}
	
	private static final void handleError(Throwable ex) {
	    // ... handle error here...
	  }
}
