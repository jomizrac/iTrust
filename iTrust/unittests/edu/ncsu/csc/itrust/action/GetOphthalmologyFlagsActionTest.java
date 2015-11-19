package edu.ncsu.csc.itrust.action;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.beans.OphthalmologyFlagBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.ITrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class GetOphthalmologyFlagsActionTest extends TestCase {
	
	private TestDataGenerator gen = new TestDataGenerator();
	/** Freya Chandler */
	private String pidString = "15";
	/** Brooke Tran */
	private long loggedInMID = 9900000022L;
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private GetOphthalmologyFlagsAction action;

	/**
	 * Set up data for testings
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		
		this.action = new GetOphthalmologyFlagsAction(factory, loggedInMID, pidString);
	}

	/**
	 * reset data after testing
	 */
	@After
	public void tearDown() throws Exception {
		gen.clearAllTables();
	}

	/**
	 * Creates Ophthalmology flags for Freya Chandler
	 */
	@Test
	public void testCreateFlags() {
		List<OphthalmologyFlagBean> flags = null;
		try {
			flags = action.createFlags();
			assertNotNull(flags);
		} catch (ITrustException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Creates Ophthalmology flags for Freya Chandler and adds them to the database
	 */
	@Test
	public void testAddFlags() {
		try {
			List<OphthalmologyFlagBean> flags = action.createFlags();
			action.addFlags(flags);
		} catch (ITrustException e) {
			fail(e.getMessage());
		}
	}
}
