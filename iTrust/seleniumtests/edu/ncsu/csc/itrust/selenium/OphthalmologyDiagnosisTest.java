package edu.ncsu.csc.itrust.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import edu.ncsu.csc.itrust.enums.TransactionType;

public class OphthalmologyDiagnosisTest extends iTrustSeleniumTest {
	private WebDriver driver = null;
	
	protected void setUp() throws Exception {
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		
	}
	
	protected void tearDown() throws Exception {
		gen.clearAllTables();
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public void testGetOphthalmologyDiagnosis() throws Exception {
		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver)this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");
		
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		//find patient
		driver.findElement(By.linkText("Document Office Visit")).click();
		
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		//Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp", driver.getCurrentUrl());
		
		//On the correct page 
		assertEquals("iTrust - Document Office Visit" , driver.getTitle());
		
		driver.findElementByLinkText("10/12/2015").click();
		
		Select select = new Select (driver.findElement(By.name("ICDCode")));
		assertEquals(5,select.getOptions().size());	
	}
	
	public void testGetNonOphthalmologyDiagnosis() throws Exception {
		// login HCP Brooke Tran
		HtmlUnitDriver driver = (HtmlUnitDriver)this.login("9900000022", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000022L, 0L, "");
		
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		//find patient
		driver.findElement(By.linkText("Document Office Visit")).click();
		
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("23");
		driver.findElement(By.xpath("//input[@value='23']")).submit();
		//Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp", driver.getCurrentUrl());
		
		//On the correct page 
		assertEquals("iTrust - Document Office Visit" , driver.getTitle());
		
		driver.findElementByLinkText("03/31/2011").click();
		
		Select select = new Select(driver.findElementByName("ICDCode"));
		assertEquals(18,select.getOptions().size());	
	}
	
	/*public void testGetOphthalmologyDiagnosisPatient() throws Exception {
		
	}
	
	public void testGetOphthalmologyDependant() throws Exception {
		
	}*/
}