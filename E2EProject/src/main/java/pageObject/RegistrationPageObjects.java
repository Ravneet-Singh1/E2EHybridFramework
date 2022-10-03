package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationPageObjects {

	WebDriver driver;

	public RegistrationPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	By firstName = By.id("firstName");

	By lastName = By.id("lastName");

	By userEmail = By.id("userEmail");

	By userMobile = By.id("userMobile");

	public By occupation = By.cssSelector(".custom-select");

	By gender = By.xpath("//input[@value='Male']");

	By userPassword = By.id("userPassword");

	By confirmPassword = By.id("confirmPassword");

	By ageConfirmation = By.xpath("//input[@formcontrolname='required']");

	By register = By.id("login");

	public WebElement getFirstName() {
		return driver.findElement(firstName);
	}

	public WebElement getLastame() {
		return driver.findElement(lastName);
	}

	public WebElement getUserEmail() {
		return driver.findElement(userEmail);
	}

	public WebElement getUserMobile() {
		return driver.findElement(userMobile);
	}

	public WebElement getOccupation() {
		return driver.findElement(occupation);
	}

	public WebElement getGender() {
		return driver.findElement(gender);
	}

	public WebElement getUserPassword() {
		return driver.findElement(userPassword);
	}

	public WebElement getConfirmPassword() {
		return driver.findElement(confirmPassword);
	}

	public WebElement getAgeConfirmation() {
		return driver.findElement(ageConfirmation);
	}

	public WebElement getRegister() {
		return driver.findElement(register);
	}
}
