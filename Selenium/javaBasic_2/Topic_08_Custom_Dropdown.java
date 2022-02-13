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

public class Topic_08_Custom_Dropdown {
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
	
	
	public void TC_01_JQuery() {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");		
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "2");
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "16");
		
		CustomDropDownList("span#number-button>span.ui-selectmenu-icon", "ul#number-menu div", "19");
		
		CustomDropDownList("span#salutation-button>span.ui-selectmenu-icon", "div.ui-selectmenu-open li", "Dr.");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button")).getText(), "Dr.");
		}
	
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectInCustomDropDownList("div.ui", "div.visible.menu>div", "Stevie Feliciano");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui")).getText(), "Stevie Feliciano");
		
		
		selectInCustomDropDownList("div.ui", "div.visible.menu>div", "Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui")).getText(), "Justen Kitsune");
	}
	
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns");
		
		selectInCustomDropDownList("li.dropdown-toggle", "ul.dropdown-menu>li", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		
		selectInCustomDropDownList("li.dropdown-toggle", "ul.dropdown-menu>li", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		
		selectInCustomDropDownList("li.dropdown-toggle", "ul.dropdown-menu>li", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
	}
	
	public void TC_04_Angular_select() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		scrollToElement(By.xpath("//ng-select[@bindvalue='ethnicityCode']/ancestor::div[@class='col-lg-3 col-md-12']"));
		SleepInSecond(2);
		selectInCustomDropDownList("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Tỉnh Kiên Giang");
		SleepInSecond(3);
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] div.ng-value-container div.ng-star-inserted")).getText(), "Tỉnh Kiên Giang");
		
		scrollToElement(By.xpath("//ng-select[@bindvalue='districtCode']/ancestor::div[@class='col-lg-3 col-md-12']"));
		SleepInSecond(2);
		selectInCustomDropDownList("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Huyện U Minh Thượng");
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] div.ng-star-inserted")).getText(), "Huyện U Minh Thượng");
		
		scrollToElement(By.xpath("//ng-select[@bindvalue='wardCode']/ancestor::div[@class='col-lg-6 col-md-12']"));
		SleepInSecond(2);
		selectInCustomDropDownList("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label", "Xã Hoà Chánh");
//		Assert.assertEquals(driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] div.ng-star-inserted")).getText(), "Xã Hoà Chánh");
		
	}
	
	public void TC_05_Editable_Dropdownlist() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		enterToCustomDropDownList("div#default-place>input[type='text']", "div#default-place li", "Peugeot");
		
		enterToCustomDropDownList("div#slide-place>input[type='text']", "div#slide-place li", "Alfa Romeo");
		
		enterToCustomDropDownList("div#fade-place>input[type='text']", "div#fade-place li", "Audi");
	}
	
	public void TC_06_Angular_Enter() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		scrollToElement(By.xpath("//ng-select[@bindvalue='ethnicityCode']/ancestor::div[@class='col-lg-3 col-md-12']"));
		SleepInSecond(2);
		enterToCustomDropDownList("ng-select[bindvalue='provinceCode'] input", "div[role='option']>span.ng-option-label", "Tỉnh Quảng Ngãi");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[@class='ng-value-label ng-star-inserted']")).getText(), "Tỉnh Quảng Ngãi");
		
		scrollToElement(By.xpath("//ng-select[@bindvalue='ethnicityCode']/ancestor::div[@class='col-lg-3 col-md-12']"));
		SleepInSecond(2);
		enterToCustomDropDownList("ng-select[bindvalue='districtCode'] input", "div[role='option']>span.ng-option-label", "Thành phố Quảng Ngãi");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='districtCode']//span[@class='ng-value-label ng-star-inserted']")).getText(), "Thành phố Quảng Ngãi");
		
		enterToCustomDropDownList("ng-select[bindvalue='wardCode'] input", "div[role='option']>span.ng-option-label", "Phường Nghĩa Lộ");
		SleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='wardCode']//span[@class='ng-value-label ng-star-inserted']")).getText(), "Phường Nghĩa Lộ");
		
//		enterToCustomDropDownList(projectPath, projectPath, projectPath)
	}
	
	
	
	public void selectInCustomDropDownList(String parentLocator, String childLocator, String expectedText) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentLocator)));
		
		List<WebElement> allNumbers = driver.findElements(By.cssSelector(childLocator));
		
		for (WebElement number : allNumbers) {
			String actualText = number.getText();
			System.out.println("Actual Text: " + actualText);
			
			if (actualText.equals(expectedText)) {
				
//				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", number);
				
				SleepInSecond(3);
				
				number.click();
				
				SleepInSecond(3);
		
				break;
			}
		}
	}
	
	public void enterToCustomDropDownList(String parentLocator, String childLocator, String expectedText) {
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedText);
		SleepInSecond(2);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(parentLocator)));
		
		List<WebElement> allValues = driver.findElements(By.cssSelector(childLocator));
		
		for (WebElement value : allValues) {
			String actualText = value.getText();
			
			if (actualText.equals(expectedText)) {
				value.click();
				
				SleepInSecond(2);
				
				break;
			}
		}
	}
	
	public void CustomDropDownList(String parentLocator, String childLocator, String expectedText) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		List<WebElement> allValues = driver.findElements(By.cssSelector(childLocator));
		
		for (WebElement value : allValues) {
			String actualText = value.getText();
			System.out.println(actualText);
			
			if(actualText.equals(expectedText)) {
				SleepInSecond(3);
				
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", value);
				
				value.click();
				
				SleepInSecond(3);
		
				break;
			}	
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
