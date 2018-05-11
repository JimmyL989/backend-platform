package com.platform.frame.log.log4j.extLevel;

import org.apache.log4j.Level;

/**
 * 该类是扩展的sql日志级别
 * @author yang.li
 */
public class SqlLevel extends Level
{
	public static final int SQL_LEVEL_INT = Level.INFO_INT + 1;

	public static final Level SQLLEVEL = new SqlLevel(SQL_LEVEL_INT, "SQLLOG", 7);

	private static final String LOG_MSG = "SQLLOG";

	protected SqlLevel(int level, String name, int sysLogLevel)
	{
		super(level, name, sysLogLevel);
	}

	public static Level toLevel(String sArg)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return SQLLEVEL;
		}
		return (Level) toLevel(sArg, Level.INFO);
	}

	public static Level toLevel(int val)
	{
		if (val == SQL_LEVEL_INT)
		{
			return SQLLEVEL;
		}
		return (Level) toLevel(val, Level.INFO);
	}

	public static Level toLevel(int val, Level defaultLevel)
	{
		if (val == SQL_LEVEL_INT)
		{
			return SQLLEVEL;
		}
		return Level.toLevel(val, defaultLevel);
	}

	public static Level toLevel(String sArg, Level defaultLevel)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return SQLLEVEL;
		}
		return Level.toLevel(sArg, defaultLevel);
	}
}
