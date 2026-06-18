package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// "this.driver" --> "driver" refers to local variable
		super(driver);
		this.driver = driver;
		// Initialize all the elements with "driver"(Refer from "Standalone class")
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement finalMessage;

	public String getOrderDetails()

	{ // All actions has to be mentioned in seperate method
		// Get the text of final page
		return finalMessage.getText();

	}

}
