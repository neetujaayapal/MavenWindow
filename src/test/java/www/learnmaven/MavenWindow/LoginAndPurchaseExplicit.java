package www.learnmaven.MavenWindow;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndPurchaseExplicit { // test time = 29.504 sec ,used one sleep()

	String ranEmail;

	WebDriver driver;

	Actions ac;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		ac = new Actions(driver);

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

	    driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	@Test(priority = 1)
	public void registerTest() {

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(2) input")).sendKeys("Neetu");

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(3) input")).sendKeys("Jayapalan");

		Random rnd = new Random();

		int ranNumber = rnd.nextInt(1000);
		ranEmail = "name" + ranNumber + "@gmail.com";

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(4) input")).sendKeys(ranEmail);

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(5) input")).sendKeys("343842324");

		driver.findElement(By.cssSelector("div#content form fieldset:nth-of-type(2)>div:nth-of-type(1) input"))
				.sendKeys("Naveenlab");

		driver.findElement(By.cssSelector("div#content form fieldset:nth-of-type(2)>div:nth-of-type(2) input"))
				.sendKeys("Naveenlab");

		driver.findElement(By.cssSelector("div.buttons input:nth-of-type(1)")).click();
// submit
		driver.findElement(By.cssSelector("div.buttons input:nth-of-type(2)")).submit();

		String accountAlertText = driver.findElement(By.cssSelector("div#content h1")).getText();

		System.out.println(accountAlertText);

		Assert.assertEquals(accountAlertText, "Your Account Has Been Created!", "text is not matching");

	}

	@Test(priority = 2)
	public void loginTest() {

		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

		driver.findElement(By.cssSelector(
				"div#account-login div#content>div>div:nth-of-type(2) div.well form div:nth-of-type(1) input"))
				.sendKeys(ranEmail);

		driver.findElement(By.cssSelector(
				"div#account-login div#content>div>div:nth-of-type(2) div.well form div:nth-of-type(2) input"))
				.sendKeys("Naveenlab");

		WebElement loginBtnField = driver.findElement(
				By.cssSelector("div#account-login div#content>div>div:nth-of-type(2) div.well form>input"));
// action click
		ac.click(loginBtnField).perform();

	}

	@Test(priority = 3)
	public void purchaseTest() {

		loginTest();

		driver.findElement(By.cssSelector("ul.nav.navbar-nav>li:nth-of-type(6) a")).click();

		WebElement addToCartElement = driver
				.findElement(By.cssSelector("div#content>div:nth-of-type(2)>div:nth-of-type(2) div.button-group span"));

		addToCartElement.click();

		driver.findElement(By.cssSelector("header div.container div.row div.col-sm-3 button.btn.btn-inverse")).click();

		driver.findElement(By.cssSelector("ul.list-inline>li:nth-of-type(5) span")).click();

		explicitWait(driver, 10, By.cssSelector("#input-payment-firstname")).sendKeys("Neetu");
		

		driver.findElement(By.cssSelector("#input-payment-lastname")).sendKeys("Jayapalan");

		driver.findElement(By.cssSelector("#input-payment-address-1")).sendKeys("Mcmurchy");

		driver.findElement(By.cssSelector("#input-payment-city")).sendKeys("Brampton");

		driver.findElement(By.cssSelector("#input-payment-postcode")).sendKeys("L6X1X7");

		 WebElement country =
		 driver.findElement(By.cssSelector("#input-payment-country"));
		
	    select(country).selectByVisibleText("Canada");


		selectWithWait(driver,By.cssSelector("#input-payment-zone"), 50, "Ontario",driver.findElement(By.cssSelector("#input-payment-zone")));
		

// billing detail

		explicitWait(driver, 10, By.cssSelector("div.buttons.clearfix input")).click();

// delivery detail
		explicitWait(driver, 40, By.cssSelector("input#button-shipping-address")).click();
		

// delivery method
		explicitWait(driver, 10, By.cssSelector("input#button-shipping-method")).click();
		

// payment method
		
		explicitWait(driver, 10, By.cssSelector("div.panel-group>div:nth-of-type(5)>div:nth-of-type(2)>div>div.buttons div.pull-right input:nth-of-type(1)")).click();
	
		driver.findElement(By.cssSelector(
				"div.panel-group>div:nth-of-type(5)>div:nth-of-type(2)>div>div.buttons div.pull-right input:nth-of-type(2)"))
				.click();

// confirm order	
		
		explicitWait(driver, 10, By.cssSelector("div#collapse-checkout-confirm div.panel-body>div:nth-of-type(2) input")).click();
		
	    WebDriverWait w = new WebDriverWait(driver, 20);
	    
	    w.until(ExpectedConditions.textToBe(By.cssSelector("div#content h1"), "Your order has been placed!"));

		String messagePlacedText = driver.findElement(By.cssSelector("div#content h1")).getText();

		Assert.assertEquals(messagePlacedText, "Your order has been placed!", "Message not matching");

		driver.findElement(By.cssSelector("ul.list-inline li.dropdown span:nth-of-type(1)")).click();

		driver.findElement(By.cssSelector("ul.dropdown-menu.dropdown-menu-right>li:nth-of-type(5) a")).click();

		WebElement logOutMessageElement = driver.findElement(By.cssSelector("div#content h1"));

		String logOutMessage = logOutMessageElement.getText();

		System.out.println(logOutMessage);

		Assert.assertEquals(logOutMessage, "Account Logout", "Message not matching");

	}

	@AfterMethod
	public void tearDown() {

		driver.close();
	}

	public void selectWithWait(WebDriver driver ,By locator, int time, String name,WebElement element) {

		WebDriverWait w = new WebDriverWait(driver, time);

		w.until(ExpectedConditions.textToBePresentInElement(element, name));

		Select sc = new Select(driver.findElement(locator));

		try {
			sc.selectByVisibleText(name);
		}

		catch (Exception e) {

			sc.selectByValue(name);;
		}

	}

	public WebElement explicitWait(WebDriver driver, int sec, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, sec);

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	public Select select(WebElement element) {
		
				Select sc = new Select(element);
		
				return sc;

}
}
