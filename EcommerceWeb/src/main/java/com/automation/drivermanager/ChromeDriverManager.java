package com.automation.drivermanager;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager{

	private ChromeDriverService chService;
	
	@Override
	protected void startService(){
		if(null == chService) {
			chService= new ChromeDriverService.Builder().usingDriverExecutable(new File("chromedriver.exe"))
					.usingAnyFreePort().build();
			try {
				chService.start();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void stopService() {
	
		if(chService != null && chService.isRunning()) {
			chService.stop();
			
		}
	}

	@Override
	protected void createdriver() {
	
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options= new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver= new ChromeDriver(chService, capabilities);
		
	}

}
