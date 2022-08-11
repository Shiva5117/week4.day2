package week4.day2;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class AdministratorCertifications {

	public static void main(String[] args) {
		//Download and set the path
		WebDriverManager.chromedriver().setup();
														
		//Launch the ChromeBrowser
		ChromeDriver driver = new ChromeDriver();
														
		//Load the URL
		driver.get("https://login.salesforce.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
														
		//Maximize the window
		driver.manage().window().maximize();
		
		//Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		
		//Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");
		
		//click on the login button
		driver.findElement(By.id("Login")).click();
		
		//click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//Switch to the next window using Window handles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		//click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//Create Shadow
		Shadow dom = new Shadow(driver);
		
		//Click Resources and mouse hover on Learning On Trail head
		dom.findElementByXPath("//span[text()='Learning']").click();
		dom.findElementByXPath("//span[text()='Learning on Trailhead']").click();
		
		//Click on Sales force Certifications
		Actions builder = new Actions(driver);
		WebElement ele = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		builder.moveToElement(ele).click().perform();
		
		//Click on Certification Administrator
		driver.findElement(By.linkText("Administrator")).click();
		
		//Navigate to Certification - Administrator Overview window
		driver.findElement(By.linkText("Credentials")).click();
		
		//Verify the Certifications available for Administrator Certifications should be displayed
		List<WebElement> certifications = driver.findElements(By.className("credentials-card_title"));
		for (WebElement webElement : certifications) {
			System.out.println(webElement.getText());
		}
		

	}

}
