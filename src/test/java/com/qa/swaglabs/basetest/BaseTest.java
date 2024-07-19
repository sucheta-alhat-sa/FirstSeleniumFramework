package com.qa.swaglabs.basetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	public LoginPage loginPage;
	public static Logger log = Logger.getLogger(BaseTest.class);
	
	public WebDriver initializedriver() {
		
		prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\swaglabs\\config\\config.properties");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			log.info("launching the chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			log.info("launching the chrome browser");
			driver = new FirefoxDriver();
		}else {
			log.info("No proper browser defined...");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		tdriver.set(driver);
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
	
	@BeforeMethod(alwaysRun= true)
	public LoginPage launchApp() {
		driver=initializedriver();
		loginPage = new LoginPage(driver);
		log.info("entering the url");
		driver.get(prop.getProperty("url"));
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		log.info("quitting the driver");
		driver.quit();
	}
	
	public String getScreenshot() {
		log.info("taking the screenshot");
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
