package com.platform.frame.util;

import javax.servlet.http.HttpServletRequest;

import com.platform.authorization.model.LogonModel;
import com.platform.frame.config.CONFIG;

/**
 * 
 * @author yang.li
 *
 */
public class SessionUtil {
	/**
	 * 获取当前用户ID
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request)
	{
		LogonModel logonUser = getLogonUser(request);
		if (logonUser!=null)
		{
			return logonUser.getUserid();
		}
		else
		{
			return null;
		}

	}
	
	/**
	 * 获取登录用户Sys_userModel对象
	 *
	 * @param request
	 * @return 登录用户Sys_userModel对象
	 */
	public static LogonModel getLogonUser(HttpServletRequest request)
	{
		LogonModel logonUser = (LogonModel) request.getSession().getAttribute(CONFIG.LOGON_USER);

		return logonUser;
	}
}
