package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

public class FinishPage extends BasePage{
	WebDriver driver;
	public FinishPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	private By thankYouMsg = By.cssSelector("h2.complete-header");
	
	//getters
	public WebElement getThankYouMsg() {
		return getElement(thankYouMsg);
	}
	
	//page actions
	
	public String thankYouMsgText() {
		return getThankYouMsg().getText();
	}

}
