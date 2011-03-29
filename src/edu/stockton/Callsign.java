package edu.stockton;

/**
 * The Callsign data object contains information for a 
 * particular airline. 
 * Used primarily for the CallsignEngine class.
 *
 */
public class Callsign {
	private String designator;
	private String company;
	private String telephony;
	
	public Callsign(String telephony, String designator, String company) {
		this.telephony = telephony;
		this.designator = designator;
		this.company = company;
	}
	
	/**
	 * Gets the 3-letter designator
	 * @return The designator
	 */
	public String getDesignator() {
		return designator;
	}
	
	/**
	 * Gets the aircraft company
	 * @return The company
	 */
	public String getCompany() {
		return company;
	}
	
	/**
	 * Gets the telephony 
	 * @return The telephony
	 */
	public String getTelephony() {
		return telephony;
	}
	
	/**
	 * Sets the designator value
	 * @param designator The 3-letter designator
	 */
	public void setDesignator(String designator) {
		this.designator = designator;
	}
	
	/**
	 * Sets the company value
	 * @param company The aircraft company
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
	 * Sets the telephony value
	 * @param telephony The telephony string
	 */
	public void setTelephony(String telephony) {
		this.telephony = telephony;
	}
}
