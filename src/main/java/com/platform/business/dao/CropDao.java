package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.CropDto;
import com.platform.frame.dao.BaseDao;

public interface CropDao extends BaseDao<CropDto> {

	public List<Map<String, Object>> queryCrop(CropDto dto);
	
	public List<Map<String, Object>> queryCropCombo();
	
	public Long queryCropCount(CropDto dto);
	
	public void insertCrop(CropDto dto);
	
	public Map<String, Object> queryCropById(String crops_id);
	
	public void updateCrop(CropDto dto);
	
	public void deleteCrop(String crops_id);
	
}
