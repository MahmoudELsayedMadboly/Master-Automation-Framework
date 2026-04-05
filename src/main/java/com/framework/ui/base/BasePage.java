package com.framework.ui.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.framework.core.driver.DriverManager;
import com.framework.core.logging.LogManager;
import com.framework.ui.utils.BrowserUtils;
import com.framework.ui.utils.WaitUtils;

public abstract class BasePage {
	
	
	//BasePage — the parent class for every Page Object in any consumer project.
	
	
	protected WebDriver driver;
	
	
	public BasePage() {
		
		this.driver = DriverManager.getDriver();
		
	}
	
	
	
	
	
	// ──handle element retrieval ──────────────────────────────────────────────────
	
	
protected WebElement findElement(By locator) {
		
		LogManager.debug("Finding element: " + locator);
		
		
		return WaitUtils.waitForVisibilityOfElement(locator);
		
		
	}
	
	

	
	
	protected WebElement findElement(By locator , int time) {
		
		LogManager.debug("Finding element: " + locator);
		
		
		return WaitUtils.waitForVisibility(locator, time);
		
		
	}
	
	
	protected List<WebElement> findElementList(By locator) {
		
        LogManager.debug("Finding list of elements: " + locator);
        
        WaitUtils.waitForPresence(locator);
        
        return driver.findElements(locator);
        
    }
	
	
	
	
	
	
	
	// ──handle click ──────────────────────────────────────────────────────────────
	 
    protected void click(By locator) {
    	
        LogManager.debug("Clicking: " + locator);
    
        
        WaitUtils.waitForClickability(locator).click();
        
    }
    
 
    protected void click(WebElement element) {
    	
        LogManager.debug("Clicking web element");
        
        WaitUtils.waitForVisibility(element).click();
        
    }
 
    protected void clickWithJS(By locator , int time) {
    	
        LogManager.debug("JS clicking: " + locator);
        
        BrowserUtils.clickWithJS(findElement(locator,time));
        
    }
	
	
    
    
    
 // ──handle typing ───────────────────────────────────────────────────────────────
    
    protected void type(By locator, int time , String text) {
    	
        LogManager.debug("Typing '" + text + "' into: " + locator);
        
        WebElement element = findElement(locator , time);
        
        element.clear();
        
        element.sendKeys(text);
        
    }
 
    protected void clearAndType(By locator, int time , String text) {
    	
        LogManager.debug("Clearing and typing '" + text + "' into: " + locator);
        
        WebElement element = findElement(locator , time);
        
        element.clear();
        
        element.sendKeys(text);
        
    }
    
    
    
    
    
    
 // ── Dropdown ───────────────────────────────────────────────────────────
    
    
    
    
    protected void selectByVisibleText(By locator, String text) {
    	
        LogManager.debug("Selecting by visible text '" + text + "' from: " + locator);
        
        
        new Select(findElement(locator)).selectByVisibleText(text);
        
    }
 
    
    
    protected void selectByValue(By locator, String value) {
    	
        LogManager.debug("Selecting by value '" + value + "' from: " + locator);
        
        new Select(findElement(locator)).selectByValue(value);
        
    }
    
    
 
    protected void selectByIndex(By locator, int index) {
    	
        LogManager.debug("Selecting by index '" + index + "' from: " + locator);
        
        new Select(findElement(locator)).selectByIndex(index);
        
    }
    
    
    
    
    
    
    
 // ── Get text & attributes ──────────────────────────────────────────────
    
    
    
    protected String getText(By locator) {
    	
        LogManager.debug("Getting text from: " + locator);
        
        return findElement(locator).getText().trim();
        
    }
 
    
    
    protected String getAttribute(By locator, String attribute) {
    	
        LogManager.debug("Getting attribute '" + attribute + "' from: " + locator);
        
        return findElement(locator).getAttribute(attribute);
        
    }
    
    
    
    
 // ── Visibility checks ──────────────────────────────────────────────────
    
    protected boolean isDisplayed(By locator) {
    	
        LogManager.debug("Checking if displayed: " + locator);
        
        try {
        	
            return findElement(locator).isDisplayed();
            
        } catch (Exception e) {
        	
            return false;
            
        }
    }
    
    
 
    protected boolean isEnabled(By locator) {
    	
        LogManager.debug("Checking if enabled: " + locator);
        
        return findElement(locator).isEnabled();
        
    }
    
    
 
    protected boolean isSelected(By locator) {
    	
        LogManager.debug("Checking if selected: " + locator);
        
        return findElement(locator).isSelected();
        
    }
    
    
    

}











































































