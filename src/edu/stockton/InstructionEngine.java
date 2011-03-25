package edu.stockton;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class InstructionEngine {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document instructionsDoc;
	private static Element tree;
	private static NodeList instructionsList;
	private static String xmlFilename = "instructions.xml";
	
	private static HashMap instructions;
	
	public InstructionEngine() throws Exception {
		loader = factory.newDocumentBuilder();
		instructionsDoc = loader.parse(xmlFilename);
		tree = instructionsDoc.getDocumentElement();
		instructionsList = tree.getChildNodes();
		
		instructions = new HashMap();
		for(int i = 0; i < instructionsList.getLength(); i++) {
			NodeList instructionNodes = instructionsList.item(i).getChildNodes();
			
			// instruction constructor params
			String type = "";
			String phrase = "";
			ArrayList<Param> params;
			
			// Retrieve instruction data 
			for(int j = 0; j < instructionNodes.getLength(); j++) {
				Element instructionNode = (Element) instructionNodes.item(j);
				String xmlTag = instructionNode.getTagName();
				
				if(xmlTag.equalsIgnoreCase("type")) type = instructionNode.getTextContent();
				if(xmlTag.equalsIgnoreCase("phrase")) phrase = instructionNode.getTextContent();
				if(xmlTag.equalsIgnoreCase("params")) {
					NodeList paramNodes = instructionNode.getChildNodes();
					
					
					
				}

			}
			
			Instruction instruction = new Instruction();
			instructions.put(phrase, instruction);
			
			
		}
	}
}
