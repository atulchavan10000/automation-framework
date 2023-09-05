package selenium_framework.tests;

import selenium_framework.TestComponents.BaseTest;
import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.CheckoutPage;
import selenium_framework.pageobjects.ConfirmationPage;
import selenium_framework.pageobjects.LandingPage;
import selenium_framework.pageobjects.ProductCatalogue;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
public class SubmitOrderTest extends BaseTest{

	
	@Test
	public void submitOrder() throws IOException {
		String productName = "ZARA COAT 3";
		
		LandingPage landingPage = launchApplication();
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
