package com.platform.authorization.dto;

import com.platform.frame.dao.domain.Identifiable;
import com.platform.frame.dto.BaseDto;

/**
 * 
 * @author yang.li
 *
 */
public class LogonDto extends BaseDto {

	private static final long serialVersionUID = 8490704987527717586L;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	private String rootid = "";
	
	private String userid = "";

	public String getRootid() {
		return rootid;
	}

	public void setRootid(String rootid) {
		this.rootid = rootid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
