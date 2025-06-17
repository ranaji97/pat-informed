package com.patinformed.testscript;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.patinformed.generic.BaseClass;
import com.patinformed.pom.HomePage;
import com.utility.ListenerImpl;

@Listeners(ListenerImpl.class)
public class HomePageTest extends BaseClass {

	HomePage homepage;

	@Test
	public void dateDifferenceinDays() {
		homepage = new HomePage(driver);
		driver.get("https://pinformed.wipo.int/");
		homepage.inputDataInSearchBox("par");
		homepage.clickOnpatentListOption();

		//  Identify block by two known values inside it
	    String text1 = "1773302"; 
	    String text2 = "";       

	    WebElement block = homepage.getBlockByTwoTexts(text1, text2);

	    if (block == null) {
	        System.out.println(" No block found containing both: " + text1 + " AND " + text2);
	        return;
	    }

	    String filingDateStr = homepage.getFilingDate(block);
	    String publicationDateStr = homepage.getPublicationDate(block);

	    if (filingDateStr != null && publicationDateStr != null) {
	        LocalDate filingDate = LocalDate.parse(filingDateStr);
	        LocalDate publicationDate = LocalDate.parse(publicationDateStr);
	        long diff = ChronoUnit.DAYS.between(filingDate, publicationDate);

	        System.out.println(" Block identified by: " + text1 + " AND " + text2);
	        System.out.println(" Filing Date: " + filingDateStr + " | Publication Date: " + publicationDateStr);
	        System.out.println(" Difference: " + diff + " days");
	    } else {
	        System.out.println(" Missing one or both dates in the matched block.");
	    }
	}
}