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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		//Download WebDriver and Set path
		WebDriverManager.chromedriver().setup();
		
		//Launch Chrome browser
		ChromeDriver driver = new ChromeDriver();
		
		//Launch URL
		driver.get("https://www.nykaa.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//MAximize the browser
		driver.manage().window().maximize();
		
		//Mouse over on Brands and Search L'Oreal Paris
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		WebElement search = driver.findElement(By.linkText("L'Oreal Paris"));
		builder.moveToElement(search).click().perform();
		
		// Check the title contains L'Oreal Paris(Hint-GetTitle)
		System.out.println("Title of the page: "+driver.getTitle());
		
		//Click sort By and select customer top rated
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		
		//Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		WebElement ele1 = driver.findElement(By.xpath("//span[text()='Hair']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ele1)).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("(//div[@class='control-value'])[1]")).click();
		
		//Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		
		//check whether the Filter is applied with Shampoo
		if (driver.findElement(By.xpath("(//div[@class='css-1emjbq5'])[1]")).getText().contains("Shampoo")) {
			System.out.println("\nConfirmed Shampoo is applied in Filter");
		}else {
			System.out.println("\nNot Confirmed");
		}
		
		//Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.className("css-xrzmfa")).click();
		
		//GO to the new window and select size as 175ml
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		String win1 = list.get(1);
		driver.switchTo().window(win1);
		WebElement ele2 = driver.findElement(By.xpath("//select[@class='css-2t5nwu']"));
		Select select = new Select(ele2);
		select.selectByValue("0");
		
		//Print the MRP of the product
		String price = driver.findElement(By.xpath("(//span[@class='css-1jczs19'])[1]")).getText();
		System.out.println("\nPrice of Shampoo: "+price);
		
		//Click on ADD to BAG
		driver.findElement(By.xpath("(//span[text()='Add to Bag'])[1]")).click();
		
		//Go to Shopping Bag
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		
		//Print the Grand Total amount
		WebElement frame = driver.findElement(By.className("css-acpm4k"));
		driver.switchTo().frame(frame);
		String TotalPrice = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println("\nGrand Total: "+TotalPrice);
		
		//Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		//Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//Check if this grand total is the same in step 14
		if (driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText().contains(TotalPrice)) {
			System.out.println("\nYes, Grand total is equal to TotalPrice");
		}else {
			System.out.println("\nNo, Grand total is not equal to TotalPrice");
		}
		
		//Close all windows
		driver.quit();

	}

}
