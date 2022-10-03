package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class OrdersPageObject extends AbstractComponent{

	WebDriver driver;

	public OrdersPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productName;

	
	public List<WebElement> getOrderPageProducts() {
		return productName;
	}
	
	public Boolean isProductAvailableInOrdersPage(String actualProduct) {

		Boolean productAvailable = getOrderPageProducts().stream()
				.anyMatch(productOrdered -> productOrdered.getText().equalsIgnoreCase(actualProduct));

		return productAvailable;
	}

}
