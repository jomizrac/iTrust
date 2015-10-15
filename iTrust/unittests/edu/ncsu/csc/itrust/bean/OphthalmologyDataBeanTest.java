package edu.ncsu.csc.itrust.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.OphthalmologyDataBean;

/**
 */
public class OphthalmologyDataBeanTest extends TestCase {

	private OphthalmologyDataBean odbean;
	
	@Override
	/**
	 * sets up ophthalmology data bean for testing purposes
	 */
	protected void setUp() throws Exception {
		odbean = new OphthalmologyDataBean();
		odbean.setAcuityDenominator(1);
		odbean.setAcuityNumerator(1);
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
	 * test ophthalmology data equals
	 * @throws Exception
	 */
	public void testOphthalmologyDataEquals() throws Exception {
		assertEquals(Integer.valueOf(1), Integer.valueOf(odbean.getAcuityDenominator()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(odbean.getAcuityNumerator()));
		assertEquals(Double.valueOf(2.0), odbean.getODSphere());
		assertEquals(Double.valueOf(2.0), odbean.getOSSphere());
		assertEquals(Double.valueOf(3.0), odbean.getODCylinder());
		assertEquals(Double.valueOf(3.0), odbean.getOSCylinder());
		assertEquals(Integer.valueOf(4), Integer.valueOf(odbean.getODAxis()));
		assertEquals(Integer.valueOf(4), Integer.valueOf(odbean.getOSAxis()));
		assertEquals(Double.valueOf(1.0), odbean.getODAdd());
		assertEquals(Double.valueOf(1.0), odbean.getOSAdd());
		
	}
}
