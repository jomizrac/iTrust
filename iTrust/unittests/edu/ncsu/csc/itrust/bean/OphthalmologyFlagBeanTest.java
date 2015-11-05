package edu.ncsu.csc.itrust.bean;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;

public class OphthalmologyFlagBeanTest extends TestCase {

	private OphthalmologyFlagBean bean;
	
	private OphthalmologyFlagBean bean2;
	
	@Before
	/**
	 * Creates an OphthalmologyFlagBean for tests
	 */
	public void setUp() throws Exception {
		this.bean = new OphthalmologyFlagBean();
		bean.setFid(1L);
		bean.setMid(1L);
		bean.setValue(OphthalmologyFlag.AfricanAmerican40);
		bean.setFlagged(true);
		
		this.bean2 = new OphthalmologyFlagBean();
		bean2.setFid(3L);
		bean2.setMid(4L);
		bean2.setValue(OphthalmologyFlag.Over60);
		bean2.setFlagged(false);
	}
	
	@Test
	/**
	 * Verifies data in setup is correctly set.
	 * @throws Exception
	 */
	public void testOphthalmologyFlagBean() throws Exception {
		assertEquals(bean.getFid(), 1L);
		assertEquals(bean.getMid(), 1L);
		assertEquals(bean.getValue(), OphthalmologyFlag.parseEnum("Race African American and over age 40"));
		assertTrue(bean.isFlagged());
		
		assertEquals(bean2.getFid(), 3L);
		assertEquals(bean2.getMid(), 4L);
		assertEquals(bean2.getValue(), OphthalmologyFlag.parseEnum("60"));
		assertFalse(bean2.isFlagged());
	}
}
