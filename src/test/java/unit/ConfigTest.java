package unit;

import org.testng.annotations.Test;

import constants.IConstant;
import constants.Profile;
import utils.Configuration;

public class ConfigTest {

	@Test
	public void test() {
		Configuration configuration = new Configuration(Profile.DB);
		System.err.println(configuration.getProperties(IConstant.DB_URL));
	}
}
