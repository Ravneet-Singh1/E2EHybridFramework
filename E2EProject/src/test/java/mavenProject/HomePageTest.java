package mavenProject;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import abstractComponents.AbstractComponent;
import pageObject.CartPageObject;
import pageObject.HomePageObjects;
import pageObject.OrdersPageObject;
import pageObject.PaymentDetailsPageObject;
import pageObject.RegistrationPageObjects;
import pageObject.confirmOrderPageObject;
import pageObject.productsListObject;
import resources.base;

public class HomePageTest extends base {

	private static Logger log = LogManager.getLogger(base.class.getName());

//	WebDriver driver;
	String productName;
	String selectCountry;

	AbstractComponent abc;
	RegistrationPageObjects rpObj;
	HomePageObjects hpObj;
	productsListObject plObj;
	PaymentDetailsPageObject pdpObj;
	confirmOrderPageObject copObj;
	CartPageObject cpObj;
	OrdersPageObject opObj;

	// =============================================================================================
	// REGISTRATION PAGE
	// =============================================================================================

	@Test
	public void RegistrationTest() throws InterruptedException {

		hpObj = new HomePageObjects(driver);
		hpObj.goToRegisterPage();

		rpObj = new RegistrationPageObjects(driver);

		log.info("Entering firstName");
		rpObj.getFirstName().sendKeys(prop.getProperty("firstName"));

		rpObj.getLastame().sendKeys(prop.getProperty("lastName"));

		rpObj.getUserEmail().sendKeys(prop.getProperty("email"));

		rpObj.getUserMobile().sendKeys(prop.getProperty("phoneNumber"));

		Select dropdown = new Select(rpObj.getOccupation());
		dropdown.selectByVisibleText(prop.getProperty("occupation"));

		rpObj.getGender().click();

		rpObj.getUserPassword().sendKeys(prop.getProperty("password"));

		rpObj.getConfirmPassword().sendKeys(prop.getProperty("confirmPassword"));

		rpObj.getAgeConfirmation().click();

		Thread.sleep(2000);

		rpObj.getRegister().click();
	}

	// =============================================================================================
	// LOGIN PAGE
	// =============================================================================================

	@Test
	public void LogIn() {

		hpObj = new HomePageObjects(driver);

		hpObj.loginPage(prop.getProperty("email"), prop.getProperty("password"));
	}

	// =============================================================================================
	// FIND PRODUCT PAGE
	// =============================================================================================

	@Test(dependsOnMethods = "LogIn")
	public void FindProductTest() {

		plObj = new productsListObject(driver);

		productName = prop.getProperty("productName");
		selectCountry = prop.getProperty("country");

		log.info("Searching required product");
		List<WebElement> products = plObj.getProductsList();
		log.info("Got all the products");

		WebElement productChoosed = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		log.info("Product found..");
		productChoosed.findElement(plObj.addCart).click();
		log.info("Product added to cart successfully !!");

	}

	// =============================================================================================
	// CART PAGE
	// =============================================================================================

	@Test(dependsOnMethods = "FindProductTest")
	public void cartTest() throws InterruptedException {

		abc = new AbstractComponent(driver);
		abc.goToCart();
		log.info("Moved to Cart Page !!");

		log.info(driver.getTitle());

		cpObj = new CartPageObject(driver);

		List<WebElement> cartProducts = cpObj.getCartProducts();
		log.info("Total Products in cart : " + cartProducts.size());

		Assert.assertTrue(cpObj.isProductAvailableInCart(productName)); // This will fail the script if the required
																		// product is not available in cart.

		log.info("Moving to checkout page");
		cpObj.goToCheckoutPage();

	}

	// =============================================================================================
	// PAYMENT DETAILS PAGE
	// =============================================================================================

	@Test(dependsOnMethods = "cartTest")
	public void paymentDetails() {

		log.info("Select Country..");
		pdpObj = new PaymentDetailsPageObject(driver);

		pdpObj.selectCountry(selectCountry);
		log.info(selectCountry.toUpperCase() + " selected");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", pdpObj.placeOrder());
		log.info("Order Placed..");

	}

	// =============================================================================================
	// ORDER CONFIRM PAGE
	// =============================================================================================

	@Test(dependsOnMethods = "paymentDetails")
	public void confirmOrderPage() {

		copObj = new confirmOrderPageObject(driver);

		log.info(driver.getTitle());

		String confirmMessage = copObj.getConfirmationMessage();
		log.info(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		copObj.downloadOrderDetails();
		log.info("Order PDF Downloaded");

	}

	@Test(dependsOnMethods = "confirmOrderPage")
	public void orderHistoryTest() {

		log.info("Moving to orders page");
		abc.goToOrdersPage();
		log.info(driver.getTitle());

		opObj = new OrdersPageObject(driver);

		Assert.assertTrue(opObj.isProductAvailableInOrdersPage(productName));
		log.info("Product is available in Orders page..");
	}
}
