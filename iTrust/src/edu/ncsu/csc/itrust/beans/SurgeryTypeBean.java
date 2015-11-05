package edu.ncsu.csc.itrust.beans;

import java.io.Serializable;

/**
 * A bean for storing Surgery Data gathered during an office visit.
 * 
 * A bean's purpose is to store data. Period. Little or no functionality is to be added to a bean 
 * (with the exception of minor formatting such as concatenating phone numbers together). 
 * A bean must only have Getters and Setters (Eclipse Hint: Use Source > Generate Getters and Setters
 * to create these easily)
 */
public class SurgeryTypeBean implements Serializable {

	private static final long serialVersionUID = -7201556261684536867L;
	
	
	private long surgeryID;
	private String surgeryName;
	public long getSurgeryID() {
		return surgeryID;
	}
	public void setSurgeryID(long surgeryID) {
		this.surgeryID = surgeryID;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}	
}
