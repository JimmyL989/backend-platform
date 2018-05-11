package com.platform.frame.log;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.platform.frame.log.log4j.bean.Operatelog;
import com.platform.frame.log.log4j.extLevel.OperateLevel;


/**
 * 该类为操作日志处理类
 * @author yang.li
 */
public class OperateLogger
{
	private static Logger logger;

	public OperateLogger()
	{
		OperateLogger.logger = Logger.getLogger("OperateLogger");
	}

	/**
	 * 操作日志记录方法
	 * 
	 * @param logID 日志ID
	 * @param userid 用户ID
	 * @param userip 用户IP
	 * @param macaddr mac地址
	 * @param operate 请求功能(functionid)
	 * @param url 请求URL
	 * @param starttime 操作开始时间
	 * @param endtime 操作结束时间
	 * @param module 请求功能模块(请求功能父节点)
	 * @param description 操作描述
	 * @return 操作日志ID
	 */
	public String log(String logID, String userid, String userip, String macaddr,String operate, String url, Date starttime, Date endtime, String module, String description, String timecost)
	{
		Operatelog olog = new Operatelog(logID, userid, userip, macaddr, operate, url, new Timestamp(starttime.getTime()), new Timestamp(endtime.getTime()), module, description, timecost);
		OperateLogger.logger.log(OperateLevel.OPERATELEVEL, olog);

		return logID;
	}
}
