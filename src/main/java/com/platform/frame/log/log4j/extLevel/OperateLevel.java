package com.platform.frame.log.log4j.extLevel;

import org.apache.log4j.Level;

/**
 * 该类是扩展的operate日志级别
 * @author yang.li
 */
public class OperateLevel extends Level
{
	public static final int OPERATE_LEVEL_INT = Level.WARN_INT + 1;

	public static final Level OPERATELEVEL = new OperateLevel(OPERATE_LEVEL_INT, "OPERATELOG", 7);

	private static final String LOG_MSG = "OPERATELOG";

	protected OperateLevel(int level, String name, int sysLogLevel)
	{
		super(level, name, sysLogLevel);
	}

	public static Level toLevel(String sArg)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return OPERATELEVEL;
		}
		return (Level) toLevel(sArg, Level.INFO);
	}

	public static Level toLevel(int val)
	{
		if (val == OPERATE_LEVEL_INT)
		{
			return OPERATELEVEL;
		}
		return (Level) toLevel(val, Level.INFO);
	}

	public static Level toLevel(int val, Level defaultLevel)
	{
		if (val == OPERATE_LEVEL_INT)
		{
			return OPERATELEVEL;
		}
		return Level.toLevel(val, defaultLevel);
	}

	public static Level toLevel(String sArg, Level defaultLevel)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return OPERATELEVEL;
		}
		return Level.toLevel(sArg, defaultLevel);
	}
}
