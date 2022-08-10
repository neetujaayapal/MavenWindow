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
		// new tab child
		driver.findElement(By.cssSelector("#tabButton")).click();
		// window child
		driver.findElement(By.cssSelector("#windowButton")).click();
		
		sleep();

		Set<String> handles = driver.getWindowHandles();

		// System.out.println("handles = " + handles);

		for (int count = 0; count <= 3; count++)

			for (String windowHandle : handles) {

				count++;
				if (!windowHandle.equals(parentWindowHandle)) {

					driver.switchTo().window(windowHandle);
                  // printing child 1 
					if (count == 2) {
						// prints the window handle of three child
						System.out.println("Window Handle 1 =" + driver.getWindowHandle());
						// printing child 2
					} else if (count == 3) {

						System.out.println("Window Handle 2 =" + driver.getWindowHandle());

						driver.close();

					}
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
