package edu.ncsu.csc.itrust.beans.forms;

/**
 * A form to contain data coming from editing the Ophthalmology Data for an office visit.
 * 
 * A form is a bean, kinda. You could say that it's a “form” of a bean :) 
 * Think of a form as a real-life administrative form that you would fill out to get 
 * something done, not necessarily making sense by itself.
 */
public class EditSurgeryDataForm {
	
	private long surgeryID;
	private String SurgeryNotes;
	
	public String getSurgeryNotes() {
		return SurgeryNotes;
	}
	public void setSurgeryNotes(String surgeryNotes) {
		SurgeryNotes = surgeryNotes;
	}
	public long getSurgeryID() {
		return surgeryID;
	}
	public void setSurgeryID(long surgeryID) {
		this.surgeryID = surgeryID;
	}


}
