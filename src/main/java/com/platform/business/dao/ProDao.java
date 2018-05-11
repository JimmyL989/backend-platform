package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.ProDto;
import com.platform.frame.dao.BaseDao;

public interface ProDao extends BaseDao<ProDto> {

	public List<Map<String, Object>> queryPro(ProDto dto);
	
	public Long queryProCount(ProDto dto);
	
	public void insertPro(ProDto dto);
	
	public Map<String, Object> queryProById(String productid);
	
	public void updatePro(ProDto dto);
	
	public void deletePro(String productid);
	
	public List<Map<String, Object>> queryRegions(String productid);
	
	public List queryTreeGrid();
	
	public void deleteGrantRegion(String productid);
	
	public void insertGrantRegion(ProDto dto);
	
}
