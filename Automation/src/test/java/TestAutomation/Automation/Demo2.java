package TestAutomation.Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Demo2 {
	
	@Test
	public void loginfundsIndiaApp() throws Exception {
		
		//DesiredCapabilities cap= DesiredCapabilities.chrome();
    	//cap.setCapability("version","");
    	//cap.setPlatform(Platform.LINUX);
    	
    	//ChromeOptions remoteOptions = new ChromeOptions();
    	
    	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\sauk0719\\Documents\\drivers_latest\\chromedriver.exe");
    	
    	
    	//System.out.println(System.getProperty("webdriver.chrome.driver"));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.FIREFOX);
    
		FileInputStream fis= new FileInputStream(new File("C:\\Users\\sauk0719\\Documents\\SauravAutomation\\Automation\\Data\\sampledata.txt"));
		
		Properties pobj= new Properties();
		
		pobj.load(fis);
		
		
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), cap);
		
		driver.get(pobj.getProperty("url"));
		
		String title= driver.getTitle();
		
		System.out.println(pobj.get("url"));
		
		if(title.contains("FundsIndia")) {
			System.out.println("Test case is passed");
		}
		
		driver.findElement(By.name("email")).sendKeys(pobj.get("username").toString());
		
		driver.findElement(By.name("pwd")).sendKeys(pobj.get("password").toString());
		
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		Thread.sleep(5000);
		
		WebElement elem=driver.findElement(By.xpath("//h1[text()='LOGIN']"));
		
		System.out.println(elem.getText());
		
		driver.close();
	}

}
