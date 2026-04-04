package com.framework.ui.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.core.config.ConfigManager;
import com.framework.core.driver.DriverManager;
import com.framework.core.logging.LogManager;

public class WaitUtils {


	//inside this framework will use explicit waits

	private WaitUtils() {}


	private static final int defaultWaitTime = ConfigManager.getInt("default wait time in seconds"); 


	private static WebDriverWait getWait(int seconds) {

		return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds));

	}




	// ── handle Visibility ─────────────────────────────────────────────────────────


	public static WebElement waitForVisibilityOfElement(By locator) {

		LogManager.debug("Waiting for visibility of: " + locator);


		return getWait(defaultWaitTime).until(ExpectedConditions.visibilityOfElementLocated(locator));


	}




	public static WebElement waitForVisibility(By locator, int seconds) {

		LogManager.debug("Waiting for visibility of: " + locator);

		return getWait(seconds)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}




	public static WebElement waitForVisibility(WebElement element) {

		LogManager.debug("Waiting for visibility of: " + element);

		return getWait(defaultWaitTime)
				.until(ExpectedConditions.visibilityOf(element));
		
	}



	// ──handle Clickability ───────────────────────────────────────────────────────



	public static WebElement waitForClickability(By locator) {

		LogManager.debug("Waiting for clickability: " + locator);

		return getWait(defaultWaitTime)
				.until(ExpectedConditions.elementToBeClickable(locator));
		
	}




	public static WebElement waitForClickability(By locator , int seconds) {

		LogManager.debug("Waiting for clickability: " + locator);

		return getWait(seconds)
				.until(ExpectedConditions.elementToBeClickable(locator));
		
	}
	
	
	
	public static WebElement waitForClickability(WebElement element) {

		LogManager.debug("Waiting for clickability: " + element);

		return getWait(defaultWaitTime)
				.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	
	
	
	
	// ──handle Presence───────────────────────────────────────────────────────────
	 
    public static WebElement waitForPresence(By locator) {
    	
        LogManager.debug("Waiting for presence: " + locator);
        
        return getWait(defaultWaitTime)
        		.until(ExpectedConditions.presenceOfElementLocated(locator));
        
    }
    
    
    
    
    
 // ── Invisibility ───────────────────────────────────────────────────────
    
    public static boolean waitForInvisibility(By locator) {
    	
        LogManager.debug("Waiting for invisibility: " + locator);
        
        return getWait(defaultWaitTime)
        		.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        
    }
    
    
    
    
    // ── Text ───────────────────────────────────────────────────────────────
    
    public static boolean waitForTextToBe(By locator, String text) {
    	
    	LogManager.debug("Waiting for invisibility: " + locator);
    	
        return getWait(defaultWaitTime)
        		.until(ExpectedConditions.textToBe(locator, text));
        
    }
 
    public static boolean waitForTextToContain(By locator, String text) {
    	
        return getWait(defaultWaitTime)
        		.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        
    }
    
    
    
    
 // ── URL ────────────────────────────────────────────────────────────────
    
    public static boolean waitForUrlToContain(String urlFragment) {
    	
    	LogManager.debug("Waiting for getting URL" + urlFragment);
    	
    	
        return getWait(defaultWaitTime)
        		.until(ExpectedConditions.urlContains(urlFragment));
        
    }
    
    
 
    public static boolean waitForUrlToContain(String urlFragment, int seconds) {
    	
    	LogManager.debug("Waiting for getting URL" + urlFragment);
    	
        return getWait(seconds)
        		.until(ExpectedConditions.urlContains(urlFragment));
        
    }
    
    
    
    
 
    // ── Alert ──────────────────────────────────────────────────────────────
 
    public static void waitForAlert() {
    	
    	
    	LogManager.debug("Waiting for getting Alert");
    	
    	
        getWait(defaultWaitTime).until(ExpectedConditions.alertIsPresent());
        
    }
}