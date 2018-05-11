package com.platform.frame.log.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.platform.frame.log.log4j.buffer.AbstractLogBuffer;

/**
 * 该类为自定义log4j日志Appender类
 * @author yang.li
 */
public abstract class AbstractLogAppender extends AppenderSkeleton
{
	protected int bufferSize = 1;
	
	/**过滤条件，满足条件的日志才输出*/
	protected String filterExp = "all";

	protected AbstractLogBuffer logBuffer;
	
	public AbstractLogAppender()
	{
		super();
	}

	abstract protected void append(LoggingEvent event);

	abstract protected Object getLogEntity(LoggingEvent event);

	public boolean requiresLayout()
	{
		return false;
	}
	
	public void finalize()
	{
		close();
	}

	public void close()
	{
		new LogScheduler(logBuffer).saveLogBuffer();
		logBuffer = null;
	}
	
	public void setBufferSize(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}

	public int getBufferSize()
	{
		return bufferSize;
	}

	public String getFilterExp()
	{
		return filterExp;
	}

	public void setFilterExp(String filterExp)
	{
		this.filterExp = filterExp;
	}
}
