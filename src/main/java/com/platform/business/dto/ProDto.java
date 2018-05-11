package com.platform.business.dto;

import com.platform.frame.dto.BaseDto;

public class ProDto extends BaseDto {

	private static final long serialVersionUID = 8490704987527717586L;
	
	private String product_name = "";
	
	private String crop = "";
	
	private String effect_time = "";
	
	private String expire_time = "";
	
	private String productid = "";
	
	private String regionIds = "";
	
	private String regionid = "";

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

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

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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
	
}
