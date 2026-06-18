package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	// If i mentioned "static" declaring the method whithout even delclaring the
	// object like "ExtentReporterNG.getReportObject()"

	public static ExtentReports getReportObject()

	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// ExtentSpartReporter is helper class which is helping to create some
		// configuration and that will finally reports to its main class

		ExtentReports extent = new ExtentReports();
		// attach the mail class "ExtentSparkReporter"
		extent.attachReporter(reporter);
		// Append tester details in report
		extent.setSystemInfo("TesterName", "Alagusaravanan P");
		extent.flush();
		return extent;

	}

}
