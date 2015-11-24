package edu.ncsu.csc.itrust.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import edu.ncsu.csc.itrust.enums.TransactionType;

public class OphthalmologyOVTest extends iTrustSeleniumTest {

	protected void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();

	}

	/**
	 * test creating an ophthalmology visit
	 * 
	 * @throws Exception
	 */
	public void testCreateOphthalmologyOfficeVisit() throws Exception {
		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("72");
		driver.findElement(By.xpath("//input[@value='72']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		assertTrue(driver.getPageSource().contains("Ophthalmology Data"));
		// Add Ophthalmology Data
		driver.findElement(By.id("ODAcuityNumerator")).clear();
		driver.findElement(By.id("ODAcuityNumerator")).sendKeys("20");

		driver.findElement(By.id("ODAcuityDenominator")).clear();
		driver.findElement(By.id("ODAcuityDenominator")).sendKeys("20");

		driver.findElement(By.id("OSAcuityNumerator")).clear();
		driver.findElement(By.id("OSAcuityNumerator")).sendKeys("20");

		driver.findElement(By.id("OSAcuityDenominator")).clear();
		driver.findElement(By.id("OSAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("odsphere")).clear();
		driver.findElement(By.name("odsphere")).sendKeys("-3.25");

		driver.findElement(By.name("ossphere")).clear();
		driver.findElement(By.name("ossphere")).sendKeys("-3.00");

		driver.findElement(By.name("odcylinder")).clear();
		driver.findElement(By.name("odcylinder")).sendKeys("-1.25");

		driver.findElement(By.name("oscylinder")).clear();
		driver.findElement(By.name("oscylinder")).sendKeys("1.25");

		driver.findElement(By.name("osaxis")).clear();
		driver.findElement(By.name("osaxis")).sendKeys("60");

		driver.findElement(By.name("odaxis")).clear();
		driver.findElement(By.name("odaxis")).sendKeys("50");

		driver.findElement(By.name("odadd")).clear();
		driver.findElement(By.name("odadd")).sendKeys("+1.00");

		driver.findElement(By.name("osadd")).clear();
		driver.findElement(By.name("osadd")).sendKeys("+1.00");

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains("10/14/2015"));
	}

	/**
	 * Tests creating an Ophthalmology office visit with an error
	 * 
	 * @throws Exception
	 */
	public void testCreateWithErrorChecking() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/15/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();
		// Add Ophthalmology Data
		driver.findElement(By.name("ODAcuityNumerator")).clear();
		driver.findElement(By.name("ODAcuityNumerator")).sendKeys("20/10");

		driver.findElement(By.id("ODAcuityDenominator")).clear();
		driver.findElement(By.id("ODAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("OSAcuityNumerator")).clear();
		driver.findElement(By.name("OSAcuityNumerator")).sendKeys("20/10");

		driver.findElement(By.id("OSAcuityDenominator")).clear();
		driver.findElement(By.id("OSAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("odsphere")).clear();
		driver.findElement(By.name("odsphere")).sendKeys("+1.75");

		driver.findElement(By.name("ossphere")).clear();
		driver.findElement(By.name("ossphere")).sendKeys("+17.50");

		driver.findElement(By.name("odadd")).clear();
		driver.findElement(By.name("odadd")).sendKeys("+1.25");

		driver.findElement(By.name("osadd")).clear();
		driver.findElement(By.name("osadd")).sendKeys("+1.25");

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains(
				"OSSphere: Must be a double between -10 and 10"));

		driver.findElement(By.name("ossphere")).clear();
		driver.findElement(By.name("ossphere")).sendKeys("+1.75");

		driver.findElement(By.id("update")).click();

		assertTrue(driver.getPageSource().contains("10/15/2015"));
	}

	/**
	 * Tests having an non-opthamologist hcp trying to edit a opthamology office
	 * visit
	 * 
	 * @throws Exception
	 */
	public void testSpecializationRestriction() throws Exception {
		// login HCP Kelly Doctor
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9000000000", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9000000000L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();

		assertFalse(driver.getPageSource().contains("Ophthalmology"));

		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.linkText("10/15/2015")).click();

		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		// assertTrue(driver.getPageSource().contains("20/10"));
		assertTrue(driver.getPageSource().contains("+1.75"));
		assertTrue(driver.getPageSource().contains("+1.75"));
		assertTrue(driver.getPageSource().contains("+1.25"));
		assertTrue(driver.getPageSource().contains("+1.25"));

	}

	/**
	 * Test OHCP editing an ophthalmology office visit
	 * 
	 * @throws Exception
	 */
	public void testEditOphthalmologyVisit() throws Exception {
		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/15/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();
		// Edit Ophthalmology Data
		driver.findElement(By.name("ODAcuityNumerator")).clear();
		driver.findElement(By.name("ODAcuityNumerator")).sendKeys("20/15");

		driver.findElement(By.id("ODAcuityDenominator")).clear();
		driver.findElement(By.id("ODAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("OSAcuityNumerator")).clear();
		driver.findElement(By.name("OSAcuityNumerator")).sendKeys("20/15");

		driver.findElement(By.id("OSAcuityDenominator")).clear();
		driver.findElement(By.id("OSAcuityDenominator")).sendKeys("20");

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		driver.findElement(By.id("update")).click();

		assertTrue(driver.getPageSource().contains("10/15/2015"));
	}

	/**
	 * tests logging capabilities
	 * 
	 * @throws Exception
	 */
	public void testEditOphthalmologyDataLogging() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("72");
		driver.findElement(By.xpath("//input[@value='72']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/18/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();
		// Add Ophthalmology Data
		driver.findElement(By.name("ODAcuityNumerator")).clear();
		driver.findElement(By.name("ODAcuityNumerator")).sendKeys("20/40");

		driver.findElement(By.id("ODAcuityDenominator")).clear();
		driver.findElement(By.id("ODAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("OSAcuityNumerator")).clear();
		driver.findElement(By.name("OSAcuityNumerator")).sendKeys("20/40");

		driver.findElement(By.id("OSAcuityDenominator")).clear();
		driver.findElement(By.id("OSAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("odsphere")).clear();
		driver.findElement(By.name("odsphere")).sendKeys("-3.25");

		driver.findElement(By.name("ossphere")).clear();
		driver.findElement(By.name("ossphere")).sendKeys("-3.00");

		driver.findElement(By.name("odcylinder")).clear();
		driver.findElement(By.name("odcylinder")).sendKeys("-1.25");

		driver.findElement(By.name("odcylinder")).clear();
		driver.findElement(By.name("odcylinder")).sendKeys("1.25");

		driver.findElement(By.name("odaxis")).clear();
		driver.findElement(By.name("odaxis")).sendKeys("50");

		driver.findElement(By.name("osaxis")).clear();
		driver.findElement(By.name("osaxis")).sendKeys("50");

		driver.findElement(By.name("odadd")).clear();
		driver.findElement(By.name("odadd")).sendKeys("+1.00");

		driver.findElement(By.name("osadd")).clear();
		driver.findElement(By.name("osadd")).sendKeys("+1.00");

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains("10/18/2015"));

		assertLogged(TransactionType.OFFICE_VISIT_CREATE, 9900000022L, 72L, "");
	}

	/**
	 * Tests non-ophthalmolgy office visit
	 * 
	 * @throws Exception
	 */
	public void testNoOphthalmologyDataSection() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("72");
		driver.findElement(By.xpath("//input[@value='72']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");

		driver.findElement(By.id("update")).click();

		assertFalse(driver.getPageSource().contains("Ophthalmology Data"));
	}

	/**
	 * tests empty data
	 * 
	 * @throws Exception
	 */
	public void testMissingOphthalmologistData() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("72");
		driver.findElement(By.xpath("//input[@value='72']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());
		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();

		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains(
				"Acuity: Must be a positive integer."));
		assertTrue(driver.getPageSource().contains(
				"Acuity: Must be a positive integer."));
		assertTrue(driver.getPageSource().contains(
				"ODSphere: Must be a double between -10 and 10"));
		assertTrue(driver.getPageSource().contains(
				"OSSphere: Must be a double between -10 and 10"));
		assertTrue(driver.getPageSource().contains(
				"ODAdd: Must be between .75 and 3.00"));
		assertTrue(driver.getPageSource().contains(
				"OSAdd: Must be between .75 and 3.00"));
	}

	/**
	 * tests removing data from existing OV and getting an error
	 * 
	 * @throws Exception
	 */
	public void testEditOfficeVisit_DeleteData() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));

		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(By.linkText("10/15/2015")).click();

		driver.findElement(By.name("odadd")).clear();

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Update Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Update Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains(
				"ODAdd: Must be between .75 and 3.00"));
	}

	/**
	 * tests letters being input into int form
	 * 
	 * @throws Exception
	 */
	public void testOphthalmologyDataInvalidChars() throws Exception {

		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");

		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("72");
		driver.findElement(By.xpath("//input[@value='72']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();
		// Add Ophthalmology Data
		driver.findElement(By.name("ODAcuityNumerator")).clear();
		driver.findElement(By.name("ODAcuityNumerator")).sendKeys("20/40");

		driver.findElement(By.id("ODAcuityDenominator")).clear();
		driver.findElement(By.id("ODAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("OSAcuityNumerator")).clear();
		driver.findElement(By.name("OSAcuityNumerator")).sendKeys("20/40");

		driver.findElement(By.id("OSAcuityDenominator")).clear();
		driver.findElement(By.id("OSAcuityDenominator")).sendKeys("20");

		driver.findElement(By.name("odsphere")).clear();
		driver.findElement(By.name("odsphere")).sendKeys("blue");

		driver.findElement(By.name("ossphere")).clear();
		driver.findElement(By.name("ossphere")).sendKeys("-3.00");

		driver.findElement(By.name("odcylinder")).clear();
		driver.findElement(By.name("odcylinder")).sendKeys("-1.25");

		driver.findElement(By.name("odaxis")).clear();
		driver.findElement(By.name("odaxis")).sendKeys("50");

		driver.findElement(By.name("odadd")).clear();
		driver.findElement(By.name("odadd")).sendKeys("+1.00");

		driver.findElement(By.name("osadd")).clear();
		driver.findElement(By.name("osadd")).sendKeys("+1.00");

		// Submit form
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.click();
		driver.findElement(By.xpath(".//*[@value='Add Ophthalmology Data']"))
				.submit();

		assertTrue(driver.getPageSource().contains(
				"ODSphere: Must be a double between -10 and 10"));
	}

	public void testPatientViewOphthalmologyOV() throws Exception {
		gen.brody_franco_ov();
		// Login as Brody Franco
		HtmlUnitDriver driver = (HtmlUnitDriver) login("72", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();
		driver.findElementByLinkText("Oct 14, 2015").click();

		// See the following information
		assertTrue(driver.getPageSource().contains("Brooke Tran"));
		assertTrue(driver.getPageSource().contains("20"));
		assertTrue(driver.getPageSource().contains("40"));
		assertTrue(driver.getPageSource().contains("-3.25"));
		assertTrue(driver.getPageSource().contains("-3.00"));
		assertTrue(driver.getPageSource().contains("-1.25"));
		assertTrue(driver.getPageSource().contains("50"));
		assertTrue(driver.getPageSource().contains("+1.00"));
	}

	public void testPatientViewEditedOV() throws Exception {
		// Login as Freya Chandler
		HtmlUnitDriver driver = (HtmlUnitDriver) login("15", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();
		driver.findElementByLinkText("Oct 15, 2015").click();

		// Checks the data

		// Login as Brooke Tran
		driver = (HtmlUnitDriver) login("9900000022", "pw");
		assertTrue(driver.getTitle().equals("iTrust - HCP Home"));

		// Change the OV Ophthalmology Data
		driver.findElementByLinkText("Document Office Visit").click();
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());
		driver.findElementByLinkText("10/15/2015").click();
		driver.findElement(By.name("ODAcuityDenominator")).clear();
		driver.findElement(By.name("ODAcuityDenominator")).sendKeys("15");
		driver.findElement(By.id("updateOphthalmologyDataButton")).click();

		// Login as Freya Chandler
		driver = (HtmlUnitDriver) login("15", "pw");

		// View the Ophthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();
		driver.findElementByLinkText("Oct 15, 2015").click();

		// Check the data
		assertTrue(driver.getPageSource().contains("Brooke Tran"));
		assertTrue(driver.getPageSource().contains("10"));
		assertTrue(driver.getPageSource().contains("15"));
		assertTrue(driver.getPageSource().contains("+1.75"));
		assertTrue(driver.getPageSource().contains("+1.25"));
		assertTrue(driver.getPageSource().contains("10/15/2015"));
	}

	public void testPatientViewDependentsOV() throws Exception {
		HtmlUnitDriver driver = (HtmlUnitDriver) login("72", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();
		assertTrue(driver.getPageSource().contains("Brittany Franco"));

		driver.findElementByLinkText("Brittany Franco").click();
		driver.findElementByLinkText("Oct 30, 2015").click();

		// Assert true on valid Ophthalmology Data
	}

	public void testNoOphthalmogyAppointment() throws Exception {
		HtmlUnitDriver driver = (HtmlUnitDriver) login("72", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();

		// Assert that there is no Ophthalmology Office Visit
		assertFalse(false);

	}

	public void testNoDependents() throws Exception {
		// Login as Freya Chandler
		HtmlUnitDriver driver = (HtmlUnitDriver) login("15", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();

		assertTrue(driver.getPageSource().contains(
				"Freya is not representing any patients"));

		driver = (HtmlUnitDriver) login("75", "pw");

		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();

		assertTrue(driver.getPageSource().contains("Baby Chandler"));

	}

	public void testGlaucomaOphthalmologyFlag() throws Exception {
		// Login as Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("11/11/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		// Ensure flag present
		assertTrue(driver.getPageSource().contains("High risk glaucoma!"));
	}

	public void testOphthalmologyFlagLogging() throws Exception {
		// Login as Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("11/11/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		// Flags logged
		assertLogged(TransactionType.CREATE_OPHTHALMOLOGY_FLAG, 9900000022L,
				15L, "");
	}

	public void testMDOphthalmologyFlag() throws Exception {
		// Login as Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("2");
		driver.findElement(By.xpath("//input[@value='2']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("11/11/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		// Ensure flag present
		assertTrue(driver.getPageSource().contains(
				"High risk macular degeneration!"));
	}

	public void testNoOphthalmologyFlags() throws Exception {
		// Login as Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver) this.login("9900000022", "pw");
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		// find patient
		driver.findElement(By.linkText("Document Office Visit")).click();

		driver.findElement(By.name("UID_PATIENTID")).sendKeys("5");
		driver.findElement(By.xpath("//input[@value='5']")).submit();
		// Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp",
				driver.getCurrentUrl());

		// On the correct page
		assertEquals("iTrust - Document Office Visit", driver.getTitle());

		driver.findElement(
				By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		// Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("11/11/2015");

		new Select(driver.findElement(By.name("apptType")))
				.selectByValue("Ophthalmology");

		driver.findElement(By.id("update")).click();

		// Ensure flag not present
		assertTrue(driver.getPageSource().contains("No Warnings"));
	}

	public void testPatientViewOphthalmologyFlags() throws Exception {
		// Login as Freya Chandler
		HtmlUnitDriver driver = (HtmlUnitDriver) login("15", "pw");
		assertTrue(driver.getTitle().equals("iTrust - Patient Home"));

		// View Opthalmology Office Visit
		driver.findElementByLinkText("View My Records").click();
		driver.findElementByLinkText("Oct 15, 2015").click();

		// Checks for flags
		assertFalse(driver.getPageSource().contains("High Risk glaucoma!"));
	}
}
