package APIAutomation.apiautomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PostRequestAutomation {
	
	@Test
	public void postRequestAutomation() {
		
		RestAssured.baseURI= "http://reqres.in/api/users";
	
	}

}
