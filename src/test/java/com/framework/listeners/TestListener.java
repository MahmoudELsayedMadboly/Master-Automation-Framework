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

	
	@Override
	public void onTestSuccess(ITestResult result) {
	    
		LogManager.info("✔ PASSED:  " + getTestName(result));
		
	  }

	  
	@Override
	public void onTestFailure(ITestResult result) {
	    
		
		LogManager.error("✘ FAILED:  " + getTestName(result));
		
        LogManager.error("  Reason:  " + result.getThrowable().getMessage());
        
     // take screenshot automatically on failure
        takeFailureScreenshot(result);
		
	  }

	@Override
	public void onTestSkipped(ITestResult result) {
	    

		LogManager.warn("⊘ SKIPPED: " + getTestName(result));
		
	  }
	
	
	@Override
    public void onFinish(ITestContext context) {
		
        int total   = context.getAllTestMethods().length;
        
        int passed  = context.getPassedTests().size();
        
        int failed  = context.getFailedTests().size();
        
        int skipped = context.getSkippedTests().size();
        
 
        LogManager.info("═══════════════════════════════════════");
        
        LogManager.info("Suite finished: " + context.getName());
        
        LogManager.info("Total: "   + total   +
                        " | Passed: "  + passed  +
                        " | Failed: "  + failed  +
                        " | Skipped: " + skipped);
        
        LogManager.info("═══════════════════════════════════════");
        
    }
}
