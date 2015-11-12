package edu.ncsu.csc.itrust.dao.ophthalmologyFlag;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.dao.mysql.OphthalmologyFlagDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.OphthalmologyFlag;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class OphthalmologyFlagDAOTest extends TestCase {
	
	private OphthalmologyFlagDAO dao = TestDAOFactory.getTestInstance().getOphthalmologyFlagDAO();
	private TestDataGenerator gen;
	private OphthalmologyFlagBean bean;

	@Before
	/**
	 * Set up the test by clearing the test data and creating an OphthalmoogyFlagBean
	 */
	public void setUp() throws Exception {
		this.gen = new TestDataGenerator();
		gen.clearAllTables();
		
		this.bean = new OphthalmologyFlagBean();
		bean.setFid(1L);
		bean.setMid(1L);
		bean.setValue(OphthalmologyFlag.Smoker);
		bean.setFlagged(true);
	}

	@After
	/**
	 * Tear down by deleting all test data
	 */
	public void tearDown() throws Exception {
		gen.clearAllTables();
	}

	@Test
	/**
	 * Tests the methods of the OphthalmologyFlagDAO
	 * @throws Exception
	 */
	public void testOphthalmologyFlagDAO() throws Exception {
		// No flags should be in the database at the beginning
		List<OphthalmologyFlagBean> results = dao.getFlagsByMid(1L);
		assertTrue(results.isEmpty());
		
		// Add the bean to the database
		dao.setFlag(bean);
		results = dao.getFlagsByMid(1L);
		assertFalse(results.isEmpty());
		
		// Get the flag and verify contents
		OphthalmologyFlagBean testResult = dao.getFlag(bean);
		assertEquals(testResult.getFid(), 1L);
		assertEquals(testResult.getMid(), 1L);
		assertEquals(testResult.getValue(), OphthalmologyFlag.parseEnum("Smoker"));
		assertTrue(testResult.isFlagged());
	}
}
