package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class base {

	public WebDriver driver;
	public Properties prop;

	public ExtentSparkReporter reporter;
	public ExtentReports extent;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();

		// C:\\Users\\ravneesi\\eclipse-workspace\\E2EProject <-
		// System.getProperty("user.dir")
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\mavenProject\\data.properties");

		prop.load(fis);

//		String browserName = prop.getProperty("browser");
//      If Input is given from the maven cmd (which browser to use) eg: mvn test -PSubmit_Order -Dbrowser=Firefox
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		// For ChromeHeadless
		if (browserName.contains("Chrome")) {

			ChromeOptions options = new ChromeOptions(); // To run headless
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("Headless")) {
				options.addArguments("headless");
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));
			}

			else {
				driver = new ChromeDriver();
			}

		}

		// For Mozilla Firefox
		else if (browserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// For MicrosoftEdge
		else if (browserName.equals("MicrosoftEdge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// Implicit wait of 10 sec
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		String path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		return path;
	}

	@BeforeTest(alwaysRun = true)
	public WebDriver launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		return driver;
	}

	@AfterTest(alwaysRun = true)
	public void TearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

}
