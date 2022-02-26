package javaBasic_2;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_12_Handle_PopUp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopUpBy = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		Assert.assertFalse(driver.findElement(loginPopUpBy).isDisplayed());
		
		driver.findElement(By.cssSelector("div#button-login-dialog>button.login_")).click();
		
		Assert.assertTrue(driver.findElement(loginPopUpBy).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']/preceding-sibling::div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		
		Assert.assertFalse(driver.findElement(loginPopUpBy).isDisplayed());
	}
	
	public void TC_02_Fixed_Popup() {
		driver.get("https://bizbooks.vn/");
		
		By loginPopupBy = By.cssSelector("div#md-signin");
		
		By signUpPopupBy = By.cssSelector("div#md-signup");
		
		By alertMessageBy = By.cssSelector("div.ajax-response>span.text-danger");
		
		driver.findElement(By.xpath("//span[text()='ĐĂNG NHẬP']")).click();
		
		driver.findElement(By.xpath("//div[@class='h-dropdown__menu h-dropdown__menu--sm']/a[text()='Đăng nhập']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopupBy));
		
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(signUpPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("div#md-signin input[name='email']")).sendKeys("miaowkitty2511@gmail.com");
		
		driver.findElement(By.cssSelector("div#md-signin input[name='password']")).sendKeys("automsation123");
		
		driver.findElement(By.cssSelector("button.js-btn-member-login")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageBy));
		
		Assert.assertEquals(driver.findElement(alertMessageBy).getText(), "Tài khoản không tồn tại");
		
		driver.findElement(By.xpath("//div[@id='md-signin']//a[text()='Tạo tài khoản']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(signUpPopupBy));
		
		Assert.assertTrue(driver.findElement(signUpPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.xpath("//div[@id='md-signup']//a[text()='Tạo tài khoản']")).click();
		
		driver.findElement(By.xpath("//div[@id='md-signup']//a[text()='Đăng nhập']")).sendKeys(Keys.ESCAPE);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(signUpPopupBy));
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(signUpPopupBy).isDisplayed());
	}
	
	public void TC_03_Fixed_Popup() {
		driver.get("https://jtexpress.vn/");
		
		By jtExpressPopup = By.cssSelector("div.carousel-container");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(jtExpressPopup));
		
		Assert.assertTrue(driver.findElement(jtExpressPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.close")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(jtExpressPopup));
		
		Assert.assertFalse(driver.findElement(jtExpressPopup).isDisplayed());
	}
	
	public void TC_04_Random_Popup_In_DOM() {
		
	}
	
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
		jsExecutor.executeScript("window.scrollBy(0,200)");
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
