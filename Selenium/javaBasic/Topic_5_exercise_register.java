package javaBasic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_5_exercise_register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test 
		public void TC01_Register_01(){
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
			sleepInSecond(1);
			//kiểm tra hiển thị của error message họ và tên
			Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Vui lòng nhập họ tên']")).isDisplayed());
			//kiểm tra hiển thị của error message email
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(.,'địa chỉ email')]")).isDisplayed());
			//kiểm tra hiển thị của error message của nhập lại email
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'nhập lại địa chỉ email')]")).isDisplayed());
			//kiểm tra hiển thị của error message của password
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='field']/label[contains(text(),'Vui lòng nhập mật khẩu')]")).isDisplayed());
			//kiểm tra hiển thị của error message của nhạp lại password
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'nhập lại mật khẩu')]")).isDisplayed());
			//kiểm tra hiển thị của error message của điện thoại
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'số điện thoại')]")).isDisplayed());		
	}
	
	@Test
		public void TC02_Register_02() {
			driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Lê Thành Phát");
			//nhập email k hợp lệ vào ô email
			driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@");
			//nhập email k hợp lệ vào ô nhập lại email
			driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@");
			// nhập kí tự hợp lệ vào ô password		
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
			//nhập kí tự hợp lệ vào ô nhập lại password
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
			//nhập kí tự hợp lệ vào ô điện thoại
			driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987677785");
			//click vào ô đăng ký
			//driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
			//kiểm tra error message ở ô email
			sleepInSecond(2);
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Vui lòng nhập email hợp lệ')]")).isDisplayed());
			//kiểm tra error message ở ô nhập lại email
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Email nhập lại không đúng')]")).isDisplayed());
	}
	
	@Test
		public void TC03_Register_03() {
		//clear hết data các ô
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).clear();
	
		driver.findElement(By.xpath("//input[@id='txtEmail']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();
			
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		
		sleepInSecond(2);
		// nhập lại dữ liệu các ô
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Lê Thành Phát");
		
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987677785");
		
		//kiểm tra error message ở ô nhập lại email
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Email nhập lại không đúng')]")).isDisplayed());
		
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		sleepInSecond(3);
	}
	
	@Test
		public void TC04_Register_04() {
			driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
			
			driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
			
			driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123");
			
		//Kiểm tra error message ở 2 field password
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Nhập lại Mật khẩu')]/parent::div/preceding-sibling::div/label[contains(text(),'6 ký tự')]")).isDisplayed());
			
			Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']/parent::div/following-sibling::div/label[contains(text(),'6 ký tự')]")).isDisplayed());		
	}
	
	@Test
		public void TC05_Register_05() {
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234567");
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='field']/label[contains(text(),'không khớp')]")).isDisplayed());
	}
	
	@Test
	public void TC06_Register_06() {
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtEmail']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();
			
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Lê Thành Phát");
		
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='field']/label[contains(text(),'10-11 số.')]")).isDisplayed());
		
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("004627274");
		
		sleepInSecond(2);
		
		//div[@class='field']/label[contains(text(),'Số điện thoại bắt đầu bằng')]
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='field']/label[contains(text(),'Số điện thoại bắt đầu bằng')]")).isDisplayed());
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
