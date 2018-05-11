package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.AwstationDto;
import com.platform.business.dto.CropDto;
import com.platform.frame.dao.BaseDao;

public interface AwstationDao extends BaseDao<AwstationDto> {

	public List<Map<String, Object>> queryAwstation(AwstationDto dto);
	
	public Long queryAwstationCount(AwstationDto dto);
	
	public void insertAwstation(AwstationDto dto);
	
	public Map<String, Object> queryAwstationById(String id);
	
	public void updateAwstation(AwstationDto dto);
	
	public void deleteAwstation(String id);
	
}
