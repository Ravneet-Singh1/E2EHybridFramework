package pageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CartPageObject extends AbstractComponent {

	WebDriver driver;

	public CartPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = "li[class='totalRow'] button[type='button']")
	WebElement checkout;

	
	public List<WebElement> getCartProducts() {
		return cartProducts;
	}

	public Boolean isProductAvailableInCart(String productName) {

		Boolean productAvailable = getCartProducts().stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		return productAvailable;
	}

	public void goToCheckoutPage() {
		checkout.click();
	}
}
