package seleniumAutomation.Automation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.PageObject.OrderSummaryPage;
import com.automation.PageObject.ProductSearchPage;
import com.automation.PageObject.UserDetailsPage;
import com.automation.drivermanager.DriverManager;

public class UserRegistrationTest {
	
	private WebDriver driver;
	private UserDetailsPage userDetailsPage;
	private ProductSearchPage productSearchPage;
    private OrderSummaryPage orderSummaryPage;
	//private OrderConfirmationPage orderConfirmationPage;
	
	@BeforeTest
	public void setUpDriver() {
		driver= DriverManager.getChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void launch() {
		userDetailsPage=UserDetailsPage.init(driver).launch();
		
		//validate if the page is loaded
		Assert.assertTrue(userDetailsPage.isAt());
	}
	
	@Test(dependsOnMethods = "launch")
	public void enterUserInfoAndSubmit()
	{
		userDetailsPage.setFirstname("fn").setLastName("ln").setDOB("dob").setEmail("em").setAddress("address").submit();
		
		//validate if the page is loaded
		productSearchPage= ProductSearchPage.init(driver);
		Assert.assertTrue(productSearchPage.isAt());
		
	}
	
	@Test(dependsOnMethods = "enterUserInfoAndSubmit")
	public void productSearch()
	{
		productSearchPage.setUsername("us").setLastname("ln").setEmail("email").setPassword("pass").submit();
		//validate if the page is loaded
		
		orderSummaryPage = OrderSummaryPage.init(driver);
		Assert.assertTrue(orderSummaryPage.isAt());
		
	}
	
}
