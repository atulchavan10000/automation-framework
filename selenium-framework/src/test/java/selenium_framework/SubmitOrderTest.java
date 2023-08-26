package selenium_framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.CheckoutPage;
import selenium_framework.pageobjects.ConfirmationPage;
import selenium_framework.pageobjects.LandingPage;
import selenium_framework.pageobjects.ProductCatalogue;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
public class SubmitOrderTest {

	public static void main(String[] args) {
		
		
		String productName = "ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginToApp("atulchavan@gmail.com", "Google123$");
			
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Assert.assertTrue(cartPage.verifyProductDisplayedInCart(productName));
		CheckoutPage checkoutPage = cartPage.clickOnCheckout();	    
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

	    Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
}
