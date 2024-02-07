package frameworkPractice.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameworkPractice.pageObjects.loginPage;

public class standaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		//Login page automation
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("maniraajsn@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("M@n1raaj");
		driver.findElement(By.id("login")).click();
		
		loginPage lp = new loginPage(driver);
		
		//webdriver wait accept 2 parameters drivers and duration
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//Get the list of elements from the page
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//stream method which perofrms loop and if in a single line. used above java 1.8.
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		// last of type used to get the last item
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		

		
		//wait till loading icon disappears and confirm if product is added using flash display
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		// click on cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// check if right item is added to the cart and submit
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean Match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//enter details and place order
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "canada").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String cnfMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(cnfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
