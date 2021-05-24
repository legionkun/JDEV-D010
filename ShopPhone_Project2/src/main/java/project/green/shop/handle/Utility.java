package project.green.shop.handle;

import javax.servlet.http.HttpServletRequest;

public class Utility {
	public static String getSiteURL(HttpServletRequest request)
	{
		String getURL= request.getRequestURL().toString();
			return getURL.replace(request.getServletPath(),"");	
	}
}
