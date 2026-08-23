package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import components.BaseTest;
import pageobjects.CartPage;
import pageobjects.ProductCatalogue;

public class ErrorValidation extends BaseTest {

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void LoginErrorValidation(HashMap<String, String> input) throws IOException {

		landingPage.loginApplication(input.get("email"), "199922dst!taN19992");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test(dataProvider = "getData")
	public void ProductErrorValidation(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\resources\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
