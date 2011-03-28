package edu.stockton;

import java.util.ArrayList;

public class Instruction {
	private String phrase;
	private String type;
	private ArrayList<Param> params;
	
	public Instruction() {
		
	}
	
	public Instruction(String phrase, String type, ArrayList<Param> params) {
		this.phrase = phrase;
		this.type = type;
		this.params = params;
	}
	
	public String getPhrase() {
		return phrase;
	}
	
	public String getType() {
		return type;
	}
	
	public ArrayList<Param> getParams() {
		return params;
	}
	
	
	
}
