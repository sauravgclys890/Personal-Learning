package com.automation.PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrderSummaryPage extends Page{
	
	public OrderSummaryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    public static OrderSummaryPage init(WebDriver driver) {
    	return new OrderSummaryPage(driver);
    }
	@Override
	public boolean isAt() {
		return isAt(10, TimeUnit.SECONDS);
	}

}
