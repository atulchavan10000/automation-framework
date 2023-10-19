package selenium_framework.tests;

import selenium_framework.TestComponents.BaseTest;
import selenium_framework.pageobjects.CartPage;
import selenium_framework.pageobjects.CheckoutPage;
import selenium_framework.pageobjects.ConfirmationPage;
import selenium_framework.pageobjects.OrdersPage;
import selenium_framework.pageobjects.ProductCatalogue;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getDataForSubmitOrder", dataProviderClass = DataProviderFactory.class, groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductCatalogue productCatalogue = landingPage.loginToApp(input.get("userEmail"), input.get("userPass"));
		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Assert.assertTrue(cartPage.verifyProductDisplayedInCart(input.get("productName")));
		CheckoutPage checkoutPage = cartPage.clickOnCheckout();	    
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

	    Assert.assertTrue(confirmationPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	// to verify if zara coat 3 is displaying on orders page
	// depends on submitOrder test
	@Test(dependsOnMethods= {"submitOrder"})	
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginToApp("atulchavan@gmail.com", "Google123$");
		
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplayed(productName));
	}
}
