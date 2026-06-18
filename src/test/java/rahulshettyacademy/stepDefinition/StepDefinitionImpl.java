package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")

	public void I_landed_on_Ecommerce_Page() throws IOException

	{
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	// (".+$) this is regular expression it accepts any char
	public void logged_in_username_and_password(String username, String password)

	{

		// username and password came from cucumber "Examples"
		productCatalogue = landingPage.LoginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException

	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^checkout the product (.+) and submit the order$")
	// "And" is the conjection of the previous step so we can use "When" also
	public void checkout_the_product_and_submit_the_order(String productName) throws InterruptedException

	{

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
		System.out.println(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();

	}

	@Then("{string} message is displayed on Confirmation page")
	// "Thankyou for the order." is dynamic text which is coming from cucumber
	// {String} will be replaced {"Thankyou for the order."} during runtime
	public void message_display_on_confirmation_page(String string)

	{
		String finalMessage = confirmationPage.getOrderDetails();
		Assert.assertTrue(finalMessage.equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed on login page")
	public void message_is_displayed_on_login_page(String string) throws InterruptedException

	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
