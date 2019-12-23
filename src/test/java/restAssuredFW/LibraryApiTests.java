package restAssuredFW;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class LibraryApiTests extends TestBase{
	
	@Test
	public void Test5() {
		
		RestAssured.baseURI = TestBase.prop.getProperty("LIBRARYAPIURL");		
		Response res =given().
			header("Content-Type","application/json").
			body(Payload.addBookBody("abcd","1238456")).log().all().
			when().
			post(Resources.addBookToLibrary()).
			then().assertThat().statusCode(200).and().log().all().and()
			.extract().response();	
		
		
		JsonPath js = Utilities.rawToJson(res);
		js.get("ID");
	}
	

}
