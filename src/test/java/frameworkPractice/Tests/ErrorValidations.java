package frameworkPractice.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.pageObjects.productCatalogue;
import frameworkPractice.pageObjects.verifyCart;
import frameworkPractice.testingComponents.BaseTest;

public class ErrorValidations extends BaseTest{

	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	@Test
	public void LoginErrorValidation() throws IOException {
	
				
		productCatalogue pc = lp.loginAction("maniraajsn@gmail.com", "M@nfnfhhf");
		//System.out.println(lp.getErrorMessage());
		Assert.assertEquals("Incorrect email password.", lp.getErrorMessage());
			}
	/*@Test
	public void ProductErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 33";
		//String countryName = "Canada";
		
		productCatalogue pc = lp.loginAction("maniraajsn@gmail.com", "M@n1raaj");
		
		List <WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		verifyCart vc = pc.goToCartPage();
			
		Boolean Match = vc.CartVerification(productName);
		System.out.println(Match);
		Assert.assertFalse(Match);
	}*/
}
