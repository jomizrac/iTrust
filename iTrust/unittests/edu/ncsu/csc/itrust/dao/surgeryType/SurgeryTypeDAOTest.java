package edu.ncsu.csc.itrust.dao.surgeryType;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.beans.SurgeryTypeBean;
import edu.ncsu.csc.itrust.dao.mysql.SurgeryTypeDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class SurgeryTypeDAOTest {

	private TestDataGenerator gen = new TestDataGenerator();
	private SurgeryTypeDAO surgeryDAO = TestDAOFactory.getTestInstance().getSurgeryTypeDAO();
	
	/**
	 * Sets up the test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		gen.clearAllTables();
		gen.standardData();
	}

	/**
	 * Cleans up after the test
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		gen.clearAllTables();
	}

	/**
	 * Ensures the database has Surgery Types
	 */
	@Test
	public void testGetSurgeryTypes() {
		// get surgeries in database
		try {
			List<SurgeryTypeBean> results = surgeryDAO.getSurgeryTypes();
			assertFalse(results.isEmpty());
		} catch (DBException e) {
			fail(e.getMessage());
		}
	}
}
