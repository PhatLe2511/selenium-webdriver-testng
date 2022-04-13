package javaBasic_2;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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


public class Topic_17_Wait_II_Find_Element {
		
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	String apiImage = "api.jpg";
	String appiumImage = "appium.jpg";
	String seleniumImage = "selenium.jpg";
	String fileSeparate = File.separator;
	
	String apiImageLocation = projectPath + fileSeparate + "Attachment" + fileSeparate + apiImage;
	String appiumImageLocation = projectPath + fileSeparate + "Attachment" + fileSeparate + appiumImage;
	String seleniumImageLocation = projectPath + fileSeparate + "Attachment" + fileSeparate + seleniumImage;
	
	
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 5);
		
		
	}
	
	
	public void TC_01_Wait_Result_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	public void TC_02_Dead_Wait_Result_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		sleepInSecond(4);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
		
	public void TC_03_Explicit_Wait_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	public void TC_04_Explicit_Wait_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.cssSelector("div#start>button")).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4"))).isDisplayed();
		
		//Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}
	
	public void TC_05_Date_Picker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer"))).isDisplayed();
		
		System.out.println(driver.findElement(By.cssSelector("div#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel>span")).getText());
		
		driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_RadCalendar1Panel']//a[text()='22']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,rcSelected)]//a[text()='22']"))).isDisplayed();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel>span")).getText(), "Tuesday, March 22, 2022");
	}
	
	public void TC_06_Upload_file() {
		driver.get("https://gofile.io/uploadFiles");
		
		WebElement uploadButton = driver.findElement(By.xpath("//input[@type='file']"));
		
		uploadButton.sendKeys(apiImageLocation + "\n" + appiumImageLocation + "\n" + seleniumImageLocation);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success>h5")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.callout-success>h5")).isDisplayed());
		
		
		String downloadLink = driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).getAttribute("href");
		
		driver.get(downloadLink);
			
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='" + apiImage + "']")));
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//a/span[text()='" + apiImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a/span[text()='" + appiumImage + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a/span[text()='" + seleniumImage + "']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + apiImage + "']/parent::a/parent::div/following-sibling::div/a")).isDisplayed());		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + appiumImage + "']/parent::a/parent::div/following-sibling::div/a")).isDisplayed());		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + seleniumImage + "']/parent::a/parent::div/following-sibling::div/a")).isDisplayed());		
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + apiImage + "']/parent::a/parent::div/following-sibling::div/button//span[text()='Play']")).isDisplayed());		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + appiumImage + "']/parent::a/parent::div/following-sibling::div/button//span[text()='Play']")).isDisplayed());		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + seleniumImage + "']/parent::a/parent::div/following-sibling::div/button//span[text()='Play']")).isDisplayed());		
			
	}
	
	public void getTitle (String expectedTitle) {
		Set<String> pageTitles = driver.getWindowHandles();
		
		for (String page : pageTitles) {
			driver.switchTo().window(page);
			
			String actualTitle = driver.getTitle();
			
			if (actualTitle.equals(expectedTitle)) {
				
				break;
			}
		}
	}
	
 	public void sleepInSecond(long second) {
		try {
			Thread.sleep(1000 * second);
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
