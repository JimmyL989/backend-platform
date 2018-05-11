package com.platform.frame.log.log4j;

import com.platform.frame.log.log4j.buffer.AbstractLogBuffer;

/**
 * 该类为日志调度类
 * @author yang.li
 */
public class LogScheduler
{
	private AbstractLogBuffer logBuffer;

	public LogScheduler(AbstractLogBuffer logBuffer)
	{
		this.logBuffer = logBuffer;
	}

	public void saveLogBuffer()
	{
		saveLogBuffer(logBuffer);
	}

	/**
	 * 启用线程输出logbuffer中的日志
	 * @param buffer
	 */
	public static void saveLogBuffer(final AbstractLogBuffer buffer)
	{
		if (buffer != null)
		{
			Thread thread = new Thread(new Runnable()
			{
				public void run()
				{
					buffer.flushLogBuffer();
				}
			});
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
		}
	}
}
