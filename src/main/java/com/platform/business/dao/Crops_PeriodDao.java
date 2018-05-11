package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.CropDto;
import com.platform.business.dto.Crops_PeriodDto;
import com.platform.business.dto.DCPDto;
import com.platform.frame.dao.BaseDao;

public interface Crops_PeriodDao extends BaseDao<Crops_PeriodDto> {

	public List<Map<String, Object>> queryCrop(Crops_PeriodDto dto);
	
	public Long queryCropCount(Crops_PeriodDto dto);
	
	public void insertCrop(Crops_PeriodDto dto);
	
	public Map<String, Object> queryCropById(String crops_id);
	
	public void updateCrop(Crops_PeriodDto dto);
	
	public void deleteCrop(String crops_id);
	
	public List<Map<String, Object>> queryPeriod(Crops_PeriodDto dto);
	
}
