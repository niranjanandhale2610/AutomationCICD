package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalouge productCatalouge;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productCatalouge = landingPage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);	
		Assert.assertTrue(match);
		cartPage.scrollDown();
		CheckoutPage checkoutPage = cartPage.gotoCheckout();
		checkoutPage.selectCountry("India");
		checkoutPage.longScrollDown();
		checkoutPage.scrollDown();
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String StrArg1)
	{
		Assert.assertEquals(StrArg1, landingPage.getErrorMessage());
		driver.close();
	}
	

}
