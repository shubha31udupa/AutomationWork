package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import UIPages.dashboardPage;
import UIPages.userPage;
import Utils.utilsLog;
import Utils.utilsUi;
import Utils.utilsEnums.filterDropdown;
import Utils.utilsEnums.pages;

/**
 * This test cases class contains all test cases related to user filter
 * @author shubha
 *
 */
public class userTestCases extends baseTestCases {
	
	userPage uPage;
	SoftAssert sAssert;

	@Test(priority=1,groups={"web"})
	public void navigateToUsersTestCase()
	{
		utilsLog.writelog("-------- navigateToUsersTestCase Start------------");
		dashboardPage dPage=new dashboardPage(driver);
		uPage=dPage.clickOnUsers();
		Assert.assertTrue(uPage.pagevalidation(pages.userpage.getValue()), "User Page URL validation");
		utilsLog.writelog("-------- navigateToUsersTestCase End------------");
	}
	
	@Test(priority=1,dependsOnMethods={"navigateToUsersTestCase"},groups={"web"})
	public void userUIValidationTestCase()
	{
		utilsLog.writelog("-------- userUIValidationTestCase Start------------");
		Assert.assertTrue(uPage.basePageUiValidation(), "User Page Top Navigation UI validation");
		Assert.assertTrue(uPage.userPageUiValidation(), "User page UI validation");
		utilsLog.writelog("-------- userUIValidationTestCase End------------");
	}
	
	void clearfilterValidation(String paginationInfo,int numberOfRows)
	{
		uPage.clickOnClearFilter();
		sAssert.assertTrue(paginationInfo.contains(uPage.getPaginationInfo()), "Pagination info validation");
		sAssert.assertTrue(numberOfRows==uPage.getTotalNumberOfRows(), "Total number of rows display validation");
	}
	
