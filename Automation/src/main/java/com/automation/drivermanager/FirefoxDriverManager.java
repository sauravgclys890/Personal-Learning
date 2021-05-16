package com.automation.drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{

	private WebDriver driver;
	
	@Override
	protected void startService() {
		if(null == driver) {
			driver = new FirefoxDriver(); 
		}
		
	}

	@Override
	protected void stopService() {
		
		
	}

	@Override
	protected void createdriver() {
		
		
	}

	
}
