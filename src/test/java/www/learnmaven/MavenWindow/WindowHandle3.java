package www.learnmaven.MavenWindow;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WindowHandle3 {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://demoqa.com/browser-windows");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void windowHandleTest() {
		// parent
		String parentWindowHandle = driver.getWindowHandle();

		System.out.println("Parent Handle =" + parentWindowHandle);

		System.out.println("Parent Title =" + driver.getTitle());

		System.out.println("Parent URL =" + driver.getCurrentUrl());
		// new tab child
		driver.findElement(By.cssSelector("#tabButton")).click();

		ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());

		System.out.println(windowHandles);

		// window child
		driver.findElement(By.cssSelector("#windowButton")).click();

		ArrayList<String> windowHandles2 = new ArrayList<String>(driver.getWindowHandles());

		System.out.println(windowHandles2);

		windowHandles2.removeAll(windowHandles);

		System.out.println("2nd child window =" + windowHandles2);

	}

	@AfterMethod
	public void tearDown() {

		driver.close();
	}

}
