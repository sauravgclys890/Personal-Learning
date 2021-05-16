package com.automation.genriclib;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;


public class NotificationValidator extends ElementValidator {

	private final WebElement button;
    private final WebElement notification;
	public NotificationValidator(final WebElement button, final WebElement notification) {
		this.button= button;
		this.notification= notification;
		
	}
	@Override
	public boolean validate() {
		this.button.click();
		boolean result=this.notification.isDisplayed();
		
	Awaitility.await().atMost(5, TimeUnit.SECONDS).until(()-> !this.notification.isDisplayed());
	System.out.println(result && (!this.notification.isDisplayed()));
		
		return result && (!this.notification.isDisplayed());
	}

}
