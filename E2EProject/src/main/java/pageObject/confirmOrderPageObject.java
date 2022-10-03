package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class confirmOrderPageObject extends AbstractComponent {

	WebDriver driver;

	public confirmOrderPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	@FindBy(css = ".mt-3.mb-3")
	WebElement downloadOrderDetails;

	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

	public void downloadOrderDetails() {
		downloadOrderDetails.click();
	}

}
