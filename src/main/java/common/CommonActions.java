package common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.io.Files;

import constants.Attribute;
import reports.Loggers;

public class CommonActions {

	WebDriver driver;
	CommonWaits waits;

	public CommonActions(WebDriver driver, CommonWaits waits) {
		this.driver = driver;
		this.waits = waits;
	}

	public static void verifyTitle(WebDriver driver, String expectedTitle) {
		try {
			Loggers.logTheTest("Actual Title is : " + driver.getTitle() + " ---> And Expected Title is :" + expectedTitle);
			Assert.assertEquals(driver.getTitle(), expectedTitle);
		} catch (NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest("Driver is not initiated properly Or Home Page Title doesn't match");
			Assert.fail();
		}
	}

	public static void inputText(WebElement element, String input) {
		try {
			element.sendKeys(input);
			Loggers.logTheTest(input + " <-----> has been put into <-----> " + element);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage() );
			Assert.fail();
		}
	}
	
	public static void inputTextThenClickEnter(WebElement element, String input) {
		try {
			element.sendKeys(input, Keys.ENTER);
			Loggers.logTheTest(input + " <-----> has been put into <-----> " + element + " and then clicked by Enter Key");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage() );
			Assert.fail();
		}
	}
	
	public static void inputTextThenClickTab(WebElement element, String input) {
		try {
			element.sendKeys(input, Keys.TAB);
			Loggers.logTheTest(input + " <-----> has been put into <-----> " + element + " and then clicked by Tab Key");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage() );
			Assert.fail();
		}
	}
	
	public static void clickElementThenTab(WebElement element) {
		try {
			element.sendKeys(Keys.TAB);
			Loggers.logTheTest(element + "<---------> has been clicked, then click Tab Key");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage() );
			Assert.fail();
		}
	}
	
	public static void pause(long sec) {
		try {
			Thread.sleep(sec * 1000);
			Loggers.logTheTest("Sleeping ... zZz " + sec);
		}catch (InterruptedException e) {
			e.printStackTrace();
			Loggers.logTheTest("Sleep interrupted");
		
		}
	}
	
	public static void clickElement(WebElement element) {
		try {
			element.click();
			Loggers.logTheTest(element + "<---------> has been clicked");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage() ); // getMessage() Returns the detail message string of this throwable.
			Assert.fail();
		}
	}
	
	public static boolean elementEnabled(WebElement element) {
		try {
			boolean flag = element.isEnabled();
			Loggers.logTheTest(element + "<---------> is Enabled, " + flag);
			Assert.assertTrue(true, "Element is Disabled ....."); // TODO Nasir: Need to make sure
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> is Disabled\n" + e.getMessage() ); // getMessage() Returns the detail message string of this throwable.
			Assert.fail();
		}
		return true;
	}
	
	public static boolean elementDisplayed (WebElement element){
		try {
			boolean flag = element.isDisplayed();
			Loggers.logTheTest(element + "<---------> is Displayed, " + flag);
			Assert.assertTrue(true, "Element is not displayed .....");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> is not Displayed\n" + e.getMessage() );
		}
		return true;				
	}
	
	public static boolean elementSelected (WebElement element){
		try {
			boolean flag = element.isSelected();
			Loggers.logTheTest(element + "<---------> is Selected, " + flag);
			Assert.assertTrue(true, "Element is not Selected .....");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> is not Selected\n" + e.getMessage() );
		}
		return true;				
	}
	
	
	public static void clearTextFromTheField(WebElement element) {
		try {
			element.clear();
			Loggers.logTheTest("The Text from the " + element + " ---> is cleared");
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage());
			Assert.fail();
		}
	}
	
	public static void verifyTextOfTheWebElement(WebElement element, String expected) {
		String actual = element.getText();
		Loggers.logTheTest(element + " ---> Actual text : " + actual + ". Expected text : " + expected);
		Assert.assertEquals(actual, expected, "Text In the WebElement doesn't match");
	}
	
	// Used in line 47
	// Attribute is coming from package constants, we will check the outcome later
	public static String getAttributeValue(WebElement element, Attribute attribute) {
		return element.getAttribute(attribute.toString());
	}
	
	public static void verifyAttribute01(WebElement element, String expected, Attribute attribute) {
		String actual = getAttributeValue(element, attribute);
		// element.getAttribute(attribute.toString());
		Loggers.logTheTest(element + " ---> We can Enter : " + actual + " Character in the field which was similar with the Expected as: " + expected);
		Assert.assertEquals(actual, expected);
	}
	
	public static void verifyLengthOfTheFieldContent(WebElement element, String expected) {
		verifyAttribute01(element, expected, Attribute.MAX_LENGTH);
	}
	
	public static void verifyAttribute02(WebElement element, String expectedErrorMsg, Attribute attribute) {
		String actual = getAttributeValue(element, attribute);
		// element.getAttribute(attribute.toString());
		Loggers.logTheTest(element + " ---> Actual Error Message is : " + actual + ". And Expected was: " + expectedErrorMsg);
		Assert.assertEquals(actual, expectedErrorMsg);
	}
	
	public static void verifyErrorMsgUnderTheField(WebElement element, String expectedErrorMsg) {
		verifyAttribute02(element, expectedErrorMsg, Attribute.INNER_TEXT); //"innerHTML"
	}
	
	public static void verifyErrorMsg(WebElement element, String expectedErrorMsg) {
		verifyAttribute02(element, expectedErrorMsg, Attribute.INNER_TEXT); //"innerHTML"
	}
	
	// not used
	public static void verifyErrorMsgTopOfThePage(WebElement element, String expectedErrorMsg) {
		verifyAttribute02(element, expectedErrorMsg, Attribute.INNER_TEXT); //"innerHTML"
	}
	
	
	
	public static void hoverOverAction(WebDriver driver, WebElement element) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).build().perform();
			Loggers.logTheTest("Hovering on ---> " + element);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage());
			Assert.fail();
		}
	}
	
	public static void afterHoverOverClickToAnEelement(WebDriver driver, WebElement hover_element, WebElement element_after_hover) {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(hover_element).click(element_after_hover).build().perform(); // this click method is new
			Loggers.logTheTest("Hovering on ---> " + hover_element);
			Loggers.logTheTest("Clicking on ---> " + element_after_hover);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(hover_element + " || " + element_after_hover + " ---> Not Found \n" + e.getMessage()); // or represented by ||
			Assert.fail();
		}
	}
	
	public static String currentUrl(WebDriver driver) {
		Loggers.logTheTest("Current URL is : " + driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	public static String validationOfHeader(WebElement element, String expectedHeader) {
		Assert.assertEquals(element.getText(), expectedHeader);
		Loggers.logTheTest(element + " ---> Actual Header : " + element.getText() + ". Expected Header : " + expectedHeader);
		return null;
	}
	
	public static String validationOfSubHeader(WebElement element, String expectedSubHeader) {
		Assert.assertEquals(element.getText(), expectedSubHeader);
		Loggers.logTheTest(element + " ---> Actual Sub Header : " + element.getText() + ". Expected SubHeader : " + expectedSubHeader);
		return null;
	}
		
	public boolean isPresent(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		if(elements.size() != 0) {
			Loggers.logTheTest(elements + " --- > This element is present and has match of : " + elements.size());
			return true;
		}else {
			Loggers.logTheTest(elements + " --- > This element is NOT present and no match found : " + elements.size());
			return false;
		}
	}
	
	public static void selectDropdown(WebElement element, String value) {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(value);
			Loggers.logTheTest(value + " has been selected from the dropdown of ---> " + element);
		} catch (NullPointerException | NoSuchElementException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + " : This element Not Found");
			Assert.fail();
		}
	}
	
	public static void selectDropdownAll(WebElement element, List<WebElement> elements) {
		try {
			Select select = new Select(element);
			for(int i =0; i <elements.size(); i++) {
				Loggers.logTheTest(elements.get(i).getText() + " is present in the dropdown");			
				select.selectByIndex(i);
				pause(2);
			}			
			Loggers.logTheTest("Total Element: " + elements.size() + " is present in the dropdown");
		} catch (NullPointerException | NoSuchElementException  e) { // elements er exception add korte hobe
			e.printStackTrace();
			Loggers.logTheTest(element + " : This element Not Found");
			Assert.fail();
		}
	}
	
	public static void scrollIntoViewToTheElement(WebDriver driver, String script, WebElement element) {
		try {
			((JavascriptExecutor)driver).executeScript(script, element);
			Loggers.logTheTest("Javascript Executor executing to view ..." + script + " on the element of ---> " + element);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage());
			Assert.fail();
		}
		
	}
	
	public void jsExecutor(WebDriver driver, String script, WebElement element) {
		// JavascriptExecutor js = (JavascriptExecutor)driver; // instead of writing this 'js', we can write below one
		((JavascriptExecutor)driver).executeScript(script, element);
		Loggers.logTheTest("Javascript Executor executing ..." + script + " on element ---> " + element);
	}
	
	
	public void failText() {
		Loggers.logTheTest(getClass().getMethods()[0].getName() + " ---> has failed");
		Assert.fail();
	}
	
	// very very important interview question
	public static String getSreenShot(String testName, WebDriver driver) {
		TakesScreenshot ss = (TakesScreenshot) driver;
		String path = System.getProperty("user.dir") + "/test-output/screenShots";
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy_hh.mm.ss");
		String formattedDate = dateFormat.format(date);
		
		File targetFile = new File(path + "/error_" + testName + "_" + formattedDate + ".png");
		try {
			File srcFile = ss.getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, targetFile);
			Loggers.logTheTest("Screenshot has been successfully capture at: \n" + targetFile.getAbsolutePath());
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
			Loggers.logTheTest("Screenshot cannot be captured");
		}
		return targetFile.getAbsolutePath();
	}
	
	public static void switchToChildWindow(WebDriver driver, WebElement element) {
		try {
			clickElement(element);
			Set<String> allWindowHandles = driver.getWindowHandles();
			Loggers.logTheTest("Total Windows Opened: " + allWindowHandles.size()); 
			//String parent = (String)allWindowHandles.toArray()[0];
			String child = (String)allWindowHandles.toArray()[1];
			driver.switchTo().window(child);
			Loggers.logTheTest(" The Window moved to --> " + child);
		} catch (NoSuchElementException | NullPointerException e) {
			e.printStackTrace();
			Loggers.logTheTest(element + "<----------> has not been found\n" + e.getMessage());
			Assert.fail();
		}
	}
	
}
