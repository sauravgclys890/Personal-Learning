package com.automation.PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage extends Page{

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
    
    
	public ProductSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public static ProductSearchPage init(WebDriver driver) {
		return new ProductSearchPage(driver);
	}
	
	public ProductSearchPage setUsername(String username) {
		this.username.sendKeys(username);
		return this;
	}
	
	public ProductSearchPage setFirstname(String firstname) {
		this.firstName.sendKeys(firstname);
		return this;
	}
	
	public ProductSearchPage setLastname(String lastname) {
		this.lastName.sendKeys(lastname);
		return this;
	}
	
	public ProductSearchPage setEmail(String email) {
		this.email.sendKeys(email);
		return this;
	}
	
	public ProductSearchPage setPassword(String password) {
		this.password.sendKeys(password);
		return this;
	}
	
	public void submit() {
		submit.click();
	}
	@Override
	public boolean isAt() {
		return isAt(10, TimeUnit.SECONDS);
	}

}
