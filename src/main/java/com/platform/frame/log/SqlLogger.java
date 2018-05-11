package com.platform.frame.log;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.platform.frame.log.log4j.bean.Sqllog;
import com.platform.frame.log.log4j.extLevel.SqlLevel;
import com.platform.frame.util.UUIDUtils;

/**
 * 该类为sql日志处理类
 * @author yang.li
 */
public class SqlLogger
{
	private static Logger logger;

	public SqlLogger()
	{
		SqlLogger.logger = Logger.getLogger("SqlLogger");
	}

	/**
	 *
	 * @param userId 用户id
	 * @param sql sql
	 * @param ip ip地址
	 * @param mac mac地址
	 * @param starttime 开始时间
	 * @param endtime   结束时间
	 * @param success   成功标志
	 * @param type      sql操作类型
	 * @return     sql日志ID
	 */
	public String log(String userId,String sql,String ip,String mac,Date starttime, Date endtime,String success,String type)
	{
		String logId = UUIDUtils.create();
		String timecost = String.valueOf(endtime.getTime() - starttime.getTime());
		Sqllog sqlLog = new Sqllog(logId, userId, ip, mac, sql, new Timestamp(starttime.getTime()), new Timestamp(endtime.getTime()), success, type, timecost);
		SqlLogger.logger.log(SqlLevel.SQLLEVEL, sqlLog);
		return logId;
	}

}
