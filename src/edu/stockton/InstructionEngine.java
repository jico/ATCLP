package edu.stockton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * Recognizes valid ATC commands by checking strings against
 * instructions XML data library.
 * Identifies instruction parameters and returns a
 * ParsedInstruction object containing interpreted instruction
 * components (instruction phrase, param output, type).
 *
 */
public class InstructionEngine {
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder loader;
	private static Document instructionsDoc;
	private static Element tree;
	private static NodeList instructionsList;
	private static String xmlFilename = "instructions.xml";
	
	private static ArrayList<Instruction> instructions;
	
	/**
	 * Loads the instruction library into the static hash table. 
	 * The engine doesn't need to be implicitly initialized,
	 * each method does a check for initialization before proceeding.
	 */
	public static void init() {
		try {
			loader = factory.newDocumentBuilder();
			instructionsDoc = loader.parse(xmlFilename);
			tree = instructionsDoc.getDocumentElement();
			instructionsList = tree.getChildNodes();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		instructions = new ArrayList<Instruction>();
		for(int i = 0; i < instructionsList.getLength(); i++) {
			if(instructionsList.item(i).getNodeType() != 1) continue;
			NodeList instructionNodes = instructionsList.item(i).getChildNodes();
			
			// instruction constructor params
			String type = "";
			String phrase = "";
			ArrayList<Param> params = new ArrayList<Param>();
			
			// Retrieve instruction data 
			for(int j = 0; j < instructionNodes.getLength(); j++) {
				if(instructionNodes.item(j).getNodeType() != 1) continue;
				Element instructionNode = (Element) instructionNodes.item(j);
				String xmlTag = instructionNode.getTagName();
				
				if(xmlTag.equalsIgnoreCase("type")) type = instructionNode.getTextContent();
				else if(xmlTag.equalsIgnoreCase("phrase")) phrase = instructionNode.getTextContent();
				else if(xmlTag.equalsIgnoreCase("params")) {
					
					NodeList paramNodes = instructionNode.getChildNodes();
					String paramType = "";
					String inputRegex = "";
					String outputRegex = "";
										
					for(int k = 0; k < paramNodes.getLength(); k++) {
						if(paramNodes.item(k).getNodeType() != 1) continue;
						Element paramNode = (Element) paramNodes.item(k);
						
						NodeList paramDataNodes = paramNode.getChildNodes();
						
						for(int l = 0; l < paramDataNodes.getLength(); l++) {
							if(paramDataNodes.item(l).getNodeType() != 1) continue;
							Element paramDataNode = (Element) paramDataNodes.item(l);
							String paramTag = paramDataNode.getTagName();
														
							if(paramTag.equalsIgnoreCase("type")) paramType = paramDataNode.getTextContent();
							else if(paramTag.equalsIgnoreCase("input")) inputRegex = paramDataNode.getTextContent();
							else if(paramTag.equalsIgnoreCase("output")) outputRegex = paramDataNode.getTextContent();
						}
						
						Param currentParam = new Param(inputRegex, outputRegex, paramType);
						params.add(currentParam);
						
					}

				}

			}
			
			Instruction instruction = new Instruction(phrase, type, params);
			instructions.add(instruction);

		}
	}
	
	/**
	 * Identifies an instruction in a passed string sentence.
	 * @param phrase The phrase to identify
	 * @return A ParsedInstruction object of the identified instruction
	 */
	public static ParsedInstruction parse(String phrase) {
		if(instructions == null) init();
		
		int index = isInstruction(phrase);
		if(index < 0) throw new ParseException("Unrecognized instruction");
		
		String instRegex = instructions.get(index).getPhrase();
		String type = instructions.get(index).getType();
		Param param = null;
		String paramIn = null;
		String mParamRegex = null;
		
		for(Param p : instructions.get(index).getParams()) {
			String paramRegex = p.getInputRegex();
			Pattern paramPattern = Pattern.compile(paramRegex);
			Matcher paramMatcher = paramPattern.matcher(phrase);
			
			if(paramMatcher.find()) {
				param = p;
				paramIn = paramMatcher.group();
				mParamRegex = paramRegex;
				break;
			}
		}
		
		if(param == null) throw new ParseException("No valid parameters");
		
		String paramOutput = paramIn.replaceAll(param.getInputRegex(), param.getOutputRegex());
		
		ParsedInstruction parsedInstr = new ParsedInstruction(phrase, paramOutput, type, mParamRegex, instRegex);
		return parsedInstr;
	}
	
	
	/**
	 * Checks a phrase against the instruction xml library.
	 * If the passed phrase is a valid instruction, it returns
	 * the index of the phrase in the library.
	 * Otherwise, it returns -1.
	 * 
	 * @param phrase The phrase to check.
	 * @return Index value in the instructions ArrayList of the InstructionEngine class.
	 */
	public static int isInstruction(String phrase) {
		if(instructions == null) init();
		
		for(int i = 0; i < instructions.size(); i++) {
			String regex = instructions.get(i).getPhrase();
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(phrase);
			
			if(matcher.find()) return i;
		}
		
		return -1;
	}
	
}
