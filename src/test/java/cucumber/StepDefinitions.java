package cucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import components.BaseTest;
import components.ExtentTestManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.LandingPage;
import pageobjects.ProductCatalogue;

public class StepDefinitions extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
		
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		
		ExtentTestManager.getTest().info("Email: " + username);
		ExtentTestManager.getTest().info("Password: ********");
		productCatalogue = landingPage.loginApplication(username, password);
		
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {	
		List<WebElement> products = productCatalogue.getProductList();
		
		ExtentTestManager.getTest().info("Product: " + productName);
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(confirmMessage.equals(string));
		driver.quit();
		
	}

	@Then("{string} message is displayed")
	public void messageIsDisplayed(String string) {
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		driver.close();
	}
	
}
