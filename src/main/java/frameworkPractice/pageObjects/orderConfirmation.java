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

public class orderConfirmation extends abstractComponent{
	
	WebDriver driver;
	public orderConfirmation(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
	//driver.findElement(By.id("login")).click();

@FindBy(css=".hero-primary")
WebElement cnfText;

public String getOrderCnf() {
	return cnfText.getText();
}
}