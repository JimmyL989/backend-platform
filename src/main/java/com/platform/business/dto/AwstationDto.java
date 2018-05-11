package com.platform.business.dto;

import com.platform.frame.dto.BaseDto;

public class AwstationDto extends BaseDto {

	private static final long serialVersionUID = 8490704987527717586L;
	
	private String stationname = "";
	
	private String stationnum = "";
	
	private String regionId = "";
	
	private String regionId1 = "";
	
	private String regionId2 = "";
	
	private String awstation_id = "";

	public String getAwstation_id() {
		return awstation_id;
	}

	public void setAwstation_id(String awstation_id) {
		this.awstation_id = awstation_id;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStationnum() {
		return stationnum;
	}

	public void setStationnum(String stationnum) {
		this.stationnum = stationnum;
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

}
