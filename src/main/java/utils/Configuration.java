package utils;

import java.io.IOException;
import java.util.Properties;

import constants.Profile;

public class Configuration {

	private Properties properties = new Properties();
	
	String generalConfig = "config.properties";
	String browserStackConfig = "browserstack.properties";
	String dbConfig = "db.properties";

	// This is a default Constructor
	// Why I am putting loadProperty () method inside constructor
	// because when Configuration class is instantiated (when it will create object)
	// then default Constructor will be initialized, the method loadproperty() present inside
	// the Constructor will also be initialized
	public Configuration(Profile profile) {
		
		switch (profile) {
		case GENERAL:
			loadProperty(generalConfig);
			break;
		case BROWSERSTACK:
			loadProperty(browserStackConfig);
			break;
		case DB:
			loadProperty(dbConfig);
			break;
		default:
			break;
		}
	}
		
	public void loadProperty (String profile) {
			try {
			properties.load(getClass().getClassLoader().getResourceAsStream(profile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Individual return type method created
	public String getProperties(String key) {
		return properties.getProperty(key);
	}
}
