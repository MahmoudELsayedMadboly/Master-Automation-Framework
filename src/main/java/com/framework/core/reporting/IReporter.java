package com.framework.core.reporting;



/**
 * 
 * IReporter — the reporting contract for this framework.
 *
 * This is an interface only. No reporting library is imported here.
 * When you want to use ExtentReports or Allure in a specific project,
 * you create a class that implements this interface in that project.
 * 
 */ 



public interface IReporter {

	//called when test starts
	//use to create a test node in the report

	public void startsTest(String testName);




	//called when test passes
	// message parameter describes what passed

	public void testPass(String message);





	//called when test fails
	// message parameter describes what failed
	//throwable parameter describes what is the casue of the failure

	public void testFail(String message , Throwable Throwable);





	//called when test skipped
	// message parameter describes the reason for skipping

	public void skipTest(String message);




	//attach a screenshot in fir the current test in the report
	//parameter tell the screenshot path

	public void attachScreenshot(String screenShotPath);




	//called once after all tests finish
	//flush & write the report to desk

	public void flush();

}
