package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.BaseTest;
import components.Retry;
import data.DatabaseDataProvider;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalogue;

public class DbOrderTest extends BaseTest {

	@Test(dataProvider = "testinfodata", retryAnalyzer = Retry.class, dataProviderClass = DatabaseDataProvider.class)
	public void dbSubmitOrder(HashMap<String, String> input) throws IOException {

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

	@Test(dataProvider = "testinfodata", dependsOnMethods = {
			"dbSubmitOrder" }, retryAnalyzer = Retry.class, dataProviderClass = DatabaseDataProvider.class)
	public void dbOrderHistoryTest(HashMap<String, String> input) {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(input.get("product")));

	}

}
