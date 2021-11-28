package javaBasic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_2_xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://facebook.com/");
	}

	@Test
	public void TC_01_xpath() {
	driver.findElement(By.xpath("//input[@type='text']")).sendKeys("thanhphat2511");
		sleepInSecond(1);
	}

	@Test
	public void TC_02_name() {
		// Login Page title
		driver.findElement(By.name("pass")).sendKeys("abcxyz");
		sleepInSecond(1);
	}
	
	@Test
	public void  TC_03_css() {
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void  TC_04_linkText() {
		driver.findElement(By.linkText("Forgotten password?")).click();
		sleepInSecond(1);
	}
	
	@Test
	public void  TC_05_PartiallinkText() {
		driver.findElement(By.partialLinkText("Tiếng")).click();
		sleepInSecond(1);
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
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
