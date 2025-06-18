package com.patinformed.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;

	@FindBy(xpath = "//button[contains(text(),'I have read and agree to the terms')]")
	private WebElement agreeButton;

	@FindBy(xpath = "//input[contains(@placeholder,'Search pharmaceutical patents')]")
	private WebElement searchButton;

	@FindBy(xpath = "//ul[contains(@class,'associations flex column')]//div[contains(text(),'RAPIDLY DISINTEGRATING GELATINOUS COATED TABLETS')]")
	private WebElement patentOption;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public void clickOnAgreeButton() {
		agreeButton.click();
	}

	public void clickOnpatentListOption() {
		patentOption.click();
	}

	public void inputDataInSearchBox(String data) {
		searchButton.click();
		searchButton.sendKeys(data);
		agreeButton.click();
	}

	@FindBy(xpath = "//table[contains(@class, 'patentDetails')]")
	private WebElement allPatentBlocks;

	//  find block containing both textA and textB
	public WebElement getBlockByTwoTexts(String textA, String textB) {
	    List<WebElement> blocks = driver.findElements(By.xpath("//table[contains(@class, 'patentDetails')]"));
	    for (WebElement block : blocks) {
	        String blockText = block.getText();
	        if (blockText.contains(textA) && blockText.contains(textB)) {
	            return block;
	        }
	    }
	    return null; 
	}

	public String getPublicationDate(WebElement block) {
		try {
			WebElement pub = block.findElement(
					By.xpath("//b[contains(text(),'Publication date')]/parent::td/following-sibling::td"));
			return pub.getText().split("\\(")[0].trim();
		} catch (Exception e) {
			return null;
		}
	}

	public String getFilingDate(WebElement block) {
		try {
			WebElement file = block
					.findElement(By.xpath("//b[contains(text(),'Filing date')]/parent::td/following-sibling::td"));
			return file.getText().split("\\(")[0].trim();
		} catch (Exception e) {
			return null;
		}
	}

}
