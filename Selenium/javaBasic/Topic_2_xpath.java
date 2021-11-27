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
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_class() {
	driver.findElement(By.className("inputtext _55r1 _6luy")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_02_id() {
		// Login Page title
		driver.findElement(By.id("pass")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_xpath() {
		// Login form displayed
		driver.findElement(By.xpath("//input[@type='password']")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_PartialLinkText() {
		// Login form displayed
		driver.findElement(By.linkText("Forgotten")).click();
		sleepInSecond(3);
	}
	
	@Test
	public void TC_04_Name() {
		// Login form displayed
		driver.findElement(By.name("email")).sendKeys("abcxyz");
		sleepInSecond(3);
	}
	
	
	@Test
	public void TC_05_css() {
		// Login form displayed
		driver.findElement(By.cssSelector("#did_submit")).click();
		sleepInSecond(3);
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
