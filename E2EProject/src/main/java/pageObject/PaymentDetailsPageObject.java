package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class PaymentDetailsPageObject extends AbstractComponent {

	WebDriver driver;

	public PaymentDetailsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement countryList;

	@FindBy(css = ".ta-results")
	WebElement expectedCountries;

	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement actualCountry;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	public By expectedCountry = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		waitForElementTobeVisible(expectedCountry);
		actualCountry.click();
	}

	public WebElement placeOrder() {
		return placeOrder;
	}

}
