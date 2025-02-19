package com.qa.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

public class LoginPage extends BasePage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// locators
	private By userName = By.id("user-name");
	private By password = By.cssSelector("#password");
	private By loginButton = By.id("login-button");
	private By errorButton = By.cssSelector("h3[data-test='error']");

	/// getters
	public WebElement getUserName() {
		return getElement(userName);
	}

	public WebElement getUserPassword() {
		return getElement(password);
	}

	public WebElement getLoginButton() {
		return getElement(loginButton);
	}

	public WebElement getErrorButton() {
		return getElement(errorButton);
	}

	// page actions

	public String getLoginPageTitle() {

		return getPageTitle();
	}

	public InventoryPage loginIntoApp(String username, String password) {
		getUserName().sendKeys(username);
		getUserPassword().sendKeys(password);
		getLoginButton().click();
		InventoryPage inventoryPage = new InventoryPage(driver);
		return inventoryPage;
	}

	// method overloading

	public void loginIntoApp() {
		getUserName().sendKeys();
		getUserPassword().sendKeys();
		getLoginButton().click();
	}

	public String errorMessage() {
		return getErrorButton().getText();
	}

}
