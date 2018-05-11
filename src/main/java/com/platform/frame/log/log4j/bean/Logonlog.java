package com.platform.frame.log.log4j.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 该类为登录日志实体类
 * 
 * @author yang.li
 * @since 2014-05-27 18:09:56
 */
public class Logonlog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String logonId;

	/**
	 * 用户登录id
	 */
	private String userid;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * MAC地址
	 */
	private String macaddr;

	/**
	 * 上线时间
	 */
	private Timestamp logontime;

	/**
	 * 下线时间
	 */
	private Timestamp logofftime;

	/**
	 * 登录成功标志，1为成功，0为失败
	 */
	private String logonflag;

	/**
	 * 登录失败原因
	 */
	private String logfailedreason;

	private String sessionId;

	// 构造器

	/** 缺省构造器 */
	public Logonlog() {
	}

	/** 全参数构造器 */
	public Logonlog(final String logonId, final String userid, final String userip, final String macAddr, final Timestamp logontime, final String logonflag, final String failReson, String sessionId) {
	
		this.logonId = logonId;
		this.userid = userid;
		this.ip = userip;
		this.macaddr = macAddr;
		this.logontime = logontime;
		this.logonflag = logonflag;
		this.logfailedreason = failReson;
		this.sessionId = sessionId;
	}

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}

	public String getMacaddr() {
		return macaddr;
	}

	public void setLogontime(Timestamp logontime) {
		this.logontime = logontime;
	}

	public Timestamp getLogontime() {
		return logontime;
	}

	public void setLogofftime(Timestamp logofftime) {
		this.logofftime = logofftime;
	}

	public Timestamp getLogofftime() {
		return logofftime;
	}

	public void setLogonflag(String logonflag) {
		this.logonflag = logonflag;
	}

	public String getLogonflag() {
		return logonflag;
	}

	public void setLogfailedreason(String logfailedreason) {
		this.logfailedreason = logfailedreason;
	}

	public String getLogfailedreason() {
		return logfailedreason;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String toString() {
		String str = "登录日志: 用户[" + userid + "] IP[" + ip + "] MAC[" + this.macaddr + "] 在时间[" + this.logontime + "]进行登录操作";
		return str;
	}
}
