package user.portal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseUtil.BaseClass;
import dto.User;
import utils.ExcelUtil;

public class HomePageDataDrivenTest extends BaseClass{

	@DataProvider(name = "userDataExplicitArray")
	public Object[][] getDataExArray() {
		Object[][] obj = new Object[3][2];
		Object[] objAr1 = {"user1","pass1"};
		Object[] objAr2 = {"user2","pass2"};
		Object[] objAr3 = {"user3","pass3"};
		
		obj[0] = objAr1;
		obj[1] = objAr2;
		obj[2] = objAr3;
		
		return obj;
	}
	
	@DataProvider(name = "userDataAnnonArray")
	public Object[][] getDataAnArray() {
		return new Object[][] {
			{"user1","pass1"},
			{"user2","pass2"},
			{"user3","pass3"}
		};
	}
	
	@DataProvider(name = "userDataIteratorObj")
	public Iterator<Object[]> getDataIteratorObj(){
		List<Object[]> listOfObjArr = new ArrayList<Object[]>();
		
		Object[] objAr1 = {"user1list","pass1list"};
		Object[] objAr2 = {"user2list","pass2list"};
		Object[] objAr3 = {"user3list","pass3list"};
		
		listOfObjArr.add(objAr1);
		listOfObjArr.add(objAr2);
		listOfObjArr.add(objAr3);
		
		return listOfObjArr.iterator();
	}
	
	// Standard Approach
	@DataProvider(name = "userDataIteratorMap")
	public Iterator<Map<String, String>> getDataIteratorMap(){
		List<Map<String, String>> listOfMap = new ArrayList<>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("password", "passmap1");
		map1.put("username", "usermap1");
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("username", "usermap2");
		map2.put("password", "passmap2");
		
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("password", "passmap3");
		map3.put("username", "usermap3");
		
		listOfMap.add(map1);
		listOfMap.add(map2);
		listOfMap.add(map3);
		
		return listOfMap.iterator();
	}
	
	// Standard Approach - Pro Max
	@DataProvider(name = "userDataIteratorDTO")
	public Iterator<User> getDataIteratorDTO(){
		List<User> listOfObjArr = new ArrayList<>();
		
		User user1 = new User("user1list","pass1list");
		User user2 = new User("user2list","pass2list");
		User user3 = new User("user3list","pass3list");
		
		listOfObjArr.add(user1);
		listOfObjArr.add(user2);
		listOfObjArr.add(user3);
		
		return listOfObjArr.iterator();
	}
	
	// Standard Approach - Pro Max
	@DataProvider(name = "userDataIteratorDTO_excel")
	public Iterator<User> getDataIteratorDTO_excel(){
		ExcelUtil excelUtil = new ExcelUtil(excelFileName, excelSheetName);
		return excelUtil.getUserCreds().iterator();
	}
	
	@Test(dataProvider = "userDataIteratorObj", enabled = false)
	public void loginValidation(String userIdFieldText, String passwordFieldText) {
		homePage.inputUserIdField(userIdFieldText);
		homePage.inputPasswordField(passwordFieldText);
		homePage.clickCheckBox();
	}
	
	// Standard Approach
	@Test(dataProvider = "userDataIteratorMap", enabled = false)
	public void loginValidationMap(Map<String, String> map) {
		homePage.inputUserIdField(map.get("username"));
		homePage.inputPasswordField(map.get("password"));
		homePage.clickCheckBox();
	}
	
	// Standard Approach - Pro Max
	@Test(dataProvider = "userDataIteratorDTO_excel")
	public void loginValidationMap(User user) {
		homePage.inputUserIdField(user.getUser());
		homePage.inputPasswordField(user.getPass());
		homePage.clickCheckBox();
	}
}
