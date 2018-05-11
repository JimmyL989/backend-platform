package com.platform.business.model;

public class ProModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String product_name = "";
	
	private String crop = "";
	
	private String effect_time = "";
	
	private String expire_time = "";
	
	private String productid = "";
	
	private String regionIds = "";

	public String getRegionIds() {
		return regionIds;
	}

	public void setRegionIds(String regionIds) {
		this.regionIds = regionIds;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getEffect_time() {
		return effect_time;
	}

	public void setEffect_time(String effect_time) {
		this.effect_time = effect_time;
	}

	public String getExpire_time() {
		return expire_time;
	}

	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
}
