package baseTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import APIAutomation.Utils.ExtentReportListner;
import APIAutomation.Utils.FileandEnv;
import io.restassured.RestAssured;

@Listeners(ExtentReportListner.class)
public class BaseTest extends ExtentReportListner{
	
	@BeforeTest
	public void baseTest() {
		System.out.println(FileandEnv.envAndFile().get("serverUrl"));
		RestAssured.baseURI=FileandEnv.envAndFile().get("serverUrl");
	}

}
