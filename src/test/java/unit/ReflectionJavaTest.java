package unit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import dto.User;

public class ReflectionJavaTest {

	@SuppressWarnings("unused")
	@Test
	public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, CloneNotSupportedException {
		User user1 = new User();
		User user2 = (User)user1.clone();
		Class<?> user = user1.getClass();
				//User.class.getClass().newInstance();
		for(Method method: user.getMethods()) {
			//System.err.println(method.getName());
		}
		
		for(Constructor<?> constructor: user.getConstructors()) {
			//System.err.println(constructor.getName());
		}
		
		for(Field field: user.getFields()) {
			System.err.println(field.getName());
		}
		
		System.err.println(user1.hashCode());
		System.err.println(user2.hashCode());
	}
}
