package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class ArchitectCertifications {

	public static void main(String[] args) {
		//Download and set the path
		WebDriverManager.chromedriver().setup();
		
		//Launch the ChromeBrowse
		ChromeDriver driver = new ChromeDriver();
		
		//Launch the URL
		driver.get("https://login.salesforce.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Maximize the browser
		driver.manage().window().maximize();
		
		//Login to the webpage
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123"+Keys.ENTER);
		
		//Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		
		//Switch to next page
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		String parentWindow = windows.get(0);
		String childWindow = windows.get(1);
		driver.switchTo().window(childWindow);
		
		//Click on Learn More link in Mobile Publisher
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		//Create Shadow claass
		Shadow dom = new Shadow(driver);
		
		//Click  On Learning
		dom.findElementByXPath("//span[text()='Learning']").click();
		
		//Select SalesForce Certification Under Learnings
		dom.findElementByXPath("//span[text()='Learning on Trailhead']").click();
		WebElement element = dom.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();
		
		//Choose Your Role as Sales force Architect
		driver.findElement(By.xpath("//div[text()='Architect']")).click();
		
		//Get the Text(Summary) of Sales force Architect
		String textSummary = driver.findElement(By.xpath("//div[contains(text(),'expertise using the Salesforce platform')]")).getText();
		System.out.println("Salesforce Architect Summary:\n"+textSummary);
		
		//Get the List of Sales force Architect Certifications Available
		List<WebElement> certifications = driver.findElements(By.className("credentials-card_title"));
		System.out.println("\n\nCertifictions:\n");
		for (WebElement webElement : certifications) {
			System.out.println(webElement.getText());
		}
		
		//Click on Application Architect 
		driver.findElement(By.linkText("Application Architect")).click();
		
		//Get the List of Certifications available
		List<WebElement> certification = driver.findElements(By.className("credentials-card_title"));
		System.out.println("\n\nCertifictions in Salesforce Architect:\n");
		for (WebElement webElement : certification) {
			System.out.println(webElement.getText());
		}

	}

}
