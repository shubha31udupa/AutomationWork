package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import UIPages.dashboardPage;
import UIPages.newUserPage;
import UIPages.userPage;
import Utils.utilsLog;
import Utils.utilsUi;
import Utils.utilsEnums.pages;
/**
 * This test case class used for all tests related to create new user page
 * @author shubha
 *
 */
public class newUserTestCases extends baseTestCases {
	
	userPage uPage;
	newUserPage nuPage;
	SoftAssert sAssert;

	@Test(priority=1,groups={"web"})
	public void navigateToNewUsersTestCase()
	{
		utilsLog.writelog("-------- navigateToNewUsersTestCase Start------------");
		dashboardPage dPage=new dashboardPage(driver);
		uPage=dPage.clickOnUsers();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "User Page URL validation");
		
		nuPage=uPage.clickOnNewUser();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "New User Page URL validation");
		
		utilsLog.writelog("-------- navigateToNewUsersTestCase End------------");
	}
	
	@Test(priority=1,dependsOnMethods={"navigateToNewUsersTestCase"},groups={"web"})
	public void newuserUIValidationTestCase()
	{
		utilsLog.writelog("-------- newuserUIValidationTestCase Start------------");
		Assert.assertTrue(nuPage.basePageUiValidation(), "New User Page Top Navigation UI validation");
		Assert.assertTrue(nuPage.newUserPageUiValidation(), "New User page UI validation");
		utilsLog.writelog("-------- newuserUIValidationTestCase End------------");
	}
	
	@Test(priority=1,dependsOnMethods={"newuserUIValidationTestCase"},groups={"web"})
	public void cancelButtonTestcase()
	{
		utilsLog.writelog("-------- cancelButtonTestcase Start------------");
		
		uPage= nuPage.clickOnCancel();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "User Page URL validation");
		
		nuPage=uPage.clickOnNewUser();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "New User Page URL validation");
		
		utilsLog.writelog("-------- cancelButtonTestcase End------------");
	}
	
	@Test(priority=1,dependsOnMethods={"newuserUIValidationTestCase"},groups={"web"})
	public void missingValueTestCase()
	{
		utilsLog.writelog("-------- missingValueTestCase Start------------");
		sAssert=new SoftAssert();
		
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getUsernameError().contains(utilsUi.propLocalization.getProperty("newuser.error.blank")), "User Name error message");
		sAssert.assertTrue(nuPage.getPasswordError().contains(utilsUi.propLocalization.getProperty("newuser.error.blank")), "Password error message");
		sAssert.assertTrue(nuPage.getEmailError().contains(utilsUi.propLocalization.getProperty("newuser.error.invalid")), "Email error message");
		
		nuPage.enterUserName(utilsUi.uiDetails.getProperty("username"));
		nuPage.enterPassword(utilsUi.uiDetails.getProperty("password"));
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getEmailError().contains(utilsUi.propLocalization.getProperty("newuser.error.invalid")), "Email error message");
		
		utilsLog.writelog("-------- missingValueTestCase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
	
	@Test(priority=1,dependsOnMethods={"missingValueTestCase"},groups={"web"})
	public void invalidEmailTestCase()
	{
		utilsLog.writelog("-------- invalidEmailTestCase Start------------");
		sAssert=new SoftAssert();
		
		nuPage.enterEmail(utilsUi.uiDetails.getProperty("invalidemail"));
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getEmailError().contains(utilsUi.propLocalization.getProperty("newuser.error.invalid")), "Email error message1");
		
		nuPage.enterEmail(utilsUi.uiDetails.getProperty("invalidemail2"));
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getEmailError().contains(utilsUi.propLocalization.getProperty("newuser.error.invalid")), "Email error message2");
		
		nuPage.enterUserName(utilsUi.uiDetails.getProperty("invalidusername"));
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getUsernameError().contains(utilsUi.propLocalization.getProperty("newuser.error.format")), "User error message1");
		
		nuPage.enterUserName(utilsUi.uiDetails.getProperty("username"));//existing user
		nuPage.clickOnCreateUser();
		sAssert.assertTrue(nuPage.getUsernameError().contains(utilsUi.propLocalization.getProperty("newuser.error.taken")), "User error message2");
		
		utilsLog.writelog("-------- invalidEmailTestCase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
	
	@Test(priority=1,dependsOnMethods={"missingValueTestCase"},groups={"web"})
	public void validUserTestCase()
	{
	
		utilsLog.writelog("-------- validUserTestCase Start------------");
		String email=utilsUi.randomEmail();
		uPage= nuPage.createNewUser(email+utilsUi.uiDetails.getProperty("username"), utilsUi.uiDetails.getProperty("password"), email+utilsUi.uiDetails.getProperty("email"));
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "User Page URL validation");
		
		uPage = nuPage.clickOnUsers();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "User Page URL validation");
		uPage.enterEmail(email+utilsUi.uiDetails.getProperty("email"));
		uPage.clickOnFilter();
		Assert.assertTrue(uPage.getTotalNumberOfRows()==1, "Seaching email contains");
		
		utilsLog.writelog("-------- validUserTestCase End------------");
	}

}
