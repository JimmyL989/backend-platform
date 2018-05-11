package com.platform.business.dto;

import com.platform.frame.dto.BaseDto;

public class DCPDto extends BaseDto {

	private static final long serialVersionUID = 8490704987527717586L;
	
	private String productId = "";
	
	private String regionId = "";
	
	private String regionId1 = "";
	
	private String regionId2 = "";
	
	private String period_id = "";
	
	private String relation_period_id = "";
	
	private String stime = "";
	
	private String etime = "";
	
	private String ids = "";

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public String getRelation_period_id() {
		return relation_period_id;
	}

	public void setRelation_period_id(String relation_period_id) {
		this.relation_period_id = relation_period_id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionId1() {
		return regionId1;
	}

	public void setRegionId1(String regionId1) {
		this.regionId1 = regionId1;
	}

	public String getRegionId2() {
		return regionId2;
	}

	public void setRegionId2(String regionId2) {
		this.regionId2 = regionId2;
	}

	public String getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(String period_id) {
		this.period_id = period_id;
	}

}
