package pageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import abstractComponents.AbstractComponent;

public class productsListObject extends AbstractComponent {

	WebDriver driver;

	public productsListObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	By productsListWait = By.cssSelector(".col-lg-4");

	By successMessageBy = By.id("toast-container");

	By addToCart = By.xpath("(//button[@class='btn btn-custom'])[3]");

	public WebElement getProductByName(String productName, List<WebElement> productAvailable) {

		WebElement productChoosed = productAvailable.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return productChoosed;
	}

	public void addProductTocart(WebElement requiredProduct) {
		requiredProduct.findElement(addToCart).click();
	}

	public By productsLists = By.cssSelector(".col-lg-4");

	public By addCart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> getProductsList() {
		waitForElementTobeVisible(productsListWait);
		return driver.findElements(productsLists);
	}

	public WebElement getSuccessMessage() {
		waitForElementTobeVisible(successMessageBy);
		return driver.findElement(successMessageBy);
	}
}
