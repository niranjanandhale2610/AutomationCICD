package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalouge;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
		String productName = "ZARA COAT 3";		
		ProductCatalouge productCatalouge = landingPage.loginApplication("niranjan.andhle@gmail.com", "Nirjan@1991");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		
		String productName = "ZARA COAT 3";		
		ProductCatalouge productCatalouge = landingPage.loginApplication("niranjan.andhale@gmail.com", "Niranjan@1991");	
		productCatalouge.getProductList();
		productCatalouge.addProductToCart(productName);
		CartPage cartPage = productCatalouge.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");	
		Assert.assertFalse(match);
	}

}
