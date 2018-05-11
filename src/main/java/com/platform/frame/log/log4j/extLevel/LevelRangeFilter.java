package com.platform.frame.log.log4j.extLevel;

/**
 *  该类是log level range filter
 * @author yang.li
 */

import org.apache.log4j.spi.LoggingEvent;

public class LevelRangeFilter extends org.apache.log4j.varia.LevelRangeFilter
{
	public LevelRangeFilter()
	{
		super();
	}

	/**
	 * 根据级别过滤日志(不包括边界)
	 */
	public int decide(LoggingEvent event)
	{
		if (this.getLevelMin() != null && event.getLevel().toInt() <= this.getLevelMin().toInt())
			return -1;
		if (this.getLevelMax() != null && event.getLevel().toInt() >= this.getLevelMax().toInt())
			return -1;
		return !this.getAcceptOnMatch() ? 0 : 1;
	}
}
