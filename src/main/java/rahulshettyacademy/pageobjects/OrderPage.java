package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	public Boolean verifyOrderDisplay(String productName)

	{ // Validate weather added all cart product matching with input ("ZARA COAT 3")
		// cart items matches with the input ("ZARA COAT 3") then it will return "True"
		Boolean match = productNames.stream().anyMatch(s -> s.getText().equalsIgnoreCase(productName));
		return match;
	}

}
