package javaBasic_2;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_04_WebBrowser_II {

	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	
	
	@BeforeClass
		public void beforeClass() {
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		
		//Verify login URL
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// Verify Register URL
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//span[contains(text(),'Create')]")).click();
		
		String registerUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(registerUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_02_Title() {
		driver.get("http://live.techpanda.org/");
		
		//Verify login title
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageTitle = driver.getTitle();
		
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Verify register title
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//span[contains(text(),'Create')]")).click();
		
		String registerPageTitle = driver.getTitle();
		
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//span[contains(text(),'Create')]")).click();
		
		String registerUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(registerUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		String loginPageUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		String registerPageTitle = driver.getTitle();
		
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_04_Source() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageSource = driver.getPageSource();
		
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//div[@class='col-1 new-users']//span[contains(text(),'Create')]")).click();
		
		String registerPageSource = driver.getPageSource();
		
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void AfterClass() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
		
		
	}
}
