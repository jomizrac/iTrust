package edu.ncsu.csc.itrust.action;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.action.base.EditOfficeVisitBaseAction;
import edu.ncsu.csc.itrust.beans.SurgeryDataBean;
import edu.ncsu.csc.itrust.beans.forms.EditSurgeryDataForm;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.SurgeryDataDAO;	
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.ITrustException;
import edu.ncsu.csc.itrust.validate.SurgeryDataFormValidator;


public class EditSurgeryDataAction extends EditOfficeVisitBaseAction{

	private SurgeryDataDAO surgeryData;
	
	public void deleteSurgeryData(SurgeryDataBean bean) throws ITrustException {
		verifySaved();
		surgeryData.remove(bean.getSurgeryID());
		
	}
	
	public EditSurgeryDataAction(DAOFactory factory, long hcpid,
			String pidString) throws ITrustException {
		super(factory, hcpid, pidString);
		surgeryData = factory.getSurgeryDataDAO();
	}
	
	public EditSurgeryDataAction(DAOFactory factory, long hcpid,
			String pidString, String ovIDString) throws ITrustException {
		super(factory, hcpid, pidString, ovIDString);
		surgeryData = factory.getSurgeryDataDAO();
	}
	
	public void addSurgeryData(SurgeryDataBean bean)
			throws ITrustException {
		verifySaved();
		surgeryData.add(bean);
	}
	
	public void editSurgeryData(SurgeryDataBean bean)
			throws ITrustException {
		verifySaved();
		surgeryData.edit(bean);
	}
	
	public List<SurgeryDataBean> getSurgeryData() throws DBException {
		if (isUnsaved()) {
			return new ArrayList<SurgeryDataBean>();
		} else {
			return surgeryData.getList(getOvID());
		}
	}
	
	public List<SurgeryDataBean> getSurgeryTypes() throws DBException {
		return surgeryData.getSurgeryTypes();
	}
	
	/**
	 * converts it into a bean, and returns that bean.
	 * 
	 * @param form
	 *            The form to convert.
	 * @return
	 * @throws FormValidationException
	 */
	public SurgeryDataBean formToBean(EditSurgeryDataForm form)
			throws FormValidationException {
		SurgeryDataFormValidator validator = new SurgeryDataFormValidator();
		validator.validate(form);
		SurgeryDataBean bean = new SurgeryDataBean();
		bean.setSurgeryID(Long.valueOf(form.getSurgeryID()));
		bean.setSurgeryNotes(form.getSurgeryNotes());

		return bean;
	}
	
}
