package com.platform.frame.config;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;

/**
 * 全局变量配置
 * 
 * @author yang.li
 *
 */

public class CONFIG {

	public static ServletContext servletContext = null;
	
	/** 数据库 */
	public static String DATABASE = "";

	public static String APPCLIENT = "";

	public static String APPCLIENT_LOGON = "";

	public static String APPCLIENT_NOT_LOGON_CODE;

	/** 登录用户对象 */
	public static String LOGON_USER = "logon_sysuser";

	/** 用户有权限访问的根菜单 */
	public static String ROOT_MENU = "_ROOTMENU_";

	/** 初始化显示的模块 */
	public static String ROOT_FLAG = "_ROOT_FLAG_";

	/** 用户有权限访问的二级菜单 */
	public static String SECOND_MENU = "_SECONDMENU_";

	/** 用于显示的系统名称 */
	public static String ROOT_NAME = "_ROOT_NAME_";

	/** 用户有权限访问的所有菜单和按钮 */
	public static String ALL_AUTH_URL_ID = "_ALL_AUTH_URL_ID_";
	
	/** 用户默认密码 */
	public static String DEFAULT_PASSWD = "";
	
	/** 系统中配置的所有菜单url和按钮url(HashMap) */
	public static String ALL_URL = "_ALL_URL_";
	
	/** 系统登录用户统计临时访问量阈值(大于该值则写数据库) */
	public static int SYS_COUNT_BUFFER = 1;
	
	private static CONFIG config = null;

	public CONFIG() {
		initParameters();
	}

	public static CONFIG getInstance() {
		return config == null ? new CONFIG() : config;
	}

	private static void initParameters() {

		ResourceBundle bundle = ResourceBundle.getBundle("config");

		APPCLIENT = bundle.getString("APPCLIENT");
		APPCLIENT_LOGON = bundle.getString("APPCLIENT_LOGON");
		APPCLIENT_NOT_LOGON_CODE = bundle.getString("APPCLIENT_NOT_LOGON_CODE");
		DEFAULT_PASSWD = bundle.getString("DEFAULT_PASSWD");
		SYS_COUNT_BUFFER = Integer.parseInt(bundle.getString("SYS_COUNT_BUFFER"));
		DATABASE = bundle.getString("DATABASE");
	}

}
