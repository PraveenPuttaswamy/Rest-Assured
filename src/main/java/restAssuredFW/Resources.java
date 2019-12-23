package restAssuredFW;

public class Resources {
	
	public static String nearbySearch() {
		String res = "/maps/api/place/nearby/json";
		return res;
	}
	
	
	public static String addPlace() {
		String res = "/maps/api/place/add/json";
		return res;
	}
	
	
	public static String deletePlace() {
		String res = "/maps/api/place/delete/json";
		return res;
	}
	
	public static String addPlaceXML() {
		String res = "/maps/api/place/add/xml";
		return res;
	}
	
	public static String addBookToLibrary() {
		String res = "Library/Addbook.php";
		return res;
	}

}
