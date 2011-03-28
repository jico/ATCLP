package edu.stockton;

public class ParsedInstruction {
	private String phrase;
	private String param;
	private String type;
	
	public ParsedInstruction() {
		
	}
	
	public ParsedInstruction(String phrase, String param, String type) {
		this.phrase = phrase;
		this.param = param;
		this.type = type;
	}
	
	public String getPhrase() {
		return phrase;
	}
	
	public String getParam() {
		return param;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return "phrase[" + phrase + "]\n" + "type[" + type + "]\n" + "param[" + param + "]";
	}
	
}
