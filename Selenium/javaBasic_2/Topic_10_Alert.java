package javaBasic_2;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	Alert alert;
	
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
	}
	
	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		By jsAlert = By.xpath("//button[text()='Click for JS Alert']");
		
		scrollToElement(jsAlert);
		
		driver.findElement(jsAlert).click();
		
		sleepInSecond(2);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}
	
	
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		By jsConfirm = By.xpath("//button[text()='Click for JS Confirm']");
		
		scrollToElement(jsConfirm);
		
		driver.findElement(jsConfirm).click();
		
		sleepInSecond(2);
		
		//khai bao bien alert va Switch to alert
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Nhan confirm tren alert
		alert.accept();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Ok");
		
		driver.findElement(jsConfirm).click();
		
		sleepInSecond(2);
		
		//Nhan cancel tren alert
		alert.dismiss();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}
	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		By jsPrompt = By.xpath("//button[text()='Click for JS Prompt']");
		
		String sendKeyToText = "Yeu Kieu Anh";
		
		scrollToElement(jsPrompt);
		
		driver.findElement(jsPrompt).click();
		
		alert = driver.switchTo().alert();
		
		alert.sendKeys(sendKeyToText);
		
		sleepInSecond(3);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + sendKeyToText);
		
		sleepInSecond(3);
		
		driver.findElement(jsPrompt).click();
		
		//Sendkeys to alert
		alert.sendKeys(sendKeyToText);
		
		sleepInSecond(3);
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: null");
	}
	
	
	public void TC_04_Authentication_Alert() {
		
		String username = "admin";
		
		String password = "admin";
		
		//dien username va pass word vao authencation alert
		
		//driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		
		sleepInSecond(2);
		
		//Kiem tra trang dang nhap thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(3000 * second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
		jsExecutor.executeScript("window.scrollBy(0,200)");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
