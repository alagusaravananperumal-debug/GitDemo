package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

//Note: Once the Listener methods completed then we need to configure in "testng.xml"

public class Listeners extends BaseTest implements ITestListener {
//ItestListener --> Interface Provided by TestNG

	// That object must be stored in a variable of type ExtentTest.
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	private String filePath;

	// To resolve concurrency issue multiple times, multiple test trying to access
	// one single variable which is keeping overridden
	// We can resolve the above issus using "ThreadLocal"

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// We need to provide testcase name in "createTest"
		// "result" holds the info about the tests which is going to executed
		// Test case name stored in "result.getMethod().getMethodName()"
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread Id "ErrorValidationTest"-->1, ProductErrorValidation"-->2
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// not implemented
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// "getThrowable" it will print the error msg
		extentTest.get().fail(result.getThrowable());
		// "Field" part of class not an method
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// take screenshot
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Attach Screenshot in report
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// not implemented
	}

	@Override
	public void onFinish(ITestContext context) {
		//pushes all that buffered data into the actual report file (HTML, Spark, or whichever reporter you configured
		extent.flush();
	}

}
