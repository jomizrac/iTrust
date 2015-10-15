package edu.ncsu.csc.itrust.action;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.forms.EditOphthalmologyDataForm;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;

/**
 * Tests the EditOphthalmologyDataForm class
 * @author Michael
 *
 */
public class EditOphthalmologyDataFormTest extends TestCase {

	private TestDataGenerator gen = new TestDataGenerator();
	private EditOphthalmologyDataForm form;
	
	/**
	 * Sets up the Data for testing
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		
		form = new EditOphthalmologyDataForm();
		form.setAcuityDenominator("1");
		form.setAcuityNumerator("1");
		form.setOdsphere("2.0");
		form.setOssphere("2.0");
		form.setOdcylinder("3.0");
		form.setOscylinder("3.0");
		form.setOdaxis("4");
		form.setOsaxis("4");
		form.setOdadd("1.0");
		form.setOsadd("1.0");
	}
	
	/**
	 * Tests that the getters and setters of the form work
	 * @throws Exception
	 */
	public void testOphthalmologyDataEquals() throws Exception {
		assertEquals("1", form.getAcuityDenominator());
		assertEquals("1", form.getAcuityNumerator());
		assertEquals("2.0", form.getOdsphere());
		assertEquals("2.0", form.getOssphere());
		assertEquals("3.0", form.getOdcylinder());
		assertEquals("3.0", form.getOscylinder());
		assertEquals("4", form.getOdaxis());
		assertEquals("4", form.getOsaxis());
		assertEquals("1.0", form.getOdadd());
		assertEquals("1.0", form.getOsadd());
		
	}
}
