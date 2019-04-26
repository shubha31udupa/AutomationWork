package Utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Common ui functions/events defined in this class
 * @author shubha
 *
 */
public class utilsUi {
	
	public static Properties propLocalization;
	public static Properties uiDetails;
	
	/**
	 * Function to load global property files(localization and uidetails)
	 * @param filename
	 * @param localization
	 */
	public static void loadProperties(String filename,boolean localization) 
	{
		Properties prop=new Properties();
			try
			{
//				prop.load(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
				prop.load(new FileInputStream(filename));
				utilsLog.writelog("Property file loaded:"+filename);
			}
			catch(Exception ex)
			{
				utilsLog.writelog("Can not load property file:"+filename);
			}
			
		if(localization)
			propLocalization=prop;
		else
			uiDetails=prop;
	}
	
	/**
	 * Explicit wait till next page to display
	 * @param driver
	 * @param pageName
	 */
	public static boolean waitUntilNextPageIsDisplayed(WebDriver driver, String pageName)
	{
		boolean returnValue=false;
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 15);
			returnValue= wait.until(ExpectedConditions.urlContains(pageName));
		}
		catch(Exception ex)
		{
			utilsLog.writelog("Given url not found - "+pageName);
		}
		return returnValue;
	}
	
	/**
	 * function to enter a value in text box
	 * @param elementName
	 * @param elementValue
	 */
	public static void enterValue(WebElement elementName,String elementValue)
	{
		elementName.clear();
		elementName.sendKeys(elementValue);
	}
	
	/**
	 * function to select value in dropdown list
	 * @param ele
	 * @param ddlValue
	 */
	public static void dropdownselect(WebElement ele,String ddlValue)
	{
		Select ddl=new Select(ele);
		ddl.selectByVisibleText(ddlValue);
	}
	
	/**
	 * This function is used to create random test user
	 * @param email
	 * @return
	 */
	  public static String randomEmail(){
	        return UUID.randomUUID().toString();
	    }		

}
