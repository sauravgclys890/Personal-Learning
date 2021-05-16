package studentAPI;

import org.testng.annotations.Test;

import APIAutomation.apiConfig.APIPath;
import APIAutomation.apiConfig.HeaderConfig;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.PostAPIPojo;

public class SerTest extends BaseTest{
	
	

	@Test
	public void serTest1() {
		
		PostAPIPojo pojo= new PostAPIPojo("eve.holt@reqres.in", "pistol");
		
		System.out.println(pojo.toString());
		Response res=RestAssured.given().when().headers(new HeaderConfig().defaultHeader())
				.body(pojo).post(APIPath.apipaths.REGISTER_USER);
		
		System.out.println(res.getBody().asString());
		
		System.out.println(pojo.getEmail());
		
		System.out.println(pojo.getPassword());
	}
}
