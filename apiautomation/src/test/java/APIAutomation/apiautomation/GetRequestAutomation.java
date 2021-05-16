package APIAutomation.apiautomation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestAutomation {
	
	@Test
	public void getRequestAutomation() {
		
		RestAssured.baseURI= "http://reqres.in/api/users";
		
		Response res= RestAssured.given().param("page", "2")
		.when().get();
		
		System.out.println(res.body().asString());
		
		System.out.println(res.getStatusCode());
		
		System.out.println(res.getContentType());
		
		System.out.println(res.getTime());
		
		System.out.println(res.getCookies());
		
		System.out.println(res.prettyPrint());
		//.then().assertThat().log().all().statusCode(200);
	}
	

}
