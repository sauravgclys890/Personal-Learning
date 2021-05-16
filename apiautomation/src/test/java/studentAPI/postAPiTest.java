package studentAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import APIAutomation.Utils.JavaUtils;
import APIAutomation.apiConfig.APIPath;
import APIAutomation.apiConfig.HeaderConfig;
import apiBuilder.PostAPIBuilder;
import apiVerifications.APIVerifications;
import baseTest.BaseTest;
import groovyjarjarantlr.StringUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class postAPiTest extends BaseTest{
	
	@Test
	public void validPostTest() {
			test.log(LogStatus.INFO, "Post test is started.....");
			
		Response res= RestAssured.given().when().headers(new HeaderConfig().defaultHeader()).body(new PostAPIBuilder()
				      .postRequestBody(JavaUtils.randomString(),JavaUtils.randomString())).when()
		              .post(APIPath.apipaths.CREATE_USER);
		
		System.out.println(res.getBody().asString());
		
		APIVerifications.responseKeyValidationFromJsonObject(res, "name");
		APIVerifications.responseCodeValidation(res, 201);
		APIVerifications.responseTimevalidation(res);
		
		test.log(LogStatus.INFO, "Post test is ended");
	}

}
