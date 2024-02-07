package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import dto.User;
import reports.Loggers;

public class ExcelUtil {

	Workbook workbook;
	Sheet sheet;

	public ExcelUtil(String fileName, String sheetName) {
		try {
			workbook = WorkbookFactory.create(getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			Loggers.logTheTest(String.format("This excel file : %s not found", fileName));
		}
		sheet = workbook.getSheet(sheetName);
	}

	public List<User> getUserCreds() {
		List<User> listOfUsers = new ArrayList<User>();

		int rowSize = sheet.getLastRowNum();
		
		for(int i = 1; i <= rowSize; i++) {
			Map<String, String> map = getUserData(i);
			User user = new User(map.get("username"), map.get("password"));
			listOfUsers.add(user);
		}
		
		return listOfUsers;
	}

	public Map<String, String> getUserData(int rowNum) {
		Map<String, String> map = new HashMap<String, String>();

		Row row = sheet.getRow(rowNum);

		int cellCountInRow = row.getLastCellNum();

		String user_value = null;
		String pass_value = null;

		for (int i = 0; i < cellCountInRow; i++) {
			try {
				Cell cell = row.getCell(i);
				String header_value = sheet.getRow(0).getCell(i).getStringCellValue();

				if (header_value.equalsIgnoreCase("username")) {
					user_value = cell.getStringCellValue();
				} else if (header_value.equalsIgnoreCase("password")) {
					pass_value = cell.getStringCellValue();
				}
			} catch (NullPointerException e) {
				continue;
			}
		}

		if(user_value != null && pass_value != null) {
			// To make it dynamic, pass header value as key
			map.put("username", user_value);
			map.put("password", pass_value);
		}

		return map;
	}

	public List<Map<String, Object>> getDataList(){
		List<Map<String, Object>> listOfMaps = new ArrayList<Map<String,Object>>();
		
		System.err.println("Total Rows : " + sheet.getLastRowNum());
		
		for(int i = 1; i <= sheet.getLastRowNum(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Row row = sheet.getRow(i);
			System.err.println("Last cell num : " + row.getLastCellNum());
			for(int j = 0; j < row.getLastCellNum(); j++) {
				try {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					Cell cell = row.getCell(j);
					Object value = null;
					if(cell.getCellType() == CellType.BOOLEAN) {
						 value = cell.getBooleanCellValue();
					}else if(cell.getCellType() == CellType.STRING) {
						value = cell.getStringCellValue();
					}else if(cell.getCellType() == CellType.NUMERIC) {
						value = cell.getNumericCellValue();
					}
					map.put(key, value);
				}catch (NullPointerException e) {
					// TODO: handle exception
				}
			}
			listOfMaps.add(map);
		}
		
		return listOfMaps;
	}
}
