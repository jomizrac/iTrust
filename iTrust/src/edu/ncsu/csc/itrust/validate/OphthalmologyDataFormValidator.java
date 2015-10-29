package edu.ncsu.csc.itrust.validate;


import edu.ncsu.csc.itrust.beans.forms.EditOphthalmologyDataForm;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.BeanValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

/**
 * Validates an Ophthamlology Data Form
 * @author jbpolla2
 *
 */
public class OphthalmologyDataFormValidator extends BeanValidator<EditOphthalmologyDataForm> {

	/**
	 * Validates the form passed into it and makes sure all strings in the form would be valid to be 
	 * used as the attributes of the bean.
	 * 
	 * @param EditOphthalmologyDataForm form to be validated
	 */
	public void validate(EditOphthalmologyDataForm bean) throws FormValidationException{
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getODAcuityDenominator(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getODAcuityNumerator(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getOSAcuityDenominator(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getOSAcuityNumerator(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		bean.setOdsphere(roundNearestQuarterDiopter(bean.getOdsphere()));
		errorList.addIfNotNull(
				checkFormat("ODSphere", bean.getOdsphere(), ValidationFormat.OPHTHALMOLOGY_DATA_SPHERE, false));
		bean.setOssphere(roundNearestQuarterDiopter(bean.getOssphere()));
		errorList.addIfNotNull(
				checkFormat("OSSphere", bean.getOssphere(), ValidationFormat.OPHTHALMOLOGY_DATA_SPHERE, false));				
		bean.setOdcylinder(roundNearestQuarterDiopter(bean.getOdcylinder()));
		errorList.addIfNotNull(
				checkFormat("ODCylinder", bean.getOdcylinder(), ValidationFormat.OPHTHALMOLOGY_DATA_CYLINDER, true));
		bean.setOscylinder(roundNearestQuarterDiopter(bean.getOscylinder()));
		errorList.addIfNotNull(
				checkFormat("OSCylinder", bean.getOscylinder(), ValidationFormat.OPHTHALMOLOGY_DATA_CYLINDER, true));
		if (bean.getOdcylinder() == null || bean.getOdcylinder().equals("")) {
			errorList.addIfNotNull(
					checkFormat("ODAxis", bean.getOdaxis(), ValidationFormat.OPHTHALMOLOGY_DATA_NULLAXISOD, true));
		} else {
			errorList.addIfNotNull(
					checkFormat("ODAxis", bean.getOdaxis(), ValidationFormat.OPHTHALMOLOGY_DATA_AXIS, false));
		}
		if (bean.getOscylinder() == null || bean.getOscylinder().equals("")) {
			errorList.addIfNotNull(
					checkFormat("OSAxis", bean.getOsaxis(), ValidationFormat.OPHTHALMOLOGY_DATA_NULLAXISOS, true));
		} else {
			errorList.addIfNotNull( 
					checkFormat("OSAxis", bean.getOsaxis(), ValidationFormat.OPHTHALMOLOGY_DATA_AXIS, false));
		}
		errorList.addIfNotNull( 
				checkFormat("ODAxis", bean.getOdaxis(), ValidationFormat.OPHTHALMOLOGY_DATA_AXIS, true));
		bean.setOdadd(roundNearestQuarterDiopter(bean.getOdadd()));
		errorList.addIfNotNull(
				checkFormat("ODAdd", bean.getOdadd(), ValidationFormat.OPHTHALMOLOGY_DATA_ADD, false));
		bean.setOsadd(roundNearestQuarterDiopter(bean.getOsadd()));
		errorList.addIfNotNull(
				checkFormat("OSAdd", bean.getOsadd(), ValidationFormat.OPHTHALMOLOGY_DATA_ADD, false));
		
		if (errorList.hasErrors()){
			throw new FormValidationException(errorList);
		}
	}
	
	/**
	 * Rounds the given value to the nearest quarter.  The Ophthalmology data in data format 6.17 requires data to be roundest to the nearest quarter diopter.
	 * @param value
	 * @return
	 */
	private String roundNearestQuarterDiopter(String value) {
		if (value != null && !(value.equals(""))) {
			try {
				Double diopter = Double.parseDouble(value);
				diopter = Math.round(diopter * 4)/4.0;
				value = diopter.toString();
			} catch (NumberFormatException e) {
				return value;
			}
		}
		return value;
	}
}
