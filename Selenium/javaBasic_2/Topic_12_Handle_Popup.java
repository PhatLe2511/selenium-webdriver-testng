package javaBasic_2;

import java.util.List;
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
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Handle_Popup {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor jsExecutor;
	Alert alert;
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	

	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		By loginPopupBy = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		By alertMessageBy = By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']/preceding-sibling::div[@class='row error-login-panel']");
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("div#button-login-dialog>button.login_")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopupBy));
		
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(alertMessageBy));
		
		Assert.assertEquals(driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']/preceding-sibling::div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loginPopupBy));
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
			
	}
	
	public void TC_02_Fixed_Popup() {
		driver.get("https://bizbooks.vn/");
		
		By loginPopupBy = By.cssSelector("div#md-signin div.md-sign__content");
		
		By signupPopupBy = By.cssSelector("div#md-signup div.md-sign__content");
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(signupPopupBy).isDisplayed());
		
		driver.findElement(By.xpath("//a//span[text()='ĐĂNG NHẬP']")).click();
		
		driver.findElement(By.xpath("//div[@class='h-dropdown__menu h-dropdown__menu--sm']//a[text()='Đăng nhập']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(loginPopupBy));
		
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(signupPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("div#md-signin input[name='email']")).sendKeys("phatl2511@gmail.com");
		
		driver.findElement(By.cssSelector("div#md-signin input[name='password']")).sendKeys("phatl2511");
		
		driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
		
		//Assert.assertEquals(driver.findElement(By.cssSelector("div#md-signin span.text-danger")).getText(), "Tài khoản không tồn tại");
		
		driver.findElement(By.xpath("//div[@id='md-signin']//a[text()='Tạo tài khoản']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(signupPopupBy));
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertTrue(driver.findElement(signupPopupBy).isDisplayed());
		
//		driver.findElement(By.xpath("//div[@id='md-signup']//a[text()='Đăng nhập']")).click();
		
		driver.findElement(By.cssSelector("div#md-signup input[name='email']")).sendKeys(Keys.ESCAPE);
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(signupPopupBy));
		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		Assert.assertFalse(driver.findElement(signupPopupBy).isDisplayed());
		
	}
	
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
		By mailPopupBy = By.cssSelector("div.mailch-wrap");
				
		//Nếu xuất hiện - đóng và qua step 3
		if (driver.findElement(mailPopupBy).isDisplayed()) {
			System.out.println("------Popup hien thi va dong");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(mailPopupBy).isDisplayed());
		}
		
		Assert.assertTrue(isPageLoadedSuccess(driver));
		
		//input vao search field khi popup da bien mat
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("selenium");
		
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		sleepInSecond(3);
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		System.out.println("All post titles: " + postTitles.size());
		
		for (WebElement postTitle : postTitles) {
			Assert.assertTrue(postTitle.getText().contains("Selenium"));
		}
		
		
		
	}
	
	public void TC_04_Random_Popup_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		
		By vnkPopupBy = By.cssSelector("div#tve_editor div[data-style='cb_style_7']>div.tve-content-box-background");
		
		Assert.assertTrue(isPageLoadedSuccess(driver));
		
		if(driver.findElement(vnkPopupBy).isDisplayed()) {
			System.out.println("----Popup hien va bien mat----");
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(vnkPopupBy).isDisplayed());
		}
		
		driver.findElement(By.cssSelector("button.btn-danger"));
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		
	}
	
	public void TC_05_Random_Popup_In_DOM() {
		driver.get("https://kynaforkids.vn/");
		
		By kynaPopupBy = By.cssSelector("div.modal-body>a");
		
		Assert.assertTrue(isPageLoadedSuccess(driver));
		
		if(driver.findElement(kynaPopupBy).isDisplayed()) {
			System.out.println("----Popup hien va bien mat----");
			driver.findElement(By.cssSelector("div#popup-banner img.close-popup")).click();
			sleepInSecond(3);
			Assert.assertFalse(driver.findElement(kynaPopupBy).isDisplayed());
		}
		
		
			
	}
	
	public void TC_06_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		
		By vnkPopupBy = By.cssSelector("div.popup-content");
		
		List<WebElement> vnkPopupElement = driver.findElements(vnkPopupBy);
		
		if (vnkPopupElement.size() > 0) {
			System.out.println("-----Popup hien thi va dong-----");
			driver.findElement(By.cssSelector("button.close")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElements(vnkPopupBy).size(), 0);
		}
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
	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 130);
		ExpectedCondition<Boolean> jqueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jqueryLoad);
	}
}
