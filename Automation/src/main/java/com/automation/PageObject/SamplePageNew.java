package com.automation.PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.genriclib.ElementValidator;
import com.automation.genriclib.NotificationValidator;

public class SamplePageNew {
	
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

    public SamplePageNew(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goTo(){
        this.driver.get("https://wrappixel.com/demos/admin-templates/admin-pro/main/ui-notification.html");
    }

public List<ElementValidator> getElementValidator(){
	List<ElementValidator> list= new ArrayList<ElementValidator>();
	
	list.add(new NotificationValidator(btnInfo, infoAlert));
	list.add(new NotificationValidator(btnWarning,warningAlert));
    list.add(new NotificationValidator(btnSuccess, successAlert));
    list.add(new NotificationValidator(btnDanger, dangerAlert));
	
	return list;
	
}

}
