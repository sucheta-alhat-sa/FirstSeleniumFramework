package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

import io.qameta.allure.Step;

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
	@Step("clicking on the Checkout button step...")
	public CheckoutPage clickCheckoutButton() {
		getCheckoutButton().click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	@Step("getting the cart page header text step...")
	public String cartHeaderText() {
		return getCartHeader().getText();
	}
	
	@Step("checking the product is getting displayed on the cart step...")
	public String productDisplayedOnCartPage(String productName) {
		return driver.findElement(By.xpath("//div[contains(text(),'"+productName+"')]")).getText();
	}

}
