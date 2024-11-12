package com.project.bachthree.automation.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListener implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;  // UI if the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the testmethods
	
	public  void onTestStart(ITestResult result) {
	    System.out.println("Test Started..");
	  }

		/*
		 * public void onTestSuccess(ITestResult result) {
		 * System.out.println("Test Passed!"); }
		 * 
		 * public void onTestSkipped(ITestResult result) {
		 * System.out.println("Test Skipped!"); }
		 */
	
	public  void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	    System.out.println("Test Failed!");
	  }
	
		/*
		 * public void onStart(ITestContext context) {
		 * 
		 * System.out.println("Test Execution is started.."); }
		 */
	
	
	
	
   public void onStart(ITestContext context) {
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/reports/Extentreport.html"); //specific location 
		sparkReporter.config().setDocumentTitle("Automation report"); //title of report
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Hosted FROM", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Lav");
		extent.setSystemInfo("os", "Windows 10");
		extent.setSystemInfo("Browser Tested", "cross browser");
	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName()); //create a new entry in the report
		test.log(Status.PASS, "Test case Passed is:" + result.getName());  //update status pass/ fail
	
		
	}
	
	
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case Failed is:"+ result.getName());
		test.log(Status.FAIL, "test case Failed due to:"+ result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case Skipped is:"+ result.getName());
		
	}
	
	public void onFinish(ITestResult context) {
		
		extent.flush();
		System.out.println("FINISHED!");
	}
	

	public  void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("Test completed!");
	}
	
}
