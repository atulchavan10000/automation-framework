package selenium_framework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public Boolean verifyProductDisplayedInCart(String productName) {
		return productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
	}
	
	
	public CheckoutPage clickOnCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}
