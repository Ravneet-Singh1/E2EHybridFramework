package resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends base implements ITestListener {

	ExtentReports extent = ExtentReportNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  // For providing concurrency in parallel execution
	

	@Override
	public void onTestStart(ITestResult arg0) {

		test = extent.createTest(arg0.getMethod().getMethodName()); // This will get the method name (Test Name)
		
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {

//		test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult arg0) {

//		test.log(Status.FAIL, "Test Failed");
//		test.fail(arg0.getThrowable()); // It will give the error message

		extentTest.get().log(Status.FAIL, "Test Failed");
		extentTest.get().fail(arg0.getThrowable()); // It will give the error message
		
		// Screenshot code

		try {
			driver = (WebDriver) arg0.getTestClass().getRealClass().getField("driver").get(arg0.getInstance()); // To
																												// bring
																												// the
																												// life
																												// of
																												// driver
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String filePath = null;

		try {
			filePath = takeScreenshot(arg0.getMethod().getMethodName(), driver); // "takeScreenshot" is the method in
																					// base.java
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(filePath, arg0.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Finishing the test");
		extent.flush();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
