package edu.stockton;

public class Component {
	private String text;
	private String type;
	
	public Component(String text, String type) {
		this.text = text.trim();
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public void setText(String text) {
		this.text = text.trim();
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
