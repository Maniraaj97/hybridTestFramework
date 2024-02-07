package frameworkPractice.pageObjects;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPractice.AbstractComponents.abstractComponent;

public class loginPage extends abstractComponent{
	
	WebDriver driver;
	public loginPage(WebDriver driver)
	{
		super(driver); // driver can be accessed to parent classes using super
		this.driver = driver;
		PageFactory.initElements(driver, this); // init is used for @findby
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
	//driver.findElement(By.id("login")).click();

@FindBy(id="userEmail")
WebElement userEmail;

@FindBy(id="userPassword")
WebElement userPassword;

@FindBy(id="login")
WebElement login;

@FindBy(css="[class*='flyInOut']")
WebElement errorMessage;


public productCatalogue loginAction(String email, String password)
{
	userEmail.sendKeys(email);
	userPassword.sendKeys(password);
	login.click();
	productCatalogue pc = new productCatalogue(driver);
	return pc;
}

public String getErrorMessage() {
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
}

public void goTO()
{
	driver.get("https://rahulshettyacademy.com/client");
	driver.manage().window().maximize();
}
}
