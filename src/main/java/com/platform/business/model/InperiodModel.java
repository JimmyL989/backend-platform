package com.platform.business.model;

public class InperiodModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String productId = "";
	
	private String regionId = "";
	
	private String regionId1 = "";
	
	private String regionId2 = "";
	
	private String type = "";
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}
