package com.platform.business.model;

public class CropModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String crops_name = "";
	
	private String crops_id = "";

	public String getCrops_name() {
		return crops_name;
	}

	public void setCrops_name(String crops_name) {
		this.crops_name = crops_name;
	}

	public String getCrops_id() {
		return crops_id;
	}

	public void setCrops_id(String crops_id) {
		this.crops_id = crops_id;
	}
	
}
