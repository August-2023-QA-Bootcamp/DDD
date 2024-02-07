package unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.testng.annotations.Test;

import constants.Profile;
import utils.Configuration;
import utils.JDBCUtils;

public class JDBCUnit {

	@Test(enabled = false)
	public void connectivity_test() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employees_db", "postgres", "hr");
		Statement statement = connection.createStatement();
		
		statement.execute("SELECT * FROM countries");
		
		ResultSet resultSet = statement.getResultSet();
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		System.out.println(metaData.getColumnCount());
		
		while(resultSet.next()) {
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				System.out.print(resultSet.getString(i) + ", ");
			}
			System.out.println();
		}
		connection.close();
	}
	
	@Test
	public void JDBCUtilsTest() throws SQLException {
		
		JDBCUtils utils = new JDBCUtils(new Configuration(Profile.DB));
		utils.executeStatement("select * from dummyuser");
		
		for(Map<String, String> map: utils.getResult()) {
			System.out.println(map);
		}
		
		utils.closeConnection();
	}
}
