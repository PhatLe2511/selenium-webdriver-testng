package javaBasic_2;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_8_Custom_Dropdown {
		WebDriver driver;
		WebDriverWait explicitWait;
		JavascriptExecutor jsExecutor;
		String projectPath = System.getProperty("user.dir");
		
	@BeforeClass	
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		
	}
	
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");		
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "2");
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "16");
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		
		
	}
	
	
	public void CustomDropDownList(String parentLocator, String childLocator, String expectedText) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentLocator)));
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector(childLocator));
		
		for (WebElement number : allNumbers) {
			String actualText = number.getText();
			System.out.println("Actual Text: " + actualText);
			
			if (actualText.equals(expectedText)) {
				
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", number);
				
				SleepInSecond(3);
				
				number.click();
				
				SleepInSecond(3);
				
				break;
			}
		}
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
