package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.swaglabs.basetest.BaseTest;
import com.qa.swaglabs.pages.CartPage;
import com.qa.swaglabs.pages.CheckoutOverview;
import com.qa.swaglabs.pages.CheckoutPage;
import com.qa.swaglabs.pages.FinishPage;
import com.qa.swaglabs.pages.InventoryPage;

public class AddProductToCartTest extends BaseTest {
	InventoryPage inventoryPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverview checkoutoverview;
	FinishPage finishPage;

	@Test(priority = 1, groups = { "login" }, description = "Verify the add to Product cart flow test")

	public void addProductToCartTest() {

		inventoryPage = loginPage.loginIntoApp(prop.getProperty("username"), prop.getProperty("password"));
		inventoryPage.addProductIntocart(prop.getProperty("product"));
		cartPage = inventoryPage.clickCartOption();
		String productName = cartPage.productDisplayedOnCartPage(prop.getProperty("product"));
		Assert.assertEquals(productName, prop.getProperty("product"));
		checkoutPage = cartPage.clickCheckoutButton();
		checkoutoverview = checkoutPage.enterCheckoutInfo(prop.getProperty("firstName"), prop.getProperty("lastName"),
				prop.getProperty("postalCode"));
		String productsName = checkoutoverview.inventoryItems();
		Assert.assertEquals(productsName, prop.getProperty("product"));
		finishPage = checkoutoverview.clickFinishButton();
		String thankYouText = finishPage.thankYouMsgText();
		Assert.assertEquals(thankYouText, "THANK YOU FOR YOUR ORDER");

	}

}
