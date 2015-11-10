package edu.ncsu.csc.itrust.validate;

import edu.ncsu.csc.itrust.beans.SurgeryDataBean;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;

public class SurgeryDataFormValidator extends BeanValidator<SurgeryDataBean>{

	public void validate(SurgeryDataBean bean) throws FormValidationException{
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(
				checkFormat("SurgeryDataNotes", bean.getSurgeryNotes(), ValidationFormat.NOTES, false));
		if (errorList.hasErrors()){
			throw new FormValidationException(errorList);
		}
		
	}

}
