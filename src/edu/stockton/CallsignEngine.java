package edu.stockton;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CallsignEngine {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document callsignsDoc;
	private static Element tree;
	private static NodeList callsigns;
	private static String xmlFilename = "callsignsTestSet.xml";
	
	public CallsignEngine() throws Exception {
		loader = factory.newDocumentBuilder();
		callsignsDoc = loader.parse(xmlFilename);
		tree = callsignsDoc.getDocumentElement();
		callsigns = tree.getChildNodes();
	}
	
	public static boolean isDesignator(String text) {
		for(int i = 0; i < callsigns.getLength(); i++) {
			Element callsign = (Element) callsigns.item(i);
			if(text.equalsIgnoreCase(callsign.getFirstChild().getTextContent())) return true;
		}
		return false;
	}
	
	public static String designatorToCompany(String designator) {
		if(!isDesignator(designator)) return "Unrecognized designator";
		
		for(int i = 0; i < callsigns.getLength(); i++) {
			Element callsign = (Element) callsigns.item(i);
			if(designator.equalsIgnoreCase(callsign.getFirstChild().getTextContent())) {
				return callsign.getLastChild().getTextContent();
			}
		}
		
		return "Unrecognized designator";
	}
	
	public static String companyToDesignator(String company) {		
		for(int i = 0; i < callsigns.getLength(); i++) {
			Element callsign = (Element) callsigns.item(i);
			if(company.equalsIgnoreCase(callsign.getLastChild().getTextContent())) {
				return callsign.getFirstChild().getTextContent();
			}
		}
		
		return "Unrecognized company";
	}
}
