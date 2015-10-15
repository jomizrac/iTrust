package edu.ncsu.csc.itrust.beans.forms;

/**
 * A form to contain data coming from editing the Ophthalmology Data for an office visit.
 * 
 * A form is a bean, kinda. You could say that it's a �form� of a bean :) 
 * Think of a form as a real-life administrative form that you would fill out to get 
 * something done, not necessarily making sense by itself.
 */
public class EditOphthalmologyDataForm {
	
	private String acuityNumerator;
	private String acuityDenominator;
	private String odsphere;
	private String ossphere;
	private String odcylinder;
	private String oscylinder;
	private String odaxis;
	private String osaxis;
	private String odadd;
	private String osadd;
	
	/**
	 * @return the acuity
	 */
	public String getAcuityNumerator() {
		return acuityNumerator;
	}
	/**
	 * @param acuity the acuity to set
	 */
	public void setAcuityNumerator(String acuity) {
		this.acuityNumerator = acuity;
	}
	
	/**
	 * @return the acuityDenominator
	 */
	public String getAcuityDenominator() {
		return acuityDenominator;
	}
	/**
	 * @param acuityDenominator the acuityDenominator to set
	 */
	public void setAcuityDenominator(String acuityDenominator) {
		this.acuityDenominator = acuityDenominator;
	}
	/**
	 * @return the odsphere
	 */
	public String getOdsphere() {
		return odsphere;
	}
	/**
	 * @param odsphere the odsphere to set
	 */
	public void setOdsphere(String odsphere) {
		this.odsphere = odsphere;
	}
	/**
	 * @return the ossphere
	 */
	public String getOssphere() {
		return ossphere;
	}
	/**
	 * @param ossphere the ossphere to set
	 */
	public void setOssphere(String ossphere) {
		this.ossphere = ossphere;
	}
	/**
	 * @return the odcylinder
	 */
	public String getOdcylinder() {
		return odcylinder;
	}
	/**
	 * @param odcylinder the odcylinder to set
	 */
	public void setOdcylinder(String odcylinder) {
		this.odcylinder = odcylinder;
	}
	/**
	 * @return the oscylinder
	 */
	public String getOscylinder() {
		return oscylinder;
	}
	/**
	 * @param oscylinder the oscylinder to set
	 */
	public void setOscylinder(String oscylinder) {
		this.oscylinder = oscylinder;
	}
	/**
	 * @return the odaxis
	 */
	public String getOdaxis() {
		return odaxis;
	}
	/**
	 * @param odaxis the odaxis to set
	 */
	public void setOdaxis(String odaxis) {
		this.odaxis = odaxis;
	}
	/**
	 * @return the osaxis
	 */
	public String getOsaxis() {
		return osaxis;
	}
	/**
	 * @param osaxis the osaxis to set
	 */
	public void setOsaxis(String osaxis) {
		this.osaxis = osaxis;
	}
	/**
	 * @return the odadd
	 */
	public String getOdadd() {
		return odadd;
	}
	/**
	 * @param odadd the odadd to set
	 */
	public void setOdadd(String odadd) {
		this.odadd = odadd;
	}
	/**
	 * @return the osadd
	 */
	public String getOsadd() {
		return osadd;
	}
	/**
	 * @param osadd the osadd to set
	 */
	public void setOsadd(String osadd) {
		this.osadd = osadd;
	}
	
}