	@Test(priority=1,dependsOnMethods={"userUIValidationTestCase"},groups={"web"})
	public void clearFilterTestCase()
	{
		sAssert=new SoftAssert();
		utilsLog.writelog("-------- clearFilterTestCase Start------------");
		
		String paginationInfo=uPage.getPaginationInfo();
		int numberOfRows=uPage.getTotalNumberOfRows();
		
		clearfilterValidation(paginationInfo, numberOfRows);
		uPage.filterAll(utilsUi.uiDetails.getProperty("username"), utilsUi.uiDetails.getProperty("email"), utilsUi.uiDetails.getProperty("fromdate"), 
				utilsUi.uiDetails.getProperty("todate"),filterDropdown.equals.getValue(), filterDropdown.equals.getValue());
		
		clearfilterValidation(paginationInfo, numberOfRows);
		sAssert.assertTrue(uPage.getEmail().equals(""), "Clear Email validation");
		sAssert.assertTrue(uPage.getUserName().equals(""), "Clear user name validation");
		sAssert.assertTrue(uPage.getFromDate().equals(""), "Clear from date validation");
		sAssert.assertTrue(uPage.getToDate().equals(""), "Clear to date validation");
		
		utilsLog.writelog("-------- clearFilterTestCase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
	
	void noRecordFoundValidation(String value)
	{
		uPage.clickOnFilter();
		sAssert.assertTrue(uPage.getNoRecordFoundMessage().contains(utilsUi.propLocalization.getProperty("filter.error")), "No record found test - "+value);
	}
	
	@Test(priority=1,dependsOnMethods={"userUIValidationTestCase"},groups={"web"})
	public void seachInvalidDataTestcase()
	{
		sAssert=new SoftAssert();
		utilsLog.writelog("-------- seachInvalidDataTestcase Start------------");
		
		uPage.clickOnClearFilter();
		uPage.enterUserName(utilsUi.uiDetails.getProperty("invalidusername"));
		noRecordFoundValidation(utilsUi.uiDetails.getProperty("invalidusername"));
		
		uPage.clickOnClearFilter();
		uPage.enterEmail(utilsUi.uiDetails.getProperty("invalidemail"));
		noRecordFoundValidation(utilsUi.uiDetails.getProperty("invalidemail"));
		
		uPage.clickOnClearFilter();
		uPage.enterFromDate(utilsUi.uiDetails.getProperty("invalidfromdate"));
		uPage.enterToDate(utilsUi.uiDetails.getProperty("invalidtodate"));
		noRecordFoundValidation("future date range");
		
		uPage.clickOnClearFilter();
		uPage.enterFromDate(utilsUi.uiDetails.getProperty("invalidfromdate2"));
		uPage.enterToDate(utilsUi.uiDetails.getProperty("invalidtodate2"));
		noRecordFoundValidation("invalid date range");
		
		uPage.filterAll(utilsUi.uiDetails.getProperty("invalidusername"), utilsUi.uiDetails.getProperty("invalidemail"), utilsUi.uiDetails.getProperty("invalidfromdate"), 
				utilsUi.uiDetails.getProperty("invalidtodate"),filterDropdown.equals.getValue(), filterDropdown.equals.getValue());
		noRecordFoundValidation("invalid data");
		
		utilsLog.writelog("-------- seachInvalidDataTestcase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
	
	@Test(priority=1,dependsOnMethods={"userUIValidationTestCase"},groups={"web"})
	public void searchEmptyTestcase()
	{
		sAssert=new SoftAssert();
		utilsLog.writelog("-------- searchEmptyTestcase Start------------");
		
		uPage.clickOnClearFilter();
		String paginationInfo=uPage.getPaginationInfo();
		int numberOfRows=uPage.getTotalNumberOfRows();
		
		uPage.clickOnFilter();
		sAssert.assertTrue(paginationInfo.contains(uPage.getPaginationInfo()), "Pagination info validation");
		sAssert.assertTrue(numberOfRows==uPage.getTotalNumberOfRows(), "Total number of rows display validation");
		
		utilsLog.writelog("-------- searchEmptyTestcase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
	
	@Test(priority=1,dependsOnMethods={"userUIValidationTestCase"},groups={"web"})
	public void searchValidTestcase()
	{
		sAssert=new SoftAssert();
		utilsLog.writelog("-------- searchValidTestcase Start------------");
		
		uPage.clickOnClearFilter();
		uPage.filterAll("sample126", "sample126@gmail.com", utilsUi.uiDetails.getProperty("fromdate"), 
				utilsUi.uiDetails.getProperty("todate"),filterDropdown.equals.getValue(), filterDropdown.equals.getValue());
		sAssert.assertTrue(uPage.getTotalNumberOfRows()==1, "Seaching 1 record");
		
		uPage.clickOnClearFilter();
		uPage.enterEmail("sample126");
		uPage.selectEmailFilterType(filterDropdown.startsWith.getValue());
		uPage.clickOnFilter();
		sAssert.assertTrue(uPage.getTotalNumberOfRows()==1, "Seaching email starts with");
		
		uPage.clickOnClearFilter();
		uPage.enterUserName("126");
		uPage.selectUserNameFilterType(filterDropdown.endsWith.getValue());
		uPage.clickOnFilter();
		sAssert.assertTrue(uPage.getTotalNumberOfRows()==1, "Seaching username ends with");
		
		uPage.clickOnClearFilter();
		uPage.enterEmail("126@gmail");
		uPage.selectEmailFilterType(filterDropdown.contains.getValue());
		uPage.clickOnFilter();
		sAssert.assertTrue(uPage.getTotalNumberOfRows()==1, "Seaching email contains");
		
		utilsLog.writelog("-------- searchValidTestcase End------------");
		sAssert.assertAll();
		sAssert =null;
	}

	@Test(priority=1,dependsOnMethods={"userUIValidationTestCase"},groups={"web"})
	public void searchStatusTestcase()
	{
		sAssert=new SoftAssert();
		utilsLog.writelog("-------- searchStatusTestcase Start------------");
		
		uPage.filterAll(utilsUi.uiDetails.getProperty("username"), utilsUi.uiDetails.getProperty("email"), utilsUi.uiDetails.getProperty("fromdate"), 
				utilsUi.uiDetails.getProperty("todate"),filterDropdown.contains.getValue(), filterDropdown.equals.getValue());
		
		sAssert.assertTrue(uPage.getEmailStatusMessage(filterDropdown.contains.getValue().toLowerCase()).contains(utilsUi.propLocalization.getProperty("user.email")+" "+
		filterDropdown.contains.getValue().toLowerCase()), "Email status msg verification");
		
		sAssert.assertTrue(uPage.getUserNameStatusMessage(filterDropdown.equals.getValue().toLowerCase()).contains(utilsUi.propLocalization.getProperty("user.username")+" "+
				filterDropdown.equals.getValue().toLowerCase()), "Username status msg verification");
		
		sAssert.assertTrue(uPage.getFromDateStatus().contains(utilsUi.propLocalization.getProperty("filter.greater")),"From date status msg verification");
		
		sAssert.assertTrue(uPage.getToDateStatus().contains(utilsUi.propLocalization.getProperty("filter.lesser")),"To date status message verification");
		
		utilsLog.writelog("-------- searchStatusTestcase End------------");
		sAssert.assertAll();
		sAssert =null;
	}
}
