package com.platform.business.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.DCPDto;
import com.platform.frame.service.BaseService;

public interface DCPService extends BaseService<DCPDto> {
	
	public DataGrid dataGrid(DCPDto dto, PageHelper ph);
	
	public JSONArray queryDivision(String id);
	
	public Object queryPeriod(DCPDto dto);
	
	public void addDCP(DCPDto dto);
	
	public Map<String, Object> queryPeriodById(DCPDto dto);

	public void deleteDCP(String id);
	
	public void batchDeleteDCP(DCPDto dto);
	
}
