package UIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.utilsUi;

/**
 * This class is created to handle user page
 * @author shubha
 *
 */
public class userPage extends basePage {
	
	userPage(WebDriver driver)
	{
		super(driver);
	}

	By adminLocator=By.linkText(utilsUi.propLocalization.getProperty("page.admin").toUpperCase());
	By pageTitleLocator=By.id("page_title");
	By selectAllLocator=By.id("collection_selection_toggle_all");
	By batchActionLocator=By.linkText(utilsUi.propLocalization.getProperty("user.batchaction"));
	By iDLocator=By.linkText(utilsUi.propLocalization.getProperty("user.id"));
	By userNameLocator=By.linkText(utilsUi.propLocalization.getProperty("user.username"));
	By createdLocator=By.linkText(utilsUi.propLocalization.getProperty("user.created"));
	By emailLocator=By.linkText(utilsUi.propLocalization.getProperty("user.email"));
	By rowsLocator=By.xpath("//tbody/tr");
	By newuserLocator=By.linkText(utilsUi.propLocalization.getProperty("newuser.heading"));
	By filterTextLocator=By.xpath("//h3[text()='"+utilsUi.propLocalization.getProperty("filter.heading")+"']");
	By filterUserNameTextLocator=By.xpath("//label[@for='q_username']");
	By filterEmailTextLocator=By.xpath("//label[@for='q_email']");
	By filterCreatedTextLocator=By.xpath("//label[text()='"+utilsUi.propLocalization.getProperty("filter.created")+"']");
	By usernameSelectLocator=By.xpath("//label[@for='q_username']/following-sibling::select");
	By emailSelectLocator=By.xpath("//label[@for='q_email']/following-sibling::select");
	By filterUsernameLocator=By.id("q_username");
	By filterEmailLocator=By.id("q_email");
	By fromDateLocator=By.id("q_created_at_gteq_datetime");
	By toDateLocator=By.id("q_created_at_lteq_datetime");
	By clearFilterButtonLocator=By.linkText(utilsUi.propLocalization.getProperty("filter.clearfilter"));
	By filterButtonLocator=By.xpath("//input[@type='submit' and @value='"+utilsUi.propLocalization.getProperty("filter.filter")+"']");
	By paginationInfoLocator=By.className("pagination_information");
	By noUserFoundLocator=By.className("blank_slate");
	By searchStatusLocator=By.xpath("//h3[text()='"+utilsUi.propLocalization.getProperty("filter.status")+"']");
	By currentFiltersLocator=By.tagName("h4");
	By fromDateStatusLocator=By.cssSelector(".current_filter.current_filter_created_at_gteq_datetime");
	By toDateStatusLocator=By.cssSelector(".current_filter.current_filter_created_at_lteq_datetime");
	String emailStatus=".current_filter.current_filter_email_",usernameStatus=".current_filter.current_filter_username_";
	
	public boolean userPageUiValidation()
	{
		boolean returnValue;
		returnValue=driver.findElement(adminLocator)!=null;
		returnValue=returnValue&&driver.findElement(pageTitleLocator).getText().contains(utilsUi.propLocalization.getProperty("user.heading"));
		returnValue=returnValue&&driver.findElement(batchActionLocator).getText().contains(utilsUi.propLocalization.getProperty("user.batchaction"));
		returnValue=returnValue&&driver.findElement(iDLocator)!=null;
		returnValue=returnValue&&driver.findElement(userNameLocator)!=null;
		returnValue=returnValue&&driver.findElement(createdLocator)!=null;
		returnValue=returnValue&&driver.findElement(emailLocator)!=null;
		returnValue=returnValue&&driver.findElement(newuserLocator)!=null;
		returnValue=returnValue&&driver.findElement(filterTextLocator)!=null;
		returnValue=returnValue&&driver.findElement(filterUserNameTextLocator).getText().contains(utilsUi.propLocalization.getProperty("user.username").toUpperCase());
		returnValue=returnValue&&driver.findElement(filterEmailTextLocator).getText().contains(utilsUi.propLocalization.getProperty("user.email").toUpperCase());
		returnValue=returnValue&&driver.findElement(filterCreatedTextLocator)!=null;
		return returnValue;
	}
	
	public newUserPage clickOnNewUser()
	{
		driver.findElement(newuserLocator).click();
		return new newUserPage(driver);
	}
	
	public void clickOnFilter()
	{
		driver.findElement(filterButtonLocator).click();
	}
	
	public void clickOnClearFilter()
	{
		driver.findElement(clearFilterButtonLocator).click();
	}
	
	public void enterUserName(String name)
	{
		utilsUi.enterValue(driver.findElement(filterUsernameLocator), name);
	}
	
	public void enterEmail(String email)
	{
		utilsUi.enterValue(driver.findElement(filterEmailLocator), email);
	}
	
	public void enterFromDate(String date)
	{
		utilsUi.enterValue(driver.findElement(fromDateLocator), date);
	}
	
	public void enterToDate(String date)
	{
		utilsUi.enterValue(driver.findElement(toDateLocator), date);
	}
	
	public void selectUserNameFilterType(String type)
	{
		utilsUi.dropdownselect(driver.findElement(usernameSelectLocator), type);
	}
	
	public void selectEmailFilterType(String type)
	{
		utilsUi.dropdownselect(driver.findElement(emailSelectLocator), type);
	}
	
	public void filterAll(String userName,String email,String fromDate,String toDate,String emailType,String usernameType)
	{
		selectUserNameFilterType(usernameType);
		enterUserName(userName);
		selectEmailFilterType(emailType);
		enterEmail(email);
		enterFromDate(fromDate);
		enterToDate(toDate);
		clickOnFilter();
	}
	
	public int getTotalNumberOfRows()
	{
		return driver.findElements(rowsLocator).size();
	}
	
	public String getPaginationInfo()
	{
		return driver.findElement(paginationInfoLocator).getText();
	}
	
	public String getUserName()
	{
		return driver.findElement(filterUsernameLocator).getText();
	}
	
	public String getEmail()
	{
		return driver.findElement(filterEmailLocator).getText();
	}
	
	public String getToDate()
	{
		return driver.findElement(toDateLocator).getText();
	}
	
	public String getFromDate()
	{
		return driver.findElement(fromDateLocator).getText();
	}
	
	public String getNoRecordFoundMessage()
	{
		return driver.findElement(noUserFoundLocator).getText();
	}
	
	public String getEmailStatusMessage(String status)
	{
		return driver.findElement(By.cssSelector(emailStatus+status)).getText();
	}
	
	public String getUserNameStatusMessage(String status)
	{
		return driver.findElement(By.cssSelector(usernameStatus+status)).getText();
	}
	
	public String getFromDateStatus()
	{
		return driver.findElement(fromDateStatusLocator).getText();
	}
	
	public String getToDateStatus()
	{
		return driver.findElement(toDateStatusLocator).getText();
	}
}

