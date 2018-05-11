package com.platform.frame.log.log4j.appender;

import org.apache.log4j.spi.LoggingEvent;

import com.platform.frame.log.log4j.AbstractLogAppender;
import com.platform.frame.log.log4j.LogScheduler;
import com.platform.frame.log.log4j.buffer.OperateLogBuffer;

/**
 * 该类为操作日志Appender类,继承AbstractLogAppender
 * @author yang.li
 */
public class OperateLogAppender extends AbstractLogAppender
{
	public OperateLogAppender()
	{
		super();
		this.logBuffer = new OperateLogBuffer(getBufferSize());
	}
	
	public void append(LoggingEvent event)
	{
		logBuffer.appendLog(getLogEntity(event));
		
		LogScheduler logScheduler = null;

		if (logBuffer.isFull())
		{
			logScheduler = new LogScheduler(logBuffer);
			logBuffer = new OperateLogBuffer(getBufferSize());
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
