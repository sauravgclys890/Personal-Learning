package com.automation.genriclib;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.automation.drivermanager.DriverManager;
import com.automation.drivermanager.DriverManagerFactory;
import com.automation.drivermanager.DriverType;
import com.relevantcodes.extentreports.LogStatus;

import APIAutomation.Utils.ExtentReportListner;
import APIAutomation.Utils.FileandEnv;

@Listeners(ExtentReportListner.class)
public class BaseClass extends ExtentReportListner{
	
	public static WebDriver driver;
	
	@BeforeClass
	public void confibBC() {
		
		test.log(LogStatus.INFO, "Launch browser before class methos initiated");
		driver=DriverManagerFactory.getManager(DriverType.CHROME).getDriver();
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "before class in passed");
	}
	
	@BeforeMethod
	public void launchBrowser() {
		test.log(LogStatus.INFO, "Launch Application is started");
		if(driver != null) {
			driver.get(FileandEnv.envAndFile().get("serverUrl"));
		}else {
			driver=DriverManager.getChromeDriver();
			driver.get(FileandEnv.envAndFile().get("serverUrl"));
		}
		
		test.log(LogStatus.PASS, "before Method in passed");
	}
	
	@AfterMethod
	public void logoutApp() {
		test.log(LogStatus.INFO, "Application is logout successfully");
	}
	
	@AfterClass
	public void configAC() {
		if(driver != null) {
			driver.quit();
			driver= null;
		}
		test.log(LogStatus.PASS, "Driver has been quit successfully");
	}

}
