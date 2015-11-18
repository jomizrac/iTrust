package edu.ncsu.csc.itrust.validate;

import edu.ncsu.csc.itrust.beans.SurgeryTypeBean;
import edu.ncsu.csc.itrust.exception.ErrorList;
import edu.ncsu.csc.itrust.exception.FormValidationException;

public class SurgeryTypeBeanValidator extends BeanValidator<SurgeryTypeBean> {

	@Override
	public void validate(SurgeryTypeBean s) throws FormValidationException {
		ErrorList errorList = new ErrorList();
		errorList.addIfNotNull(checkFormat("Surgery Type Name", s.getSurgeryName(), ValidationFormat.APPT_TYPE_NAME, false));
		if (errorList.hasErrors())
			throw new FormValidationException(errorList);
	}

}
