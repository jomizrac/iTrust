package edu.ncsu.csc.itrust.dao.surgeryData;

import java.util.List;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.OfficeVisitBean;
import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;
import edu.ncsu.csc.itrust.beans.SurgeryDataBean;
import edu.ncsu.csc.itrust.dao.mysql.OfficeVisitDAO;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyDataDAO;
import edu.ncsu.csc.itrust.dao.mysql.SurgeryDataDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

/**
 * Tests the OphthalmologyDataDAO class
 * 
 * @author Michael
 *
 */
public class SurgeryDataDAOTest extends TestCase {
	private SurgeryDataDAO dao = TestDAOFactory.getTestInstance().getSurgeryDataDAO();
	private OfficeVisitDAO ovdao = TestDAOFactory.getTestInstance().getOfficeVisitDAO();
	private TestDataGenerator gen;
	private SurgeryDataBean sdbean;
	
	/**
	 * Sets up the Data for testing
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		
		sdbean = new SurgeryDataBean();
		sdbean.setId(1);
		sdbean.setSurgeryID(1);
		sdbean.setSurgeryNotes("Kinda nothing");
		sdbean.setVisitID(66);
	}
	
	/**
	 * Tests the getList() method 
	 * @throws Exception
	 */
	public void testGetList() throws Exception {
		//empty database
		List<SurgeryDataBean> list = dao.getList(1);
		assertEquals(0, list.size());
		
		//load one record
		//gen.;
		//gen.ovOphthalmologyData();
		//list = dao.getList(117);
		//assertEquals(1, list.size());
		
		// should still return none when there are no records with the given id 
		list = dao.getList(100);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the add() method
	 * @throws Exception
	 */
	//public void testAdd() throws Exception {	
		//add valid bean
	//	dao.add(sdbean);
	//	SurgeryDataBean curr = dao.getList(66).get(0);
	//	assertEquals(1, dao.getList(66).size());
	//	assertEquals(1L, curr.getSurgeryID()); getting increasing numbers
	//	assertEquals(1L, curr.getId());
	//	assertEquals(String.valueOf("Kinda nothing"), curr.getSurgeryNotes());
		
	//}
	
	/**
	 * Tests the edit() method
	 * @throws Exception
	 */
	public void testEdit() throws Exception {
		// empty database
		// Editing a non existent record doesn't do anything.
		SurgeryDataBean bean = new SurgeryDataBean();
		bean.setId(92);
		bean.setVisitID(92);
		bean.setSurgeryID(2);
		bean.setSurgeryNotes("Totally something");
		dao.edit(bean);
		
		assertEquals(0, dao.getList(92).size()); // nothing created
		
		//load one record
		//gen.;
		//gen.ovOphthalmologyData();
		//List<OphthalmologyDataBean> list = dao.getList(117);
		//assertEquals(1, list.size());
		
		//bean = list.get(0);
		//bean.setOSAcuityDenominator(70);
		//bean.setOSAdd(2.3);
		//dao.edit(bean);
		
		//OphthalmologyDataBean currBean = dao.getList(117).get(0);
		//assertEquals(Integer.valueOf(70), Integer.valueOf(currBean.getOSAcuityDenominator()));
		//assertEquals(Double.valueOf(2.3), currBean.getOSAdd());
		
	}
	
	/**
	 * Tests the getOfficeVisitsWithOpthData() method
	 * @throws Exception
	 */
	//public void testGetOfficeVisitsWithSurgeryData() throws Exception {
		//Patient __ needs an office visit
	//	gen.ovOphthalmology();
	//	gen.ovOphthalmologyData();
	//	List<OfficeVisitBean> ovlist = dao.getOfficeVisitsWithOpthData(15);
	//	assertEquals(1, ovlist.size());
	//}
}
