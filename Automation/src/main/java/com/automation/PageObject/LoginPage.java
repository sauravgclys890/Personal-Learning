package com.automation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(name = "email")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "login")
    private WebElement loginBt;
    
    public LoginPage(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    }
    
    public static LoginPage using(WebDriver driver) {
    	return new LoginPage(driver);
    }
    
    public LoginPage setUsername(String username) {
    	this.userName.sendKeys(username);
    	return this;
    }
    
    public LoginPage setPassword(String password) {
    	this.password.sendKeys(password);
    	return this;
    }
    
    public void login() {
    	this.loginBt.click();
    }

}
