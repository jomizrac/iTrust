package edu.ncsu.csc.itrust.action;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.action.base.EditOfficeVisitBaseAction;
import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;
import edu.ncsu.csc.itrust.beans.forms.EditOphthalmologyDataForm;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyDataDAO;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.exception.ITrustException;
import edu.ncsu.csc.itrust.validate.OphthalmologyDataFormValidator;

/**
 * Allows an HCP to add/edit/delete Ophthalmology Data for an office visit.
 */
public class EditOphthalmologyDataAction extends EditOfficeVisitBaseAction {

	private OphthalmologyDataDAO ophthDataDAO;

	/**
	 * Creates an EditOphthalmologyDataAction for an existing office visit.
	 * 
	 * @param factory
	 *            The DAO Factory.
	 * @param hcpid
	 *            The HCP id.
	 * @param pidString
	 *            The patient id as a string.
	 * @param ovIDString
	 *            The office visit as a string.
	 * @throws ITrustException
	 */
	public EditOphthalmologyDataAction(DAOFactory factory, long hcpid,
			String pidString, String ovIDString) throws ITrustException {
		super(factory, hcpid, pidString, ovIDString);
		ophthDataDAO = factory.getOphthalmologyDataDAO();
	}

	/**
	 * Creates an EditOphthalmologyDataAction for an non-existent office visit.
	 * Most methods will throw exceptions if built with this constructor.
	 * 
	 * @param factory
	 *            The DAO Factory.
	 * @param hcpid
	 *            The HCP id.
	 * @param pidString
	 *            The patient ID as a string.
	 * @throws ITrustException
	 */
	public EditOphthalmologyDataAction(DAOFactory factory, long hcpid,
			String pidString) throws ITrustException {
		super(factory, hcpid, pidString);
		ophthDataDAO = factory.getOphthalmologyDataDAO();
	}

	/**
	 * Get the list of the Ophthalmology Data associated with the office visit.
	 * 
	 * @return A list of Ophthalmology Data.
	 * @throws DBException
	 */
	public List<OphthalmologyDataBean> getOphthalmologyData()
			throws DBException {
		if (isUnsaved()) {
			return new ArrayList<OphthalmologyDataBean>();
		} else {
			return ophthDataDAO.getList(getOvID());
		}
	}

	/**
	 * Add Ophthalmology Data to the office visit.
	 * 
	 * @param bean
	 *            The Ophthalmology Data to add.
	 * @throws ITrustException
	 */
	public void addOphthalmologyData(OphthalmologyDataBean bean)
			throws ITrustException {
		verifySaved();
		ophthDataDAO.add(bean);
	}

	/**
	 * Modify Ophthalmology Data in this office visit.
	 * 
	 * @param bean
	 *            The Ophthalmology Data to modify
	 * @throws ITrustException
	 */
	public void editOphthalmologyData(OphthalmologyDataBean bean)
			throws ITrustException {
		verifySaved();
		ophthDataDAO.edit(bean);
	}

	/**
	 * Delete Ophthalmology Data from this office visit.
	 * 
	 * @param bean
	 *            The Ophthalmology Data to delete.
	 * @throws ITrustException
	 */
	// public void deleteOphthalmologyData(OphthalmologyDataBean bean) throws
	// ITrustException {
	// verifySaved();
	// ophthDataDAO.remove(bean.getId());
	// }

	/**
	 * Verify the contents of the given Ophthalmology Data bean.
	 * 
	 * @param bean
	 *            The Ophthalmology Data to check.
	 * @throws ITrustException
	 * @throws FormValidationException
	 */
	// public void validate(EditOphthalmologyDataForm bean) throws
	// ITrustException, FormValidationException {
	// verifySaved();
	// OphthalmologyDataFormValidator validator = new
	// OphthalmologyDataFormValidator();
	// validator.validate(bean);
	// }

	/**
	 * converts it into a bean, and returns that bean.
	 * 
	 * @param form
	 *            The form to convert.
	 * @return
	 * @throws FormValidationException
	 * @throws DBException
	 */
	public OphthalmologyDataBean formToBean(EditOphthalmologyDataForm form)
			throws FormValidationException, DBException {
		OphthalmologyDataFormValidator validator = new OphthalmologyDataFormValidator();
		validator.validate(form);
		OphthalmologyDataBean bean = new OphthalmologyDataBean();
		bean.setODAcuityNumerator(Integer.valueOf(form.getODAcuityNumerator()));
		bean.setODAcuityDenominator(Integer.valueOf(form.getODAcuityDenominator()));
		bean.setOSAcuityNumerator(Integer.valueOf(form.getOSAcuityNumerator()));
		bean.setOSAcuityDenominator(Integer.valueOf(form.getOSAcuityDenominator()));
		bean.setODSphere(Double.valueOf(form.getOdsphere()));
		bean.setOSSphere(Double.valueOf(form.getOssphere()));
		bean.setODCylinder(Double.valueOf(form.getOdcylinder()));
		bean.setOSCylinder(Double.valueOf(form.getOscylinder()));
		bean.setODAxis(Integer.valueOf(form.getOdaxis()));
		bean.setOSAxis(Integer.valueOf(form.getOsaxis()));
		bean.setODAdd(Double.valueOf(form.getOdadd()));
		bean.setOSAdd(Double.valueOf(form.getOsadd()));

		return bean;
	}

}
