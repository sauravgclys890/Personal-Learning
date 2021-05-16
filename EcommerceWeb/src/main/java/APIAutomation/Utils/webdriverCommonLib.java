package APIAutomation.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webdriverCommonLib {

	private WebDriverWait wait;
	private WebDriver driver;
	public WebElement getVisibility(WebDriver driver, WebElement webElement, int timeout) {
		this.driver=driver;
		WebElement element = null;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element;
		}
	
	public boolean getVisibility(WebDriver driver, String xpath, int timeout) {
		this.driver=driver;
		Boolean element = false;
		wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
		return element;
		}
	public void clickElementWhenClickable(WebDriver driver,By locator, int timeout) {
		this.driver=driver;
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
}
