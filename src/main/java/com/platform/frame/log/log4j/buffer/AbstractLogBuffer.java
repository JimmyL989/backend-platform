package com.platform.frame.log.log4j.buffer;

import java.util.ArrayList;

/**
 * 该类为log缓存抽象类
 * @author yang.li
 */
public abstract class AbstractLogBuffer
{
	protected int bufferSize;

	protected ArrayList buffer;

	/**
	 * 判断log缓存是否已满
	 * @return true or false
	 */
	public boolean isFull()
	{
		return buffer.size() >= bufferSize;
	}

	/**
	 * 日志存入logBuffer
	 * @param log Object对象
	 * @return true or false(缓存是否已满)
	 */
	public abstract boolean appendLog(Object log);
	
	/**
	 * 将buffer中的日志flush
	 *
	 */
	public abstract void flushLogBuffer();
}
