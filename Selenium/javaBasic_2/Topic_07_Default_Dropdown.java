package javaBasic_2;


import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicListUI.ListSelectionHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	public void TC_01_Rode() {
		 
		driver.get("https://www.rode.com/wheretobuy");
		
		 select = new Select(driver.findElement(By.id("where_country")));
		 
		 Assert.assertFalse(select.isMultiple());
		 
		 select.selectByVisibleText("Vietnam");
		 
		 Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		 
		 SleepInSecond(5);
		 
		 driver.findElement(By.id("search_loc_submit")).click();
		 
		 SleepInSecond(3);
		 
		 Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "32");
		 
		 List<WebElement> storeName = driver.findElements(By.cssSelector("div#search_results div.store_name"));
		 
		 Assert.assertEquals(storeName.size(), 32);
		 
		 for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
	}
	
	@Test
	public void TC_02_Nopcommerce() {
		
		String firstName = "Le";
		String lastName = "Thanh Phat";
		String dateOfBirthDay = "25";
		String dateOfBirthMonth = "November";
		String dateOfBirthYear = "1996";
		String email = "thanhphat" + getRanomNumber() + "@gmail.com";
		String password = "thanhphat2511";
		
		
		
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.cssSelector("span.male>input")).click();
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(dateOfBirthDay);
		
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(dateOfBirthMonth);
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(dateOfBirthYear);
		
		
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		
		SleepInSecond(3);
		
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		
		SleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthDay);
		
		List<WebElement> birthDay = driver.findElements(By.cssSelector("select[name='DateOfBirthDay']>option"));
		
		Assert.assertEquals(birthDay.size(), 32);
		for (WebElement day : birthDay) {
			System.out.println(day.getText());
		}
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthMonth);
		
		List<WebElement> birthMonth = driver.findElements(By.cssSelector("select[name='DateOfBirthMonth']>option"));
		
		Assert.assertEquals(birthMonth.size(), 13);
		for (WebElement month : birthMonth) {
			System.out.println(month.getText());
		}
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthYear);
		
		List<WebElement> birthYear = driver.findElements(By.cssSelector("select[name='DateOfBirthYear']>option"));
		
		Assert.assertEquals(birthYear.size(), 112);
		for (WebElement year : birthYear) {
			System.out.println(year.getText());
		}
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	
	public int getRanomNumber() {
		Random random = new Random();
		return random.nextInt(999);
	}
	
	
	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
