package user.portal;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class HomePageTest extends BaseClass{

	@Test (enabled = true, groups = {"smokeTest", "regressionTest"})
	public void homePageLandingTest() {
		homePage.homePageLanding();
	}
	
	@Test (groups = {"sanityTest", "functionalTest", "regressionTest"})
	public void helpValidationTest() {
		homePage.helpValidation();
		//Assert.fail();
	}
	
	@Test (groups = {"smokeTest", "sanityTest", "regressionTest"})
	public void loginValidationTest() {
		homePage.loginValidation();
	}
	
	@Test (groups = {"smokeTest", "functionalTest", "regressionTest"})
	public void newUserRegistrationValidationTest() {
		homePage.directingToNewUserRegistration();
		homePage.stepOneOfNewUserRegistration();
	}
}
