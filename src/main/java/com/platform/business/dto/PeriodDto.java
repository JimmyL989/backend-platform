package com.platform.business.dto;

import com.platform.frame.dto.BaseDto;

public class PeriodDto extends BaseDto {

	private static final long serialVersionUID = 8490704987527717586L;
	
	private String period_name = "";
	
	private String period_id = "";

	public String getPeriod_name() {
		return period_name;
	}

	public void setPeriod_name(String period_name) {
		this.period_name = period_name;
	}

	public String getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(String period_id) {
		this.period_id = period_id;
	}

}
