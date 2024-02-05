package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

static ExtentReports extentReports;
	
	// ExtentReports dependency required too
	// This method is ExtentReports type and method name is initialReports()
	public static ExtentReports initialReports() {
		if(extentReports == null) {
			extentReports = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent_report.html");
			spark.config().setReportName("CMS Automation Test Report");
			spark.config().setDocumentTitle("Report - Automation Test");
			spark.config().setTheme(Theme.DARK);
			extentReports.attachReporter(spark);
			extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name")); // OS = Operating System
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("OS Arch", System.getProperty("os.arch"));
			extentReports.setSystemInfo("Environment", "SIT"); // QA or SIT = System Integration Test, UAT = User Acceptance Test
		}
		return extentReports;
	}
}
