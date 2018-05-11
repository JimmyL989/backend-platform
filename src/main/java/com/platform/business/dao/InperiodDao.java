package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.CropDto;
import com.platform.business.dto.InperiodDto;
import com.platform.frame.dao.BaseDao;

public interface InperiodDao extends BaseDao<InperiodDto> {

	public List<Map<String, Object>> queryCrop(InperiodDto dto);
	
	public List<Map<String, Object>> queryCropCombo();
	
	public Long queryCropCount(InperiodDto dto);
	
	public void insertCrop(CropDto dto);
	
	public Map<String, Object> queryCropById(InperiodDto dto);
	
	public void updateCrop(InperiodDto dto);
	
	public void deleteCrop(String crops_id);
	
}
