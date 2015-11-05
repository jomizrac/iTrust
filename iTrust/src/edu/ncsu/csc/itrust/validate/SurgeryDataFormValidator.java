package edu.ncsu.csc.itrust.validate;

import edu.ncsu.csc.itrust.beans.forms.EditSurgeryDataForm;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;

public class SurgeryDataFormValidator extends BeanValidator<EditSurgeryDataForm>{

	public void validate(EditSurgeryDataForm bean) throws FormValidationException{
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getSurgeryID(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		errorList.addIfNotNull(
				checkFormat("Acuity", bean.getSurgeryNotes(), ValidationFormat.OPHTHALMOLOGY_DATA_ACUITY, false));
		if (errorList.hasErrors()){
			throw new FormValidationException(errorList);
		}
		
	}

}
