package com.qa.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.swaglabs.base.BasePage;

public class InventoryPage extends BasePage{
	WebDriver driver;
	public InventoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	private By inventoryPageHeader = By.cssSelector(".product_label");
	private By clickCartIcon = By.cssSelector("a.shopping_cart_link");
	
	@FindBy(xpath = "//div[@class='inventory_item']/div[2]//div")
	private List<WebElement> productNameList;
	
	//getters
	public WebElement getInventoryHeader() {
		return getElement(inventoryPageHeader);
	}
	
	public List<WebElement> getInventoryProductsList(){
		return productNameList;
	}
	
	public WebElement getClickCartIcon() {
		return getElement(clickCartIcon);
	}
	
	//page actions
	
	public String inventoryPageTitle() {
		return getPageTitle();
	}
	
	
	public String inventoryPageHeader() {
		return getPageHeader(inventoryPageHeader);
	}
	
	
	public WebElement inventoryProductsList(String product) {
		List<WebElement> list = getInventoryProductsList();
		WebElement prod = list.stream().filter(e -> e.getText().equalsIgnoreCase(product)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void addProductIntocart(String product) {
		WebElement prod = inventoryProductsList(product);
		String prodName = prod.getText();
		driver.findElement(By.xpath("//div[contains(text(),'"+prodName+"')]/../../following-sibling::div//button[contains(text(),'ADD TO CART')]")).click();
	}
	
	
	public CartPage clickCartOption() {
		WebElement cart = getClickCartIcon();
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
