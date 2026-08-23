package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import components.BaseTest;
import components.Retry;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equals("THANKYOU FOR THE ORDER."));

	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" }, retryAnalyzer = Retry.class)
	public void OrderHistoryTest(HashMap<String, String> input) {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\resources\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

		/*
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "misterdcs92@gmail.com"); map.put("password", "1999222dst!taN1999222");
		 * map.put("product", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "misterdcs1992@gmail.com"); map1.put("password",
		 * "1999222dst!taN1999222"); map1.put("product", "ADIDAS ORIGINAL"); return new
		 * Object[][] {{map}, {map1}};
		 */

	}

}
