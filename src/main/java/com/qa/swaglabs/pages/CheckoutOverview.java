package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

public class CheckoutOverview extends BasePage{
	WebDriver driver;
	public CheckoutOverview(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	private By overviewHeader = By.cssSelector("div.subheader");
	private By inventoryItem = By.cssSelector("div.cart_item div.inventory_item_name");
	private By finishButton = By.linkText("FINISH");
	
	//getters
	public WebElement getOverviewHeader() {
		return getElement(overviewHeader);
	}
	
	public WebElement getInventoryItem() {
		return getElement(inventoryItem);
	}
	
	public WebElement getFinishButton() {
		return getElement(finishButton);
	}
	
	//page actions
	
	public String overviewPageHeader() {
		return getOverviewHeader().getText();
	}
	
	
	public String inventoryItems() {
		return getInventoryItem().getText();
	}
	
	
	public FinishPage clickFinishButton() {
		getFinishButton().click();
		FinishPage finishPage = new FinishPage(driver);
		return finishPage;
	}

}
