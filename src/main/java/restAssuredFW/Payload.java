package restAssuredFW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payload {

	public static String getPostBody() {
		
		String body = "{" + 
				"\"location\":{" + 
				"\"lat\" : -38.383494," + 
				"\"lng\" : 33.427362" + 
				"}," + 
				"\"accuracy\":50," + 
				"\"name\":\"Frontline house\"," + 
				"\"phone_number\":\"(+91) 983 893 3937\"," + 
				" \"address\" : \"29, side layout, cohen 09\"," + 
				"\"types\": [\"shoe park\",\"shop\"]," + 
				"\"website\" : \"http://google.com\"," + 
				"\"language\" : \"French-IN\"}";
		
		return	body; 
	}
	
	
	public static String getDeleteBody(String placeId) {
		
		String body = "{\n" + 
				"\"place_id\": \"" + placeId + "\"\n" +
				"}";
		
		return	body; 
	}
	
	public static String addBookBody(String isbn, String aisle) {
		String book = "{\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
		return book;
	}
	
	
	public static String getPostBodyFromXMLFile() throws IOException {
		return GenerateStringFromResources(System.getProperty("user.dir")+"\\src\\main\\java\\restAssuredFW\\postDataBodyXML.xml");
	}
	
	public static String GenerateStringFromResources(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
