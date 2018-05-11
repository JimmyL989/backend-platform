package com.platform.frame.log.log4j;

import java.io.Serializable;


	/**
	 *系统登录历史纪录
	 *@author yang.li
	 */

public class Sys_logonlogModel implements Serializable
{
	private String sessionid;

	private String id;

	/**
	 *用户登录名
	 */
	private String userid;

	/**
	 *IP
	 */
	private String ip;

	/**
	 *MAC地址
	 */
	private String macaddr;

	/**
	 *上线时间
	 */
	private String logontime;

	/**
	 *下线时间
	 */
	private String logofftime;

	/**
	 *登录成功标志，1为成功，0为失败
	 */
	private String logonflag;

	/**
	 *登录失败原因
	 */
	private String logfailedreason;



	public void setSessionid(String sessionid)
	{
		this.sessionid = sessionid;
	}

	public String getSessionid()
	{
		return sessionid;
	}


	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}


	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUserid()
	{
		return userid;
	}


	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getIp()
	{
		return ip;
	}


	public void setMacaddr(String macaddr)
	{
		this.macaddr = macaddr;
	}

	public String getMacaddr()
	{
		return macaddr;
	}


	public void setLogontime(String logontime)
	{
		this.logontime = logontime;
	}

	public String getLogontime()
	{
		return logontime;
	}


	public void setLogofftime(String logofftime)
	{
		this.logofftime = logofftime;
	}

	public String getLogofftime()
	{
		return logofftime;
	}


	public void setLogonflag(String logonflag)
	{
		this.logonflag = logonflag;
	}

	public String getLogonflag()
	{
		return logonflag;
	}


	public void setLogfailedreason(String logfailedreason)
	{
		this.logfailedreason = logfailedreason;
	}

	public String getLogfailedreason()
	{
		return logfailedreason;
	}

}
