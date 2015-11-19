package edu.ncsu.csc.itrust.bean;

import edu.ncsu.csc.itrust.beans.SurgeryTypeBean;
import junit.framework.TestCase;

/**
 * Test for SurgeryTypeBean
 */
public class SurgeryTypeBeanTest extends TestCase
{
	/**
	 * Test for a bean.
	 */
	public  void testBean()
	{
		SurgeryTypeBean bean = new SurgeryTypeBean();
		bean.setSurgeryID(1);
		bean.setSurgeryName("Cataract Surgery");
		
		assertTrue(bean.getSurgeryName().equals("Cataract Surgery"));
		assertTrue(bean.getSurgeryID() == 1L);
	}
}
