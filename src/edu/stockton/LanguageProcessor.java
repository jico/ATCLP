package edu.stockton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

public class LanguageProcessor {
	private static NumberEngine numberEngine;
	private static CallsignEngine callsignEngine;
	private static InstructionEngine instructionEngine;
	
	private static ArrayList<String> tokens;
	private static ArrayList<Component> components;
	
	public LanguageProcessor() throws Exception {
		numberEngine = new NumberEngine();
		callsignEngine = new CallsignEngine();
		instructionEngine = new InstructionEngine();
		
		tokens = new ArrayList<String>();
		components = new ArrayList<Component>();
	}
	
	public static ATCCommand parse(String command) {
		String[] words = command.split(" ");
		for(String word : words) {
			tokens.add(word);
		}
		
		
		// Tag each token and group
		String currentTag = "";
		String componentText = "";
		for(int i = 0; i < tokens.size(); i++) {
			
			String thisTag = tag(tokens.get(i));
			
			if(thisTag.equals(currentTag) && i < tokens.size()-1) {
				componentText += tokens.get(i) + " ";	
			} else if(i == 0) {
				currentTag = thisTag;
				componentText += tokens.get(i) + " ";
			} else {
				if(i == tokens.size()-1) componentText += tokens.get(i) + " ";
				Component thisComponent = new Component(componentText, currentTag);
				components.add(thisComponent);
				componentText = "";
				componentText += tokens.get(i) + " ";
				currentTag = thisTag;
			}

		}
		
		String recipient = "";
		
		// Do conversions for each token
		for(int i = 0; i < components.size(); i++) {
			Component current = components.get(i);
			String type = current.getType();
			
			
			if(type.equals("number")) {				
				current.setText(NumberEngine.toNumeric(current.getText()));
				components.set(i, current);
				
				// Recognize recipient and build recipient string
				if(recipient == "" && components.get(i-1).getType() == "callsign") {
					recipient = components.get(i-1).getText() + components.get(i).getText();
				}
				
			}
			if(type.equals("callsign")) {
				current.setText(CallsignEngine.telephonyToDesignator(current.getText()));
				components.set(i, current);
			}
		}
		
		String phrase = "";
		for(Component component : components) {
			phrase += component.getText() + " ";
		}
		System.out.println(phrase);
		ParsedInstruction instruction = instructionEngine.parse(phrase);
		System.out.println(instruction);
		
		ATCCommand parsed = new ATCCommand(recipient, instruction.getType(), instruction.getParam());
		return parsed;

	}
	
	public static String tag(String token) {
		String tag = "unidentified";
		if(numberEngine.isNumber(token)) tag = "number";
		if(callsignEngine.isCallsign(token)) tag = "callsign";
		return tag;
	}
	
	public static void main(String args[]) throws Exception {
		LanguageProcessor LP = new LanguageProcessor();
		
		
		
		System.out.println(parse("cactus twenty eighty descend and maintain mach point seven five").toXML());
		
		
	}
	
	
}
