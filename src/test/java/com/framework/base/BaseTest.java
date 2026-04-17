package com.framework.base;

import java.sql.DriverManager;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.framework.core.config.ConfigManager;
import com.framework.core.driver.DriverFactory;
import com.framework.core.logging.LogManager;

public class BaseTest {



	/**
	 * BaseTest — the parent class for all UI test classes.
	 *
	 * Manages the full WebDriver lifecycle:
	 * - @BeforeMethod: creates driver, maximizes, navigates to base URL
	 * - @AfterMethod:  quits driver, cleans ThreadLocal
	 *
	 */




	@BeforeMethod
	@Parameters({"browser", "headless"})
	public void setUp(

			@Optional("") String browserParam,
			@Optional("") String headlessParam

			) {


		//determine which browser will use

		String browser;

		if(browserParam.isEmpty()) {

			browser = ConfigManager.getString("browser_1");

		}else {

			browser = browserParam;

		}


		//determine headless mode

		String headless;

		if(headlessParam.isEmpty()) {

			headless = ConfigManager.getString("is.headless");


		}else {

			headless = headlessParam;

		}



		//log what is about to happen
		LogManager.info("=== Test Setup — Browser: " + browser + " | Headless: " + headless + " ===");


		// define driver

		WebDriver driver = DriverFactory.createDriver(browser, Boolean.parseBoolean(headless));

		com.framework.core.driver.DriverManager.getDriver();


		//configure browser

		driver.manage().window().maximize();

		//String time = "";

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getInt("default.Time")));

		String baseUrl = ConfigManager.getString("base.Url");

		LogManager.info("Navigating to base URL: " + baseUrl);


		driver.get(baseUrl);


	}



	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {

		LogManager.info("=== Test Teardown ===");


		//driver is created & stored in the setUp method so here just will retrived the already stored driver in "DriverManager"
		WebDriver driver = com.framework.core.driver.DriverManager.getDriver();
		
		if(driver != null) {
			
			driver.quit();
			
			LogManager.info("Browser closed successfully");
			
		}
		
		com.framework.core.driver.DriverManager.removeDriver();

	}
















}

















