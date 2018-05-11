package com.platform.business.model;

public class Crops_PeriodModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String crops_id = "";
	
	private String period_id = "";
	
	private String sdate = "";
	
	private String edate = "";
	
	private String crops_period_id = "";


	public String getCrops_period_id() {
		return crops_period_id;
	}

	public void setCrops_period_id(String crops_period_id) {
		this.crops_period_id = crops_period_id;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public String getPeriod_id() {
		return period_id;
	}

	public void setPeriod_id(String period_id) {
		this.period_id = period_id;
	}

	public String getCrops_id() {
		return crops_id;
	}

	public void setCrops_id(String crops_id) {
		this.crops_id = crops_id;
	}
	
}
