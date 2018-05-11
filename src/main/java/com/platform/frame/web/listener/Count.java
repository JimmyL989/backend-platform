package com.platform.frame.web.listener;

import java.io.Serializable;

/**
 * 计数器Bean
 * 
 * @author yang.li
 */
public class Count implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 计数品开始统计日期 */
	private String createDate;

	/** 日最高访问量发生日期 */
	private String maxDate;

	/** 今天的日期 */
	private String todayDate;

	/** 临时计数，值大于一定量时将内存数据count对象存入数据库 */
	private String tempCount = "0";

	/** 总访问量 */
	private String allCount = "0";

	/** 日平均访问量 */
	private String avgCount = "0";

	/** 日最高访问量 */
	private String maxCount = "0";

	/** 在线人数 */
	private String onlineCount = "0";

	/** 今日访问量 */
	private String todayCount = "0";

	/** 昨日访问量 */
	private String yesterdayCount = "0";

	/**
	 * 构造器
	 */
	public Count(String allCount, String todayCount, String yesterdayCount,
			String avgCount, String maxCount, String todayDate, String maxDate,
			String createDate) {
		this.allCount = allCount;
		this.todayCount = todayCount;
		this.yesterdayCount = yesterdayCount;
		this.avgCount = avgCount;
		this.maxCount = maxCount;
		this.todayDate = todayDate;
		this.maxDate = maxDate;
		this.createDate = createDate;
	}

	public Count() {
	}

	public void setTempCount(String tempCount) {
		this.tempCount = tempCount;
	}

	public String getTempCount() {
		return this.tempCount;
	}

	public void setAllCount(String allCount) {
		this.allCount = allCount;
	}

	public String getAllCount() {
		return this.allCount;
	}

	public void setAvgCount(String avgCount) {
		this.avgCount = avgCount;
	}

	public String getAvgCount() {
		return this.avgCount;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}

	public String getMaxCount() {
		return this.maxCount;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMaxDate() {
		return this.maxDate;
	}

	public void setOnlineCount(String onlineCount) {
		this.onlineCount = onlineCount;
	}

	public String getOnlineCount() {
		return onlineCount;
	}

	public void setTodayCount(String todayCount) {
		this.todayCount = todayCount;
	}

	public String getTodayCount() {
		return this.todayCount;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getTodayDate() {
		return this.todayDate;
	}

	public void setYesterdayCount(String yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}

	public String getYesterdayCount() {
		return this.yesterdayCount;
	}
}
