package com.platform.authorization.model;

/**
 * 
 * @author yang.li
 *
 */
public class UserModel implements java.io.Serializable {
	
	private String usercode = "";
	
	private String username = "";
	
	private String isvalid = "";
	
	private String sys_user_id = "";
	
	private String sys_group_id = "";
	
	private String exist = "";
	
	// 用户角色id集
	private String ids = "";
	
	public String getSys_group_id() {
		return sys_group_id;
	}

	public void setSys_group_id(String sys_group_id) {
		this.sys_group_id = sys_group_id;
	}

	public String getExist() {
		return exist;
	}

	public void setExist(String exist) {
		this.exist = exist;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getSys_user_id() {
		return sys_user_id;
	}

	public void setSys_user_id(String sys_user_id) {
		this.sys_user_id = sys_user_id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
}
