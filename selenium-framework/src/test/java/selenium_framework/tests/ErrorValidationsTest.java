package selenium_framework.tests;

import selenium_framework.TestComponents.BaseTest;
import selenium_framework.TestComponents.RetryAnalyzer;
import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.ProductCatalogue;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = RetryAnalyzer.class)
	public void loginError() throws IOException {
		landingPage.loginToApp("wrong@gmail.com", "wrongpass");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect aaemail or password.");
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginToApp("atulchavan@gmail.com", "Google123$");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductDisplayedInCart("ZARA COAT 3"));
	}
}
