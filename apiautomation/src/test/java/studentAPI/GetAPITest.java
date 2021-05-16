package studentAPI;

import static org.testng.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.LogStatus;

import APIAutomation.apiConfig.APIPath;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAPITest extends BaseTest{
	
	@Test
	public void getAPITest() {
		
	test.log(LogStatus.INFO, "My test is starting..........");
		
	Response res=RestAssured.given().when().get(APIPath.apipaths.GET_LIST_OF_USER);
	
	test.log(LogStatus.INFO, res.getBody().asString());
	
	test.log(LogStatus.INFO, "My status code is "+ res.getStatusCode());
	
	test.log(LogStatus.INFO, "Response time is ="+ res.getTime());
	
	JSONObject obj= new JSONObject(res.getBody().asString());
	
	String firstChar = String.valueOf(res.getBody().asString().charAt(0));
	
	System.out.println(firstChar);
	
	if(res.getBody() instanceof JSONArray) {
		System.out.println("it is JSON Array");
	} else if(res.getBody() instanceof JSONObject) {
		System.out.println("it is jSON object");
	}
	else {
		System.out.println("nothing");
	}
	System.out.println(obj);
	
	assertEquals(obj.get("page"), 2);
	
	JSONObject obj1= obj.getJSONObject("ad");
	
	System.out.println(obj1);
	
	JSONArray array=obj.getJSONArray("data");
	
	
	System.out.println(array);
	
	for(int i=0; i< array.length(); i++) {
		JSONObject arrayobj= array.getJSONObject(i);
		
		System.out.println(arrayobj.get("first_name"));
		
		test.log(LogStatus.INFO, arrayobj.getString("first_name"));
	}
	
	test.log(LogStatus.PASS, "My test is passed");
	
	test.log(LogStatus.INFO, "My test is ended.......");
	
	}
	

}
