package javaBasic;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_4_xpath_II {
String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/");
	}
	
	@Test
	public void TC_01_MobileClick() {
		driver.findElement(By.cssSelector("li[class='level0 nav-1 first']")).click();
		SleepInSecond(2);
	}
	
	
	@Test
		public void TC_02_Checkdisplay() {
		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='product-collection-image-2']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'$50')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='product-info'])[1]//div[@class='rating-box']")).isDisplayed());
		SleepInSecond(2);
	}
	
	@Test
	public void TC_03_AddtoCartClick() {
		driver.findElement(By.xpath("(//div[@class='product-info'])[1]//button")).click();
		driver.findElement(By.xpath("//td[@class='product-cart-actions']/input")).click();
		driver.findElement(By.xpath("//td[@class='product-cart-actions']/input")).clear();
		driver.findElement(By.xpath("//td[@class='product-cart-actions']/input")).sendKeys("5");
		driver.findElement(By.xpath("//button[@type='submit' and @title='Update']")).click();
		SleepInSecond(2);
		driver.findElement(By.xpath("//button[@id='empty_cart_button' or @class='button2 btn-empty']")).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
