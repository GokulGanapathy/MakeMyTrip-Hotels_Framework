package TestComponents;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportClass{
	
	public static ExtentReports getExtentReporting() {
		
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Script Reports");
		reporter.config().setDocumentTitle("Test Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Gokul");
		extent.setSystemInfo("Testing for", "MakeMyTrip-Hotels for Sprinto");
		extent.setSystemInfo("Date and Time of Testing", (new Date()).toString());	
		return extent;
	}

}
