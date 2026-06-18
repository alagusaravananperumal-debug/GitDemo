package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	String name = "Alagusaravanan";

	// Constructor take the same as "Class Name" then it will execute first
//Sending argument in any classes all the arguments you can catch in constructor
	public ProductCatalogue(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	// List value so we are using "List" after the "WebElement"
	List<WebElement> products;

	// Partial WebElement is called "by.locator" and return type is "By"
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	// Here "driver.findElement" is a webelement
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList()

	{ // Wait until the product got loaded and Pick the all items
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName)

	{
		// Iterate all product and check the product name "ZARO COAT 3" and also "ZARO
		// COAT 3" segment in <b> tag
		// If we filter their may be multiple results, based on that fetch the first
		// result using -->findFirst()
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName)

	{
		// click on "Add To cart" of "ZARO COAT 3"
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		// Added Explicit wait to get an alert "Added to the cart"
		waitForElementToAppear(toastMessage);
		// Added Explicit wait to wait until the animating icon will disappear
		waitForElementToDisappear(spinner);
	}

}
