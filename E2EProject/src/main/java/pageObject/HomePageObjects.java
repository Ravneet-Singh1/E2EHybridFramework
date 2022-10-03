package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class HomePageObjects extends AbstractComponent {

	WebDriver driver;

	public HomePageObjects(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".btn1")
	WebElement register;

	@FindBy(id = "userEmail")
	WebElement emailId;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(id = "toast-container")
	WebElement loginError;

	public void loginPage(String email, String pswd) {
		emailId.sendKeys(email);
		password.sendKeys(pswd);
		login.click();
	}

	public void goToRegisterPage() {
		register.click();
	}

	public String getLoginError() {
		waitForWebElementTobeVisible(loginError);
		return loginError.getText();
	}

}
