package TestCases;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.utilsLog;
import Utils.utilsWebServices;
/**
 * This test case class is used to test APIs
 * @author shubha
 *
 */
public class webserviceTestCases extends baseTestCases {
	SoftAssert sAssert;
	JSONArray jArray;
	JSONObject obj;
	String url;
	
	@SuppressWarnings("unchecked")
	void createJsonObject(String apiName)
	{
		if(obj==null)
		{
			obj=new JSONObject();
			obj.put("userId", new Integer(101));
			obj.put("title", "This is title for "+apiName);
		}
		else
			obj.put("title", "This is title for "+apiName);
	}
	
	@BeforeClass(groups={"webservice"})
	@Parameters({"url"})
	public void setUrl(String url)
	{
		this.url=url;
	}
	
	@Test(groups={"webservice"})
	public void postsGetServiceTestcase()
	{
		utilsLog.writelog("-------- postsGetServiceTestcase Start------------");
		Assert.assertTrue(!utilsWebServices.callGetService(url,"posts").isEmpty(), "Post Get service test");
		utilsLog.writelog("-------- postsGetServiceTestcase End------------");
	}
	
	@Test(groups={"webservice"})
	public void postsPostServiceTestcase()
	{
		utilsLog.writelog("-------- postsPostServiceTestcase Start------------");
		createJsonObject("post");
		sAssert=new SoftAssert();
		
		JSONObject postObj=utilsWebServices.callPostService(url,"posts",obj);	
		sAssert.assertTrue(!postObj.isEmpty(), "Posts Post service Test");
		
		jArray=utilsWebServices.callGetService(url,"posts");
		sAssert.assertTrue(jArray.contains(postObj), "Posts Post service validation");
		
		sAssert.assertTrue(utilsWebServices.callPostService(url,"posts",postObj).isEmpty(), "Posts Post service Test(posting same object twice)");
		
		utilsLog.writelog("-------- postsPostServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
	
	@Test(groups={"webservice"})
	public void postsPutServiceTestcase()
	{
		utilsLog.writelog("-------- postsPutServiceTestcase Start------------");
		sAssert=new SoftAssert();
		obj=null;
		createJsonObject("post");
		
		obj=utilsWebServices.callPostService(url,"posts",obj);
		createJsonObject("post - edit");
		sAssert.assertTrue(!utilsWebServices.callPutService(url,"posts/"+obj.get("id").toString(),obj).isEmpty(),"Posts Put service test");
		
		jArray=utilsWebServices.callGetService(url,"posts");
		sAssert.assertTrue(jArray.contains(obj), "Posts Put service validation");
		
		sAssert.assertTrue(utilsWebServices.callPutService(url,"posts/abcdxxx",obj).isEmpty(),"Posts Put service test for id abcdxxx");
		
		utilsLog.writelog("-------- postsPutServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
	
	@Test(groups={"webservice"})
	public void commentsGetServiceTestcase()
	{
		utilsLog.writelog("-------- commentsGetServiceTestcase Start------------");
		Assert.assertTrue(!utilsWebServices.callGetService(url,"comments").isEmpty(), "Comments Get service test");
		utilsLog.writelog("-------- commentsGetServiceTestcase End------------");
	}
	
	@Test(groups={"webservice"})
	public void commentsPostServiceTestcase()
	{
		utilsLog.writelog("-------- commentsPostServiceTestcase Start------------");
		createJsonObject("comments");
		sAssert=new SoftAssert();
		
		JSONObject postObj=utilsWebServices.callPostService(url,"comments",obj);	
		sAssert.assertTrue(!postObj.isEmpty(), "Comments Post service Test");
		
		jArray=utilsWebServices.callGetService(url,"comments");
		sAssert.assertTrue(jArray.contains(postObj), "Comments Post service validation");
		
		sAssert.assertTrue(utilsWebServices.callPostService(url,"comments",postObj).isEmpty(), "Comments Post service Test(posting same object twice)");

		utilsLog.writelog("-------- commentsPostServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
	
	@Test(groups={"webservice"})
	public void commentsPutServiceTestcase()
	{
		utilsLog.writelog("-------- commentsPutServiceTestcase Start------------");
		sAssert=new SoftAssert();
		createJsonObject("comments");
		
		obj=utilsWebServices.callPostService(url,"comments",obj);
		createJsonObject("comments - edit");
		sAssert.assertTrue(!utilsWebServices.callPutService(url,"comments/"+obj.get("id").toString(),obj).isEmpty(),"Comments Put service test");
		
		jArray=utilsWebServices.callGetService(url,"comments");
		sAssert.assertTrue(jArray.contains(obj), "Comments Put service validation");
		
		sAssert.assertTrue(utilsWebServices.callPutService(url,"comments/abcdxxx",obj).isEmpty(),"Comments Put service test for id abcdxxx");
		
		utilsLog.writelog("-------- commentsPutServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
	

	@Test(groups={"webservice"})
	public void usersGetServiceTestcase()
	{
		utilsLog.writelog("-------- usersGetServiceTestcase Start------------");
		Assert.assertTrue(!utilsWebServices.callGetService(url,"users").isEmpty(), "Users Get service test");
		utilsLog.writelog("-------- usersGetServiceTestcase End------------");
	}
	
	@Test(groups={"webservice"})
	public void usersPostServiceTestcase()
	{
		utilsLog.writelog("-------- usersPostServiceTestcase Start------------");
		createJsonObject("users");
		sAssert=new SoftAssert();
		
		JSONObject postObj=utilsWebServices.callPostService(url,"users",obj);	
		sAssert.assertTrue(!postObj.isEmpty(), "Users Post service Test");
		
		jArray=utilsWebServices.callGetService(url,"users");
		sAssert.assertTrue(jArray.contains(postObj), "Users Post service validation");
		
		sAssert.assertTrue(utilsWebServices.callPostService(url,"users",postObj).isEmpty(), "Users Post service Test(posting same object twice)");
		
		utilsLog.writelog("-------- usersPostServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
	
	@Test(groups={"webservice"})
	public void usersPutServiceTestcase()
	{
		utilsLog.writelog("-------- usersPutServiceTestcase Start------------");
		sAssert=new SoftAssert();
		obj=null;
		createJsonObject("users");
		
		obj=utilsWebServices.callPostService(url,"users",obj);
		createJsonObject("users - edit");
		sAssert.assertTrue(!utilsWebServices.callPutService(url,"users/"+obj.get("id").toString(),obj).isEmpty(),"Users Put service test");
		
		jArray=utilsWebServices.callGetService(url,"users");
		sAssert.assertTrue(jArray.contains(obj), "Users Put service validation");
		
		sAssert.assertTrue(utilsWebServices.callPutService(url,"users/abcdxxx",obj).isEmpty(),"Users Put service test for id abcdxxx");
		
		utilsLog.writelog("-------- usersPutServiceTestcase End------------");
		sAssert.assertAll();
		sAssert=null;
	}
}
