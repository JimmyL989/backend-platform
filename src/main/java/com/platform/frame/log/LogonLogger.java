package com.platform.frame.log;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.platform.frame.log.log4j.bean.Logonlog;
import com.platform.frame.log.log4j.extLevel.LogonLevel;

/**
 * 该类为登录日志处理类
 * 
 * @author yang.li
 * @since 2014-05-27 18:08:25
 */
public class LogonLogger {
	
	private static Logger logger;

	public LogonLogger() {
		LogonLogger.logger = Logger.getLogger("LogonLogger");
	}

	/**
	 * 
	 * @param userId
	 * @param ip
	 * @param mac
	 * @param logontime
	 * @param logonflag
	 * @param failReson
	 * @return
	 */
	public String log(String logonId, String userId, String ip, String mac, Date logontime, String logonflag, String failReson, String sessionId) {
		Logonlog logonlog = new Logonlog(logonId, userId, ip, mac, new Timestamp(logontime.getTime()), logonflag, failReson, sessionId);
		LogonLogger.logger.log(LogonLevel.LOGONLEVEL, logonlog);
		
		return logonId;
	}

}
