package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean verifyProductDisplay(String productName)
	{
		List <WebElement> cartproducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cartproducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage gotoCheckout() 
	{
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
