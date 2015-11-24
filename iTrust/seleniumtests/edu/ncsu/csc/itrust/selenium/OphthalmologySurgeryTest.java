package edu.ncsu.csc.itrust.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import edu.ncsu.csc.itrust.enums.TransactionType;
public class OphthalmologySurgeryTest extends iTrustSeleniumTest{

	protected void setUp() throws Exception{
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		
	}
	/**
	 * test creating an ophthalmology surgery visit
	 * @throws Exception
	 */
	public void testCreateOphthalmologySurgeryOfficeVisit() throws Exception{
		// login HCP Dr Laser
		HtmlUnitDriver driver = (HtmlUnitDriver)this.login("9900000023", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000023L, 0L, "");
		
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		//find patient
		driver.findElement(By.linkText("Document Office Visit")).click();
		
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		//Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp", driver.getCurrentUrl());
		
		//On the correct page 
		assertEquals("iTrust - Document Office Visit" , driver.getTitle());
		
		driver.findElement(By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		//Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");
		
		new Select(driver.findElement(By.name("apptType"))).selectByValue("Ophthalmology Surgery");
		
		driver.findElement(By.id("update")).click();
		
		assertTrue(driver.getPageSource().contains("Surgery"));
		
		//Add surgery data
		new Select(driver.findElement(By.name("surgeryID"))).selectByValue("1");
		driver.findElement(By.id("surgeryNotes")).clear();
		driver.findElement(By.id("surgeryNotes")).sendKeys("This is a hold up");
		
		
		//Submit form
		driver.findElement(By.xpath(".//*[@value='Add Surgery Data']")).click();
		//driver.findElement(By.xpath(".//*[@value='Add Surgery Data']")).submit();
				
		assertTrue(driver.getPageSource().contains("Cataract Surgery"));
	}
	
	//test no surgery type
	public void testNoSurgeryType() throws Exception{
		// login HCP Dr Laser
		HtmlUnitDriver driver = (HtmlUnitDriver)this.login("9900000023", "pw");
		assertLogged(TransactionType.HOME_VIEW, 9900000023L, 0L, "");
		
		assertTrue(driver.getPageSource().contains("iTrust - HCP Home"));
		//find patient
		driver.findElement(By.linkText("Document Office Visit")).click();
		
		driver.findElement(By.name("UID_PATIENTID")).sendKeys("15");
		driver.findElement(By.xpath("//input[@value='15']")).submit();
		//Verify Document Office Visit page
		assertEquals(ADDRESS + "auth/hcp-uap/documentOfficeVisit.jsp", driver.getCurrentUrl());
		
		//On the correct page 
		assertEquals("iTrust - Document Office Visit" , driver.getTitle());
		
		driver.findElement(By.xpath(".//*[@value='Yes, Document Office Visit']")).click();
		//Add date
		driver.findElement(By.name("visitDate")).clear();
		driver.findElement(By.name("visitDate")).sendKeys("10/14/2015");
		
		new Select(driver.findElement(By.name("apptType"))).selectByValue("Ophthalmology Surgery");
		
		driver.findElement(By.id("update")).click();
		
		assertTrue(driver.getPageSource().contains("Surgery"));
		
		//Add Surgery Data
		driver.findElement(By.id("surgeryNotes")).clear();
		driver.findElement(By.id("surgeryNotes")).sendKeys("This is a hold up");
		
		
		//Submit form
		driver.findElement(By.xpath(".//*[@value='Add Surgery Data']")).click();
		//driver.findElement(By.xpath(".//*[@value='Add Surgery Data']")).submit();
				
		assertTrue(driver.getPageSource().contains("Must select a Surgery Type"));
	}
	
}
