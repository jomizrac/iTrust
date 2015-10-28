package edu.ncsu.csc.itrust.dao.ophthalmologyData;

import java.util.List;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.OfficeVisitBean;
import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;
import edu.ncsu.csc.itrust.dao.mysql.OfficeVisitDAO;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyDataDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

/**
 * Tests the OphthalmologyDataDAO class
 * 
 * @author Michael
 *
 */
public class OphthalmologyDataDAOTest extends TestCase {
	private OphthalmologyDataDAO dao = TestDAOFactory.getTestInstance().getOphthalmologyDataDAO();
	private OfficeVisitDAO ovdao = TestDAOFactory.getTestInstance().getOfficeVisitDAO();
	private TestDataGenerator gen;
	private OphthalmologyDataBean odbean;
	
	/**
	 * Sets up the Data for testing
	 * @throws Exception
	 */
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		
		odbean = new OphthalmologyDataBean();
		odbean.setVisitID(66);
		odbean.setODAcuityDenominator(1);
		odbean.setODAcuityNumerator(1);
		odbean.setOSAcuityDenominator(1);
		odbean.setOSAcuityNumerator(1);
		odbean.setODSphere(2.0);
		odbean.setOSSphere(2.0);
		odbean.setODCylinder(3.0);
		odbean.setOSCylinder(3.0);
		odbean.setODAxis(4);
		odbean.setOSAxis(4);
		odbean.setODAdd(1.0);
		odbean.setOSAdd(1.0);
	}
	
	/**
	 * Tests the getList() method 
	 * @throws Exception
	 */
	public void testGetList() throws Exception {
		//empty database
		List<OphthalmologyDataBean> list = dao.getList(1);
		assertEquals(0, list.size());
		
		//load one record
		gen.ovOphthalmology();
		gen.ovOphthalmologyData();
		list = dao.getList(117);
		assertEquals(1, list.size());
		
		// should still return none when there are no records with the given id 
		list = dao.getList(100);
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the add() method
	 * @throws Exception
	 */
	public void testAdd() throws Exception {	
		//add valid bean
		dao.add(odbean);
		OphthalmologyDataBean curr = dao.getList(66).get(0);
		assertEquals(1, dao.getList(66).size());
		assertEquals(Integer.valueOf(1), Integer.valueOf(curr.getODAcuityDenominator()));
		assertEquals(Double.valueOf(2.0), curr.getODSphere());
		assertEquals(Double.valueOf(3.0), curr.getOSCylinder());
		assertEquals(Double.valueOf(1.0), curr.getODAdd());
		
	}
	
	/**
	 * Tests the edit() method
	 * @throws Exception
	 */
	public void testEdit() throws Exception {
		// empty database
		// Editing a non existent record doesn't do anything.
		OphthalmologyDataBean bean = new OphthalmologyDataBean();
		bean.setId(92);
		bean.setVisitID(92);
		bean.setODAcuityDenominator(1);
		bean.setODAcuityNumerator(1);
		bean.setOSAcuityDenominator(1);
		bean.setOSAcuityNumerator(1);
		bean.setODSphere(2.0);
		bean.setOSSphere(2.0);
		bean.setODCylinder(3.0);
		bean.setOSCylinder(3.0);
		bean.setODAxis(4);
		bean.setOSAxis(4);
		bean.setODAdd(1.0);
		bean.setOSAdd(1.0);
		dao.edit(bean);
		
		assertEquals(0, dao.getList(92).size()); // nothing created
		
		//load one record
		gen.ovOphthalmology();
		gen.ovOphthalmologyData();
		List<OphthalmologyDataBean> list = dao.getList(117);
		assertEquals(1, list.size());
		
		bean = list.get(0);
		bean.setOSAcuityDenominator(70);
		bean.setOSAdd(2.3);
		dao.edit(bean);
		
		OphthalmologyDataBean currBean = dao.getList(117).get(0);
		assertEquals(Integer.valueOf(70), Integer.valueOf(currBean.getOSAcuityDenominator()));
		assertEquals(Double.valueOf(2.3), currBean.getOSAdd());
		
	}
	
	/**
	 * Tests the getOfficeVisitsWithOpthData() method
	 * @throws Exception
	 */
	public void testGetOfficeVisitsWithOpthData() throws Exception {
		//Patient 15 has an Ophthalmology office visit
		gen.ovOphthalmology();
		gen.ovOphthalmologyData();
		List<OfficeVisitBean> ovlist = dao.getOfficeVisitsWithOpthData(15);
		assertEquals(1, ovlist.size());
	}
}
