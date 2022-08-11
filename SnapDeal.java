package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		//Download and set the path
		WebDriverManager.chromedriver().setup();
														
		//Launch the ChromeBrowser
		ChromeDriver driver = new ChromeDriver();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
														
		//Load the URL
		driver.get("https://www.snapdeal.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
														
		//Maximize the window
		driver.manage().window().maximize();
		
		//Go to Mens Fashion
		driver.findElement(By.xpath("//span[@class='catText']")).click();
		
		//Go to Sports Shoes
		driver.findElement(By.xpath("//span[@class='linkTest']")).click();
		
		//Get the count of the sports shoes
		String totalCount = driver.findElement(By.xpath("//div[@class='sub-cat-count ']")).getText();
		System.out.println("Count of sport shoes:"+totalCount);
		
		//Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		//Sort by Low to High
		WebElement E1 = driver.findElement(By.xpath("//div[@class='sort-selected']"));
		wait.until(ExpectedConditions.elementToBeClickable(E1)).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		
		//Check if the items displayed are sorted correctly
		System.out.println("\nSorted Items:\n");
		List<WebElement> priceList = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		try {
			for (WebElement webElement : priceList) {
				String	sortedList = webElement.getText();
			}
		} catch (StaleElementReferenceException e) {
			List<WebElement> li = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
			for (WebElement webElement : li) {
				String	sortedList = webElement.getText();
				System.out.println(sortedList);
			}
		}
		//Select the price range (900-1200)
		driver.findElement(By.className("input-filter")).clear();
		driver.findElement(By.className("input-filter")).sendKeys("400");
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow')]")).click();
		
		//Filter with color Black
		try {
			driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
		} catch (StaleElementReferenceException e) {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
		}
		
		//Verify the filters
		System.out.println("\nFilters applied:");
		String filter1 = driver.findElement(By.className("sort-selected")).getText();
		String filter2 = driver.findElement(By.xpath("//a[@class='clear-filter-pill']")).getText();
		String filter3 = driver.findElement(By.xpath("//a[@class='clear-filter-pill  ']")).getText();
		System.out.println(filter1);
		System.out.println(filter2);
		System.out.println(filter3);
		
		//Mouse Hover on first resulting Training shoes
		WebElement E3 = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(E3).perform();
		
		//click QuickView button
		driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]")).click();
		
		//Print the cost and the discount percentage
		String price = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		System.out.println("\nPrice of the shoe: "+price);
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("\nDiscount of the shoe: "+discount);
		
		//Take the snapshot of the shoe
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("Shoe.png");
		FileUtils.copyFile(source, destination);
		
		//Close the current window
		driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]")).click();
		
		//Close the main window
		driver.close();

	}

}