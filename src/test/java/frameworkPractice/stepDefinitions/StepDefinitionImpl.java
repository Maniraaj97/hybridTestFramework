package frameworkPractice.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import frameworkPractice.pageObjects.checkOutPage;
import frameworkPractice.pageObjects.loginPage;
import frameworkPractice.pageObjects.orderConfirmation;
import frameworkPractice.pageObjects.productCatalogue;
import frameworkPractice.pageObjects.verifyCart;
import frameworkPractice.testingComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	public loginPage lp;
	public productCatalogue pc;
	public orderConfirmation oc ;
	//public verifyCart vc;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		lp = launchBrowser();
	}
	
	@Given ("^Logged in with username (.+) and pwd (.+)$")
	public void Logged_in_with_username_and_pwd(String username, String pwd) {
		pc = lp.loginAction(username,pwd);
	}
	
	@When ("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {
		List <WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		//vc = pc.goToCartPage();
	}
	
	@When ("^Checkout (.+) and submit the Order at (.+)$")
	public void Checkout_and_submit_the_Order(String productName, String countryName) {
		verifyCart vc = pc.goToCartPage();
		
		Boolean Match = vc.CartVerification(productName);
		Assert.assertTrue(Match);
		checkOutPage  cop = vc.goToCheckOut();
		
		cop.selectCountry(countryName);
		oc = cop.submit();
		}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		
		String cnfMsg = oc.getOrderCnf();
		Assert.assertTrue(cnfMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed_(String string1) {
		
		Assert.assertEquals(string1, lp.getErrorMessage());	
		driver.close();
		}
		
}
