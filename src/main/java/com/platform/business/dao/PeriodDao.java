package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.PeriodDto;
import com.platform.frame.dao.BaseDao;

public interface PeriodDao extends BaseDao<PeriodDto> {

	public List<Map<String, Object>> queryPeriod(PeriodDto dto);
	
	public List<Map<String, Object>> queryPeriodCombo();
	public List<Map<String, Object>> queryPeriodComboByCrops(String crops_id);
	
	public Long queryPeriodCount(PeriodDto dto);
	
	public void insertPeriod(PeriodDto dto);
	
	public Map<String, Object> queryPeriodById(String period_id);
	
	public void updatePeriod(PeriodDto dto);
	
	public void deletePeriod(String period_id);
	
}
