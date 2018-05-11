package com.platform.frame.dto;

import com.platform.frame.dao.domain.Identifiable;

public class BaseDto implements Identifiable {
	
	private String sqlOrder = "";
	
	private String sqlLimit = "";
	
	private String before = "";
	private String after = "";

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public String getSqlOrder() {
		return sqlOrder;
	}

	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	public String getSqlLimit() {
		return sqlLimit;
	}

	public void setSqlLimit(String sqlLimit) {
		this.sqlLimit = sqlLimit;
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
