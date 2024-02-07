// parent to all the classes
// inheritance instead of object creation each time to reduce the memory
package frameworkPractice.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import frameworkPractice.pageObjects.verifyCart;
import frameworkPractice.pageObjects.verifyOrder;

public class abstractComponent {
	
	WebDriver driver;

	public abstractComponent(WebDriver driver) {
		this.driver = driver; // driver from child classes can be used locally in this class using this 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	//webdriver wait accept 2 parameters drivers and duration
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	public void waitForElementToAppear(By findBy) // return type of visibility is wait and not driver
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) // return type of visibility is wait and not driver
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementTODisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));	
	}
	
	public verifyCart goToCartPage() {
		cartHeader.click();
		verifyCart vc = new verifyCart(driver);
		return vc;
	}
	
	public verifyOrder goToOrderPage() {
		orderHeader.click();
		verifyOrder vo = new verifyOrder(driver);
		return vo;
	}
}
