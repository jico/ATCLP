package edu.stockton;

public class Param {
	private String type;
	private String inputRegex;
	private String outputRegex;
	
	public Param(String inputRegex, String outputRegex, String type) {
		this.inputRegex = inputRegex;
		this.outputRegex = outputRegex;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getInputRegex() {
		return inputRegex;
	}
	
	public String getOutputRegex() {
		return outputRegex;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setInputRegex(String inputRegex) {
		this.inputRegex = inputRegex;
	}
	
	public void setOutputRegex(String outputRegex) {
		this.outputRegex = outputRegex;
	}
	
}