package edu.ncsu.csc.itrust.beans;

import java.io.Serializable;

/**
 * A bean for storing Ophthalmology Data gathered during an office visit.
 * 
 * A bean's purpose is to store data. Period. Little or no functionality is to be added to a bean 
 * (with the exception of minor formatting such as concatenating phone numbers together). 
 * A bean must only have Getters and Setters (Eclipse Hint: Use Source > Generate Getters and Setters
 * to create these easily)
 */
public class OphthalmologyDataBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201556261684536867L;
	
	private Integer acuityNumerator;
	private Integer acuityDenominator;
	private Double ODSphere;
	private Double OSSphere;
	private Double ODCylinder;
	private Double OSCylinder;
	private Integer ODAxis;
	private Integer OSAxis;
	private Double ODAdd;
	private Double OSAdd;
	private long id;
	private long visitID;

	
	/**
	 * Getter for acuity numerator
	 * @return acuity numerator
	 */
	public Integer getAcuityNumerator() {
		return acuityNumerator;
	}
	
	/**
	 * Setter for acuity numerator
	 * @param acuityNumerator
	 */
	public void setAcuityNumerator(Integer acuityNumerator) {
		this.acuityNumerator = acuityNumerator;
	}
	
	/** getter for acuity denominator
	 * @return the acuityDenominator
	 */
	public Integer getAcuityDenominator() {
		return acuityDenominator;
	}

	/** setter for acuity denominator
	 * @param acuityDenominator the acuityDenominator to set
	 */
	public void setAcuityDenominator(Integer acuityDenominator) {
		this.acuityDenominator = acuityDenominator;
	}

	/**
	 * getter for OD Sphere
	 * @return od sphere
	 */
	public Double getODSphere() {
		return ODSphere;
	}
	
	/**
	 * setter for od sphere
	 * @param oDSphere
	 */
	public void setODSphere(Double oDSphere) {
		ODSphere = oDSphere;
	}
	
	/**
	 * getter for os sphere
	 * @return os sphere
	 */
	public Double getOSSphere() {
		return OSSphere;
	}
	
	/**
	 * setter for os sphere
	 * @param oSSphere
	 */
	public void setOSSphere(Double oSSphere) {
		OSSphere = oSSphere;
	}
	
	/**
	 * getter for od cylinder
	 * @return od cylinder
	 */
	public Double getODCylinder() {
		return ODCylinder;
	}
	
	/**
	 * setter for od cylinder
	 * @param oDCylinder
	 */
	public void setODCylinder(Double oDCylinder) {
		ODCylinder = oDCylinder;
	}
	
	/**
	 * getter for os cylinder
	 * @return os cylinder
	 */
	public Double getOSCylinder() {
		return OSCylinder;
	}
	
	/**
	 * setter for os cylinder
	 * @param oSCylinder
	 */
	public void setOSCylinder(Double oSCylinder) {
		OSCylinder = oSCylinder;
	}
	
	/**
	 * getter for od axis 
	 * @return od axis
	 */
	public Integer getODAxis() {
		return ODAxis;
	}
	
	/**
	 * setter for od axis
	 * @param oDAxis
	 */
	public void setODAxis(Integer oDAxis) {
		ODAxis = oDAxis;
	}
	
	/**
	 * getter for os axis
	 * @return os axis
	 */
	public Integer getOSAxis() {
		return OSAxis;
	}
	
	/**
	 * setter for os axis
	 * @param oSAxis
	 */
	public void setOSAxis(Integer oSAxis) {
		OSAxis = oSAxis;
	}
	
	/**
	 * getter for od add
	 * @return of add
	 */
	public Double getODAdd() {
		return ODAdd;
	}
	
	/**
	 * setter for od add
	 * @param oDAdd
	 */
	public void setODAdd(Double oDAdd) {
		ODAdd = oDAdd;
	}
	
	/**
	 * getter for os add
	 * @return os add
	 */
	public Double getOSAdd() {
		return OSAdd;
	}
	
	/**
	 * setter for os add
	 * @param oSAdd
	 */
	public void setOSAdd(Double oSAdd) {
		OSAdd = oSAdd;
	}
	
	/**
	 * getter for visit id
	 * @return visit id
	 */
	public long getVisitID() {
		return visitID;
	}

	/**
	 * setter for visit id
	 * @param visitID
	 */
	public void setVisitID(long visitID) {
		this.visitID = visitID;
	}
	
	/**
	 * getter for id
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * setter for id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
}
