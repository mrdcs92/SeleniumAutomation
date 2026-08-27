package tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;

import components.BaseTest;
import components.DatabaseUtils;
import components.Retry;
import data.DatabaseDataProvider;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.OrderPage;
import pageobjects.ProductCatalogue;

public class DbOrderTest extends BaseTest {

	@Test
	public void verifyDatabaseConnection() {

		try (Connection connection = DatabaseUtils.getConnection()) {

			Assert.assertNotNull(connection, "Database connection should not be null");

			Assert.assertFalse(connection.isClosed(), "Database connection should be open");

		} catch (SQLException e) {

			Assert.fail("Unable to connect to the QA database: " + e.getMessage(), e);
		}
	}

	@Test(dataProvider = "testinfodata", dependsOnMethods = {
			"verifyDatabaseConnection" }, retryAnalyzer = Retry.class, dataProviderClass = DatabaseDataProvider.class)
	public void dbSubmitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// List<WebElement> products = productCatalogue.getProductList();

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
