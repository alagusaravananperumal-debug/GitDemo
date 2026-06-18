package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

//Parent File
// WebElement will start with "driver.
//If any compilation issue, then change 600 --> 500 in the "js.executeScript("window.scrollBy(0,600)");"

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class) // Rerun test
	public void LogInErrorValidation() throws InterruptedException, IOException {
		// Login Related Error Message
		landingPage.LoginApplication("alagusaravanan@gmail.com", "Tharanya");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		// Product Related Error Message
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.LoginApplication("alagusaravanan.perumal@gmail.com",
				"Tharanya25!");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");

		Assert.assertFalse(match);
		System.out.println(match);

	}

}
