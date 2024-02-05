package baseUtil;

import static constants.IConstant.*;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.CommonActions;
import constants.Profile;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ForgotUserId;
import pages.HomePage;
import pages.NewUserRegistration;
import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.Configuration;

public class BaseClass {

	public WebDriver driver;
	public HomePage homePage;	
	Configuration configuration;
	public Dimension dimension;
	public Actions actions;
	public JavascriptExecutor js;
	public ForgotUserId forgotUserId;
	public Select select;
	// public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitlyWait));
	public NewUserRegistration newUserRegistration;
	static ExtentReports report;
	ExtentTest extentTest;
	
	protected String excelFileName;
	protected String excelSheetName;
	
	@BeforeSuite
	public void initialReporting() {
		report = ExtentManager.initialReports();
	}
	
	@BeforeClass
	public void beforeClassSetUp() {
		configuration = new Configuration(Profile.GENERAL); 
		// default Constructor of Configuration Class will be initialized
		excelFileName = configuration.getProperties(EXCEL_FILE_NAME);
		excelSheetName = configuration.getProperties(EXCEL_SHEET_NAME);
	}
	
	@BeforeMethod
	public void initialTest(Method method) {
		extentTest = ExtentTestManager.createTest(report, method.getName());
		extentTest.assignCategory(method.getDeclaringClass().getName());
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void setUP(@Optional(EDGE) String browserName) {
		// If any reason our test suit doesn't have parameter or value, then @Optional(FIREFOX) will work BY LINE 98
		// If there is empty value or wrong value in crossbrowser_suite.xml suite, then browser will not match and get the default one
		// WebdriverManager is instantiating the ChromeDriver
		initDriver(browserName);		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(configuration.getProperties(URL));
		// How can we convert a String to Long type
		long pageLoadWait = Long.parseLong(configuration.getProperties(PAGELOAD_WAIT));
		long implicitlyWait = Long.parseLong(configuration.getProperties(IMPLICITLY_WAIT));
		//long explicitlyWait = Long.parseLong(configuration.getProperties(EXPLICITLY_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
		initClass();
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver; // Memorize it
	}
	
	public void initDriver(String browserName) {
	    //String browserName =	configuration.getProperties(BROWSER);
	
		switch (browserName) {
		
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
			break;

		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
	
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	
	}
	
	public void initClass() {
		homePage = new HomePage(driver);
		forgotUserId = new ForgotUserId(driver);
		newUserRegistration = new NewUserRegistration(driver); 
	}
	
	@AfterMethod
	public void afterEachTest(Method method, ITestResult result) {
		for(String group: result.getMethod().getGroups()) {
			extentTest.assignCategory(group);
		}
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test PASSED");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(CommonActions.getSreenShot(method.getName(), driver));
			extentTest.log(Status.FAIL, "Test FAILED");
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test SKIPPED");
		}
	}
	
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}
	
	@AfterSuite
	public void publishReport() {
		report.flush();
	}

	
}
