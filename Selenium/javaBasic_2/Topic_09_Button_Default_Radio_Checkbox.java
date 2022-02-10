package javaBasic_2;


import java.util.List;
import java.util.Random;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Default_Radio_Checkbox {
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
		
	public void TC_01_Button() {
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-login")).click();
		
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("thanhphatle@gmail.com");
		
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		
		String loginButtonBackgroundColorRgb = driver.findElement(loginButtonBy).getCssValue("background-color");
		
		System.out.println("RGB color: " + loginButtonBackgroundColorRgb);
		
		//Verify RGB color
		
//		Assert.assertEquals(loginButtonBackgroundColorRgb, "rgba(201, 33, 39, 0.09)");
		
		//Convert to Hexa
		
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRgb).asHex();
		
		System.out.println("Hexa color: " + loginButtonBackgroundColorHexa);
		
		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(), "#C92127");
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-login")).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButtonBy));
		
		sleepInSecond(2);
		
		driver.findElement(loginButtonBy).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
		
	public void TC_02_Default_Radio() {
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		//Khai bao bien cac radio
		By twoDotZeroPetrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		
		By twoDotZeroPetrolParent = By.xpath("//label[text()='2.0 Petrol, 147kW']/parent::li");
		
		By threeDotSixPetrol = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		By oneDotSixDiesel = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::input");
		
		By oneDotSixDieselParent = By.xpath("//label[text()='2.0 Diesel, 103kW']/parent::li");
		
		//Click radio va verify
		
		Assert.assertFalse(driver.findElement(twoDotZeroPetrol).isSelected());
		
		scrollToElement(twoDotZeroPetrolParent);
		
		driver.findElement(twoDotZeroPetrol).click();
		
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(twoDotZeroPetrol).isSelected());
		
		Assert.assertFalse(driver.findElement(threeDotSixPetrol).isEnabled());
		
		Assert.assertFalse(driver.findElement(oneDotSixDiesel).isSelected());
		
		scrollToElement(oneDotSixDieselParent);
		
		sleepInSecond(2);
		
		driver.findElement(oneDotSixDiesel).click();
		
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(oneDotSixDiesel).isSelected());
		
		Assert.assertFalse(driver.findElement(twoDotZeroPetrol).isSelected());
		
	}
		
	public void TC_03_Default_Checkbox() {
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		//Khai bao bien
		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		
		By leatherTrimCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");
		
		By luggageCompartementCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
		
		By heatedFrontCheckbox = By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input");
		
		By airConditioningCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		
		By airConditioningCheckboxParent = By.xpath("//label[text()='Dual-zone air conditioning']/parent::li");
		
		By rainSensorCheckbox = By.xpath("//label[text()='Rain sensor']/preceding-sibling::input");
		
		By towBarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
		
		//Scroll den Air Conditioning
		scrollToElement(airConditioningCheckboxParent);
		
		//Check checkbox
		checkToCheckBox(luggageCompartementCheckbox);
		
		checkToCheckBox(heatedFrontCheckbox);
		
		checkToCheckBox(airConditioningCheckbox);
		
		checkToCheckBox(rainSensorCheckbox);
		
		
		sleepInSecond(2);
		
		//verify Checkbox is disabled
		
		Assert.assertTrue(isElementDisabled(leatherTrimCheckbox));
		
		Assert.assertTrue(isElementDisabled(towBarCheckbox));
		
		//Verify Checkbox is checked
		
		Assert.assertTrue(isElementSelected(rearSideCheckbox));
		
		Assert.assertTrue(isElementSelected(luggageCompartementCheckbox));
		
		Assert.assertTrue(isElementSelected(heatedFrontCheckbox));
		
		Assert.assertTrue(isElementSelected(airConditioningCheckbox));
		
		Assert.assertTrue(isElementSelected(rainSensorCheckbox));
		
		//Assert.assertTrue(isElementSelected(leatherTrimCheckbox));
		
		sleepInSecond(2);
		
		//uncheck Checkbox
		
		unCheckToCheckBox(rearSideCheckbox);
		
		unCheckToCheckBox(luggageCompartementCheckbox);
		
		unCheckToCheckBox(heatedFrontCheckbox);
		
		unCheckToCheckBox(airConditioningCheckbox);
		
		unCheckToCheckBox(rainSensorCheckbox);
		
		
		sleepInSecond(2);
		
		// verify Check is unchecked
		
		Assert.assertFalse(isElementSelected(rearSideCheckbox));
		
		Assert.assertFalse(isElementSelected(luggageCompartementCheckbox));
		
		Assert.assertFalse(isElementSelected(heatedFrontCheckbox));
		
		Assert.assertFalse(isElementSelected(airConditioningCheckbox));
		
		Assert.assertFalse(isElementSelected(rainSensorCheckbox));
		
		Assert.assertFalse(isElementSelected(towBarCheckbox));
		
		sleepInSecond(2);		
	}
	
	public void TC_04_Mutiple_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		By emotionalDisorder = By.xpath("//label[text()=' Emotional Disorder ']/parent::span");
		
		scrollToElement(emotionalDisorder);
		
		//liet ke cac checkboxes
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("div.form-input-wide input[type='checkbox']"));
		System.out.println("Checkboxes: " + checkboxes);
		
		//Action click
		for (WebElement checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		//Verify checkbox dc chon
		
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				Assert.assertTrue(checkbox.isSelected());
			}
		}
		
		
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		
		for (WebElement checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				Assert.assertFalse(checkbox.isSelected());
			}
		}
		
			
	}
	
	
	public void TC_05_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		By winterCheckbox = By.cssSelector("input[value='Winter']");
		
		By yourFav = By.xpath("//div[contains(text(),'Your')]");
		
		
		//Click by javascript
		clickByJavaScript(winterCheckbox);
		
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(winterCheckbox).isSelected());
		
		Assert.assertEquals(driver.findElement(yourFav).getText(), "Your favorite season is: Winter");
		
	}
	
	public void TC_06_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::*/input");
		
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::*/input");
		
		clickByJavaScript(checkedCheckbox);
		
		clickByJavaScript(indeterminateCheckbox);
		
		sleepInSecond(1);
		
		Assert.assertTrue(isElementSelected(checkedCheckbox));
		
		Assert.assertTrue(isElementSelected(indeterminateCheckbox));
		
		clickByJavaScript(checkedCheckbox);
		
		clickByJavaScript(indeterminateCheckbox);
		
		sleepInSecond(1);
		
		Assert.assertFalse(isElementSelected(checkedCheckbox));
		
		Assert.assertFalse(isElementSelected(indeterminateCheckbox));
	}
	
	public void TC_07_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
				
		By myselfRegister = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		
		By relativeRegister = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		By fullnameField = By.cssSelector("input[formcontrolname='registerFullname']");
		
		By phoneNumberField = By.cssSelector("input[formcontrolname='registerPhoneNumber']");
		
		Assert.assertEquals(driver.findElements(fullnameField).size(), 0);
		
		Assert.assertEquals(driver.findElements(phoneNumberField).size(), 0);
				
		clickByJavaScript(relativeRegister);
		
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(fullnameField).isDisplayed());
		
		Assert.assertTrue(driver.findElement(phoneNumberField).isDisplayed());
		
		
		clickByJavaScript(myselfRegister);
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(fullnameField).size(), 0);
		
		Assert.assertEquals(driver.findElements(phoneNumberField).size(), 0);
	}
	
	public void TC_08_Custom_Radio_Checkbox() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By capitalVietNam = By.xpath("//div[@aria-label='Hà Nội']");
		
		By cityQuangNinh = By.xpath("//div[@data-answer-value='Quảng Ninh']");
		
		By cityQuangNgai = By.xpath("//div[@data-answer-value='Quảng Ngãi']");
		
		Assert.assertEquals(driver.findElement(capitalVietNam).getAttribute("aria-checked"), "false");
		
		clickByJavaScript(capitalVietNam);
		
		sleepInSecond(1);
		
		Assert.assertEquals(driver.findElement(capitalVietNam).getAttribute("aria-checked"), "true");
		
		clickByJavaScript(cityQuangNinh);
		
		clickByJavaScript(cityQuangNgai);
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(cityQuangNinh).getAttribute("aria-checked"), "true");
		
		Assert.assertEquals(driver.findElement(cityQuangNgai).getAttribute("aria-checked"), "true");
	}
	
	
	public void clickByJavaScript(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
	
	public void checkToCheckBox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckBox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public boolean isElementDisabled(By by) {
		if (!driver.findElement(by).isEnabled()) {
			return true;
		} else {
		return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			return true;
		} else {
			return false;
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
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(3000 * second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}

}
