package com.platform.business.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.Crops_PeriodDto;
import com.platform.business.dto.DCPDto;
import com.platform.frame.service.BaseService;

public interface Crops_PeriodService extends BaseService<Crops_PeriodDto> {

	public DataGrid dataGrid(Crops_PeriodDto dto, PageHelper ph);
	
	public void insertCrop(Crops_PeriodDto dto);
	
	public Map<String, Object> queryCropById(String crops_id);
	
	public void updateCrop(Crops_PeriodDto dto);
	
	public void deleteCrop(String crops_id);
	
	public Object queryPeriod(Crops_PeriodDto dto);
	
}
