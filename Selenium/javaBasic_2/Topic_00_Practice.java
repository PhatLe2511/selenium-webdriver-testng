package javaBasic_2;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Practice {
		WebDriver driver;
		WebDriverWait explicitWait;
		JavascriptExecutor jsExecutor;
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
		Actions action;
		String fileSaperate = File.separator;
		Select select;
		
		String firstName = "Thanh Phat";
		String lastName = "LE";
		String editFirstName = "Man Nhi";
		String editLastName = "LE";
		String dateBirthDay = "25";
		String monthBirthDay = "November";
		String yearBirthDay = "1996";
		String email =  "thanhphat" + getRandomNumber() + "@gmail.com";
		String password = "123456789a";
		
		String apiImage = "api.jpg";
		String apiImageLocation =  projectPath + fileSaperate + "Attachment" + fileSaperate + apiImage;
		
		
		
		
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
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	

	public void TC_01() {
		
				driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
				
				driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
				driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
				driver.findElement(By.cssSelector("input#btnLogin")).click();
				
				action.moveToElement(driver.findElement(By.xpath("//b[text()='PIM']"))).perform();
				SleepInSecond(2);
				
				driver.findElement(By.id("menu_pim_addEmployee")).click();
				driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
				driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
				
				driver.findElement(By.cssSelector("input[type='file']")).sendKeys(apiImageLocation);
				SleepInSecond(2);
				
				String employeeID = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
				
				driver.findElement(By.cssSelector("input#btnSave")).click();
				
				SleepInSecond(2);
				
				Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
				Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
				Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
				
				Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), firstName);
				Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), lastName);
				Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeID);
				
				driver.findElement(By.cssSelector("input#btnSave")).click();
				
				Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
				Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
//				Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
				
				driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
				driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
				driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
				driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);
				
				driver.findElement(By.cssSelector("input#btnSave")).click();
				
				Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
				Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
				
				Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
				Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), editLastName);
				SleepInSecond(2);
		}
	

	public void TC_02() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.id("where_country")));
		
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Vietnam");
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		driver.findElement(By.id("search_loc_submit")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "33");
		
		List<WebElement> storeNames = driver.findElements(By.cssSelector("div#search_results div.store_name"));
		
		System.out.println(storeNames.size());
		
		for (WebElement store : storeNames) {
			Assert.assertEquals(storeNames.size(), 33);
			System.out.println(store.getText());
		}
		
	}
	
	public void TC_03() {
		driver.get("https://demo.nopcommerce.com/register");
		
		driver.findElement(By.cssSelector("input#gender-male")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(dateBirthDay);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(monthBirthDay);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(yearBirthDay);
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		SleepInSecond(2);
		
		
		List<WebElement> dateBirthDay =  driver.findElements(By.cssSelector("select[name='DateOfBirthDay']>option"));
		
		Assert.assertEquals(dateBirthDay.size(), 32);
		
		for (WebElement day : dateBirthDay) {
			System.out.println(dateBirthDay.size());
		}
		
		List<WebElement> monthBirthDay =  driver.findElements(By.cssSelector("select[name='DateOfBirthMonth']>option"));
		
		Assert.assertEquals(monthBirthDay.size(), 13);
		
		for (WebElement month : monthBirthDay) {
			System.out.println(monthBirthDay.size());
		}
		
		List<WebElement> yearBirthDay =  driver.findElements(By.cssSelector("select[name='DateOfBirthYear']>option"));
		
		Assert.assertEquals(yearBirthDay.size(), 112);
		
		for (WebElement month : yearBirthDay) {
			System.out.println(yearBirthDay.size());
		}
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/");
	}
	
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
		jsExecutor.executeScript("window.scrollBy(0,200)");
	}
	
	@AfterClass
	public void AfterClass() {
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
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	
}
