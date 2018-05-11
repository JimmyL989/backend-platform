package com.platform.business.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.PeriodDto;
import com.platform.frame.service.BaseService;

public interface PeriodService extends BaseService<PeriodDto> {

	public DataGrid dataGrid(PeriodDto dto, PageHelper ph);
	
	public void insertPeriod(PeriodDto dto);
	
	public Map<String, Object> queryPeriodById(String period_id);
	
	public void updatePeriod(PeriodDto dto);
	
	public void deletePeriod(String period_id);
	
	public JSONArray queryPeriodCombo();
	public JSONArray queryPeriodComboByCrops(String crops_id);
	
}
