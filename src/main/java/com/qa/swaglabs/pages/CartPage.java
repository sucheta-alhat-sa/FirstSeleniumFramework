package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

public class CartPage extends BasePage{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	private By checkoutButton = By.cssSelector("a.checkout_button");
	private By cartHeader = By.cssSelector("div.subheader");
	
	//getter
	public WebElement getCheckoutButton() {
		return getElement(checkoutButton);
	}
	
	public WebElement getCartHeader() {
		return getElement(cartHeader);
	}
	
	//page actions
	
	public CheckoutPage clickCheckoutButton() {
		getCheckoutButton().click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	public String cartHeaderText() {
		return getCartHeader().getText();
	}
	
	
	public String productDisplayedOnCartPage(String productName) {
		return driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]")).getText();
	}

}
