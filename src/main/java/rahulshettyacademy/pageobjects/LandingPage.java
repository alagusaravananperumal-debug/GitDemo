package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	// String name = "Alagusaravanan";

	// Constructor take the same as "Class Name" then it will execute first
//Sending argument in any classes all the arguments you can catch in constructor
	public LandingPage(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page Factory
	// Page Factory "@Findby" converts
	// "driver.findElement(By.id("userEmail"))" into "@FindBy(id = "userEmail")"
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	public ProductCatalogue LoginApplication(String userName, String password)

	{
		userEmail.sendKeys(userName);
		passwordEle.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);

	}

	public String getErrorMessage() throws InterruptedException

	{
		Thread.sleep(1000);
		waitForWebElementToAppear(errorMessage);
		Thread.sleep(1000);
		return errorMessage.getText();
	}

	public void goToURL()

	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
