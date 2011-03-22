package edu.stockton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

public class LanguageProcessor {
	private static NumberEngine numberEngine;
	private static CallsignEngine callsignEngine;
	
	private static ArrayList<String> tokens;
	private static ArrayList<String> tags;
	private static ArrayList<String> components;
	
	public LanguageProcessor() throws Exception {
		numberEngine = new NumberEngine();
		callsignEngine = new CallsignEngine();
		
		tags = new ArrayList<String>();
		tokens = new ArrayList<String>();
		components = new ArrayList<String>();
	}
	
	public static void parse(String command) {
		String[] words = command.split(" ");
		for(String word : words) {
			tokens.add(word);
		}
		
		
		// Tag each token and group
		String currentTag = "";
		int componentCursor = -1;
		for(int i = 0; i < tokens.size(); i++) {
			
			String thisTag = tag(tokens.get(i));
			
			if(thisTag.equals(currentTag)) {
				components.set(componentCursor, components.get(componentCursor) + tokens.get(i) + " ");
				
			} else {				
				currentTag = thisTag;
				componentCursor++;
				components.add(componentCursor, tokens.get(i) + " ");
				
			}
			
		}
		
			
		
	}
	
	public static String tag(String token) {
		String tag = "unidentified";
		if(numberEngine.isNumber(token)) tag = "number";
		if(callsignEngine.isCallsign(token)) tag = "callsign";
		return tag;
	}
	
	
	public static void main(String[] args) throws Exception {
		LanguageProcessor LP = new LanguageProcessor();
		String test = "Cactus four seven five maintain flight level three three zero";
				
		parse(test);
		for(String s : components) System.out.println(s);
		
		
	}
	
}
