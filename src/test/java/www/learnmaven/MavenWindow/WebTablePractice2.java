package www.learnmaven.MavenWindow;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebTablePractice2 {
	
	
		
		WebDriver driver;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Drivers\\Chrome Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://cosmocode.io/automation-practice-webtable/");

		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

		driver.manage().window().maximize();

	}

	@Test
	public void webTableTest() {

		List<WebElement> noOfRows = driver.findElements(By.xpath("//table[@id = 'countries']//tbody//tr"));
		List<WebElement> noOfColumns = driver.findElements(By.xpath("//table[@id = 'countries']//tbody//tr[1]//td"));

		System.out.println("Rows =" + noOfRows.size());
		System.out.println("Columns =" + noOfColumns.size());

		String beforeXpath = "//table[@id = 'countries']//tbody//tr[";
		String afterXpath = "]//td[";

		for (int i = 2; i <= noOfRows.size(); i++) {

			for (int j = 1; j <= noOfColumns.size(); j++) {

				WebElement element = driver.findElement(By.xpath(beforeXpath + i + afterXpath + j + "]"));

				if (element.getText().equals("Dram")) {

					System.out.println("Found Dram");
				}
			}

		}

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
