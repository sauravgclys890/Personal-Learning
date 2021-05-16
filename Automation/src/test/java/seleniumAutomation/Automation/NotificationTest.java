package seleniumAutomation.Automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.PageObject.SamplePage;
import com.automation.PageObject.SamplePageNew;
import com.automation.genriclib.ElementValidator;

public class NotificationTest {
	
	private WebDriver driver;
    private SamplePageNew samplePage;
	@BeforeTest
	public void init() {
		this.driver= new ChromeDriver();
		this.samplePage = new SamplePageNew(driver);
		this.samplePage.goTo();
		this.driver.manage().window().maximize();
		
	
	}
	
	@Test
	public void test() {
		this.samplePage.getElementValidator().stream().parallel().map(ElementValidator:: validate).forEach(Assert::assertTrue);
	}
	
	@AfterTest
	public void teardown() {
		this.driver.quit();
	}

}
