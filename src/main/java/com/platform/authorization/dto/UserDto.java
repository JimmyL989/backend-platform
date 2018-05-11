package com.platform.authorization.dto;

import com.platform.frame.dao.domain.Identifiable;
import com.platform.frame.dto.BaseDto;

/**
 * 
 * @author yang.li
 *
 */
public class UserDto extends BaseDto {
	
	private String usercode = "";
	
	private String username = "";
	
	private String isvalid = "";
	
	private String sys_user_id = "";
	
	private String password = "";
	
	private String cuserid = "";
	
	private String euserid = "";
	
	private String sys_group_id = "";
	
	private String exist = "";
	
	private String ids = "";

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

	public String getSys_group_id() {
		return sys_group_id;
	}

	public void setSys_group_id(String sys_group_id) {
		this.sys_group_id = sys_group_id;
	}

	public String getEuserid() {
		return euserid;
	}

	public void setEuserid(String euserid) {
		this.euserid = euserid;
	}

	public String getCuserid() {
		return cuserid;
	}

	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
