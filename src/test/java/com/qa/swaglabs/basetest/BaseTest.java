package com.qa.swaglabs.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.qa.swaglabs.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	protected static Properties prop;
	public FileInputStream fis = null;
	public LoginPage loginPage;

	public WebDriver initializedriver() {

		prop = new Properties();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\swaglabs\\config\\config.properties");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {

			driver = new FirefoxDriver();
		} else {

		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApp() {
		driver = initializedriver();
		loginPage = new LoginPage(driver);

		driver.get(prop.getProperty("url"));
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

}
