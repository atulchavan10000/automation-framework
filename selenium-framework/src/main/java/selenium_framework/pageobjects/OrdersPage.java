package selenium_framework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> ProductNames;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public Boolean verifyOrderDisplayed(String productName) {
		return ProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	}
	

}
