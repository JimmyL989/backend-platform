package com.platform.frame.util;

import javax.servlet.http.HttpSession;

import com.platform.frame.web.listener.InitTool;

/**
 * 
 * @author yang.li
 *
 */
public class SystemUtil {
	
	public static void reloadPrivilege(HttpSession session) {

		new InitTool(session.getServletContext()).loadAllPrivilegeUrlsMap();
	}
	
	public static void loadUserPrivilegeUrlsMap(HttpSession session) {
		
		new InitTool(session.getServletContext()).loadUserPrivilegeUrlsMap(session);
	}
}
