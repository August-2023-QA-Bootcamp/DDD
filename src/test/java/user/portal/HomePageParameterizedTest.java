package user.portal;

import static constants.IParam.*;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class HomePageParameterizedTest extends BaseClass{

	@Test
	@Parameters({USERIDFIELDTEXT, PASSWORDFIELDTEXT, ERRORMSG})
	public void loginValidation(String userIdFieldText, String passwordFieldText, String errorMsg) {
		homePage.inputUserIdField(userIdFieldText);
		homePage.inputPasswordField(passwordFieldText);
		homePage.clickCheckBox();
		//homePage.clickLogin();
		homePage.errorMessage(errorMsg);
	}
}
