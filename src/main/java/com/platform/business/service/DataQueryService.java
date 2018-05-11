package com.platform.business.service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.DataQueryDto;
import com.platform.frame.service.BaseService;

public interface DataQueryService extends BaseService<DataQueryDto> {

	public DataGrid queryForecast(DataQueryDto dto, PageHelper ph);
	
	public DataGrid queryLive(DataQueryDto dto);
	
	public JSONArray queryStationCombo(String id);
	
	public JSONArray queryTypeCombo(String id);
	
}
