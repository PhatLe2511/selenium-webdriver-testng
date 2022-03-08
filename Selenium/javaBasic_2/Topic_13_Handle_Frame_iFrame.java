package javaBasic_2;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Handle_Frame_iFrame {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
		@BeforeClass
		public void BeforeClass() {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			explicitWait = new WebDriverWait(driver, 30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}
	
	
		public void TC_01_iFrame() {
			driver.get("https://kyna.vn/");
			
			Assert.assertTrue(driver.findElement(By.cssSelector("div.face-content>iframe")).isDisplayed());
			
			//Chuyen qua iframe
			driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
			
			String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
			
			System.out.println(facebookLike);
			
			Assert.assertEquals(facebookLike, "167K likes");
			
			// Chuyen qua Element thuong(khong phai iframe)
			driver.switchTo().defaultContent();
			
			//Phai chuyen lai iframe de thao tac dc voi iframe
			driver.switchTo().frame(("cs_chat_iframe"));
			
			driver.findElement(By.cssSelector("div.button_bar")).click();
			sleepInSecond(2);
			
			driver.findElement(By.cssSelector("input[ng-model='login.username']")).sendKeys("Thanh Phat");
			
			driver.findElement(By.cssSelector("input[ng-model='login.phone']")).sendKeys("058585859");
			
			new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
			
			driver.findElement(By.cssSelector("textarea[ng-model='login.content']")).sendKeys("automation test");
			sleepInSecond(3);
			
			driver.switchTo().defaultContent();
			
			String keyword = "Excel";
			
			driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
			
			driver.findElement(By.cssSelector("button.search-button")).click();
			
			List<WebElement> courseExcel = driver.findElements(By.cssSelector("div.content>h4"));
			
			for (WebElement course : courseExcel) {
				System.out.println(course.getText().toLowerCase());
				Assert.assertTrue(course.getText().toLowerCase().contains(keyword.toLowerCase()));
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		public void TC_02_Frame() {
			driver.get("https://netbanking.hdfcbank.com/netbanking/");
			
			driver.switchTo().frame("login_page");
			
			driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("berlin123");
			
			driver.findElement(By.cssSelector("a.login-btn")).click();
			sleepInSecond(2);
			
			Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
			
			
		}
		
		@AfterClass
		public void AfterClass() {
			driver.quit();
		}
		
		public void sleepInSecond(long second) {
			try {
				Thread.sleep(3000 * second);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public int getRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(999);
		}
}
