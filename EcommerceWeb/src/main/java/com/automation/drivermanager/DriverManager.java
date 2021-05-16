package com.automation.drivermanager;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	
	protected WebDriver driver;
	protected abstract void startService();
	protected abstract void stopService();
	protected abstract void createdriver();
	
	public void quitDriver() {
		if(driver != null) {
			driver.quit();
			driver=null;
		}
	}
	
	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	
	public WebDriver getDriver() {
		if(null == driver) {
			startService();
			createdriver();
		}
		return driver;
	}
	
	public static WebDriver getChromeDriver() {
		return DriverManagerFactory.getManager(DriverType.CHROME).getDriver();
		
	}
	

}
