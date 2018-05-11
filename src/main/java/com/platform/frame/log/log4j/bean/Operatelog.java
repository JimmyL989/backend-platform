package com.platform.frame.log.log4j.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 该类为操作日志实体类
 * @author yang.li
 */
public class Operatelog implements Serializable
{
	private static final long serialVersionUID = 7003339443903675827L;

	// 操作日志属性

	private String id;

	private String userid;

	private String userip;
	
	/**
	 *MAC地址
	 */
	private String macaddr;

	private String operate;

	private String url;

	private Timestamp starttime;

	private Timestamp endtime;

	private String module;

	private String description;
	
	private String timecost;

	// 构造器

	/** 缺省构造器 */
	public Operatelog()
	{
	}

	/** 全参数构造器 */
	public Operatelog(final String id, final String userid, final String userip, final String macaddr, final String operate, final String url, final Timestamp starttime, final Timestamp endtime, final String module, final String description, final String timecost)
	{
		this.id = id;
		this.userid = userid;
		this.userip = userip;
		this.macaddr = macaddr;
		this.operate = operate;
		this.url = url;
		this.starttime = starttime;
		this.endtime = endtime;
		this.module = module;
		this.description = description;
		this.timecost = timecost;
	}

	public String getTimecost() {
		return timecost;
	}

	public void setTimecost(String timecost) {
		this.timecost = timecost;
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(final String userid)
	{
		this.userid = userid;
	}

	public String getUserip()
	{
		return userip;
	}

	public void setUserip(final String userip)
	{
		this.userip = userip;
	}

	public String getOperate()
	{
		return operate;
	}

	public void setOperate(final String operate)
	{
		this.operate = operate;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(final String url)
	{
		this.url = url;
	}

	public Timestamp getStarttime()
	{
		return starttime;
	}

	public void setStarttime(final Timestamp starttime)
	{
		this.starttime = starttime;
	}

	public Timestamp getEndtime()
	{
		return endtime;
	}

	public void setEndtime(final Timestamp endtime)
	{
		this.endtime = endtime;
	}

	public String getModule()
	{
		return module;
	}

	public void setModule(final String module)
	{
		this.module = module;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public String getMacaddr()
	{
		return macaddr;
	}

	public void setMacaddr(String macaddr)
	{
		this.macaddr = macaddr;
	}

	public String toString()
	{
		String str = "操作日志: 用户[" + userid + "] 模块[" + description + "] url[" + url + "]";
		return str;
	}
}
