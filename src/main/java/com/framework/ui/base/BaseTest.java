package com.framework.ui.base;

import java.sql.DriverManager;

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

			browser = ConfigManager.getString("");

		}else {

			browser = browserParam;

		}


		//determine headless mode

		String headless;

		if(headlessParam.isEmpty()) {

			headless = ConfigManager.getString("");

		}else {

			headless = headlessParam;

		}
		
		
		
		//log what is about to happen
	    LogManager.info("=== Test Setup — Browser: " + browser + " | Headless: " + headless + " ===");
	    
	    
	    // define driver
	    
	    WebDriver driver = DriverFactory.createDriver(browser, Boolean.parseBoolean(headless));

	    
	    //confiure browser
	    
	    driver.manage().window().maximize();
	    
	    
	    driver.get(ConfigManager.getString(""));


	}



	@AfterMethod
	public void closeBrowser() {



	}


}
