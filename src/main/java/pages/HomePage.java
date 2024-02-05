package pages;

import static common.CommonActions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//em[@id='cms-homepage-header-logo-unauth' and @class='cms-icon cms-sprite-loggedout ms-3']")
	WebElement logo;
	
	@FindBy(xpath = "//span[text()='Help']")
	WebElement help;
	
	@FindBy(xpath = "//h1[contains(text(), ' CMS')]")
	WebElement helpHeader;

	@FindBy(xpath = "//p[starts-with(text(), 'The Enterprise')]")
	WebElement helpSubHeader;
	
	@FindBy(xpath = "//span[text()='About']")
	WebElement about;
		
	@FindBy (name = "user-d")
	WebElement userId;
	
	@FindBy (how = How.NAME, using = "pass-d")
	WebElement password;
	
	@FindBy(xpath = "//label[@id='cms-label-tc']")
	WebElement checkBox;
	
	@FindBy (id = "cms-login-submit")
	WebElement login;
	
	@FindBy(xpath = "//div[starts-with(text(),'Invalid')]")
	WebElement loginErrorMsg;

	@FindBy(id = "cms-forgot-userid")
	WebElement forgotUserId;

	By unlock = By.xpath("//a[text()='unlock']");

	@FindBy(className = "cms-newuser-reg")
	WebElement newUserRegistration;
	
	@FindBy(xpath = "//p[contains(text(), ' Step #')]")
	WebElement stepOneHeader;

	@FindBy(xpath = "//p[contains(text(), ' Step 1 of 3')]")
	WebElement stepOnesubHeader;


	public void homePageLanding() {
		pause(1);
		elementDisplayed(logo);
		currentUrl(driver);
		verifyTitle(driver, "CMS Enterprise Portal");
	}
	
	public void helpValidation() {
		pause(1);
		switchToChildWindow(driver, help);
		pause(1);
		currentUrl(driver);
		verifyTitle(driver, "CMS Enterprise Portal - Help Center");
		validationOfHeader(helpHeader, "CMS Enterprise Portal - Help Center");
		validationOfSubHeader(helpSubHeader, "The Enterprise Portal is a gateway that provides access to over 50 different Centers for Medicare & Medicaid Services (CMS) healthcare-based applications. It provides the ability to request access to multiple Portal-integrated CMS applications and to launch/access those applications.");
	
	}
	
	public void loginValidation() {
		inputText(userId, "August 2023 QA Bootcamp");
		pause(1);
		clearTextFromTheField(userId);
		inputText(userId, "August 2023 QA Bootcamp");
		inputText(password, "Enthrall@042023");
		pause(1);
		clearTextFromTheField(password);
		inputText(password, "Enthrall@042023");
		pause(1);
		clickElement(checkBox);
		pause(1);
		elementEnabled(login);
		verifyTextOfTheWebElement(login, "Login");
		clickElement(login);
		pause(1);
		verifyErrorMsg(loginErrorMsg, "Invalid combination of User ID and Password. Enter valid User ID and Password and try again.");
	}
	
	public void clickUnlock() throws InterruptedException {
		pause(1);
		driver.findElement(unlock).click();
		pause(1);
	}
	
	public void directingToNewUserRegistration() {
		pause(4);
		elementEnabled(newUserRegistration);
		verifyTextOfTheWebElement(newUserRegistration, "New User Registration");
		clickElement(newUserRegistration);
	}
	
	public void stepOneOfNewUserRegistration() {
		pause(1);
		currentUrl(driver);
		verifyTitle(driver, "CMS Enterprise Portal - New User Registration");
		validationOfHeader(stepOneHeader, "Step #1: Select Your Application");
		validationOfSubHeader(stepOnesubHeader,
				"Step 1 of 3 - Select your application from the dropdown. You will then need to agree to the terms & conditions.");
	}
	
	// below methods Used in HomePageParameterizedTest Class	
	public void inputUserIdField(String userIdFieldText) {
		inputText(userId, userIdFieldText);
	}
	
	public void inputPasswordField(String passwordFieldText) {
		inputText(password, passwordFieldText);
	}
	
	public void clickCheckBox() {
		pause(1);
		clickElement(checkBox);
	}
	
	public void clickLogin() {
		pause(1);
		clickElement(login);		
	}
	
	public void errorMessage(String errMsg) {
		pause(1);
		verifyErrorMsg(loginErrorMsg, errMsg);
	}

}
