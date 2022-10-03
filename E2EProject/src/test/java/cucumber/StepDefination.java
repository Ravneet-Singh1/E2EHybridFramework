package cucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import abstractComponents.AbstractComponent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.CartPageObject;
import pageObject.HomePageObjects;
import pageObject.OrdersPageObject;
import pageObject.PaymentDetailsPageObject;
import pageObject.confirmOrderPageObject;
import pageObject.productsListObject;
import resources.base;

public class StepDefination extends base {

	WebDriver driver;
	HomePageObjects hpObj;
	productsListObject plObj;
	AbstractComponent abc;
	CartPageObject cpObj;
	PaymentDetailsPageObject pdpObj;
	confirmOrderPageObject copObj;
	OrdersPageObject opObj;

	@Given("I have landed on Ecommerce page")
	public void I_have_landed_on_Ecommerce_page() throws IOException {
		driver = launchApplication();
	}

	@Then("^Validate if we are getting the (.+)$")
	public void validate_if_we_are_getting_the(String errormessage) {
		Assert.assertEquals(errormessage, hpObj.getLoginError());
		driver.close();
	}

	@Given("^Enterting (.+) and (.+)$")
	public void Enterting_username_and_password(String username, String password) {
		hpObj = new HomePageObjects(driver);
		hpObj.loginPage(username, password);
	}

	@When("^I add (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {

		plObj = new productsListObject(driver);
		List<WebElement> products = plObj.getProductsList();

		WebElement productChoosed = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		productChoosed.findElement(plObj.addCart).click();

		abc = new AbstractComponent(driver);
		abc.goToCart();

		cpObj = new CartPageObject(driver);
		cpObj.getCartProducts();
		Assert.assertTrue(cpObj.isProductAvailableInCart(productName)); // This will fail the script if the required
																		// product is not available in cart.
		cpObj.goToCheckoutPage();
	}

	@And("^Checkout product with (.+) and submit the order$")
	public void Checkout_product_and_submit_the_order(String selectCountry) {

		pdpObj = new PaymentDetailsPageObject(driver);
		pdpObj.selectCountry(selectCountry);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", pdpObj.placeOrder());

	}

	@Then("verify if {string} message is displayed on confirmation page")
	public void verify_if_message_is_displayed(String message) throws InterruptedException {
		copObj = new confirmOrderPageObject(driver);
		String confirmMessage = copObj.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		Thread.sleep(3000);
		driver.close();
	}

}
