package TestAutomation.Automation;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestFlipKart {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://flipkart.com");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.name("q")).sendKeys("mobiles", Keys.ENTER);
		
		String phoneName =driver.findElement(By.xpath("//div[text()='POCO X3 (Cobalt Blue, 128 GB)']")).getText();
		
		System.out.println(phoneName);
		
		List<WebElement> list = driver.findElements(By.xpath("//span[text()='Add to Compare']"));
		
		String res =driver.findElement(By.xpath("//span[contains(text(), 'Showing')]")).getText();
		
		String[] array = res.split(" ");
		
		for(int i=0; i<array.length; i++) {
			
			if(Integer.parseInt(array[i]) == list.size()) {
				System.out.println("Total number of result displayed "+ list.size());
			}
		}
	}

}
