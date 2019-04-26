package Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sun.net.www.protocol.http.HttpURLConnection;

/**
 * Functions related to APIs are defined in this class
 * @author shubha
 *
 */
public class utilsWebServices {

	/**
	 * This function is used to create URL used for API testing
	 * @param serviceUrl
	 * @param apiName
	 * @return
	 */
	public static URL setURL(String serviceUrl,String apiName)
	{
		URL url=null;
		try
		{
			url=new URL(serviceUrl+apiName);
		}
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		return url;
	}
	
	/**
	 * This function creates the connection to call API
	 * @param url
	 * @param apiType
	 * @param apiName
	 * @return
	 */
	public static HttpURLConnection setHTTPConnection(String url,String apiType,String apiName)
	{
		HttpURLConnection conn=null;
		try
		{
			conn = (HttpURLConnection) setURL(url,apiName).openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(apiType);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", "0");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * Get service can be called here
	 * @param url
	 * @param apiName
	 * @return
	 */
	public static JSONArray callGetService(String url,String apiName)
	{
		JSONArray jarray = null;
		HttpURLConnection conn=null;
		try
		{
			conn=setHTTPConnection(url,"GET", apiName);
			if (conn.getResponseCode() == 200) 
			{
				utilsLog.writelog("Call success for - "+apiName);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
	
				JSONParser parser=new JSONParser();
				jarray=(JSONArray)parser.parse(br);
			}
			else
			{
				utilsLog.writelog("Call failed for - "+apiName);
				jarray=new JSONArray();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(conn!=null)
				conn.disconnect();
		}
		
		return jarray;
	}
	
	/**
	 * Post service can be called here
	 * @param url
	 * @param apiName
	 * @param obj
	 * @return
	 */
	public static JSONObject callPostService(String url,String apiName,JSONObject obj)
	{
		JSONObject jObj=null;
		HttpURLConnection conn=null;
		try
		{
			conn=setHTTPConnection(url,"POST", apiName);
			OutputStream os = conn.getOutputStream();
			os.write(obj.toJSONString().getBytes());
			os.flush();
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) 
			{
				utilsLog.writelog("Call success for - "+apiName);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
	
				JSONParser parser=new JSONParser();
				jObj=(JSONObject)parser.parse(br);
			}
			else
			{
				utilsLog.writelog("Call failed for - "+apiName);
				jObj=new JSONObject();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(conn!=null)
				conn.disconnect();
		}			

		return jObj;
	}
	
	/**
	 * Put service can be called here
	 * @param url
	 * @param apiName
	 * @param obj
	 * @return
	 */
	public static JSONObject callPutService(String url,String apiName,JSONObject obj)
	{
		JSONObject jObj=null;
		HttpURLConnection conn=null;
		try
		{
			conn=setHTTPConnection(url,"PUT", apiName);
			OutputStream os = conn.getOutputStream();
			os.write(obj.toJSONString().getBytes());
			os.flush();
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) 
			{
				utilsLog.writelog("Call success for - "+apiName);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));
	
				JSONParser parser=new JSONParser();
				jObj=(JSONObject)parser.parse(br);
			}
			else
			{
				utilsLog.writelog("Call failed for - "+apiName);
				jObj=new JSONObject();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(conn!=null)
				conn.disconnect();
		}	

		return jObj;
	}
}
