package TestAutomation.Automation;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.drivermanager.DriverManager;
import com.automation.drivermanager.DriverManagerFactory;
import com.automation.drivermanager.DriverType;

public class Demo1 {

    WebDriver driver;
    
    //http://172.17.0.2:4444/wd/hub

    @BeforeMethod
    public void beforeMethod() throws Exception {
    	DesiredCapabilities cap= DesiredCapabilities.chrome();
    	cap.setCapability("version","");
    
    	cap.setPlatform(Platform.LINUX);
    	
    	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\sauk0719\\Documents\\SauravAutomation\\Automation\\chromedriver.exe");
    	
        driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), cap);
       driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void launchTestAutomationGuruTest() {
        driver.get("http://testautomationguru.com");
        Assert.assertEquals("Vinsguru", driver.getTitle());
    }

    @Test
    public void launchGoogleTest() {
        driver.get("https://www.google.com");
        Assert.assertEquals("Google", driver.getTitle());
    }

    @Test
    public void launchYahooTest() {
        driver.get("https://www.yahoo.com");
        Assert.assertEquals("Yahoo India | News, Finance, Cricket, Lifestyle and Entertainment", driver.getTitle());
    }

}
