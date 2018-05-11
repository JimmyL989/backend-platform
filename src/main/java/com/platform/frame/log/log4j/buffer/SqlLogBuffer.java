package com.platform.frame.log.log4j.buffer;

import java.util.ArrayList;

import com.platform.frame.log.SysLogger;
import com.platform.frame.log.log4j.FlushLogService;
import com.platform.frame.log.log4j.bean.Sqllog;
import com.platform.frame.util.SpringUtils;

/**
 * 该类为Sql log缓存类
 * @author yang.li
 */
public class SqlLogBuffer extends AbstractLogBuffer
{
	protected String filterExp = "";
	
	public SqlLogBuffer(int bufferSize, String filterExp)
	{
		this.buffer = new ArrayList(bufferSize);
		this.bufferSize = bufferSize;
		this.filterExp = filterExp;
	}

	/**
	 * 日志存入logBuffer
	 * @param log Sqllog对象
	 * @return true or false(缓存是否已满)
	 */
	public boolean appendLog(Object log)
	{
		if (isLogAddedToBuffer(log))
		{	
			buffer.add(log);
		}
		return isFull();
	}
	
	/**
	 * log是否应加入buffer
	 * @param log
	 * @return
	 */
	private boolean isLogAddedToBuffer(Object log)
	{
		boolean bAdd = false;
		
		if (log instanceof Sqllog)
		{
			Sqllog sqllog = new Sqllog();
			sqllog  = (Sqllog)log;
			if(this.filterExp.equalsIgnoreCase("all"))
			{
				bAdd = true;
			}
			else if (this.filterExp.indexOf(sqllog.getType()) > -1)
			{
				bAdd = true;
			}
			else
			{
				bAdd = false;
			}	
		}
		else
		{
			bAdd = false;
		}
		return bAdd;
	}
	
	/**
	 * 将buffer中的日志flush
	 *
	 */
	public void flushLogBuffer()
	{
		try
		{
			FlushLogService flushLog = (FlushLogService) SpringUtils.getDataSource("FlushLogService");
			flushLog.flushSqlLogBufferTA(buffer);
		}
		catch(Exception ee)
		{
			SysLogger.error("flushLogBuffer()异常： " + ee.getMessage());
		}
	}

}
