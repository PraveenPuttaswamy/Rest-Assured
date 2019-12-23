package restAssuredFW;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class PlaceSearchApiTests extends TestBase{
	
	@Test
	public void Test1() {
		
		RestAssured.baseURI=TestBase.prop.getProperty("HOST");
		
		given().
				param("location","-38.383494,33.427362").
				param("radius","1500").
				param("key",TestBase.prop.getProperty("KEY")).log().all().
				when().
				get(Resources.nearbySearch()).
				then().log().all().assertThat().statusCode(404).and().
				contentType(ContentType.HTML).and();			
	}
	
	@Test
	public void Test2() {
		
		RestAssured.baseURI=TestBase.prop.getProperty("HOST");
		
		given().
				queryParam("key",TestBase.prop.getProperty("KEY")).
				body(Payload.getPostBody()).and().
				when().
				post(Resources.addPlace()).
				then().assertThat().statusCode(200).and().
				body("status",equalTo("OK"));
	}
	@Test
	public void Test3() {
		
		RestAssured.baseURI=TestBase.prop.getProperty("HOST");
		Response res = given().
				queryParam("key",TestBase.prop.getProperty("KEY")).
				body(Payload.getPostBody()).and().
				when().
				post(Resources.addPlace()).
				then().assertThat().statusCode(200).and().
				body("status",equalTo("OK")).
				extract().response();
		
		String responseString = res.asString();
		JsonPath j = new JsonPath(responseString);
		String placeId = j.get("place_id");
		
		given().
			queryParam("key","qaclick123").
			body(Payload.getDeleteBody(placeId)).
			when().
			delete(Resources.deletePlace()).
			then().assertThat().statusCode(200).
			body("status",equalTo("OK"));				
	}
	
	
	@Test
	public void Test4() throws IOException {
		
		RestAssured.baseURI=TestBase.prop.getProperty("HOST");
		
		Response res = given().
				queryParam("key",TestBase.prop.getProperty("KEY")).
				body(Payload.getPostBodyFromXMLFile()).and().
				when().
				post(Resources.addPlaceXML()).
				then().assertThat().statusCode(200).and().
				contentType(ContentType.XML).and().
				extract().response();
		
				String respon = res.asString();
				XmlPath xp = new XmlPath(respon);
				xp.get("place_id");
	}
}
