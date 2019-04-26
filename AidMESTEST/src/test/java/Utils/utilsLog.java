package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class is used for logging the logs
 * @author shubha
 *
 */
public class utilsLog {
	static Logger logger;
	
	/**
	 * Function to create a log file
	 * @return
	 */
	public static Logger createlog()
	{
		logger=Logger.getLogger("webUI");
        
	       try 
	       {
	    	   PropertyConfigurator.configure(new File(".").getCanonicalPath()+"\\"+"log4j.properties");
			} 
		    catch (IOException e) 
		    {
			}
	       return logger;
	}
	/**
	 * Function to log the info into log file
	 * @param msg
	 */
	public static void writelog(String msg)
	{
		logger.info(msg);
	}

}
