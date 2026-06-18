package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

//It containing reusable code
//We can refer so many utility here like: switching to frame, switching to windows, java script executor, alterhandling, visibility
public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// Constructor can catch the variable
		// Child class to parent class send a variable to "super" keywors
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Click on "cart"
	@FindBy(css = "[routerlink*='cart']")
	WebElement clickOnCart;

	// Click on "Orders"
	@FindBy(css = "[routerlink*='myorders']")
	WebElement OrderHeader;

	// Click on "SignOut"
	@FindBy(css = "li:nth-child(5)")
	WebElement clickOnSignOut;

	// Partial WebElement is called "by.locator" and return type is "By"
	public void waitForElementToAppear(By findBy)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait until the product got loaded
		// Example: WebElement is "driver.findElements(By.cssSelector(".mb-3")"
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	// Full WebElement
	public void waitForWebElementToAppear(WebElement findBy) throws InterruptedException

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Wait until the product got loaded
		// Example: WebElement is "driver.findElements(By.cssSelector(".mb-3")"
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(findBy));
		Thread.sleep(1000);
	}

	public CartPage goToCartPage() throws InterruptedException

	{
		// Click on "Add to cart"
		Thread.sleep(1000);
		clickOnCart.click();
		Thread.sleep(1000);
		return new CartPage(driver);
	}

	public OrderPage goToOrdersPage()

	{
		// Click on "Order"
		OrderHeader.click();
		return new OrderPage(driver);
	}

	public void goToSignOutPage()

	{
		// Click on "Signout"
		clickOnSignOut.click();
	}

	// Here "driver.findElement" is a webelement so we need to pass as string
	public void waitForElementToDisappear(WebElement ele)

	{ // 4 Seconds _Application

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Added Explicit wait to wait until the animating icon will disappear
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

}
