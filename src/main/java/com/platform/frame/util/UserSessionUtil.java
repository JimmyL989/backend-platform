package com.platform.frame.util;

import com.platform.authorization.model.LogonModel;

/**
 * 
 * @author yang.li
 *
 */
public class UserSessionUtil
{
	private static ThreadLocal _user = new ThreadLocal(); 
	public static LogonModel getUser()
	{
		return (LogonModel)_user.get();
	}
	
	public static void setUser(LogonModel user)
	{
		_user.set(user);
	}
}
