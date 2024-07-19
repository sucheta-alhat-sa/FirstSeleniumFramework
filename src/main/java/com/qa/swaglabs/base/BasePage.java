package com.qa.swaglabs.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageHeader(By locator) {
		return getElement(locator).getText();
	}
	
	public WebElement getElement(By locator) {
		WebElement ele = null;
		try {
			ele = driver.findElement(locator);
			return ele;
		}catch(Exception e) {
			System.out.println("Exception occured while creating an element" + locator.toString());
			e.printStackTrace();
		}
		return ele;
	}
	
	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}catch(Exception e) {
			System.out.println("Exception occured while creating an element" + locator.toString());
			e.printStackTrace();
		}
	}
	
	public void waitForPageTitlePresent(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
