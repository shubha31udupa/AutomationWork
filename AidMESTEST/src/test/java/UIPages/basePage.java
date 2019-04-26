package UIPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.utilsLog;
import Utils.utilsUi;

/**
 * This is the base class used for all page classes created
 * @author shubha
 *
 */
public class basePage {
	
	WebDriver driver;

	basePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By siteTitleLocator=By.id("site_title");
	By dashboardLocator=By.id("dashboard");
	By productsLocator=By.id("products");
	By ordersLocator=By.id("orders");
	By usersLocator=By.id("users");
	By activeAdminDemoLocator=By.xpath("//h3[text()='"+utilsUi.propLocalization.getProperty("page.activeadmindemo")+"']");
	By activeAdminDemoDescLocator=By.xpath("//div[@id='active-admin-demo_sidebar_section']/div[@class='panel_contents']");
			//By.xpath("//h3[text()='"+utilsUi.propLocalization.getProperty("page.activeadmindemo.desc")+"']");
	
	public boolean pagevalidation(String msg)
	{
		try
		{
			utilsUi.waitUntilNextPageIsDisplayed(driver, msg);
		}
		catch(Exception ex)
		{
			utilsLog.writelog("Error while reading page -"+msg);
		}
		
		return driver.getCurrentUrl().contains(msg);
	}
	
	public boolean basePageUiValidation()
	{
		boolean returnVlaue;
		returnVlaue=driver.findElement(siteTitleLocator).getText().contains(utilsUi.propLocalization.getProperty("page.activeadmin"));
		returnVlaue=returnVlaue&&driver.findElement(dashboardLocator).getText().contains(utilsUi.propLocalization.getProperty("page.dashboard"));
		returnVlaue=returnVlaue&&driver.findElement(productsLocator).getText().contains(utilsUi.propLocalization.getProperty("page.products"));
		returnVlaue=returnVlaue&&driver.findElement(ordersLocator).getText().contains(utilsUi.propLocalization.getProperty("page.orders"));
		returnVlaue=returnVlaue&&driver.findElement(usersLocator).getText().contains(utilsUi.propLocalization.getProperty("page.users"));
		returnVlaue=returnVlaue&&driver.findElement(activeAdminDemoLocator)!=null;
		returnVlaue=returnVlaue&&driver.findElement(activeAdminDemoDescLocator).getText().contains(utilsUi.propLocalization.getProperty("page.activeadmindemo.desc"));
		return returnVlaue;
	}
	
	public void clickOnDashboard()
	{
		driver.findElement(dashboardLocator).click();
	}
	
	public void clickOnProduct()
	{
		driver.findElement(productsLocator).click();
	}

	public void clickOnOrders()
	{
		driver.findElement(ordersLocator).click();
	}
	
	public userPage clickOnUsers()
	{
		driver.findElement(usersLocator).click();
		return new userPage(driver);
	}
}
