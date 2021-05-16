package seleniumAutomation.Automation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.PageObject.LoginPage;
import com.automation.PageObject.RegistrationPage;
import com.automation.drivermanager.DriverManager;
import com.automation.drivermanager.DriverManagerFactory;
import com.automation.drivermanager.DriverType;

public class PageTest {
	
	DriverManager driverManager;
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		driverManager=DriverManagerFactory.getManager(DriverType.CHROME);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver= driverManager.getDriver();
		driverManager.maximizeWindow();
	}

	@AfterMethod
	public void afterMethod() {
		driverManager.quitDriver();
	}
	@Test
	public void testPage() {
		RegistrationPage.using(driver).launch().setFirstName("fn").setLastName("ln").setPassword("abcd").setConfirmPassword("abcd").submit();
		
		
	}
	
	@Test
	public void testLoginPage() {
		LoginPage.using(driver).setUsername("abcd").setPassword("abcd").login();
		
	}
	
     
	
}
