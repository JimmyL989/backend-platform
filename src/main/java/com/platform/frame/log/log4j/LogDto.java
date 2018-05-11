package com.platform.frame.log.log4j;

import java.sql.Date;
import java.sql.Timestamp;

import com.platform.frame.dao.domain.Identifiable;

/**
 * 
 * @author yang.li
 *
 */
public class LogDto implements Identifiable {

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	private String logonId = "";
	
	private Timestamp logofftime;

	public String getLogonId() {
		return logonId;
	}

	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}

	public Timestamp getLogofftime() {
		return logofftime;
	}

	public void setLogofftime(Timestamp logofftime) {
		this.logofftime = logofftime;
	}


}
