package abstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
	WebElement orders;

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement cart;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

//	By cart = By.xpath("(//button[@class='btn btn-custom'])[3]");

	public void goToCart() throws InterruptedException {
		Thread.sleep(1500);
//		waitForElementTobeInvisible(spinner);
		cart.click();
	}

	public void goToOrdersPage() {
		orders.click();
	}

	public void waitForElementTobeVisible(By VisibleFindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(VisibleFindBy));
	}

	public void waitForElementTobeInvisible(WebElement loading) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf((loading)));
	}

	public void waitForWebElementTobeVisible(WebElement visible) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(visible));
	}

	public void closeBrowser() {
		driver.quit();
	}
}
