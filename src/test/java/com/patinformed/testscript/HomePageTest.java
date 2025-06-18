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
		driver.get("https://patinformed.wipo.int/");
		homepage.inputDataInSearchBox("par");
		homepage.clickOnpatentListOption();

		//  Identify block by two known values inside it
	    String text1 = "2098224"; 
	    String text2 = "Belgium";       
	    boolean requireBoth = true;         

        WebElement block = homepage.getBlockByTexts(text1, text2, requireBoth);

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

            System.out.println(" Block matched using: '" + text1 + "' and '" + text2 + "'");
            System.out.println(" Filing Date: " + filingDateStr + " | Publication Date: " + publicationDateStr);
            System.out.println(" Difference in Days: " + diff);
        } else {
            System.out.println(" Dates missing. Filing: " + filingDateStr + ", Publication: " + publicationDateStr);
        }
    }
	}