package edu.ncsu.csc.itrust.validate;

import edu.ncsu.csc.itrust.beans.forms.EditSurgeryDataForm;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;

public class SurgeryDataFormValidator extends BeanValidator<EditSurgeryDataForm>{

	public void validate(EditSurgeryDataForm bean) throws FormValidationException{
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(
				checkFormat("SurgeryDataNotes", bean.getSurgeryNotes(), ValidationFormat.NOTES, false));
		errorList.addIfNotNull(
				checkFormat("SurgeryDataId", bean.getSurgeryID(), ValidationFormat.SURGERY_DATA, false));
		if (errorList.hasErrors()){
			throw new FormValidationException(errorList);
		}
		
	}

}
