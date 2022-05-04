package javaBasic_2;

import java.util.List;
import java.util.Random;
import java.util.Set;
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

public class Topic_14_Handle_Windows_Tabs {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
		@BeforeClass
		public void BeforeClass() {
			System.setProperty("webdriver.chrome.driver", projectPath + "browserDrivers/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			explicitWait = new WebDriverWait(driver, 30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}
		
		
		public void TC_01_Handle_Tabs_Windows_I(){
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			
			getTabTitle("Google");
			
			System.out.println(driver.getCurrentUrl());
			
			System.out.println(driver.getTitle());
			sleepInSecond(2);
			
			driver.close();
			
			getTabTitle("SELENIUM WEBDRIVER FORM DEMO");
			
			driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
			
			getTabTitle("Facebook – log in or sign up");
			
			System.out.println(driver.getCurrentUrl());
			
			System.out.println(driver.getTitle());
			sleepInSecond(2);
			
			driver.close();
			
			getTabTitle("SELENIUM WEBDRIVER FORM DEMO");
			
			driver.findElement(By.xpath("//a[text()='TIKI']")).click();
			
			getTabTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
			
			System.out.println(driver.getCurrentUrl());
			
			System.out.println(driver.getTitle());
			
			Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
			sleepInSecond(2);
			
			driver.close();
			
			getTabTitle("SELENIUM WEBDRIVER FORM DEMO");
			sleepInSecond(2);
		}
		
		
		public void TC_02_Handle_Tabs_Windows_II() {
			driver.get("http://live.techpanda.org/");
			
			driver.findElement(By.xpath("//a[text()='Mobile']")).click();
			
			driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
			
			driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
			
			Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");
			
			driver.findElement(By.xpath("//div[@class='actions']//span[text()='Compare']")).click();
			sleepInSecond(2);
			
			getTabTitle("Products Comparison List - Magento Commerce");
			
			System.out.println(driver.findElement(By.tagName("h1")).getText());
			
			Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "COMPARE PRODUCTS");
			
			driver.findElement(By.cssSelector("button[title='Close Window']")).click();
			sleepInSecond(2);
			
			getTabTitle("Mobile");
			
			Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");
			
		}
		
		@Test
		public void TC_03_Handle_Tabs_Windows_III() {
			driver.get("https://dictionary.cambridge.org/vi/");
			
			driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
			
			getTabTitle("Login");
			
			driver.findElement(By.cssSelector("input[value='Log in']")).click();		
		}
			
		
		
		public void getTabTitle(String expectedTitle) {
			//Lấy hết tất cả tab có trong windows
			Set<String> allTabTitle = driver.getWindowHandles();
			
			for (String tabTitle : allTabTitle) {
				driver.switchTo().window(tabTitle);
				
				String actualTitle = driver.getTitle();
				
				if (actualTitle.equals(expectedTitle)) {
					//Thoát  khỏi vong lặp
					
					break;
				}
			}
			
		}
		
		public void switchToWindowByID(String expectedID) {
			Set<String> allTabIDs = driver.getWindowHandles();
			
			for (String id : allTabIDs) {
				if (!id.equals(expectedID)) {
					driver.switchTo().window(id);
					break;
				}
			}
		}
		
		public boolean closeAllWindowsWithoutParent(String ParentID) {
			Set<String> allWindows = driver.getWindowHandles();
			
			for (String runWindows : allWindows) {
				if (!runWindows.equals(ParentID)) {
					driver.switchTo().window(runWindows);
					driver.close();
				}
			}
			driver.switchTo().window(ParentID);
			if(driver.getWindowHandles().size() == 1)
				return true;
			else 
				return false;
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
}
