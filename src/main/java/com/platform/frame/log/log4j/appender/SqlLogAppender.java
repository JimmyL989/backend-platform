package com.platform.frame.log.log4j.appender;

import org.apache.log4j.spi.LoggingEvent;

import com.platform.frame.log.log4j.AbstractLogAppender;
import com.platform.frame.log.log4j.LogScheduler;
import com.platform.frame.log.log4j.buffer.SqlLogBuffer;

/**
 * 该类为sql日志Appender类,继承AbstractLogAppender
 * @author yang.li
 */
public class SqlLogAppender extends AbstractLogAppender
{
	public SqlLogAppender()
	{
		super();
		this.logBuffer = new SqlLogBuffer(getBufferSize(),getFilterExp());
	}
	
	public void append(LoggingEvent event)
	{
		logBuffer.appendLog(getLogEntity(event));
		
		LogScheduler logScheduler = null;

		if (logBuffer.isFull())
		{
			logScheduler = new LogScheduler(logBuffer);
			logBuffer = new SqlLogBuffer(getBufferSize(),getFilterExp());
		}

		if (logScheduler != null)
		{
			logScheduler.saveLogBuffer();
		}
	}
	
	public Object getLogEntity(LoggingEvent event)
	{
		return event.getMessage();
	}
}
