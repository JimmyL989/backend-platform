package com.platform.frame.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.platform.frame.config.CONFIG;
import com.platform.frame.web.domain.Result;
import com.platform.frame.web.domain.Result.Status;

/**
 * 
 * @author yang.li
 *
 */
public class HttpUtil {
	
	public static boolean isAppClient(String urlNoContextPath) {
		
		return urlNoContextPath.indexOf(CONFIG.APPCLIENT) == -1 ? true : false;
	}

	public static boolean isNoTokenPage(String urlNoContextPath) {
		
		return (urlNoContextPath.equals("/") || urlNoContextPath.equals("") || urlNoContextPath
				.endsWith(CONFIG.APPCLIENT_LOGON));
	}
	
	public static void forwardToCode(ServletResponse response, String code) throws IOException {
		HttpServletResponse hres = (HttpServletResponse) response;
		hres.setCharacterEncoding("GBK");
//		hres.setCharacterEncoding("UTF-8");
		PrintWriter out = hres.getWriter();
		
		Result result = new Result();
		result.setStatus(Status.ERROR);
		result.setMessage(code);
		
		out.println(JSON.toJSONString(result));
		out.close();
	}
	
	/**
	 * 获取客户端的真实ip地址
	 *
	 * @param request
	 * @return 客户端的真实ip地址
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0)
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取请求url
	 *
	 * @param request
	 * @return url字符串
	 */
	public static String getRequestResource(HttpServletRequest request)
	{
		String servletPath = request.getServletPath();
		StringBuffer stringbuffer = new StringBuffer(servletPath);
		String queryString = request.getQueryString();
		if (queryString != null)
		{
			stringbuffer.append("?");
			stringbuffer.append(queryString);
		}
		String url = stringbuffer.toString();
		return url;
	}
	
}
