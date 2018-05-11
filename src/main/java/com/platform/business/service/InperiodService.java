package com.platform.business.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.InperiodDto;
import com.platform.frame.service.BaseService;

public interface InperiodService extends BaseService<InperiodDto> {

	public DataGrid dataGrid(InperiodDto dto, PageHelper ph);
	
	public void insertCrop(CropDto dto);
	
	public Map<String, Object> queryCropById(InperiodDto dto);
	
	public void updateCrop(InperiodDto dto);
	
	public void deleteCrop(String crops_id);
	
	public JSONArray queryCropCombo();
	
}
