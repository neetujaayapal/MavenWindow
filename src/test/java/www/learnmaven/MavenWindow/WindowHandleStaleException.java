package www.learnmaven.MavenWindow;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandleStaleException {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.instagram.com/");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	@Test
	public void windowHandleTest() {
		// parent
			
		WebElement parentWindowElement = driver.findElement(By.cssSelector("p.b_nGN"));
		
		parentWindowElement.getText();
				
		driver.findElement(By.cssSelector("div.iNy2T a:nth-of-type(1) img")).click();
		
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		

		for (String windowHandle : handles) {
		
		driver.switchTo().window(windowHandle);
		}
		
		parentWindowElement.getText();
		
		
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}


}
