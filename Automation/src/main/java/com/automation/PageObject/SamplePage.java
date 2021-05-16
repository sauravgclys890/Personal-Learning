package com.automation.PageObject;


import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class SamplePage {
	
	private final WebDriver driver;

    @FindBy(css="div.button-box button.btn-info")
    private WebElement btnInfo;

    @FindBy(css="div.button-box button.btn-warning")
    private WebElement btnWarning;

    @FindBy(css="div.button-box button.btn-success")
    private WebElement btnSuccess;

    @FindBy(css="div.button-box button.btn-danger")
    private WebElement btnDanger;

    @FindBy(css="div.jq-icon-info")
    private WebElement infoAlert;

    @FindBy(css="div.jq-icon-warning")
    private WebElement warningAlert;

    @FindBy(css="div.jq-icon-success")
    private WebElement successAlert;

    @FindBy(css="div.jq-icon-error")
    private WebElement dangerAlert;

    public SamplePage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        this.driver.get("https://wrappixel.com/demos/admin-templates/admin-pro/main/ui-notification.html");
    }

    public boolean validateInfoAlert(){
        return this.validate(btnInfo,infoAlert);
    }

    public boolean validateWarningAlert(){
        return this.validate(btnWarning,warningAlert);
    }

    public boolean validateSuccessAlert(){
        return this.validate(btnSuccess, successAlert);
    }

    public boolean validateDangerAlert(){
        return this.validate(btnDanger, dangerAlert);
    }

    private boolean validate(WebElement button, WebElement notification) {
        button.click();
        boolean result = notification.isDisplayed();

        try{
            Awaitility.await()
                    .atMost(5, TimeUnit.SECONDS)
                    .until(() -> !notification.isDisplayed());
        }catch(Exception e){
            result = false;
        }
        return result;
    }

}
