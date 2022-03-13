package javaBasic_2;

import java.util.List;
import java.util.Random;
import java.util.Set;
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

public class Topic_15_JavaScript_Executor {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Select select;
	
		@BeforeClass
		public void BeforeClass() {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			jsExecutor = (JavascriptExecutor) driver;
			driver.manage().window().maximize();
			explicitWait = new WebDriverWait(driver, 30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		}
		
		public void TC_01() {
			//Đến 1 trang web bất kì, tương tự nhưng driver.get
			navigateToUrlByJS("http://live.techpanda.org/");
			
			System.out.println(executeForBrowser("return document.domain"));
			
			//lay domain cua trang web va so sanh
			Assert.assertEquals(executeForBrowser("return document.domain"), "live.techpanda.org");
			
			System.out.println(executeForBrowser("return document.URL"));
			
			//lay url cua trang web va so sanh
			Assert.assertEquals(executeForBrowser("return document.URL"), "http://live.techpanda.org/");
			sleepInSecond(2);
			
			//highlight va nhan button
			hightlightElement("//a[text()='Mobile']");
			sleepInSecond(2);
			clickToElementByJS("//a[text()='Mobile']");
			sleepInSecond(2);
			
			hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
			clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
			
			//Kiem tra expected text nam trong inner text
			
			Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
			
			hightlightElement("//a[text()='Customer Service']");
			sleepInSecond(2);
			clickToElementByJS("//a[text()='Customer Service']");
			
			Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");
			
			//scroll o duoi element
			scrollToElementOnDown("//input[@id='newsletter']");
			sleepInSecond(2);
			hightlightElement("//input[@id='newsletter']");
			
			sendkeyToElementByJS("//input[@id='newsletter']", "automation1415787@gmail.com");
			
			hightlightElement("//button[@title='Subscribe']");
			sleepInSecond(2);
			clickToElementByJS("//button[@title='Subscribe']");
			sleepInSecond(2);
			
			Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
			
			navigateToUrlByJS("https://demo.guru99.com/v4/");
			sleepInSecond(2);
			
			
			System.out.println(executeForBrowser("return document.domain"));
			Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
		}
		
		public void TC_02() {
			navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			//in ra validation message
			System.out.println(getElementValidationMessage("//input[@name='fname']"));
			
			Assert.assertEquals(getElementValidationMessage("//input[@name='fname']"), "Please fill out this field.");
			
			sendkeyToElementByJS("//input[@name='fname']", "Thanh Phat");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(getElementValidationMessage("//input[@name='pass']"));
			
			Assert.assertEquals(getElementValidationMessage("//input[@name='pass']"), "Please fill out this field.");
			
			sendkeyToElementByJS("//input[@name='pass']", "12345678");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(getElementValidationMessage("//input[@name='em']"));
			
			Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please fill out this field.");
			
			sendkeyToElementByJS("//input[@name='em']", "123#@!!@$");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(getElementValidationMessage("//input[@name='em']"));
			
			//nếu driver la chrome thì so sánh với message này
			if(driver.toString().contains("chrome")) {
				Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "A part following '@' should not contain the symbol '!'.");
			//nếu driver là firefox thì so sánh với mesage này
			} else if(driver.toString().contains("firefox")) {
				Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please enter an email address.");
			}
			
			sendkeyToElementByJS("//input[@name='em']", "abc@abc");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(getElementValidationMessage("//input[@name='em']"));
			
			Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), "Please match the requested format.");
			
			sendkeyToElementByJS("//input[@name='em']", "thanhphat@hotmail.com");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(getElementValidationMessage("//li//select"));
			
			Assert.assertEquals(getElementValidationMessage("//li//select"), "Please select an item in the list.");
			
			select = new Select(driver.findElement(By.cssSelector("li select")));
			
			select.selectByVisibleText("DA NANG");
			
			clickToElementByJS("//input[@name='submit-btn']");
			sleepInSecond(1);
			
			System.out.println(executeForBrowser("return document.URL"));
			
			Assert.assertEquals(executeForBrowser("return document.URL"), "https://automationfc.github.io/");
		}
		
		public void TC_03() {
			navigateToUrlByJS("https://login.ubuntu.com/");
			sleepInSecond(2);
			
			By trackerPopupBy = By.cssSelector("div.p-modal__dialog");
			
			List<WebElement> trackerElement = driver.findElements(trackerPopupBy);
			
			if(trackerElement.size() > 0) {
				System.out.println("-----------Popup hien thi va dong-----------");
				
				clickToElementByJS("//button[@id='cookie-policy-button-accept']");
				
				Assert.assertEquals(driver.findElements(trackerPopupBy).size(), 0);
			}
			
			clickToElementByJS("//button[@data-qa-id='login_button']");
			sleepInSecond(2);
			
			Assert.assertEquals(getElementValidationMessage("//form[@name='loginform']//input[@name='email']"), "Please fill out this field.");
			
			sendkeyToElementByJS("//form[@name='loginform']//input[@name='email']", "31231@2@!#!");
			
			clickToElementByJS("//button[@data-qa-id='login_button']");
			sleepInSecond(2);
			
			Assert.assertEquals(getElementValidationMessage("//form[@name='loginform']//input[@name='email']"), "A part following '@' should not contain the symbol '@'.");
			
		}

		@Test
		public void TC_04_Remove_Attribute() {
			navigateToUrlByJS("https://demo.guru99.com/v4/");
			
			sendkeyToElementByJS("//input[@name='uid']", "mngr392123");
			
			sendkeyToElementByJS("//input[@name='password']", "jUzyhyr");
			
			clickToElementByJS("//input[@name='btnLogin']");
			sleepInSecond(2);
			
			clickToElementByJS("//a[text()='New Customer']");
			sleepInSecond(2);
			
			sendkeyToElementByJS("//input[@name='name']", "le thanh phat");
			//clickToElementByJS("//textarea[@name='addr']");
			//sendkeyToElementByJS("//textarea[@name='addr']", "65 chu van an");
			driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("65 chu van an\nHo Chi Minh");
			sendkeyToElementByJS("//input[@name='city']", "Ho Chi Minh");
			sendkeyToElementByJS("//input[@name='state']", "dssgsd");
			sendkeyToElementByJS("//input[@name='pinno']", "700000");
			sendkeyToElementByJS("//input[@name='telephoneno']", "052362727272");
			sendkeyToElementByJS("//input[@name='emailid']", "phatl@gmail.com");
			sendkeyToElementByJS("//input[@name='password']", "123456789");
			
			removeAttributeInDOM("//input[@name='dob']", "type");
			
			sendkeyToElementByJS("//input[@name='dob']", "25/11/1996");
			sleepInSecond(2);
			
			clickToElementByJS("//input[@type='submit']");
			sleepInSecond(2);
			
			Assert.assertEquals(driver.findElement(By.cssSelector("sleepInSecond(2);")).getText(), "Customer Registered Successfully!!!");
			
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
		
		public Object executeForBrowser(String javaScript) {
			return jsExecutor.executeScript(javaScript);
		}

		public String getInnerText() {
			return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
		}

		public boolean areExpectedTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		public void hightlightElement(String locator) {
			WebElement element = getElement(locator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
		}

		public void clickToElementByJS(String locator) {
			jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		}

		public void scrollToElementOnTop(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
		}

		public void scrollToElementOnDown(String locator) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
		}

		public void sendkeyToElementByJS(String locator, String value) {
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
		}

		public String getElementValidationMessage(String locator) {
			return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
		}

		public boolean isImageLoaded(String locator) {
			boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
			if (status) {
				return true;
			}
			return false;
		}

		public WebElement getElement(String locator) {
			return driver.findElement(By.xpath(locator));
		}
}
