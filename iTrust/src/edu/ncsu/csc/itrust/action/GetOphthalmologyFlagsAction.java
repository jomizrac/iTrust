package edu.ncsu.csc.itrust.action;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyFlagDAO;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;

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
	
	/**
	 * Constructor for OphthalmologyFlagAction
	 * @param factory
	 * @param loggedInMID
	 * @param pidString
	 */
	public GetOphthalmologyFlagsAction(DAOFactory factory, long loggedInMID, String pidString) {
		this.factory = factory;
		this.loggedInMID = loggedInMID;
		this.patientID = Long.parseLong(pidString);
		this.loggingAction = new EventLoggingAction(factory);
		this.patientDAO = factory.getPatientDAO();
		this.flagDAO = factory.getOphthalmologyFlagDAO();
	}
	
	/**
	 * Creates Ophthalmology flags for the given patient based upon their records.
	 * @return ArrayList of OphthamologyFlagBean
	 */
	public List<OphthalmologyFlagBean> createFlags() {
		ArrayList<OphthalmologyFlagBean> flags = new ArrayList<OphthalmologyFlagBean>();
		createCataractsFlags(flags, patientID);
		createMacularDegenerationFlags(flags, patientID);
		createGlaucomaFlags(flags, patientID);
		return flags;
	}
	
	/**
	 * Adds all cataract related flags to the flags list.
	 * @param flags
	 * @param pid
	 */
	private void createCataractsFlags(List<OphthalmologyFlagBean> flags, long pid) {
		OphthalmologyFlagBean smokerFlag = new OphthalmologyFlagBean();
		smokerFlag.setMid(pid);
		smokerFlag.setValue(OphthalmologyFlag.Smoker);
		// set flagged based on patient records
		flags.add(smokerFlag);
		
		OphthalmologyFlagBean diabetesFlag = new OphthalmologyFlagBean();
		diabetesFlag.setMid(pid);
		diabetesFlag.setValue(OphthalmologyFlag.Diabetes);
		// set flagged based on patient records
		flags.add(diabetesFlag);
	}
	
	/**
	 * Adds all age related muscular degeneration flags to the flags list
	 * @param flags
	 * @param pid
	 */
	private void createMacularDegenerationFlags(List<OphthalmologyFlagBean> flags, long pid) {
		OphthalmologyFlagBean raceFlag = new OphthalmologyFlagBean();
		raceFlag.setMid(pid);
		raceFlag.setValue(OphthalmologyFlag.RaceCaucasian);
		// set flagged based on patient records
		flags.add(raceFlag);
		
		OphthalmologyFlagBean historyFlag = new OphthalmologyFlagBean();
		historyFlag.setMid(pid);
		historyFlag.setValue(OphthalmologyFlag.FamilyHistoryAMD);
		// set flagged based on patient records
		flags.add(historyFlag);
	}
	
	/**
	 * Adds all glaucoma flags to the flags list
	 * @param flags
	 * @param pid
	 */
	private void createGlaucomaFlags(List<OphthalmologyFlagBean> flags, long pid) {
		OphthalmologyFlagBean ageFlag = new OphthalmologyFlagBean();
		ageFlag.setMid(pid);
		//check if african american
		//set flag value based on result
		// set flagged based on patient records
		flags.add(ageFlag);
		
		OphthalmologyFlagBean historyFlag = new OphthalmologyFlagBean();
		historyFlag.setMid(pid);
		historyFlag.setValue(OphthalmologyFlag.FamilyHistoryGlaucoma);
		// set flagged based on patient records
		flags.add(historyFlag);
	}
}
