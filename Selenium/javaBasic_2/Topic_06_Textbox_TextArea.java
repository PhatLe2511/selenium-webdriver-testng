package javaBasic_2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, editFirstName, editLastName;

	
	@BeforeClass
	public void BeforeTest() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
		firstName = "Thanh";
		lastName ="Phat";
		editFirstName = "Steve";
		editLastName = "Job";
	}
	
	public void TC_01_TextArea_Textbox() {
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		
		String employeeID = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		driver.findElement(By.id("btnSave")).click();
		
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value"), employeeID);
		
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
		
		driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']//input[@id='btnSave']")).click();
		
		driver.findElement(By.id("personal_txtEmpFirstName")).clear();
		driver.findElement(By.id("personal_txtEmpFirstName")).sendKeys(editFirstName);
		
		SleepInSecond(3);
		driver.findElement(By.id("personal_txtEmpLastName")).clear();
		driver.findElement(By.id("personal_txtEmpLastName")).sendKeys(editLastName);
		
		SleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		
		driver.findElement(By.xpath("//form[@id='frmEmpPersonalDetails']//input[@id='btnSave']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value"), editLastName);
		
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.id("personal_txtEmployeeId")).isEnabled());
	}

	@AfterClass

	public void AfterClass(){

		driver.quit();
	
		
	}	
	
	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRanDom() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
}
