package stepDefinition;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {

	public static WebDriver driver;
		@Given("^User is on Home Page$")
		public void user_is_on_Home_Page() throws Throwable {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("https://www.demoqa.com/books");
	 }

		@When("^User Navigate to LogIn Page$")
		public void user_Navigate_to_LogIn_Page() throws Throwable {
		    driver.findElement(By.id("login")).click();
		    
		}

		/*@When("^And User enters \"(.*?)\" and \"(.*?)\"$")
		public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
			driver.findElement(By.id("userName")).sendKeys(username); 
	        driver.findElement(By.id("password")).sendKeys(password);
	        driver.findElement(By.id("login")).click(); 
		    
		}*/
		
		/*@When("^User enters Credentials to LogIn$")
		public void user_enters_Credentials_to_LogIn(DataTable usercredentials) throws Throwable {
		    //List<List<String>> data = usercredentials.raw();
			List<Map<String, String>> data =  usercredentials.asMaps(String.class, String.class);
			//List<Map<String,String>> data = usercredentials.asMaps(String.class,String.class);
		    driver.findElement(By.id("userName")).sendKeys(data.get(0).get("Username")); 
	        driver.findElement(By.id("password")).sendKeys(data.get(0).get("Password"));
	        driver.findElement(By.id("login")).click(); 
	        
	        for(Map<String, String> data1 : usercredentials.asMaps(String.class, String.class)) {
	        	driver.findElement(By.id("userName")).sendKeys(data1.get("Username")); 
		        driver.findElement(By.id("password")).sendKeys(data1.get("Password"));
		        driver.findElement(By.id("login")).click(); 
	        	
	        }
		    
		}*/
		
		@When("^User enters Credentials to LogIn$")
		public void user_enters_Credentials_to_LogIn(List<Credentials> usercredentials) throws Throwable {
		    
	        for(Credentials cred : usercredentials) {
	        	driver.findElement(By.id("userName")).sendKeys(cred.getUsername()); 
		        driver.findElement(By.id("password")).sendKeys(cred.getPassword());
		        driver.findElement(By.id("login")).click(); 
	        	
	        }
		    
		}


		@Then("^Message displayed Login Successfully$")
		public void message_displayed_Login_Successfully() throws Throwable {
		    
			  System.out.println("Login Successfully");
		}

		@When("^User LogOut from the Application$")
		public void user_LogOut_from_the_Application() throws Throwable {
		    
			driver.findElement (By.id("submit")).click();
		}

		@Then("^Message displayed LogOut Successfully$")
		public void message_displayed_LogOut_Successfully() throws Throwable {
			System.out.println("LogOut Successfully");
			driver.quit();
		   
		}


	}


