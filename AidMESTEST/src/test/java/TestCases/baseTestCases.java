package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import Utils.utilsLog;
import Utils.utilsUi;

/**
 * This is the test case setup class. Other test cases class can be derived from it
 * @author shubha
 *
 */
public class baseTestCases {
	static String path;
	WebDriver driver;
	
	@BeforeSuite(groups={"webservice","web"})
	@Parameters({"fileNameLocalization","fileNameUIdetails"})
	public void setupProperty(String fileNameLocalization,String fileNameUIdetails)
	{
		getFilePath();
		utilsLog.createlog();
		utilsUi.loadProperties(path+"\\"+fileNameLocalization,true);
		utilsUi.loadProperties(path+"\\"+fileNameUIdetails,false);
	}
	
	void getFilePath()
	{
		try {
			path=new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass(groups={"web"})
	public void closeBrowsers()
	{
		if(driver instanceof FirefoxDriver)
		{
			try
			{
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Thread.sleep(5000);
				Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
				Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
			}
			catch(Exception ex)
			{}
		}
		else
			driver.quit();
	}
	
	@BeforeClass(groups={"web"})
	@Parameters({"browserType","url","driverPath"})
	public WebDriver browserSetup(String browserType,String url,String gecoPath)
	{
		switch (browserType)
		{
		case "FF":
			System.setProperty("webdriver.gecko.driver",gecoPath);
			driver= new FirefoxDriver();
			break;
		case "ED":
			System.setProperty("webdriver.edge.driver",gecoPath);
			driver=new EdgeDriver();
			break;
		case "CH":
			System.setProperty("webdriver.chrome.driver",gecoPath);
			 ChromeOptions options=new ChromeOptions();
			    options.addArguments("start-maximized");
			    driver = new ChromeDriver(options);

			break;
			default:
				System.setProperty("webdriver.gecko.driver",gecoPath);
				driver= new FirefoxDriver();
				break;
		}
		
		driver.get(url);
		if(!(driver instanceof ChromeDriver))
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

}
