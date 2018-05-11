package com.platform.frame.log.log4j.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 该类为Sql日志实体类
 * @author yang.li
 */
public class Sqllog implements Serializable
{
	private static final long serialVersionUID = 7003339443903675827L;

	// sql日志属性

	private String id;

	private String userid;

	private String userip;

	private String macAddr;

	private String sql;

	private Timestamp starttime;

	private Timestamp endtime;

	private String success;

	private String type;
	
	private String timecost;

	// 构造器

	/** 缺省构造器 */
	public Sqllog()
	{
	}

	/** 全参数构造器 */
	public Sqllog(final String id, final String userid, final String userip, final String macAddr, final String sql, final Timestamp starttime, final Timestamp endtime, final String success, final String type, final String timecost)
	{
		this.id = id;
		this.userid = userid;
		this.userip = userip;
		this.macAddr = macAddr;
		this.sql = sql;
		this.starttime = starttime;
		this.endtime = endtime;
		this.success = success;
		this.type = type;
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

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getUserip()
	{
		return userip;
	}

	public void setUserip(String userip)
	{
		this.userip = userip;
	}

	public String getMacAddr()
	{
		return macAddr;
	}

	public void setMacAddr(String macAddr)
	{
		this.macAddr = macAddr;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public Timestamp getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Timestamp starttime)
	{
		this.starttime = starttime;
	}

	public Timestamp getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Timestamp endtime)
	{
		this.endtime = endtime;
	}

	public String getSuccess()
	{
		return success;
	}

	public void setSuccess(String success)
	{
		this.success = success;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String toString()
	{
		String str = "Sql日志: 用户[" + userid + "] IP[" + userip + "] 操作的sql[" + sql + "]";
		return str;
	}
}
