package com.framework.core.driver;

import java.sql.DriverManager;
import java.util.logging.LogManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {


	//DriverFactory — responsible for creating WebDriver instances.


	private DriverFactory() {}


	

	public static WebDriver createDriver(String browser , boolean headless) {

		WebDriver driver;


		switch (browser.toLowerCase().trim()) {
		

		case "chrome":

			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();

			driver = new ChromeDriver(chromeOptions);

			break;
			

		case "firefox":

			WebDriverManager.firefoxdriver().setup();

			FirefoxOptions firefoxOptions = new FirefoxOptions();

			driver = new FirefoxDriver(firefoxOptions);

			break;


		case "safari":

			WebDriverManager.safaridriver().setup();

			SafariOptions safariOptions = new SafariOptions();

			driver = new SafariDriver(safariOptions);

			break;


		case "edge":

			WebDriverManager.edgedriver().setup();

			EdgeOptions edgeOptions = new EdgeOptions();

			driver = new EdgeDriver(edgeOptions);

			break;


		default:

			throw new IllegalArgumentException("Unsupported browser: '" + browser + "'. Supported values: chrome, firefox, safari");
		}

		return driver;

	}
}