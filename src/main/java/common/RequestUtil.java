package common;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static int getInt(HttpServletRequest request, String name) {
		int result = -1;
		Object value = request.getSession().getAttribute(name);
		if (value != null) {
			try {
				result = (int)value;
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static boolean isAdmin(HttpServletRequest request) {
		int auth = RequestUtil.getInt(request, Configure.SSM_AUTHOR);
		return (auth == Configure.AUTHOR_ADMIN);
	}

	public static boolean isCreater(HttpServletRequest request) {
		int auth = RequestUtil.getInt(request, Configure.SSM_AUTHOR);
		return (auth == Configure.AUTHOR_CREATER);
	}
	
	
}
