package www.learnmaven.MavenWindow;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandle {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://demoqa.com/browser-windows");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Test
	public void windowHandleTest() {
// parent
		String parentWindowHandle = driver.getWindowHandle();

		System.out.println("Parent Handle =" + parentWindowHandle);
// child
		WebElement newTabElement = driver.findElement(By.cssSelector("#tabButton"));

		newTabElement.click();

		sleep();

		Set<String> handles = driver.getWindowHandles();

		//System.out.println("handles = " + handles);

		for (String windowHandle : handles) {

			if (!windowHandle.equals(parentWindowHandle)) {

				driver.switchTo().window(windowHandle);

				System.out.println("Window Handle of Child 1 =" + driver.getWindowHandle());
				
				sleep();

				driver.close();

				sleep();

			}

		}

		driver.switchTo().window(parentWindowHandle);

		WebElement newWindowElement = driver.findElement(By.cssSelector("#windowButton"));

		newWindowElement.click();
		
		Set<String> handles1 = driver.getWindowHandles();

		for (String windowHandle : handles1) {

			if (!windowHandle.equals(parentWindowHandle)) {

				driver.switchTo().window(windowHandle);

				System.out.println("Window Handle of child 2 =" + driver.getWindowHandle());

				sleep();

				driver.close();

				sleep();
			}

		}

		driver.switchTo().window(parentWindowHandle);
		
		WebElement newWindowMsgElement = driver.findElement(By.cssSelector("#messageWindowButton"));

		newWindowMsgElement.click();
		
		Set<String> handles2 = driver.getWindowHandles();

		for (String windowHandle : handles2) {

			if (!windowHandle.equals(parentWindowHandle)) {

				driver.switchTo().window(windowHandle);

				System.out.println("Window Handle of child 3 =" + driver.getWindowHandle());

				sleep();

				driver.close();

				sleep();
			}

		}
		
		driver.switchTo().window(parentWindowHandle);

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
