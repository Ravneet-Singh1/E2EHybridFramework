package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html"; // To create a folder
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Report"); // Change the heading
		reporter.config().setDocumentTitle("Test Report"); // To set the title

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); // To attach Extent Reports with ExtentSparkReporter
		extent.setSystemInfo("Tester", "Ravneet");
		
		return extent;
	}

}
