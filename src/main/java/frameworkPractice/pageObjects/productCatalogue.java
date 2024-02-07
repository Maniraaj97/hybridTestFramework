package frameworkPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPractice.AbstractComponents.abstractComponent;

public class productCatalogue extends abstractComponent{
	
	WebDriver driver;
	public productCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
	//driver.findElement(By.id("login")).click();

@FindBy(css=".mb-3")
List <WebElement> products; 

@FindBy(css=".ng-animating")
WebElement loadIcon;

@FindBy(css = "[routerlink*='cart']")
WebElement submitbutton;

By prod = By.cssSelector(".mb-3"); // findby is used only for driver NA for by 
By addToCart = By.cssSelector(".card-body button:last-of-type");
By toastMsg = By.cssSelector("#toast-container");

public List<WebElement> getProductList()
{
	waitForElementToAppear(prod);
	return products;
}


public WebElement getProductByName(String productName) {
	WebElement prod = getProductList().stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	return prod;
}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);	
		waitForElementTODisappear(loadIcon);
	}
}
