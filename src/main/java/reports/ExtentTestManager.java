package reports;

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

static HashMap<Long, ExtentTest> tests = new HashMap<>();
	
	// This method is ExtentTest type and method name is createTest()
	public static ExtentTest createTest(ExtentReports report, String testName){
		ExtentTest eTest = report.createTest(testName);
		tests.put(Thread.currentThread().getId(), eTest);
		return eTest;
	}
	
	public static ExtentTest getTest(){
		return tests.get(Thread.currentThread().getId());
	}
}
