package javaBasic_2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class Topic_05_Element_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, fullName, password, lastName, email;
	
	
	
	
	
	By myAccountLink = By.xpath("//div[@class='footer-container']//a[text()='My Account']");
	By emailTextbox = By.xpath("//div[@class='col-2 registered-users']//input[@type='email']");
	By passwordTextbox = By.xpath("//div[@class='col-2 registered-users']//input[@type='password']");
	By loginButton = By.xpath("//button[@title='Login']");
	
	@BeforeClass
	public void BeforeClass() {
		 
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		firstName = "Thanh";
		lastName = "Phat";
		email = "ThanhPhat" + getRandomNumber() + "@gmail.com";
		password = "123456789";
		fullName = firstName + " " + lastName;
		
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		driver.get("http://live.techpanda.org/");
	}
	
	@Test
	public void TC_01_Login_Empty_Data() {
		driver.findElement(myAccountLink).click();
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(passwordTextbox).sendKeys("");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(myAccountLink).click();
		driver.findElement(emailTextbox).sendKeys("thanhphat@1241");
		driver.findElement(passwordTextbox).sendKeys("123456");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Invalid_Password() {
		driver.findElement(myAccountLink).click();
		driver.findElement(emailTextbox).sendKeys("thanhphat@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Incorrect_Email_Password() {
		driver.findElement(myAccountLink).click();
		driver.findElement(emailTextbox).sendKeys("thanhphat@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123123123123");
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Create_New_Account() {
		driver.findElement(myAccountLink).click();
		driver.findElement(By.xpath("//div[@class='col2-set']//div[@class='buttons-set']/a")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::*/p")).getText();
		
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
		
		driver.findElement(By.cssSelector("div.account-cart-wrapper>a")).click();
		driver.findElement(By.xpath("//div[@class='skip-content skip-active']//a[@title='Log Out']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed());
		
	}
	
	@Test
	public void TC_06_Login_Valid_Password() {
		driver.findElement(myAccountLink).click();
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + fullName + "!");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::*/p")).getText();
		
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	
	}
		public int getRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(999);
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
