package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class Customer_Service_Options {

	public static void main(String[] args) {
		//Download WebDriver and Set path
		WebDriverManager.chromedriver().setup();
		
		//Launch Chrome browser
		ChromeDriver driver = new ChromeDriver();
		
		//Launch URL
		driver.get("https://login.salesforce.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//MAximize the browser
		driver.manage().window().maximize();
		
		//Login to the webpage
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123"+Keys.ENTER);
		
		//Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//Switch to next webpage
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		//Click on Confirm
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//Create Shadow class
		Shadow dom = new Shadow(driver);
		
		//Click on Products and Mousehover on Service 
		dom.findElementByXPath("//span[text()='Products']").click();
		dom.findElementByXPath("//span[text()='Service']").click();
		
		//Click on Customer Services
		dom.findElementByXPath("//a[text()='Customer Service']").click();
		
		//Get the names Of Services Available
		List<WebElement> services = driver.findElements(By.xpath("//ul[@class='page-list page-list-level-2']/li"));
		System.out.println("Services Available:\n");
		for (WebElement webElement : services) {
			System.out.println(webElement.getText());
		}
		
		//Verify the title
		if (driver.getTitle().contains("Customer Service Tools")) {
			System.out.println("\n\nPage is verified");
		}else {
			System.out.println("\n\nPage is not verified");
		}
	}

}
