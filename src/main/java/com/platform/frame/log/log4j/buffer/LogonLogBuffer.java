package com.platform.frame.log.log4j.buffer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.frame.config.CONFIG;
import com.platform.frame.log.SysLogger;
import com.platform.frame.log.log4j.FlushLogService;
import com.platform.frame.log.log4j.bean.Logonlog;
import com.platform.frame.util.SpringUtils;


/**
 * 该类为登录 log缓存类
 * @author yang.li
 */
public class LogonLogBuffer extends AbstractLogBuffer {
	
	public LogonLogBuffer(int bufferSize)
	{
		this.buffer = new ArrayList(bufferSize);
		this.bufferSize = bufferSize;
	}

	/**
	 * 日志存入logBuffer
	 * @param log Logonlog对象
	 * @return true or false(缓存是否已满)
	 */
	public boolean appendLog(Object log)
	{
		if (log instanceof Logonlog)
		{
			buffer.add(log);
		}
		return isFull();
	}
	
	/**
	 * 将buffer中的日志flush
	 *
	 */
	public void flushLogBuffer()
	{
		try
		{
			FlushLogService flushLog = (FlushLogService)SpringUtils.getDataSource("FlushLogService");
			flushLog.flushLogonLogBufferTA(buffer);
		}
		catch(Exception ee)
		{
			SysLogger.error("flushLogBuffer()异常： " + ee.getMessage());
		}
	}

}
