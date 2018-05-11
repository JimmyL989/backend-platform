package com.platform.frame.log.log4j.appender;

import org.apache.log4j.spi.LoggingEvent;

import com.platform.frame.log.log4j.AbstractLogAppender;
import com.platform.frame.log.log4j.LogScheduler;
import com.platform.frame.log.log4j.buffer.LogonLogBuffer;

/**
 * 该类为登录日志Appender类,继承AbstractLogAppender
 * @author yang.li
 */
public class LogonLogAppender extends AbstractLogAppender
{
	public LogonLogAppender()
	{
		super();
		this.logBuffer = new LogonLogBuffer(getBufferSize());
	}
	
	public void append(LoggingEvent event)
	{
		logBuffer.appendLog(getLogEntity(event));
		
		LogScheduler logScheduler = null;

		if (logBuffer.isFull())
		{
			logScheduler = new LogScheduler(logBuffer);
			logBuffer = new LogonLogBuffer(getBufferSize());
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
