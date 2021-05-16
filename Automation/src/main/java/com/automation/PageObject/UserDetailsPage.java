package com.automation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserDetailsPage extends Page {
	
	private final WebDriver driver;
	
	@FindBy(name = "username")
	private WebElement username;
	
	@FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(name = "register")
    private WebElement submit;
    
    @FindBy(name = "DOB")
    private WebElement DOB;
    
    @FindBy(name ="address")
    private WebElement address;

	public UserDetailsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public static UserDetailsPage init(WebDriver driver) {
		return new UserDetailsPage(driver);
	}
	
	public UserDetailsPage launch() {
		driver.get("");
		return this;
	}

	@Override
	public boolean isAt() {
		return this.username.isDisplayed();
	}
	
	public UserDetailsPage setFirstname(String firstName) {
		this.firstName.sendKeys(firstName);
		return this;
	}
	
	public UserDetailsPage setLastName(String lastName) {
	     this.lastName.sendKeys(lastName);
	     return this;
	}
	
	public UserDetailsPage setDOB(String DOB) {
		this.DOB.sendKeys(DOB);
		return this;
	}
	
	public UserDetailsPage setEmail(String email) {
		this.email.sendKeys(email);
		return this;
	}
	
	public UserDetailsPage setAddress(String address) {
	   this.address.sendKeys(address);
       return this;
	}
	
	public void submit() {
		submit.click();
	}
	

}
