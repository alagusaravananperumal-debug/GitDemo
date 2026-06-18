package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	// Partial WebElement is called "by.locator" and return type is "By"
	// driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));

	private By resultPopup = By.cssSelector(".ta-results");

	@FindBy(css = "[placeholder = 'Select Country']")
	private WebElement Country;
	@FindBy(css = ".ta-item:nth-of-type(2)")
	private WebElement selectCountry;
	@FindBy(css = ".action__submit")
	private WebElement submit;

	public void selectCountry(String countryName) throws InterruptedException

	{ // All actions has to be mentioned in seperate method
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitForElementToAppear(resultPopup);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		Thread.sleep(2000);
		selectCountry.click();

	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,1200)");
		submit.click();
		return new ConfirmationPage(driver);
	}

}
