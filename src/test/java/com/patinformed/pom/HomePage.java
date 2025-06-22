package com.patinformed.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;

	@FindBy(xpath = "//input[contains(@placeholder,'Search pharmaceutical patents')]")
	private WebElement searchBox;

	@FindBy(xpath = "//button[contains(@class,'margin-right')]")
	WebElement searchButton;

	@FindBy(xpath = "//button[contains(text(),'I have read and agree to the terms')]")
	private WebElement agreeButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSearchButton() {
		//searchBox.sendKeys(Keys.ENTER);
		//searchButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'margin-right')]")));
		searchBtn.click();
	}

	public void clickOnAgreeButton() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement agreeButton = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//button[contains(text(),'I have read and agree to the terms')]")));

			if (agreeButton.isDisplayed()) {
				
				agreeButton.click();
				System.out.println(" Clicked on 'I agree' button.");
			}
		} catch (TimeoutException e) {
			System.out.println(" 'Agree' button did not appear in this session. Continuing...");
		} catch (Exception e) {
			System.out.println(" Unexpected error with 'Agree' button: " + e.getMessage());
		}
	}

	public void enterSearchText(String text) {
		searchBox.clear();
		searchBox.sendKeys(text);
	}
}