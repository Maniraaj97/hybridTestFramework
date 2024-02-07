package frameworkPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import frameworkPractice.AbstractComponents.abstractComponent;

public class checkOutPage extends abstractComponent{
	
	WebDriver driver;
	public checkOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
	//driver.findElement(By.id("login")).click();

@FindBy(css="[placeholder='Select Country']")
WebElement countryTextBox;

@FindBy(css=".ta-item")
WebElement visibleOptions;

@FindBy(css=".action__submit")
WebElement submitButton;

By visibleElements = By.cssSelector(".ta-results");

public void selectCountry(String countryName) {
	//enter details and place order
		Actions a = new Actions(driver);
		a.sendKeys(countryTextBox, countryName).build().perform();
		waitForElementToAppear(visibleElements);
		visibleOptions.click();
}		
		
		
public orderConfirmation submit() {		
		
submitButton.click();	
orderConfirmation oc = new orderConfirmation(driver);
return oc;
}
}