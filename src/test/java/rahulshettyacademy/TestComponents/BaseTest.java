package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver; // Commonly intilialize the browser to reuse all the methods

	public LandingPage landingPage;

	public WebDriver initilalizeDriver() throws IOException

	{

		// Property class read the global property
//write any file name with ".properties" extension then using property class in java will able to parse this file
// and extract all the global parameter values.

		Properties prob = new Properties();
		// Convert file into input stream Object,becoz "load" expecting "inputstream"
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties"); // "System.getProperty("user.dir")"
																								// fetch the dynamic
																								// directory location
																								// from local path
		// Post create "Properties" then we need to load "GlobalData.properties"
		prob.load(fis);

		// "System.getProperty" having System level variable values
		// Check weathter maven comment has any browser property if so execute it
		// otherwise take default input from "GlobalData.properties"
		String WebBrowserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prob.getProperty("browser"); // java ternary operator(if else condition)
		// String WebBrowserName = prob.getProperty("browser");

		if (WebBrowserName.contains("chrome")) {

			// Run in on Headless mode
			ChromeOptions options = new ChromeOptions();

			WebDriverManager.chromedriver().setup();
			if (WebBrowserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			// Run in full screenmode
			driver.manage().window().setSize(new Dimension(1440, 990));

		}

		else if (WebBrowserName.equalsIgnoreCase("firefox")) {
			// firefox
			// System.setProperty("webdriver.gecko.driver", "C:\\Program
			// Files\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (WebBrowserName.equalsIgnoreCase("edge")) {
			// edge
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException

	{

		// HashMap automatically create when scanning the PurchaseOrder.json file.
		// Read Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert string to HashMap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		// It reads the json file value and convert two hash map and put into list
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

	}

	// Screenshot
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports" + testcaseName + ".jpg");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "\\reports" + testcaseName + ".jpg";
	}

	@BeforeMethod(alwaysRun = true)
	// "alwaysRun = true" keyword allow the below execution always.
	// Any of the "testNG.xml" condtion coudnt restrict the below
	public LandingPage launchApplication() throws IOException

	{
		driver = initilalizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToURL();
		// Need landing page object to login that's y return here
		// It goes to the landing page and hits the URL and giving the landingPage
		// object
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}