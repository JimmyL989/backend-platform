package com.platform.frame.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 该类是日期及时间处理工具类
 * @author yang.li
 */
public class DateUtil
{
	/**
	 * 取得本年第一天的日期(格式为yyyy-mm-dd)
	 * @return String
	 */
	public static String getFirstDateOfYear()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int year = gCalendar.get(Calendar.YEAR);
		return year + "-01" + "-01";
	}

	/**
	 * 取得当年某月第一天的日期(格式为yyyy-mm-dd)
	 * @param month 月份
	 * @return String
	 */
	public static String getFirstDateOfMonth(int month)
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.set(Calendar.DAY_OF_MONTH, 1);
		int year = gCalendar.get(Calendar.YEAR);
		String strMonth = StringUtil.addChar(Integer.toString(month), '0', 2);
		return year + "-" + strMonth + "-01";
	}

	/**
	 * 取得当月第一天的日期(格式为yyyy-mm-dd)
	 * @return String
	 */
	public static String getFirstDateOfCurrentMonth()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int year = gCalendar.get(Calendar.YEAR);
		int month = gCalendar.get(Calendar.MONTH) + 1;
		String strMonth = StringUtil.addChar(Integer.toString(month), '0', 2);
		return year + "-" + strMonth + "-01";
	}
	/**
	 * 取得当月最后一天的日期(格式为yyyy-mm-dd)
	 * @return String
	 */
	public static String getLastDateOfCurrentMonth()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int year = gCalendar.get(Calendar.YEAR);
		int month = gCalendar.get(Calendar.MONTH) + 1;
		int day = gCalendar.getActualMaximum(Calendar.DATE);
		String strMonth = StringUtil.addChar(Integer.toString(month), '0', 2);
		String strDay = StringUtil.addChar(Integer.toString(day), '0', 2);
		return year + "-" + strMonth + "-" + strDay;
	}

	/**
	 * 取得当前年(四位)
	 *
	 * @return String 四位年份
	 */
	public static String getCurrentYear()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int year = gCalendar.get(Calendar.YEAR);
		return Integer.toString(year);
	}

	/**
	 * 取得当前月份
	 * @return String 两位月份(不足两位的前补0)
	 */
	public static String getCurrentMonth()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int month = gCalendar.get(Calendar.MONTH) + 1;
		String strMonth = StringUtil.addChar(Integer.toString(month), '0', 2);
		return strMonth;
	}

	/**
	 * 取得当前天
	 * @return String 两位月份(不足两位的前补0)
	 */
	public static String getCurrentDay()
	{
		GregorianCalendar gCalendar = new GregorianCalendar();
		int day = gCalendar.get(Calendar.DAY_OF_MONTH);
		String strDay = StringUtil.addChar(Integer.toString(day), '0', 2);
		return strDay;
	}

	/**
	 * 获取当前系统日期(非数据库日期,格式为yyyy-mm-dd)
	 * @return String
	 */
	public static String getCurrentDate()
	{
		GregorianCalendar lgc = new GregorianCalendar();
		String year = String.valueOf(lgc.get(Calendar.YEAR));
		String month = String.valueOf(lgc.get(Calendar.MONTH) + 1);
		if (month.length() == 1)
		{
			month = "0" + month;
		}
		String date = String.valueOf(lgc.get(Calendar.DATE));
		if (date.length() == 1)
		{
			date = "0" + date;
		}
		return year + "-" + month + "-" + date;
	}

	/**
	 * 日期的追加处理(如获得某个日期加一年、两月或者三天的新日期)
	 * @param strOriDate 原日期(格式yyyy-mm-dd)
	 * @param nFlag      追加标志(0: 年度 1：月份 2：天)
	 * @param nLen       变化的范围
	 * @return
	 */
	public static String addDate(String strOriDate, int nFlag, int nLen)
	{
		int nY = Integer.parseInt(strOriDate.substring(0, 4));
		int nM = Integer.parseInt(strOriDate.substring(5, 7));
		int nD = Integer.parseInt(strOriDate.substring(8, 10));
		GregorianCalendar gmt;

		if (nFlag == 0)
		{
			gmt = new GregorianCalendar(nY, nM - 1, 1);
			gmt.add(Calendar.YEAR, nLen);
		}
		else
		{
			if (nFlag == 1)
			{
				gmt = new GregorianCalendar(nY, nM - 1, 1);
				gmt.add(Calendar.MONTH, nLen);
			}
			else
			{
				gmt = new GregorianCalendar(nY, nM - 1, nD);
				gmt.add(Calendar.DAY_OF_YEAR, nLen);
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = format.format(gmt.getTime());

		if (nFlag == 0 || nFlag == 1)
		{
			int nNewDay = getDayNumOfMonth(strDate.substring(0, 4), strDate.substring(5, 7));
			if (nD > nNewDay)
			{
				strDate = strDate.substring(0, 7) + "-" + String.valueOf(nNewDay);
			}
			else
			{
				strDate = strDate.substring(0, 7) + "-" + StringUtil.addChar(String.valueOf(nD), '0', 2);
			}
		}
		return strDate;
	}

	/**
	 * 日期的减处理(如获得某个日期减去一年、两月或者三天的新日期)
	 * @param strOriDate 原日期(格式yyyy-mm-dd)
	 * @param nFlag      减标志(0: 年度 1：月份 2：天)
	 * @param nLen       变化的范围
	 * @return
	 */
	public static String subDate(String strOriDate, int nFlag, int nLen)
	{
		int nY = Integer.parseInt(strOriDate.substring(0, 4));
		int nM = Integer.parseInt(strOriDate.substring(5, 7));
		int nD = Integer.parseInt(strOriDate.substring(8, 10));
		nLen = nLen * (-1);
		GregorianCalendar gmt;

		if (nFlag == 0)
		{
			gmt = new GregorianCalendar(nY, nM - 1, 1);
			gmt.add(Calendar.YEAR, nLen);
		}
		else
		{
			if (nFlag == 1)
			{
				gmt = new GregorianCalendar(nY, nM - 1, 1);
				gmt.add(Calendar.MONTH, nLen);
			}
			else
			{
				gmt = new GregorianCalendar(nY, nM - 1, nD);
				gmt.add(Calendar.DAY_OF_YEAR, nLen);
			}
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = format.format(gmt.getTime());

		if (nFlag == 0 || nFlag == 1)
		{
			int nNewDay = getDayNumOfMonth(strDate.substring(0, 4), strDate.substring(5, 7));
			if (nD > nNewDay)
			{
				strDate = strDate.substring(0, 7) + "-" + String.valueOf(nNewDay);
			}
			else
			{
				strDate = strDate.substring(0, 7) + "-" + StringUtil.addChar(String.valueOf(nD), '0', 2);
			}
		}
		return strDate;
	}

	/**
	 * 获得某月的最大天数
	 * @param year  四位年份
	 * @param month 一位或两位月份
	 * @return int 某月的总天数
	 */
	public static int getDayNumOfMonth(String year, String month)
	{
		int[] nDayOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();

		if (calendar.isLeapYear(Integer.parseInt(year)))
		{
			nDayOfMonth[1] = 29;
		}
		int nDayMonth = Integer.parseInt(month);

		return nDayOfMonth[nDayMonth - 1];
	}

	/**
	 * 求两个日期之间的天数差
	 * @param firstDate 第一个日期(格式yyyy-mm-dd)
	 * @param secDate   第二个日期(格式yyyy-mm-dd)
	 * @return 两日期之间的天数差
	 */
	public static int diffTwoDate(String firstDate, String secDate)
	{
		Calendar lastCal = Calendar.getInstance();
		Calendar firstCal = Calendar.getInstance();

		int firstMonth = Integer.parseInt(firstDate.substring(5, 7));
		int lastMonth = Integer.parseInt(secDate.substring(5, 7));

		lastCal.set(Integer.parseInt(secDate.substring(0, 4)), lastMonth - 1, Integer.parseInt(secDate.substring(8, 10)));
		firstCal.set(Integer.parseInt(firstDate.substring(0, 4)), firstMonth - 1, Integer.parseInt(firstDate.substring(8, 10)));
		Date first = firstCal.getTime();
		Date last = lastCal.getTime();

		long lastTime = last.getTime();
		long firstTime = first.getTime();
		long chaTime = firstTime - lastTime;
		long diffTime = chaTime / 1000 / 60 / 60 / 24;
		return (int) diffTime;
	}

	/**
	 * 获得某月的最后一天日期
	 * @param year   四位年份
	 * @param month  一位或两位月份
	 * @return String yyyy-mm-dd格式的日期
	 */
	public static String getLastDateOfMonth(String year, String month)
	{
		int nY = Integer.parseInt(year);
		int nM = Integer.parseInt(month);
		//设置下个月第一天
		GregorianCalendar cal = new GregorianCalendar(nY, nM, 1);
		//下个月第一天减一天，为本月最后一天
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = format.format(cal.getTime());
		return strDate;
	}

	/**
	 * 获得日期对应的星期
	 * @param strDate yyyy-mm-dd格式的日期
	 * @return String 星期数字(1到7)
	 */
	public static int getWeekDay(String strDate)
	{

		Calendar rightNow = Calendar.getInstance();
		int month = Integer.parseInt(strDate.substring(5, 7));
		rightNow.set(Integer.parseInt(strDate.substring(0, 4)), month - 1, Integer.parseInt(strDate.substring(8, 10)));

		int week = rightNow.get(Calendar.DAY_OF_WEEK);
		//星期日返回7
		if (week == 1)
		{
			week = 7;
		}
		//星期几返回几
		else
		{
			week -= 1;
		}

		return week;
	}

	/**
	 * 将毫秒时间格式化为时分秒格式的时间(如3时5分6秒)
	 * @param timeMillis 毫秒时间
	 * @return String 格式化的时间
	 */
	public static String getFormattedTime(long timeMillis)
	{
		String formattedTime = "";
		//小于0
		if (timeMillis < 1.0E-10)
		{
			formattedTime = "";
		}
		else
		{
			if (timeMillis < 1000)
			{
				formattedTime = Double.toString(timeMillis) + " 毫秒";
			}
			else
			{
				//计算出天、小时、分钟
				long timeInSeconds = timeMillis / 1000;
				long days, hours, minutes;
				days = timeInSeconds / 86400;
				String strDays = Long.toString(days);
				timeInSeconds -= days * 86400;
				hours = timeInSeconds / 3600;
				String strHours = Long.toString(hours);
				timeInSeconds -= hours * 3600;
				minutes = timeInSeconds / 60;
				String strMinutes = Long.toString(minutes);
				timeInSeconds -= minutes * 60;
				String strSeconds = Long.toString(timeInSeconds);

				if (timeInSeconds > 0)
				{
					formattedTime = strSeconds + " 秒";
				}
				if (minutes > 0)
				{
					formattedTime = strMinutes + " 分 " + formattedTime;
				}
				if (hours > 0)
				{
					formattedTime = strHours + " 时 " + formattedTime;
				}
				if (days > 0)
				{
					formattedTime = strDays + " 天 " + formattedTime;
				}
			}
		}
		return formattedTime;
	}

	/**
	 * 将日期转换为中文格式的日期（yyyy年mm月dd日）
	 * @param strDate yyyy-mm-dd 或 yyyy/mm/dd 或 yyyymmdd形式的日期
	 * @return String yyyy年mm月dd日格式的日期
	 */
	public static String toShortChineseDate(String strDate)
	{
		String strCnDate = "";
		strDate = strDate.replaceAll("-", "");
		strDate = strDate.replaceAll("/", "");
		strDate = strDate.substring(0, 8);
		strCnDate = strDate.substring(0, 4) + "年";
		strCnDate = strCnDate + strDate.substring(4, 6) + "月";
		strCnDate += strDate.substring(6, 8) + "日";
		return strCnDate;
	}

	/**
	 * 将字符串转化为java.sql.Date格式，字符串的格式为：yyyy-mm-dd
	 *
	 * @param strDate
	 *            date的格式为：yyyy-mm-dd
	 * @return java.sql.Date
	 *
	 */
	public static java.sql.Date Str2SqlDate(String strDate)
	{
		java.sql.Date return_Date = null;
		try
		{
			return_Date = java.sql.Date.valueOf(strDate);
		}
		catch (Exception e)
		{
		}

		return return_Date;
	}

	/**
	 * 将字符串转化为java.util.Date格式
	 * @param strDate 格式为：yyyymmdd、yymmdd或者yyyy-mm-dd的字符串
	 * @return java.util.Date
	 */
	public static java.util.Date Str2UtilDate(String strDate)
	{
		Date dt = null;
		String strYear = null, strMonth = null, strDay = null;
		int nLength = strDate.length();
		switch (nLength)
		{
			case 6:
				strYear = strDate.substring(0, 2);
				strMonth = strDate.substring(2, 4);
				strDay = strDate.substring(4);
				break;
			case 8:
				strYear = strDate.substring(0, 4);
				strMonth = strDate.substring(4, 6);
				strDay = strDate.substring(6);
				break;
			case 10:
				strYear = strDate.substring(0, 4);
				strMonth = strDate.substring(5, 7);
				strDay = strDate.substring(8);
				break;
			default:
				return dt;
		}
		dt = Str2UtilDate(strYear, strMonth, strDay);
		return dt;
	}

	/**
	 * 将字符串转化为java.util.Date日期类型
	 *
	 * @param strYear
	 *            年
	 * @param strMonth
	 *            月
	 * @param strDay
	 *            日
	 * @return java.util.Date 日期，返回null转换失败
	 */
	public static java.util.Date Str2UtilDate(String strYear, String strMonth, String strDay)
	{
		java.util.Date dt = null;
		int nYear, nMonth, nDay;
		if (strYear.length() == 2)
			strYear = "19" + strYear;
		try
		{
			nYear = new Integer(strYear).intValue();
			nMonth = new Integer(strMonth).intValue();
			nDay = new Integer(strDay).intValue();
		}
		catch (Exception e)
		{
			return null;
		}
		if (nMonth < 1 || nMonth > 12 || nDay > 31)
			return null;
		if ((nMonth == 4 || nMonth == 6 || nMonth == 9 || nMonth == 11) && (nDay > 30))
			return null;
		if (nMonth == 2)
		{
			if (nYear % 4 != 0)
			{
				if (nDay > 28)
					return null;
			}
			else
			{
				if (nDay > 29)
					return null;
			}
		}
		dt = new Date(nYear - 1900, nMonth - 1, nDay);
		return dt;
	}

	/**
	 * 将java.util.Date日期转化为字符格式，字符串的格式为：yyyy-mm-dd
	 *
	 * @param date
	 *            要转化的date
	 * @return String 返回的格式为：yyyy-mm-dd
	 *
	 */
	public static String date2Str(java.util.Date date)
	{
		if (date == null)
		{
			return "";
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
	}

	/**
	 * java.util.Date转换到java.sql.Date
	 * @param date util类型的date
	 * @return sql类型date
	 */
	public static java.sql.Date parseDate(java.util.Date date)
	{
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 字符串转换为java.sql.Timestamp类型
	 * @param strDate yyyy-mm-dd类型的字符串
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp parseTimeStamp(String strDate)
	{
		java.sql.Timestamp d = null;

		java.util.Date da = Str2UtilDate(strDate);
		d = new java.sql.Timestamp(da.getTime());

		return d;
	}

	/**
	 * 获取字符串类型的当前时间
	 * @return 字符串类型的当前时间
	 */
	public static String getStringTimestamp()
	{
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return format.format(date);
	}
	/**
	 * 获取字符串类型的当前时间
	 * @return 字符串类型的当前时间
	 */
	public static String getUserDefinedStringTimestamp()
	{
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	
	/**
	 * 获取当前的小时分钟
	 * @author jinwenquan 2012-05-23
	 */
	public static String getUserDefinedStringTime()
	{
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}
	
	/**
	 * 
	 * @author jinwenquan 2012-05-24
	 */
	public static String getUserDefinedStringDate(int count)
	{
		Calendar strDate = Calendar.getInstance();   
		strDate.add(strDate.DATE, count);   
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");   
		return sdf.format(strDate.getTime());   
	}
	
	
//	/**
//	 * 获取数据库的当前时间(格式 yyyy-mm-dd hh24:mi:ss)
//	 * @return 数据库的当前时间
//	 */
//	public static String getDBCurrentTime()
//	{
//		BaseDao dao = new BaseDao();
//		return dao.queryForString("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual");
//	}
	/**
	 * 转换参数的时间格式(格式 yyyymmddhh24miss)
	 * @return 转换过得时间
	 */
	public static String getStringDateTime(String dateTime) {
		dateTime = dateTime.replace("-", "");
		dateTime = dateTime.replace(" ", "");
		dateTime = dateTime.replace(":", "");
		return dateTime;
	}

//	public static void main(String[] args)
//	{
//		System.out.println("parseTimeStamp() :" + getDBCurrentTime());
//	}
}
