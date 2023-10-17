package selenium_framework.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium_framework.resources.ExtentReportsConfig;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extentReports = ExtentReportsConfig.getReportObject();
	ExtentTest test;
	// solves the concurrency issue of "test" object while running parallel tests, so we make it thread safe
	ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<ExtentTest>(); 
	@Override
	public void onTestStart(ITestResult result) {
		// create test for ExtentReports object
		test = extentReports.createTest(result.getMethod().getMethodName());
		threadLocalExtentTest.set(test); // assigns a unique thread id to each "test" object
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		threadLocalExtentTest.get().fail(result.getThrowable()); // prints the error msg in extent reports
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		// take screenshot and attach it to report
		String pathOfScreenshot = getScreenshot(result.getMethod().getMethodName(), driver);
		threadLocalExtentTest.get().addScreenCaptureFromPath(pathOfScreenshot, result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}
}
