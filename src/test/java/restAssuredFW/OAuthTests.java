package restAssuredFW;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class OAuthTests extends TestBase{
	
	@Test
	public void Test6() {
		
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\restAssuredFW\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys("restdemo.987");
		driver.findElement(By.cssSelector("input[type=\"email\"]")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys("aB12#$56");
		driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(Keys.ENTER);
		driver.quit();
		
		String url = driver.getCurrentUrl();
		String authCode = ((url.split("&"))[1].split("="))[1];
		
		JsonPath accessTokenResponse = Utilities.rawToJson( given().
		queryParams("code",authCode).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_url","\"https://rahulshettyacademy.com/getCourse.php\"").
		queryParams("grant_type","authorization_code").
		when().
		post("https://www.googleapis.com/oauth2/v4/token"));
		String accessToken = accessTokenResponse.get("access_token");
		
		JsonPath res =  Utilities.rawToJson(given().
		queryParam("access_token", accessToken).
		when().
		get("https://rahulshettyacademy.com/getCourse.php"));
		System.out.println(res);

	}

}
