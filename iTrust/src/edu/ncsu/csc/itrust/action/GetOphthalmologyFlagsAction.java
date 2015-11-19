package edu.ncsu.csc.itrust.action;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyFlagDAO;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.ITrustException;

/**
 * Determines warnings to display for an Ophthalmology Office Visit
 */
public class GetOphthalmologyFlagsAction {
	
	private DAOFactory factory;
	private EventLoggingAction loggingAction;
	private long loggedInMID;
	private long patientID;
	private PatientDAO patientDAO;
	private OphthalmologyFlagDAO flagDAO;
	private PatientBean patientInformation;
	
	/**
	 * Constructor for OphthalmologyFlagAction
	 * @param factory
	 * @param loggedInMID
	 * @param pidString
	 */
	public GetOphthalmologyFlagsAction(DAOFactory factory, long loggedInMID, String pidString) throws ITrustException {
		this.factory = factory;
		this.loggedInMID = loggedInMID;
		this.patientID = Long.parseLong(pidString);
		this.loggingAction = new EventLoggingAction(this.factory);
		this.patientDAO = factory.getPatientDAO();
		this.flagDAO = factory.getOphthalmologyFlagDAO();
		try {
			this.patientInformation = patientDAO.getPatient(patientID);
		} catch (DBException e) {
			throw new ITrustException("Paient information cannot be found");
		}
	}
	
	/**
	 * Creates Ophthalmology flags for the given patient based upon their records.
	 * @return ArrayList of OphthamologyFlagBean
	 */
	public List<OphthalmologyFlagBean> createFlags() {
		ArrayList<OphthalmologyFlagBean> flags = new ArrayList<OphthalmologyFlagBean>();
		createCataractsFlags(flags);
		createMacularDegenerationFlags(flags);
		createGlaucomaFlags(flags);
		return flags;
	}
	
	/**
	 * Adds all cataract related flags to the flags list.
	 * @param flags
	 * @param pid
	 */
	private void createCataractsFlags(List<OphthalmologyFlagBean> flags) {
		OphthalmologyFlagBean smokerFlag = new OphthalmologyFlagBean();
		smokerFlag.setMid(patientID);
		smokerFlag.setValue(OphthalmologyFlag.Smoker);
		// set flagged based on user input?
		flags.add(smokerFlag);
		
		OphthalmologyFlagBean diabetesFlag = new OphthalmologyFlagBean();
		diabetesFlag.setMid(patientID);
		diabetesFlag.setValue(OphthalmologyFlag.Diabetes);
		// set flagged based on user input?
		flags.add(diabetesFlag);
	}
	
	/**
	 * Adds all age related muscular degeneration flags to the flags list
	 * @param flags
	 * @param pid
	 */
	private void createMacularDegenerationFlags(List<OphthalmologyFlagBean> flags) {
		OphthalmologyFlagBean raceFlag = new OphthalmologyFlagBean();
		raceFlag.setMid(patientID);
		raceFlag.setValue(OphthalmologyFlag.RaceCaucasian);
		if (patientInformation.getEthnicity().toString().equals("Caucasian")) {
			raceFlag.setFlagged(true);
		}
		flags.add(raceFlag);
		
		OphthalmologyFlagBean historyFlag = new OphthalmologyFlagBean();
		historyFlag.setMid(patientID);
		historyFlag.setValue(OphthalmologyFlag.FamilyHistoryAMD);
		// set flagged based on user input?
		flags.add(historyFlag);
	}
	
	/**
	 * Adds all glaucoma flags to the flags list
	 * @param flags
	 * @param pid
	 */
	private void createGlaucomaFlags(List<OphthalmologyFlagBean> flags) {
		OphthalmologyFlagBean ageFlag = new OphthalmologyFlagBean();
		ageFlag.setMid(patientID);
		if (patientInformation.getEthnicity().toString().equals("African American")) {
			ageFlag.setValue(OphthalmologyFlag.AfricanAmerican40);
			if (patientInformation.getAge() > OphthalmologyFlagBean.aaGlaucomaAge) {
				ageFlag.setFlagged(true);
			}
		} else {
			ageFlag.setValue(OphthalmologyFlag.Over60);
			if (patientInformation.getAge() > OphthalmologyFlagBean.glaucomaAge) {
				ageFlag.setFlagged(true);
			}
		}
		flags.add(ageFlag);
		
		OphthalmologyFlagBean historyFlag = new OphthalmologyFlagBean();
		historyFlag.setMid(patientID);
		historyFlag.setValue(OphthalmologyFlag.FamilyHistoryGlaucoma);
		// set flagged based on user input?
		flags.add(historyFlag);
	}
	
	/**
	 * Adds the given list of flags to the database
	 * @param flags
	 * @throws ITrustException
	 */
	public void addFlags(List<OphthalmologyFlagBean> flags) throws ITrustException {
		try {
			for (OphthalmologyFlagBean flag : flags) {
				flagDAO.setFlag(flag);
			}
		}
		catch (DBException e) {
			throw new ITrustException("Error writing flags to database");
		}
	}
}