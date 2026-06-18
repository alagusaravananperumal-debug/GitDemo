package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

//Parent File
// WebElement will start with "driver.
//If any compilation issue, then change 600 --> 500 in the "js.executeScript("window.scrollBy(0,600)");"
//Every class has every functionality

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// Run the methods without using "public static" we can use "@Test"

		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password")); // Hash
																														// Map
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);
		System.out.println(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String finalMessage = confirmationPage.getOrderDetails();
		Assert.assertTrue(finalMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	// "dependsOnMethods" will run first
	@Test(dependsOnMethods = { "SubmitOrder" })
	public void orderHistoryPage()

	{
		// TO verify "ZARA COAT 3" is displaying in orders page
		ProductCatalogue productCatalogue = landingPage.LoginApplication("alagusaravanan@gmail.com", "Tharanya25!");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@Test(dependsOnMethods = { "SubmitOrder", "orderHistoryPage" })
	public void signOutPage()

	{
		// TO verify "ZARA COAT 3" is displaying in orders page
		ProductCatalogue productCatalogue = landingPage.LoginApplication("alagusaravanan@gmail.com", "Tharanya25!");
		productCatalogue.goToSignOutPage();
	}

	// Implementing parametrization into the test with TestNG DataProvider
	@DataProvider // We Need to send the data

	public Object[][] getData() throws IOException

	{
		// Two dymentional array
		// "Object" is Generic data type it accepts any kind of data type
		// DataProvider allows to return hash map using key value pair

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "alagusaravanan@gmail.com");
//		map.put("password", "Tharanya25!");
//		map.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "alagusaravanan.perumal@gmail.com");
//		map1.put("password", "Tharanya25!");
//		map1.put("product", "ADIDAS ORIGINAL");

		// ABove input hash data was retrived from external file ("PurchaseOrder.json)
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider // We Need to send the data
//
//	public Object[][] getData()
//
//	{
//		return new Object[][] { { "alagusaravanan@gmail.com", "Tharanya25!", "ZARA COAT 3" },
//				{ "alagusaravanan.perumal@gmail.com", "Tharanya25!", "ADIDAS ORIGINAL" } };
//
//	}

}
