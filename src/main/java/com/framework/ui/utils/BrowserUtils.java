package com.framework.ui.utils;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.core.driver.DriverManager;
import com.framework.core.logging.LogManager;

public class BrowserUtils {


	private BrowserUtils() {}


	private static WebDriver driver() {

		return DriverManager.getDriver();

	}


	private static JavascriptExecutor js() {

		return (JavascriptExecutor) driver();

	}



	// ──handle Scrolling─────────────────────────────────────────────────────────────


	public static void scrollToElement(WebElement element) {

		LogManager.debug("Scrolling to element");

		js().executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

	}



	public static void scrollUp(WebElement element) {


		LogManager.debug("Scrolling to Top");

		js().executeScript("window.scrollTo(0, 0);");

	}


	public static void scrollToBottom() {

		LogManager.debug("Scrolling to bottom");

		js().executeScript("window.scrollTo(0, document.body.scrollHeight);");

	}


	public static void scrollByPixels(int x, int y) {

		js().executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);

	}




	// ── JavaScript ─────────────────────────────────────────────────────────

	public static void clickWithJS(WebElement element) {

		LogManager.debug("JS click on element");

		js().executeScript("arguments[0].click();", element);

	}



	public static void highlightElement(WebElement element) {

		LogManager.debug("Highlighting element with red border");

		js().executeScript("arguments[0].style.border='3px solid red';", element);

	}


	public static Object executeScript(String script, Object... args) {

		return js().executeScript(script, args);

	}





	// ──handle Alerts ─────────────────────────────────────────────────────────────


public static String getAlertText() {
		
		WaitUtils.waitForAlert();
		
		return driver().switchTo().alert().getText();
		
	}
	
	
	
	
	public static void acceptAlert() {

		WaitUtils.waitForAlert();

		String text = getAlertText();

		LogManager.info("Accepting alert with text: " + text);

		driver().switchTo().alert().accept();

	}
	
	

	public static void dismissAlert() {

		WaitUtils.waitForAlert();

		String text = getAlertText();

		LogManager.info("dismissing alert with text: " + text);

		driver().switchTo().alert().dismiss();

	}
	
	
	
	public static void acceptAlertWithText(String text) {
		
	    WaitUtils.waitForAlert();
	    
	    LogManager.info("Sending text to alert: " + text);
	    
	    driver().switchTo().alert().sendKeys(text);
	    
	    driver().switchTo().alert().accept();
	    
	}
	
	
	
	public static void dismissAlertWithText(String text) {
		
	    WaitUtils.waitForAlert();
	    
	    LogManager.info("Sending text to alert then dismissing: " + text);
	    
	    driver().switchTo().alert().sendKeys(text);
	    
	    driver().switchTo().alert().dismiss();
	    
	}
	
	
	
	

	
	// ──handle Windows & Tabs─────────────────────────────────────────────────────

	
	public static void switchToNewWindow(String originalHandle) {
		
        Set<String> handles = driver().getWindowHandles();
        
        for (String handle : handles) {
        	
            if (!handle.equals(originalHandle)) {
            	
                driver().switchTo().window(handle);
                
                LogManager.info("Switched to new window: " + handle);
                
                return;
                
            }
        }
        
        throw new RuntimeException("No new window found to switch to");
        
    }
	
 
    public static void switchToWindow(String handle) {
    	
        driver().switchTo().window(handle);
        
    }
    
 
    public static String getCurrentWindowHandle() {
    	
        return driver().getWindowHandle();
        
    }
 
    public static void closeCurrentWindowAndSwitch(String handle) {
    	
        driver().close();
        
        driver().switchTo().window(handle);
        
    }

    
    
 // ──handle Frames ─────────────────────────────────────────────────────────────
    
    public static void switchToFrame(WebElement frame) {
    	
        driver().switchTo().frame(frame);
        
    }
    
 
    public static void switchToDefaultContent() {
    	
        driver().switchTo().defaultContent();
        
    }
    
    
    
    
 // ── Actions ────────────────────────────────────────────────────────────
    
    public static void hoverOverElement(WebElement element) {
    	
        new Actions(driver()).moveToElement(element).perform();
        
    }
 
    public static void dragAndDrop(WebElement source, WebElement target) {
    	
        new Actions(driver()).dragAndDrop(source, target).perform();
        
    }
 
    public static void doubleClick(WebElement element) {
    	
        new Actions(driver()).doubleClick(element).perform();
        
    }
 
    public static void rightClick(WebElement element) {
    	
        new Actions(driver()).contextClick(element).perform();
        
    }
    
    
    public static void click(WebElement element) {
    	
    	new Actions(driver()).click().perform();
    	
    }
    
    
    
 
}





































