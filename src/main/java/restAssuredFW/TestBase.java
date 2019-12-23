package restAssuredFW;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

public class TestBase {
	
	public static Properties prop;
	
	@BeforeTest
	public void setProperties() throws IOException {
		prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\restAssuredFW\\env.properties");
		prop.load(fis);
	}
}
