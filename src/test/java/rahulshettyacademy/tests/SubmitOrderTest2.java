package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class SubmitOrderTest2 extends BaseTest{
	
	String productName = "ZARA COAT 3";

	
	@Test(dataProvider= "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{		
		ProductCatalouge productCatalouge = landingPage.loginApplication(input.get("email"), input.get("password"));	
		productCatalouge.getProductList();
		productCatalouge.addProductToCart(input.get("productName1"));
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("productName1"));	
		Assert.assertTrue(match);
		cartPage.scrollDown();
		CheckoutPage checkoutPage = cartPage.gotoCheckout();
		checkoutPage.selectCountry("India");
		checkoutPage.longScrollDown();
		checkoutPage.scrollDown();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));	
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalouge productCatalouge = landingPage.loginApplication("niranjan.andhale@gmail.com", "Niranjan@1991");
		OrdersPage ordersPage =productCatalouge.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"niranjan.andhale@gmail.com","Niranjan@1991","ZARA COAT 3"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "niranjan.andhale@gmail.com");
//		map.put("password", "Niranjan@1991");
//		map.put("productName1", "ZARA COAT 3");
		List<HashMap<String,String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
