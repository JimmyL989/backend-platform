package com.platform.authorization.model;

import com.platform.authorization.dto.LogonDto;

/**
 * 
 * @author yang.li
 *
 */
public class LogonModel extends LogonDto {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String password = "";
	
	private String usercode = "";
	
	private String isvalid = "";
	
	/**用户ip地址*/
	private String remoteIP = "";
	
	private String userid = "";
	
	private String username = "";
	
	private String logonId = "";
	
	private String macAddress = "";

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
