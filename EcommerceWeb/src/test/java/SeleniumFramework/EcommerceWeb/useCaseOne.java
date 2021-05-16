package SeleniumFramework.EcommerceWeb;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.PageObject.HomePage;
import com.automation.genriclib.BaseClass;
import com.relevantcodes.extentreports.LogStatus;

import APIAutomation.Utils.webdriverCommonLib;

public class useCaseOne extends BaseClass{
	
	private HomePage homepg;
	private List<WebElement> list;
	private Map<Double, WebElement> map= new HashedMap();
	webdriverCommonLib web= new webdriverCommonLib();
	@Test
	public void checkLowestPriceAndProductIncart() {
		
		test.log(LogStatus.INFO, "check Lowest price and add product in cart is started");
		homepg= HomePage.init(driver);
		System.out.println(homepg);
		Assert.assertTrue(homepg.isAt());
		list=homepg.getlistOfPriceOfproductInPopularSectionElement();
		Double lowestprice=Double.valueOf(list.get(0).getText().replace("$", ""));
		System.out.println(lowestprice);
		for(WebElement web: list) {
			String[] prices= web.getText().replace("$", "").split(" ");
			if(Double.valueOf(prices[0])< lowestprice) {
			  lowestprice= Double.valueOf(prices[0]);
			  map.put(lowestprice, web);
			}
		}
		System.out.println(lowestprice);
		
		System.out.println(map);
			
		try {
			homepg.clickAddtocartBasedOnprice(lowestprice, driver);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}
