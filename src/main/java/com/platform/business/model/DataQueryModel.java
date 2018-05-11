package com.platform.business.model;

public class DataQueryModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String regionId = "";
	
	private String regionId1 = "";
	
	private String regionId2 = "";
	
	private String stationname = "";
	
	private String play_time = "";
	
	private String play_time1 = "";
	
	private String data_id = "";
	
	private String station_id = "";

	public String getPlay_time1() {
		return play_time1;
	}

	public void setPlay_time1(String play_time1) {
		this.play_time1 = play_time1;
	}

	public String getStation_id() {
		return station_id;
	}

	public void setStation_id(String station_id) {
		this.station_id = station_id;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
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

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getPlay_time() {
		return play_time;
	}

	public void setPlay_time(String play_time) {
		this.play_time = play_time;
	}
	
}
