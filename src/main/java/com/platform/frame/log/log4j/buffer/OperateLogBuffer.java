package com.platform.frame.log.log4j.buffer;

import java.util.ArrayList;

import com.platform.frame.log.SysLogger;
import com.platform.frame.log.log4j.FlushLogService;
import com.platform.frame.log.log4j.bean.Operatelog;
import com.platform.frame.util.SpringUtils;

/**
 * 该类为Operate log缓存类
 * @author yang.li
 */
public class OperateLogBuffer extends AbstractLogBuffer
{
	public OperateLogBuffer(int bufferSize)
	{
		this.buffer = new ArrayList(bufferSize);
		this.bufferSize = bufferSize;
	}

	/**
	 * 日志存入logBuffer
	 * @param log Operatelog对象
	 * @return true or false(缓存是否已满)
	 */
	public boolean appendLog(Object log)
	{
		if (log instanceof Operatelog)
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
			flushLog.flushOperateLogBufferTA(buffer);
		}
		catch(Exception ee)
		{
			SysLogger.error("flushLogBuffer()异常： " + ee.getMessage());
		}
	}

}
