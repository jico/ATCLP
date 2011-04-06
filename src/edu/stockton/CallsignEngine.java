package edu.stockton;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Identifies aircraft telephony against the
 * callsigns XML library and can look up and convert
 * aircraft information.
 *
 */
public class CallsignEngine {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document callsignsDoc;
	private static Element tree;
	private static NodeList callsignsList;
	private static String xmlFilename = "callsigns.xml";
	
	private static HashMap callsigns;
	
	public static void init() {
		try {
			loader = factory.newDocumentBuilder();
			callsignsDoc = loader.parse(xmlFilename);
			tree = callsignsDoc.getDocumentElement();
			callsignsList = tree.getChildNodes();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		callsigns = new HashMap();
		for(int i = 0; i < callsignsList.getLength(); i++) {
			if(callsignsList.item(i).getNodeType() != 1) continue;
			NodeList callsignNodes = callsignsList.item(i).getChildNodes();
			
			// Number constructor params
			String telephony = "";
			String designator = "";
			String company = "";
			
			// Retrieve number data 
			for(int j = 0; j < callsignNodes.getLength(); j++) {
				if(callsignNodes.item(j).getNodeType() != 1) continue;
				Element callsignNode = (Element) callsignNodes.item(j);
				String xmlTag = callsignNode.getTagName();
				
				if(xmlTag.equalsIgnoreCase("telephony")) telephony = callsignNode.getTextContent().toLowerCase();
				if(xmlTag.equalsIgnoreCase("designator")) designator = callsignNode.getTextContent().toLowerCase();
				if(xmlTag.equalsIgnoreCase("company")) company = callsignNode.getTextContent().toLowerCase();
				
			}
			
			Callsign callsign = new Callsign(telephony, designator, company);
			callsigns.put(telephony, callsign);
		}
	}
	
	public static boolean isCallsign(String s) {
		if(callsigns == null) init();
		return callsigns.containsKey(s.toLowerCase());
	}
	
	public static String telephonyToDesignator(String telephony) {
		if(callsigns == null) init();
		Callsign callsign = (Callsign) callsigns.get(telephony.toLowerCase());
		return callsign.getDesignator().toUpperCase();
	}
	
	public static String telephonyToCompany(String telephony) {
		if(callsigns == null) init();
		Callsign callsign = (Callsign) callsigns.get(telephony.toLowerCase());
		return callsign.getCompany();
	}
}
