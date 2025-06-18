package com.patinformed.pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;

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
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait until the patentOption is visible and clickable
		wait.until(ExpectedConditions.elementToBeClickable(patentOption));
		patentOption.click();
	}

	public void inputDataInSearchBox(String data) {
		searchButton.click();
		searchButton.sendKeys(data);
		agreeButton.click();
	}

//	@FindBy(xpath = "//table[contains(@class, 'patentDetails')]")
//	private WebElement allPatentBlocks;

	public WebElement getBlockByTexts(String textA, String textB, boolean requireBoth) {
		boolean hastextA = textA != null && !textA.trim().isEmpty();
		boolean hastextB = textB != null && !textB.trim().isEmpty();

		if (!hastextA && !hastextB) {
			System.out.println(" No search text provided.");
			return null;
		}

		List<WebElement> blocks = driver.findElements(By.xpath("//table[contains(@class, 'patentDetails')]"));

		for (WebElement block : blocks) {
			String blockText = block.getText();

			if (requireBoth && hastextA && hastextB) {
				if (blockText.contains(textA) && blockText.contains(textB)) {
					return block;
				}
			} else {
				if ((hastextA && blockText.contains(textA)) || (hastextB && blockText.contains(textB))) {
					return block;
				}
			}
		}

		return null;
	}

	public String getPublicationDate(WebElement block) {
		try {
			WebElement pub = block.findElement(
					By.xpath(".//b[contains(text(),'Publication date')]/parent::td/following-sibling::td"));
			return pub.getText().split("\\(")[0].trim();
		} catch (Exception e) {
			return null;
		}
	}

	public String getFilingDate(WebElement block) {
		try {
			WebElement file = block
					.findElement(By.xpath(".//b[contains(text(),'Filing date')]/parent::td/following-sibling::td"));
			return file.getText().split("\\(")[0].trim();
		} catch (Exception e) {
			return null;
		}
	}
}