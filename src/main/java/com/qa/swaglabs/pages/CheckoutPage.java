package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

import io.qameta.allure.Step;

public class CheckoutPage extends BasePage{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	private By checkoutHeader = By.cssSelector("div.subheader");
	private By firstName = By.id("first-name");
	private By lastName = By.id("last-name");
	private By postalCode = By.id("postal-code");
	private By continueButton = By.cssSelector("input[type='submit']");
	
	//getters
	public WebElement getCheckoutHeader() {
		return getElement(checkoutHeader);
	}
	
	public WebElement getFirstName() {
		return getElement(firstName);
	}
	
	public WebElement getLastName() {
		return getElement(lastName);
	}
	
	public WebElement getPostalCode() {
		return getElement(postalCode);
	}
	
	public WebElement getContinueButton() {
		return getElement(continueButton);
	}
	
	//page Actions
	@Step("getting the checkout page header step...")
	public String checkoutPageHeader() {
		return getCheckoutHeader().getText();
	}
	
	@Step("Entering the user info into the checkout page step...")
	public CheckoutOverview enterCheckoutInfo(String firstName, String lastName, String postalCode) {
		getFirstName().sendKeys(firstName);
		getLastName().sendKeys(lastName);
		getPostalCode().sendKeys(postalCode);
		getContinueButton().click();
		CheckoutOverview checkoutoverview = new CheckoutOverview(driver);
		return checkoutoverview;
	}

}
