package com.platform.frame.log.log4j.extLevel;

import org.apache.log4j.Level;

/**
 * 该类是扩展的logon日志级别
 * 
 * @author yang.li
 */
public class LogonLevel extends Level
{
	public static final int LOGON_LEVEL_INT = Level.ERROR_INT + 1;

	public static final Level LOGONLEVEL = new LogonLevel(LOGON_LEVEL_INT, "LOGONLOG", 7);

	private static final String LOG_MSG = "LOGON";

	protected LogonLevel(int level, String name, int sysLogLevel)
	{
		super(level, name, sysLogLevel);
	}

	public static Level toLevel(String sArg)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return LOGONLEVEL;
		}
		return (Level) toLevel(sArg, Level.INFO);
	}

	public static Level toLevel(int val)
	{
		if (val == LOGON_LEVEL_INT)
		{
			return LOGONLEVEL;
		}
		return (Level) toLevel(val, Level.INFO);
	}

	public static Level toLevel(int val, Level defaultLevel)
	{
		if (val == LOGON_LEVEL_INT)
		{
			return LOGONLEVEL;
		}
		return Level.toLevel(val, defaultLevel);
	}

	public static Level toLevel(String sArg, Level defaultLevel)
	{
		if (sArg != null && sArg.toUpperCase().equals(LOG_MSG))
		{
			return LOGONLEVEL;
		}
		return Level.toLevel(sArg, defaultLevel);
	}
}
