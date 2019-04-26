package Utils;

/**
 * Enums used in the applications defined in this class
 * @author shubha
 *
 */
public class utilsEnums {
	/**
	 * Used for pages
	 * @author shubha
	 *
	 */
	public static enum pages
	{
		userpage("/users"),newuserpage("/users/new"); 
		
		private String value;
		
		pages(String value)
		{
			this.value=value;
		}
		
		public String getValue()
		{
			return value;
		}
	};

	/**
	 * Used for drop down in user filter page
	 * @author shubha
	 *
	 */
	public static enum filterDropdown
	{
		contains("Contains"),equals("Equals"),startsWith("Starts with"),endsWith("Ends with"); 
		
		private String value;
		
		filterDropdown(String value)
		{
			this.value=value;
		}
		
		public String getValue()
		{
			return value;
		}
	};
}
