package javaBasic_2;


import java.util.List;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Actions {
	
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
	}
	
	@Test
	public void TC_01_Hover_I() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	@Test
	public void TC_02_Hover_II() {
		driver.get("https://www.myntra.com/");
		
		action = new Actions(driver);
		
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		
		
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
	}
	
	@Test
	public void TC_03_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		action = new Actions(driver);
		
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(2);
		
		List<WebElement> selectedAllNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(selectedAllNumbers.size(), 4);
	}
	
	@Test
	public void TC_04_ClickAndHold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		//Nhan nut control
		action.keyDown(Keys.CONTROL).perform();
		
		//Click cac so
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(10)).perform();
		
		//Tha nut control
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(2);
		
		List<WebElement> selectedAllNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(selectedAllNumbers.size(), 4);
	}
	
	@Test
	public void TC_05_double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		By doubleClickMe = By.xpath("//button[text()='Double click me']");
		
		scrollToElement(doubleClickMe);
		sleepInSecond(2);
		
		action.doubleClick(driver.findElement(doubleClickMe)).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	
	@Test
	public void TC_06_right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void TC_07_DragAndDrop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		String rgbaColor = bigCircle.getCssValue("background-color");
		
		System.out.println("RGBA: " + rgbaColor);
		
		String hexaColor = Color.fromString(rgbaColor).asHex().toLowerCase();
		
		System.out.println("Hexa: " + hexaColor);
		
		Assert.assertEquals(hexaColor, "#03a9f4");
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(3000 * second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrollToElement(By by) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
		jsExecutor.executeScript("window.scrollBy(0,200)");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
