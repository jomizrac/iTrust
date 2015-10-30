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
public class SurgeryDataBean implements Serializable {
	
	private static final long serialVersionUID = -7201556261684536867L;
	
	private long id;
	private long visitID;
	private long surgeryID;
	private String surgeryNotes;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getVisitID() {
		return visitID;
	}
	
	public void setVisitID(long visitID) {
		this.visitID = visitID;
	}
	
	public long getSurgeryID() {
		return surgeryID;
	}
	
	public void setSurgeryID(long surgeryID) {
		this.surgeryID = surgeryID;
	}
	
	public String getSurgeryNotes() {
		return surgeryNotes;
	}
	
	public void setSurgeryNotes(String surgeryNotes) {
		this.surgeryNotes = surgeryNotes;
	}
}
