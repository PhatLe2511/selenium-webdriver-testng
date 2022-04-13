package javaBasic_2;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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


public class Topic_17_Wait_I_Condition_Status {
		
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	//Bien nay la dau' "/" trong file location
	
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.toUpperCase().startsWith("MAC")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/BrowserDrivers/geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		}
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		explicitWait = new WebDriverWait(driver, 20);
	}
	
	
		public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
		sleepInSecond(2);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']"))).sendKeys("thanhphat@gmail.com");
		
		Dimension confirmationEmail = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']"))).getSize();
		
		System.out.println(confirmationEmail.getHeight());		
		System.out.println(confirmationEmail.getWidth());		
		}
		
		public void TC_02_Invisible_in_DOM() {
			driver.get("https://www.facebook.com/");
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
			sleepInSecond(2);
			
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		}
		
		public void TC_02_Invisible_Not_in_DOM() {
			driver.get("https://www.facebook.com/");
				
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		}		
		
		public void TC_03_Presence_in_UI() {
			driver.get("https://www.facebook.com/");
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']"))).sendKeys("thanhphat@gmail.com");
			
			explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
			}		
		
		public void TC_03_Presence_not_in_UI() {
			driver.get("https://www.facebook.com/");
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
			
			explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
			}		
		
		public void TC_04_Staleness() {
			driver.get("https://www.facebook.com/");
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-testid='open-registration-form-button']"))).click();
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']"))).sendKeys("thanhphat@gmail.com");
			
			WebElement confirmationEmail = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
			
			explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img"))).click();
			
			explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmail));
		}		
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(1000 * second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
