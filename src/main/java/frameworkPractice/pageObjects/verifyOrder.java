package frameworkPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import frameworkPractice.AbstractComponents.abstractComponent;

public class verifyOrder extends abstractComponent{
	
	WebDriver driver;
	public verifyOrder(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
	//driver.findElement(By.id("login")).click();

@FindBy(css="tr td:nth-child(3)")
List <WebElement> orderProduct;

@FindBy(css=".totalRow button")
WebElement checkOutButton;
public Boolean OrderVerification(String productName) {
		
	Boolean Match = orderProduct.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
	return Match;
}
}
 