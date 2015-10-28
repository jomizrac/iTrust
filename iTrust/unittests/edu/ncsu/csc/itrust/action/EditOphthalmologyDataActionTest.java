package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;
import edu.ncsu.csc.itrust.beans.forms.EditOphthalmologyDataForm;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

/**
 * Tests EditOphthalmologyDataAction 
 * @author Michael
 *
 */
public class EditOphthalmologyDataActionTest extends TestCase {
	
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	private OphthalmologyDataBean odbean;
	
	/**
	 * Sets up the Data for testing
	 * @throws Exception
	 */
	@Override
	public void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		gen.hcpOphthalmology();
		
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
	 * Tests the EditOphthalmologyDataAction1 method
	 * @throws Exception
	 */
	public void testEditOphthalmologyDataAction1() throws Exception {
		EditOphthalmologyDataAction opaction = new EditOphthalmologyDataAction(factory, 9900000022L, "15", "117");
		OphthalmologyDataBean bean = opaction.getOphthalmologyData().get(0);
		assertEquals(Integer.valueOf(20), Integer.valueOf(bean.getODAcuityDenominator()));
	}
	
	/**
	 * Tests the EditOphthalmologyDataAction2 method
	 * @throws Exception
	 */
	public void testEditOphthalmologyDataAction2() throws Exception {
		EditOphthalmologyDataAction opaction2 = new EditOphthalmologyDataAction(factory, 9900000022L, "15");
		assertNotNull(opaction2);
		assertEquals(0, opaction2.getOphthalmologyData().size());
	}
	
	/**
	 * Tests the GetOphthalmologyData method
	 * @throws Exception
	 */
	public void testGetOphthalmologyData() throws Exception {
		EditOphthalmologyDataAction opaction = new EditOphthalmologyDataAction(factory, 9900000022L, "15", "117");
		OphthalmologyDataBean bean = opaction.getOphthalmologyData().get(0);
		assertEquals(Integer.valueOf(20), Integer.valueOf(bean.getODAcuityDenominator()));
		
		EditOphthalmologyDataAction opaction2 = new EditOphthalmologyDataAction(factory, 9900000022L, "15");
		assertNotNull(opaction2);
		assertEquals(0, opaction2.getOphthalmologyData().size());
	}
	
	/**
	 * Tests the AddOphthalmologyData method
	 * @throws Exception
	 */
	public void testAddOphthalmologyData() throws Exception {
		EditOphthalmologyDataAction opaction = new EditOphthalmologyDataAction(factory, 9900000022L, "15", "117");
		OphthalmologyDataBean bean = opaction.getOphthalmologyData().get(0);
		assertEquals(Integer.valueOf(20), Integer.valueOf(bean.getOSAcuityDenominator()));
		
		opaction.addOphthalmologyData(odbean);
		odbean.setOSAcuityDenominator(36);
		opaction.editOphthalmologyData(odbean);
		assertEquals(Integer.valueOf(36), Integer.valueOf(odbean.getOSAcuityDenominator()));
		
	}
	
	/**
	 * Tests the FormToBean method
	 * @throws Exception
	 */
	public void testFormToBean() throws Exception {
		EditOphthalmologyDataForm form = new EditOphthalmologyDataForm();
		form.setODAcuityDenominator("1");
		form.setODAcuityNumerator("1");
		form.setOSAcuityDenominator("1");
		form.setOSAcuityNumerator("1");
		form.setOdsphere("2.0");
		form.setOssphere("2.0");
		form.setOdcylinder("3.0");
		form.setOscylinder("3.0");
		form.setOdaxis("4");
		form.setOsaxis("4");
		form.setOdadd("1.0");
		form.setOsadd("1.0");
		
		EditOphthalmologyDataAction opaction = new EditOphthalmologyDataAction(factory, 9900000022L, "15", "117");
		OphthalmologyDataBean bean = opaction.formToBean(form);
		
		assertEquals(Integer.valueOf(1), Integer.valueOf(bean.getODAcuityDenominator()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(bean.getODAcuityNumerator()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(bean.getOSAcuityDenominator()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(bean.getOSAcuityNumerator()));
		assertEquals(Double.valueOf(2.0), bean.getODSphere());
		assertEquals(Double.valueOf(2.0), bean.getOSSphere());
		assertEquals(Double.valueOf(3.0), bean.getOSCylinder());
		assertEquals(Integer.valueOf(4), Integer.valueOf(bean.getODAxis()));
		assertEquals(Integer.valueOf(4), Integer.valueOf(bean.getOSAxis()));
		assertEquals(Double.valueOf(1.0), bean.getODAdd());
		assertEquals(Double.valueOf(1.0), bean.getOSAdd());
		
		
	}	
}