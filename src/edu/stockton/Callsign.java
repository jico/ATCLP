package edu.stockton;

public class Callsign {
	private String designator;
	private String company;
	private String telephony;
	
	public Callsign() {
		
	}
	
	public Callsign(String telephony, String designator, String company) {
		this.telephony = telephony;
		this.designator = designator;
		this.company = company;
	}
	
	public String getDesignator() {
		return designator;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getTelephony() {
		return telephony;
	}
	
	public void setDesignator(String designator) {
		this.designator = designator;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setTelephony(String telephony) {
		this.telephony = telephony;
	}
}
