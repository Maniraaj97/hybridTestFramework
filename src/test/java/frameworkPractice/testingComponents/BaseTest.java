package frameworkPractice.testingComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import frameworkPractice.pageObjects.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public loginPage lp;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\frameworkPractice\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") :
				prop.getProperty("browser");
		//Login page automation
		
		if (browserName.contains("chrome"))
		{
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")) {
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
						
				
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver ) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	
	@BeforeMethod
	public loginPage launchBrowser() throws IOException {
		driver = initializeDriver();
		lp = new loginPage(driver);
		lp.goTO();
		return lp;
		}
	
	@AfterMethod
	public void lastMethod() {
		driver.close();
	}

}