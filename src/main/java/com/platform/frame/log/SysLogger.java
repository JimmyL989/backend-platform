package com.platform.frame.log;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.platform.frame.util.SessionUtil;

import sun.reflect.Reflection;


/**
 * 该类为系统日志处理类
 * @author yang.li
 */
public class SysLogger
{
	private static String callClass = null;

	private static Logger logger = null;

	public SysLogger()
	{
	}

	private static void init()
	{
		callClass = Reflection.getCallerClass(3).toString();
		logger = (Logger) Logger.getLogger(callClass);	
	}
	
	/**
	 * 输出业务开始日志
	 * @param method 输出该日志所在的方法名
	 */
	public static void start(Object method)
	{
		init();
		logger.info(method);
	}

	/**
	 * 输出业务结束日志
	 * 
	 * @param method
	 *            Object 输出该日志所在的方法名
	 */
	public static void end(Object method)
	{
		init();
		String info = "[END] Method=" + method;
		logger.info(info);
	}

	/**
	 * 输出业务开始日志（包含登陆信息）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param message
	 *            Object  日志信息
	 */
	public static void start(HttpServletRequest request, Object message)
	{
		init();
		String remoteAddr = request.getRemoteAddr();
		String userId = SessionUtil.getUserId(request);
		String info = "[START]" + " UserId=" + userId + ", RemoteAddr=" + remoteAddr + ", Method=" + message;
		logger.info(info);
	}

	/**
	 * 输出业务结束日志（包含登陆信息）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param message
	 *            Object  日志信息
	 */
	public static void end(HttpServletRequest request, Object message)
	{
		init();
		String remoteAddr = request.getRemoteAddr();
		String userId = SessionUtil.getUserId(request);
		String info = "[END]" + " UserId=" + userId + ", RemoteAddr=" + remoteAddr + ", Method=" + message;
		logger.info(info);
	}

	/**
	 * 输出业务日志
	 * 
	 * @param message
	 *            Object 日志信息
	 */
	public static void info(Object message)
	{
		init();
		logger.info(message);
	}

	/**
	 * 输出调试日志
	 * 
	 * @param message
	 *            Object 日志信息
	 */
	public static void debug(Object message)
	{
		init();
		logger.debug(message);
	}

	/**
	 * 输出警告日志
	 * 
	 * @param message
	 *            Object 日志信息
	 */
	public static void warn(Object message)
	{
		init();
		logger.warn(message);
	}

	/**
	 * 输出错误日志
	 * 
	 * @param message
	 *            Object 日志信息
	 */
	public static void error(Object message)
	{
		init();
		logger.error(message);
	}

	/**
	 * 输出fatal错误日志
	 * 
	 * @param message
	 *            Object 日志信息
	 */
	public static void fatal(Object message)
	{
		init();
		logger.fatal(message);
	}
	
	/**
	 * 输出fatal错误日志
	 * 
	 * @param message
	 * @param e
	 *            Object 日志信息
	 */
	public static void fatal(Object message,Throwable e)
	{
		init();
		logger.fatal(message,e);
	}
}
