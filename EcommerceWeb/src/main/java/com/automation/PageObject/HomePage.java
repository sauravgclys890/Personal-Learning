package com.automation.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import APIAutomation.Utils.webdriverCommonLib;

public class HomePage extends Page{

	private WebDriver driver;
	@FindBy(xpath="//ul[@id='home-page-tabs']/following-sibling::div[@class='tab-content']/ul[@id='homefeatured']/li/div[@class='product-container']/div[@class='right-block']/descendant::span[@class='price-percent-reduction']")
	private WebElement discountProce;
	
	@FindBy(xpath="//a[text()='Popular']")
	private  WebElement popularSection;
	
	@FindBy(xpath="//ul[@id='home-page-tabs']/following-sibling::div[@class='tab-content']/ul[@id='homefeatured']/li/div[@class='product-container']/div[@class='right-block']/descendant::div[@class='content_price']")
	private WebElement priceOfproductInPopularSection;
	
	@FindAll
	({
		@FindBy(xpath="//ul[@id='home-page-tabs']/following-sibling::div[@class='tab-content']/ul[@id='homefeatured']/li/div[@class='product-container']/div[@class='right-block']/descendant::div[@class='content_price']")
	    
	})
	private List<WebElement> listOfPriceOfproductInPopularSection;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public static HomePage init(WebDriver driver) {
		return new HomePage(driver);
	}
	
	public WebElement getdiscountProceElement() {
		return discountProce;
	}
	
	public WebElement getpopularSectionElement() {
		return popularSection;
	}
	
	public WebElement getpriceOfproductInPopularSectionElement() {
		return priceOfproductInPopularSection;
	}
	
	public List<WebElement> getlistOfPriceOfproductInPopularSectionElement() {
		return listOfPriceOfproductInPopularSection;
	}
	
	public void clickpopularSection() {
		popularSection.click();
	}

	@Override
	public boolean isAt() {
		return this.popularSection.isDisplayed();
		
	}
	
	public void clickAddtocartBasedOnprice(Double price, WebDriver driver) throws Exception {
		this.driver=driver;
		String xpath="//ul[@id='home-page-tabs']/following-sibling::div[@class='tab-content']/ul[@id='homefeatured']/li/div[@class='product-container']/div[@class='right-block']"
				+ "/descendant::span[contains(text(),'"+String.valueOf(price)+"')]/parent::div/following-sibling::div/descendant::span[text()='Add to cart']";
		
		String xpath1="//ul[@id='home-page-tabs']/following-sibling::div[@class='tab-content']/ul[@id='homefeatured']/li/div[@class='product-container']"
				+ "/div[@class='left-block']/descendant::span[contains(text(),'"+String.valueOf(price)+"')]/parent::div/parent::div/descendant::img";
		
		System.out.println(xpath);
		Actions act= new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(xpath1))).perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(xpath)));
		act.moveToElement(driver.findElement(By.xpath(xpath))).click().perform();
		}
	
	
}
