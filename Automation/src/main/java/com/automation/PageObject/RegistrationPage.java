package com.automation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	private final WebDriver driver;
	
	 @FindBy(name = "firstName")
	    private WebElement firstName;

	    @FindBy(name = "lastName")
	    private WebElement lastName;

	    @FindBy(name = "email")
	    private WebElement userName;

	    @FindBy(name = "password")
	    private WebElement password;

	    @FindBy(name = "confirmPassword")
	    private WebElement confirmPassword;

	    @FindBy(name = "register")
	    private WebElement submit;

	    private RegistrationPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    public static RegistrationPage using(WebDriver driver) {
	    	return new RegistrationPage(driver);
	    }
	    
	    public RegistrationPage launch() {
	    	driver.get("http://newtours.demoaut.com/mercuryregister.php");
	    	return this;
	    }
	    
	    public RegistrationPage setFirstName(String fname) {
	    	firstName.sendKeys(fname);
	    	return this;
	    }
	    public RegistrationPage setLastName(String lname) {
	    	lastName.sendKeys(lname);
	    	return this;
	    }
	    public RegistrationPage setUserName(String username) {
	    	userName.sendKeys(username);
	    	return this;
	    }
	    
	    public RegistrationPage setPassword(String password) {
	    	this.password.sendKeys(password);
	    	return this;
	    }
	    
	    public RegistrationPage setConfirmPassword(String confirmPassword) {
	    	this.confirmPassword.sendKeys(confirmPassword);
	    	return this;
	    }
	    
	    public void submit() {
	    	this.submit.click();
	    }

}
