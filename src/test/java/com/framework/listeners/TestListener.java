package com.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import com.framework.core.logging.LogManager;
import com.framework.ui.utils.BrowserUtils;

public class TestListener implements ITestListener{
	
	
	/**
	 * TestListener — hooks into TestNG's test lifecycle.
	 *
	 * Handles:
	 * - Logging test start, pass, fail, skip
	 * - Taking screenshots automatically on failure
	 *
	 * Reporting is intentionally NOT here — attach a reporter
	 * implementation in consumer projects by implementing IReporter.
	 *
	 */
	
	
	
	// ── Private helpers ────────────────────────────────────────────────────
	 
    private String getTestName(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName()
            + "." + result.getMethod().getMethodName();
    }
 
    private void takeFailureScreenshot(ITestResult result) {
        try {
            String screenshotFolder = System.getProperty("user.dir")
                + "/target/screenshots";
            String testName = result.getMethod().getMethodName();
            String path = BrowserUtils.takeScreenshot(screenshotFolder, testName);
            LogManager.info("  Screenshot: " + path);
        } catch (Exception e) {
            LogManager.warn("Could not take screenshot: " + e.getMessage());
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onTestStart(ITestResult result) {
	    
		LogManager.info("▶ STARTED: " + getTestName(result));
		
	  }

	
	  
	public void onTestSuccess(ITestResult result) {
	    // not implemented
	  }

	  
	  
	public void onTestFailure(ITestResult result) {
	    // not implemented
	  }

	  
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
}
