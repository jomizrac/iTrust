package edu.ncsu.csc.itrust.bean;

import edu.ncsu.csc.itrust.beans.forms.EditSurgeryDataForm;
import junit.framework.TestCase;

/**
 * Test for EditSurgeryDataForm
 */
public class EditSurgeryDataFormTest extends TestCase
{
	/**
	 * Test for a bean.
	 */
	public  void testBean()
	{
		EditSurgeryDataForm bean = new EditSurgeryDataForm();
		bean.setSurgeryID("1");
		bean.setSurgeryNotes("Kinda nothing");
		
		assertTrue(bean.getSurgeryID().equals("1"));
		assertTrue(bean.getSurgeryNotes().equals("Kinda nothing"));
	}
}
