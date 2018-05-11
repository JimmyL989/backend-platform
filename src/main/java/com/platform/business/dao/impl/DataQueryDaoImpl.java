package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.DataQueryDao;
import com.platform.business.dto.DataQueryDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class DataQueryDaoImpl extends BaseDaoImpl<DataQueryDto> implements DataQueryDao {

	@Override
	public List<Map<String, Object>> queryForecast(DataQueryDto dto) {
		
		return this.selectList("queryForecast", dto);
	}
	
	@Override
	public Long queryForecastCount(DataQueryDto dto) {

		return this.selectOne("queryForecastCount", dto);
	}

	@Override
	public List<Map<String, Object>> queryLiveColumns(String id) {

		return this.selectList("queryLiveColumns", id);
	}
	
	@Override
	public List<Map<String, Object>> queryStationByRegion(String id) {
		
		return this.selectList("queryStationByRegion", id);
	}

	@Override
	public List<Map<String, Object>> queryLive(DataQueryDto dto) {

		return this.selectList("queryLive", dto);
	}

	@Override
	public List<Map<String, Object>> queryTypeCombo(String id) {
		
		return this.selectList("queryTypeCombo", id);
	}

}
