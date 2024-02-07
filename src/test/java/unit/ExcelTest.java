package unit;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import utils.ExcelUtil;

public class ExcelTest {

	@Test(enabled = false)
	public void readExcel() throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(getClass().getClassLoader().getResourceAsStream("UserData.xlsx"));
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(2); // Start from 0 index
		Cell cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		System.err.println(row.getLastCellNum()); // Start from 1
		System.err.println(sheet.getLastRowNum()); // Start from 0
		
		boolean moreThan2Value = row.getLastCellNum() > 2 ? true:false;
		System.err.println(moreThan2Value);
	}
	
	@Test(enabled = false)
	public void excelUtilTest() {
		ExcelUtil excelUtil = new ExcelUtil("UserData.xlsx", "Sheet1");
		excelUtil.getUserCreds().forEach(user -> {
			System.err.println(user.getUser());
			System.err.println(user.getPass());
		});
	}
	
	@Test
	public void downloadedSheetTest() {
		ExcelUtil excelUtil = new ExcelUtil("Student's Projects.xlsx", "Sheet1");
		excelUtil.getDataList().forEach(System.out::println);
	}
}
