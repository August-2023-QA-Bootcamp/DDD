package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.IConstant.*;

public class JDBCUtils {

	Configuration config;
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	ResultSetMetaData metaData;
	
	public JDBCUtils(Configuration configuration) throws SQLException {
		config = configuration;
		connectToDb();
		createStatement();
	}
	
	public Configuration getConfiguration() {
		return config;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public ResultSetMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(ResultSetMetaData metaData) {
		this.metaData = metaData;
	}
	
	private void connectToDb() throws SQLException {
		try {
			setConnection(DriverManager.getConnection(
					getConfiguration().getProperties(DB_URL),
					getConfiguration().getProperties(DB_USER),
					getConfiguration().getProperties(DB_PASS)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() throws SQLException {
		getConnection().close();
	}
	
	public void createStatement() throws SQLException {
		setStatement(getConnection().createStatement());
	}
	
	public void executeStatement(String query) throws SQLException {
		getStatement().execute(query);
		setResultSet(getStatement().getResultSet());
		if(getResultSet() != null) {
			setMetaData(getResultSet().getMetaData());
		}
	}
	
	public List<Map<String, String>> getResult() throws SQLException {
		List<Map<String, String>> listOfMap = new ArrayList<Map<String,String>>();
		
		int totalColumns = getMetaData().getColumnCount();
		
		while(getResultSet().next()) {
			Map<String, String> map = new HashMap<String, String>();
			
			for(int i = 1; i <= totalColumns; i++) {
				String key = metaData.getColumnName(i);
				String value = resultSet.getString(i);
				
				map.put(key, value);
			}
			listOfMap.add(map);
		}
		return listOfMap;
	}
	
}
