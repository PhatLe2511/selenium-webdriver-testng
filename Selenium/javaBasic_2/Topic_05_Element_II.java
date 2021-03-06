package javaBasic_2;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_II {
		
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		By firstNameTextbox = By.id("txtFirstname");
		By emailTextbox = By.id("txtEmail");
		By confirmEmailTextbox = By.id("txtCEmail");
		By passwordTextbox = By.id("txtPassword");
		By confirmPasswordTextbox = By.id("txtCPassword");
		By phoneTextbox = By.id("txtPhone");
		By registerButton = By.xpath("//div[@class='field_btn']/button");
		By errorFirstName = By.id("txtFirstname-error");
		By errorEmail = By.id("txtEmail-error");
		By errorConfirmEmail = By.id("txtCEmail-error");
		By errorPassword = By.id("txtPassword-error");
		By errorConfirmPassword = By.id("txtCPassword-error");
		By errorPhone = By.id("txtPhone-error");
		
	 
	@BeforeClass
	public void BeforeClass() {
		 
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	 
	}
	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Register_Empty_Data () {
		
		driver.findElement(firstNameTextbox).sendKeys("");
		driver.findElement(emailTextbox).sendKeys("");
		driver.findElement(confirmEmailTextbox).sendKeys("");
		driver.findElement(passwordTextbox).sendKeys("");
		driver.findElement(confirmPasswordTextbox).sendKeys("");
		driver.findElement(phoneTextbox).sendKeys("");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorFirstName).getText(), "Vui l??ng nh???p h??? t??n");
		Assert.assertEquals(driver.findElement(errorEmail).getText(), "Vui l??ng nh???p email");
		Assert.assertEquals(driver.findElement(errorConfirmEmail).getText(), "Vui l??ng nh???p l???i ?????a ch??? email");
		Assert.assertEquals(driver.findElement(errorPassword).getText(), "Vui l??ng nh???p m???t kh???u");
		Assert.assertEquals(driver.findElement(errorConfirmPassword).getText(), "Vui l??ng nh???p l???i m???t kh???u");
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "Vui l??ng nh???p s??? ??i???n tho???i.");
	}
	
	
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(firstNameTextbox).sendKeys("Le Thanh Phat");
		driver.findElement(emailTextbox).sendKeys("thanhphat@maii@");
		driver.findElement(confirmEmailTextbox).sendKeys("thanhphat@maii2@");
		driver.findElement(passwordTextbox).sendKeys("123456789");
		driver.findElement(confirmPasswordTextbox).sendKeys("123456789");
		driver.findElement(phoneTextbox).sendKeys("0355019946");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorEmail).getText(), "Vui l??ng nh???p email h???p l???");
		Assert.assertEquals(driver.findElement(errorConfirmEmail).getText(), "Vui l??ng nh???p email h???p l???");
		
	}
	
	@Test
	public void TC_03_Register_Incorrect_Conform_Email() {
		driver.findElement(firstNameTextbox).sendKeys("Le Thanh Phat");
		driver.findElement(emailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(confirmEmailTextbox).sendKeys("thanhphat@maill.com");
		driver.findElement(passwordTextbox).sendKeys("123456789");
		driver.findElement(confirmPasswordTextbox).sendKeys("123456789");
		driver.findElement(phoneTextbox).sendKeys("0355019946");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorConfirmEmail).getText(), "Email nh???p l???i kh??ng ????ng");
	}
	
	@Test
	public void TC_04_Register_6_Characters_Pasword() {
		driver.findElement(firstNameTextbox).sendKeys("Le Thanh Phat");
		driver.findElement(emailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(confirmEmailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(passwordTextbox).sendKeys("123");
		driver.findElement(confirmPasswordTextbox).sendKeys("123");
		driver.findElement(phoneTextbox).sendKeys("0355019946");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
		Assert.assertEquals(driver.findElement(errorConfirmPassword).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
	
	}
	
	@Test
	public void TC_05_Register_Incorrect_Confirm_Password() {
		driver.findElement(firstNameTextbox).sendKeys("Le Thanh Phat");
		driver.findElement(emailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(confirmEmailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(passwordTextbox).sendKeys("1234567");
		driver.findElement(confirmPasswordTextbox).sendKeys("12334643");
		driver.findElement(phoneTextbox).sendKeys("0355019946");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorConfirmPassword).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");
	}
	
	@Test
	public void TC_06_Register_Invalid_Phone_Number() {
		driver.findElement(firstNameTextbox).sendKeys("Le Thanh Phat");
		driver.findElement(emailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(confirmEmailTextbox).sendKeys("thanhphat@mail.com");
		driver.findElement(passwordTextbox).sendKeys("1234567");
		driver.findElement(confirmPasswordTextbox).sendKeys("1234567");
		driver.findElement(phoneTextbox).sendKeys("0968686");
		driver.findElement(registerButton).click();
		
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "S??? ??i???n tho???i ph???i t??? 10-11 s???.");
		
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys("68686");
		
		Assert.assertEquals(driver.findElement(errorPhone).getText(), "S??? ??i???n tho???i b???t ?????u b???ng: 09 - 03 - 012 - 016 - 018 - 019");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
		
	}
}
