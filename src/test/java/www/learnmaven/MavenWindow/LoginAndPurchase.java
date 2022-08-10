package www.learnmaven.MavenWindow;

import java.util.Random;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAndPurchase {

	String email;

	WebDriver driver;

	Actions ac;

	@BeforeMethod
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		ac = new Actions(driver);

		driver.get("https://www.google.com/");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void registerTest() {

		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(2) input")).sendKeys("Neetu");

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(3) input")).sendKeys("Jayapalan");

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder string1 = new StringBuilder();
		Random rnd = new Random();
		while (string1.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * characters.length());
			string1.append(characters.charAt(index));
		}
		String string2 = string1.toString();

		email = string2 + "@gmail.com";

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(4) input")).sendKeys(email);

		driver.findElement(By.cssSelector("fieldset#account>div:nth-of-type(5) input")).sendKeys("343842324");

		driver.findElement(By.cssSelector("div#content form fieldset:nth-of-type(2)>div:nth-of-type(1) input"))
				.sendKeys("Naveenlab");

		driver.findElement(By.cssSelector("div#content form fieldset:nth-of-type(2)>div:nth-of-type(2) input"))
				.sendKeys("Naveenlab");

		driver.findElement(By.cssSelector("div.buttons input:nth-of-type(1)")).click();

		driver.findElement(By.cssSelector("div.buttons input:nth-of-type(2)")).click();

		String accountAlertText = driver.findElement(By.cssSelector("div#content h1")).getText();

		System.out.println(accountAlertText);

		Assert.assertEquals(accountAlertText, "Your Account Has Been Created!", "text is not matching");

	}

	@Test(priority = 2)
	public void loginTest() {

		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

		driver.findElement(By.cssSelector(
				"div#account-login div#content>div>div:nth-of-type(2) div.well form div:nth-of-type(1) input"))
				.sendKeys(email);

		driver.findElement(By.cssSelector(
				"div#account-login div#content>div>div:nth-of-type(2) div.well form div:nth-of-type(2) input"))
				.sendKeys("Naveenlab");

		WebElement loginBtnField = driver.findElement(
				By.cssSelector("div#account-login div#content>div>div:nth-of-type(2) div.well form>input"));

		ac.click(loginBtnField).perform();

		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=common/home");

		driver.findElement(By.cssSelector("ul.nav.navbar-nav>li:nth-of-type(6) a")).click();

		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=product/category&path=24");

		WebElement addToCartElement = driver.findElement(By.cssSelector("div#content>div:nth-of-type(2)>div:nth-of-type(2) div.button-group span"));

		ac.moveToElement(addToCartElement).click().perform();
		// addToCartElement.click();

		driver.findElement(By.cssSelector("header div.container div.row div.col-sm-3 button.btn.btn-inverse")).click();

		driver.findElement(By.cssSelector("ul.list-inline>li:nth-of-type(5) span")).click();
		
		sleep();

		driver.findElement(By.cssSelector("#input-payment-firstname")).sendKeys("Neetu");

		driver.findElement(By.cssSelector("#input-payment-lastname")).sendKeys("Jayapalan");

		driver.findElement(By.cssSelector("#input-payment-address-1")).sendKeys("Mcmurchy");

		driver.findElement(By.cssSelector("#input-payment-city")).sendKeys("Brampton");

		driver.findElement(By.cssSelector("#input-payment-postcode")).sendKeys("L6X1X7");

		Select sc = new Select(driver.findElement(By.cssSelector("#input-payment-country")));

		sc.selectByVisibleText("Canada");

		Select sc2 = new Select(driver.findElement(By.cssSelector("#input-payment-zone")));
		
		sleep();

		sc2.selectByVisibleText("Ontario");
// billing detail
		driver.findElement(By.cssSelector("div.buttons.clearfix input")).click();
		
		sleep();

// delivery detail
		driver.findElement(
				By.cssSelector("div.panel-group>div:nth-of-type(3)>div:nth-of-type(2) form>div:nth-of-type(5) input")).click();

		sleep();
// delivery method
		driver.findElement(By.cssSelector("div#collapse-shipping-method>div>div:nth-of-type(2) input")).click();

		sleep();
		
// payment method
		driver.findElement(By.cssSelector(
				"div.panel-group>div:nth-of-type(5)>div:nth-of-type(2)>div>div.buttons div.pull-right input:nth-of-type(1)")).click();

		driver.findElement(By.cssSelector(
				"div.panel-group>div:nth-of-type(5)>div:nth-of-type(2)>div>div.buttons div.pull-right input:nth-of-type(2)")).click();

		sleep();
		
// confirm order		
		driver.findElement(By.cssSelector("div#collapse-checkout-confirm div.panel-body>div:nth-of-type(2) input")).click();

		driver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=checkout/success");

		WebElement orderPlacedMessageElement = driver.findElement(By.cssSelector("div#content h1"));

		String messagePlacedText = orderPlacedMessageElement.getText();

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

	public void sleep() {
		try {
			Thread.sleep(2000); // milliseconds
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
