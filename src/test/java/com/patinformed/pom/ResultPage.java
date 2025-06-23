package com.patinformed.pom;
	
	import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.DateUtil;

	public class ResultPage {
	    WebDriver driver;
	    WebDriverWait wait;

	    public ResultPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        PageFactory.initElements(driver, this);
	    }

	    public void selectFirstResult() {
	    	try {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='results']")));
	        List<WebElement> patents = driver.findElements(By.xpath("//table[@class='results']//tr/td/ul/li"));
	        if (!patents.isEmpty()) {
	            patents.get(0).click();
	        } else {
	            System.out.println(" No results found in the result table.");
	        }
	    } catch (Exception e) {
	        System.out.println(" Result table or no-data message not found: " + e.getMessage());
	    }
	    }

	    public void processPatentTables() {
	        List<WebElement> cards = driver.findElements(By.xpath("//li[contains(@class,'result card container showButtonsOnHover')]"));
	        boolean foundValidCard = false;
	       
	        for (WebElement card : cards) {
	        	 String filing = "", publication = "", grant = "";
	        	 try {
	                 filing = card.findElement(By.xpath(".//b[contains(text(),'Filing date')]/parent::td/following-sibling::td")).getText().split("\\(")[0].trim();
	             } catch (Exception e) {
	             }

	             try {
	                 publication = card.findElement(By.xpath(".//b[contains(text(),'Publication date')]/parent::td/following-sibling::td")).getText().split("\\(")[0].trim();
	             } catch (Exception e) {
	             }

	             try {
	                 grant = card.findElement(By.xpath(".//b[contains(text(),'Grant date')]/parent::td/following-sibling::td")).getText().split("\\(")[0].trim();
	             } catch (Exception e) {
	             }


	            //System.out.println("Filing: " + filing + " | Publication: " + publication + " | Grant: " + grant);

	            if (!filing.isEmpty() && !publication.isEmpty()) {
	                long diff = DateUtil.calculateDateDifference(filing, publication);
	                System.out.println("Filing and Publication = " + diff + " days");
	                foundValidCard = true;
	                break;
	            } else if (!filing.isEmpty() && !grant.isEmpty()) {
	                long diff = DateUtil.calculateDateDifference(filing, grant);
	                System.out.println("Filing and Grant = " + diff + " days");
	                foundValidCard = true;
	                break;
	            } else if (!publication.isEmpty() && !grant.isEmpty()) {
	                long diff = DateUtil.calculateDateDifference(publication, grant);
	                System.out.println("Publication and Grant = " + diff + " days");
	                foundValidCard = true;
	                break;
	            }
	        }

	        if (!foundValidCard) {
	            System.out.println("No card with at least two valid dates found to calculate difference.");
	        }
	    }
	}


