package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

//If any compilation issue, then change 600 --> 500 in the "js.executeScript("window.scrollBy(0,600)");"

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		// Create object in class which has used in newly created class ("LandingPage)
		// Sending argument in any classes all the arguments you can catch in
		// constructor
		// LandingPage landingPage = new LandingPage(driver);
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("alagusaravanan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tharanya25!");
		driver.findElement(By.id("login")).click();
		// Syntax of explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait until the product got loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// Pick the all items
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// Iterate all product and check the product name "ZARO COAT 3" and also "ZARO
		// COAT 3" segment in <b> tag
		// If we filter their may be multiple results, based on that fetch the first
		// result using -->findFirst()
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		// To filter out the last button use this CSS "last-of-type"
		// Desired product we selected in last step itself "ZARO COAT 3". So based on
		// that it will click on "Add To cart" of "ZARO COAT 3"

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Post click the add to cart button and wait for the alert message "Product
		// Added to cart"

		// Added Explicit wait to get an alert "Added to the cart"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// Added Explicit wait to wait until the animating icon will disappear (post
		// click on "Added to the cart")
		// Directly sending webelement "invisibilityOf" to resolve performance issue
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// Click on "cart"
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		// Get the all cart product value
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// Validate weather added all cart product matching with input ("ZARA COAT 3")
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		// cart items matches with the input ("ZARA COAT 3") then it will return "True"
		Assert.assertTrue(match);
		System.out.println(match);
		// Scroll into visibility and Click on checkout button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// Pick the AutosuggestiveDropdown (india) from dropdown
		Actions a = new Actions(driver);

		// "sendKeys" Accepting two argument (Webelement targer, charector sequences)
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "India").build().perform();
		// Wait until dropdown shown the value ("india")
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		// Click on the value "india"
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		// Scroll into visibility and Click on submit button
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		// Get the text of final page
		String finalMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		// check and validate get text and " Thankyou for the order. " matches
		Assert.assertTrue(finalMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}
