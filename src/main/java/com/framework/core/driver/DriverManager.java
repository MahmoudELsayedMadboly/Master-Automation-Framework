package com.framework.core.driver;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager — thread-safe WebDriver storage using ThreadLocal.
 *
 * Why ThreadLocal?
 * A static WebDriver field is shared across all threads — when tests run
 * in parallel, threads overwrite each other's driver and tests collide.
 * ThreadLocal gives each thread its own isolated WebDriver instance,
 * making parallel execution safe with zero code changes in tests.
 */

public class DriverManager {

	/*
	 * 
	 * how will this class work?
	 * 
	 * Simply, this class will manage the driver as below:
	 * 
	 * 1- ThreadLocal separates the driver so each thread has its own independent instance
       2- setDriver() SAVES the already-created driver before each test
       3- getDriver() retrieves and reuses the driver whenever and wherever needed
       4- removeDriver() cleans the driver from memory after driver.quit() closes the browser
	 * */

	private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();



	private DriverManager() {};



	//driver control methods (get, set, remove).



	/*
	 * save driver to be used
	 * it acts as @beforeMethod
	 * */


	public static void setDriver(WebDriver driver) {

		driverThreadLocal.set(driver);

	}


	//use driver whenever & anywhere needed 
	public static WebDriver getDriver() {

		return driverThreadLocal.get();

	}




	/*
	 * remove driver after use
	 * it acts as @afterMethod
	 * */

	public static void removeDriver() {

		driverThreadLocal.remove();

	}

}
