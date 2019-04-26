package UIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.utilsLog;
import Utils.utilsUi;

/**
 * This class contains functions related to create new user page
 * @author shubha
 *
 */
public class newUserPage extends basePage {
	
	newUserPage(WebDriver driver)
	{
		super(driver);
	}
	
	By adminLocator=By.linkText(utilsUi.propLocalization.getProperty("page.admin").toUpperCase());
	By pageTitleLocator=By.id("page_title");
	By usersLocator=By.linkText(utilsUi.propLocalization.getProperty("newuser.users").toUpperCase());
	By usernameTextLocator=By.xpath("//label[@for='user_username']");
	By passwordTextLocator=By.xpath("//label[@for='user_password']");
	By emailTextLocator=By.xpath("//label[@for='user_email']");
	By usernameLocator=By.id("user_username");
	By passwordLocator=By.id("user_password");
	By emailLocator=By.id("user_email");
	By usernameErrorLocator=By.xpath("//li[@id='user_username_input']/p");
	By passwordErrorLocator=By.xpath("//li[@id='user_password_input']/p");
	By emailErrorLocator=By.xpath("//li[@id='user_email_input']/p");
	By createUserButtonLocator=By.xpath("//input[@value='"+utilsUi.propLocalization.getProperty("newuser.createuser")+"']");
	By cancelButtonLocator=By.linkText(utilsUi.propLocalization.getProperty("newuser.cancel"));

	public boolean newUserPageUiValidation()
	{
		boolean returnValue=false;
		
		returnValue=driver.findElement(adminLocator)!=null;
		returnValue=returnValue&&driver.findElement(usersLocator)!=null;
		returnValue=returnValue&&driver.findElement(pageTitleLocator).getText().contains(utilsUi.propLocalization.getProperty("newuser.heading"));
		returnValue=returnValue&&driver.findElement(usernameTextLocator).getText().contains(utilsUi.propLocalization.getProperty("newuser.username"));
		returnValue=returnValue&&driver.findElement(passwordTextLocator).getText().contains(utilsUi.propLocalization.getProperty("newuser.password"));
		returnValue=returnValue&&driver.findElement(emailTextLocator).getText().contains(utilsUi.propLocalization.getProperty("newuser.email"));
		returnValue=returnValue&&driver.findElement(cancelButtonLocator)!=null;
		returnValue=returnValue&&driver.findElement(createUserButtonLocator)!=null;
		
		return returnValue;
	}
	
	public void enterUserName(String name)
	{
		utilsUi.enterValue(driver.findElement(usernameLocator), name);
	}
	
	public void enterEmail(String email)
	{
		utilsUi.enterValue(driver.findElement(emailLocator), email);
	}
	
	public void enterPassword(String password)
	{
		utilsUi.enterValue(driver.findElement(passwordLocator), password);
	}
	
	String readError(By locator)
	{
		if(driver.findElements(locator).size()>0)
			return driver.findElement(locator).getText();
		else
		{
			utilsLog.writelog("Can not find error message");
			return null;
		}
	}
	
	public String getUsernameError()
	{
		return readError(usernameErrorLocator);
	}
	
	public String getPasswordError()
	{
		return readError(passwordErrorLocator);
	}
	
	public String getEmailError()
	{
		return readError(emailErrorLocator);
	}
	
	public userPage clickOnCreateUser()
	{
		driver.findElement(createUserButtonLocator).click();
		return new userPage(driver);
	}
	
	public userPage clickOnCancel()
	{
		driver.findElement(cancelButtonLocator).click();
		return new userPage(driver);
	}
	
	public userPage createNewUser(String username,String password, String email)
	{
		enterUserName(username);
		enterPassword(password);
		enterEmail(email);
		return clickOnCreateUser();
	}
}
