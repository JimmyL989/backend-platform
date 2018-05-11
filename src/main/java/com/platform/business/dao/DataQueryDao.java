package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.DataQueryDto;
import com.platform.frame.dao.BaseDao;

public interface DataQueryDao extends BaseDao<DataQueryDto> {

	public List<Map<String, Object>> queryForecast(DataQueryDto dto);
	
	public List<Map<String, Object>> queryLive(DataQueryDto dto);
	
	public Long queryForecastCount(DataQueryDto dto);
	
	public List<Map<String, Object>> queryLiveColumns(String id);

	public List<Map<String, Object>> queryTypeCombo(String id);
	
	public List<Map<String, Object>> queryStationByRegion(String id);
	
}
