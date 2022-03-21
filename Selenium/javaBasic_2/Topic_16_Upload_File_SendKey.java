package javaBasic_2;

import java.io.File;
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


public class Topic_16_Upload_File_SendKey {
		
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	//Bien nay la dau' "/" trong file location
	String fileSaperate = File.separator;
	
	
	String apiImage = "api.jpg";
	String appiumImage = "appium.jpg";
	String seleniumImage = "selenium.jpg";
	
	String apiImageLocation = projectPath + fileSaperate + "Attachment" + fileSaperate + apiImage;
	String appiumImageLocation = projectPath + fileSaperate + "Attachment" + fileSaperate + appiumImage;
	String seleniumImageLocation = projectPath + fileSaperate + "Attachment" + fileSaperate + seleniumImage;
	
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.toUpperCase().startsWith("MAC")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/BrowserDrivers/geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "/BrowserDrivers/chromedriver.exe");
		}else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		
		
	}
	
	public void TC_01_Upload_SendKey_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		driver.findElement(uploadFileBy).sendKeys(apiImageLocation + "\n" + appiumImageLocation + "\n" + seleniumImageLocation);
		
		List<WebElement> startButtons = driver.findElements(By.xpath("//td//span[text()='Start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);	
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + apiImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + appiumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + seleniumImage + "']")).isDisplayed());
		
	}
	
	@Test
	public void TC_02_Upload_SendKey_Per_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		By uploadFileBy = By.xpath("//input[@type='file']");
		
		driver.findElement(uploadFileBy).sendKeys(apiImageLocation);
		sleepInSecond(1);
		driver.findElement(uploadFileBy).sendKeys(appiumImageLocation);
		sleepInSecond(1);
		driver.findElement(uploadFileBy).sendKeys(seleniumImageLocation);
		sleepInSecond(1);
		
		List<WebElement> startButtons = driver.findElements(By.xpath("//td//span[text()='Start']"));
		
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);	
		}

		
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + apiImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + appiumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p//a[@title='" + seleniumImage + "']")).isDisplayed());
		
	}
	
	public void sleepInSecond(long second) {

		try {
			Thread.sleep(3000 * second);
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
