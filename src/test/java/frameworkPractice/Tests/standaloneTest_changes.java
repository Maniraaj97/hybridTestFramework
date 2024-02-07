package frameworkPractice.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkPractice.pageObjects.checkOutPage;
import frameworkPractice.pageObjects.loginPage;
import frameworkPractice.pageObjects.orderConfirmation;
import frameworkPractice.pageObjects.productCatalogue;
import frameworkPractice.pageObjects.verifyCart;
import frameworkPractice.pageObjects.verifyOrder;
import frameworkPractice.testingComponents.BaseTest;

public class standaloneTest_changes extends BaseTest{

	String productName = "ZARA COAT 3";
	String countryName = "Canada";
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
	@Test(dataProvider="getData")
	public void standaloneTest_Changes(HashMap<String,String> input) throws IOException {
	
		productCatalogue pc = lp.loginAction(input.get("email"), input.get("pwd"));
		
		List <WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));
		verifyCart vc = pc.goToCartPage();
			
		Boolean Match = vc.CartVerification(input.get("product"));
		Assert.assertTrue(Match);
		checkOutPage cop = vc.goToCheckOut();
		
		cop.selectCountry(countryName);
		orderConfirmation oc  = cop.submit();
		
		String cnfMsg = oc.getOrderCnf();
		Assert.assertTrue(cnfMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"standaloneTest_Changes"})
	
	public void OrderHistoryTest() throws IOException {
			productCatalogue pc = lp.loginAction("maniraajsn@gmail.com", "M@n1raaj");
			verifyOrder vo = pc.goToOrderPage();
			Assert.assertTrue(vo.OrderVerification(productName));
		}
	
	
		
	@DataProvider
	public Object[][] getData() 
	{
		 
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("email", "maniraajsn@gmail.com");
			map.put("pwd", "M@n1raaj");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new HashMap<String, String>();
			map1.put("email", "apple123@gmail.com");
			map1.put("pwd", "Apple@123");
			map1.put("product", "ADIDAS ORIGINAL");
			
			return new Object[][] {{map},{map1}};
	}
	
}
