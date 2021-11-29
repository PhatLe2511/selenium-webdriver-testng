package javaBasic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_3_xpath_css {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_01_Computer() {
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_02_Desktop() {
		driver.findElement(By.xpath("//li[@class='active last']//li[1]/a")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_03_img() {
		driver.findElement(By.xpath("//div[@class='item-grid']//div[@data-productid='1']//img")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_04_RAM() {
		driver.findElement(By.xpath("//dd[@id='product_attribute_input_2']/select")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_05_selectRAM() {
		driver.findElement(By.xpath("//dd[@id='product_attribute_input_2']/select/option[@value='4']")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_06_selectHDD() {
		driver.findElement(By.xpath("//dd[@id='product_attribute_input_3']//li[2]/input")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_07_addToCart() {
		driver.findElement(By.id("add-to-cart-button-1")).click();
		sleepInSecond(2);
	}
	
	@Test
	public void TC_08_shoppingCart() {
		driver.findElement(By.xpath("//div[@id='bar-notification']//a")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_09_clearQuantity() {
		driver.findElement(By.xpath("//div[@class='table-wrapper']//td[@class='quantity']/input")).clear();
		sleepInSecond(1);
	}
	
	@Test
	public void TC_10_inputQuantity() {
		driver.findElement(By.xpath("//div[@class='table-wrapper']//td[@class='quantity']/input")).sendKeys("2");
		sleepInSecond(1);
	}
	
	@Test
	public void TC_11_removeShoppingCart() {
		driver.findElement(By.cssSelector("button[class='remove-btn']")).click();
		sleepInSecond(2);
	}
	
	@Test
	public void TC_13_checkDisplay() {
		driver.findElement(By.cssSelector("div[class='no-data']")).isDisplayed();
		sleepInSecond(2);
	}
	
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
