package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.swaglabs.basetest.BaseTest;
import com.qa.swaglabs.listeners.AllureReportListener;
import com.qa.swaglabs.pages.InventoryPage;
import com.qa.swaglabs.retrylogic.RetryLogic;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({ AllureReportListener.class })

public class LoginTest extends BaseTest {

	@Test(priority = 1, groups = { "login" }, description = "Verify the login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify the login page title test")
	public void loginPageTitleTest() {
		String loginPageTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Swag Labs");

	}

	@Test(priority = 2, groups = { "login" }, description = "Verify the login into the Application test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Verify the login into the Application test")
	public void loginIntoAppTest() {

		InventoryPage inventoryPage = loginPage.loginIntoApp(prop.getProperty("username"),
				prop.getProperty("password"));
		String inventoryTitle = inventoryPage.inventoryPageTitle();
		Assert.assertEquals(inventoryTitle, "Swag Labs");

	}

	@Test(priority = 3, description = "Verify the lockedout user login into the Application test", retryAnalyzer = RetryLogic.class)
	@Severity(SeverityLevel.NORMAL)
	@Description("Verify the lockedout user login into the Application test")
	public void lockedOutUserTest() {

		loginPage.loginIntoApp(prop.getProperty("lockedoutuser"), prop.getProperty("password"));
		String lockedUserErrorMsg = loginPage.errorMessage();
		Assert.assertEquals(lockedUserErrorMsg, "Epic sadface: Sorry, this user has been locked out.");

	}

}
