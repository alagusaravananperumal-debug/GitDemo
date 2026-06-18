package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	public Boolean verifyProductDisplay(String productName)

	{ // Validate weather added all cart product matching with input ("ZARA COAT 3")
		// cart items matches with the input ("ZARA COAT 3") then it will return "True"
		Boolean match = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout() throws InterruptedException

	{// click on checkout button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(2000);
		checkoutEle.click();
		return new CheckOutPage(driver);
	}

}
