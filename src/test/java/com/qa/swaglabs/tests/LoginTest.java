package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.swaglabs.basetest.BaseTest;
import com.qa.swaglabs.pages.InventoryPage;
import com.qa.swaglabs.retrylogic.RetryLogic;

public class LoginTest extends BaseTest {

	@Test(priority = 1, groups = { "login" }, description = "Verify the login page title test")

	public void loginPageTitleTest() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Swag Labs1");

	}

	@Test(priority = 2, groups = { "login" }, description = "Verify the login into the Application test")

	public void loginIntoAppTest() {

		InventoryPage inventoryPage = loginPage.loginIntoApp(prop.getProperty("username"),
				prop.getProperty("password"));
		String inventoryTitle = inventoryPage.inventoryPageTitle();
		Assert.assertEquals(inventoryTitle, "Swag Labs");

	}

	@Test(priority = 3, description = "Verify the lockedout user login into the Application test", retryAnalyzer = RetryLogic.class)

	public void lockedOutUserTest() {

		loginPage.loginIntoApp(prop.getProperty("lockedoutuser"), prop.getProperty("password"));
		String lockedUserErrorMsg = loginPage.errorMessage();
		Assert.assertEquals(lockedUserErrorMsg, "Epic sadface: Sorry, this user has been locked out.");

	}

}
