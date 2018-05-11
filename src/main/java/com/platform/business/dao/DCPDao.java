package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.DCPDto;
import com.platform.frame.dao.BaseDao;

public interface DCPDao extends BaseDao<DCPDto> {
	
	public List<Map<String, Object>> dataGrid(DCPDto dto);
	
	public List<Map<String, Object>> queryDivision(String id);
	
	public Long queryUserCount(DCPDto dto);
	
	public List<Map<String, Object>> queryPeriod(DCPDto dto);
	
	public void insertDCP(DCPDto dto);

	public void updateDCP(DCPDto dto);
	
	public Map<String, Object> queryPeriodById(DCPDto dto);
	
	public void deleteDCP(String id);
	
	public void batchDeleteDCP(DCPDto dto);

}
