package com.platform.business.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.CropDto;
import com.platform.frame.service.BaseService;

public interface CropService extends BaseService<CropDto> {

	public DataGrid dataGrid(CropDto dto, PageHelper ph);
	
	public void insertCrop(CropDto dto);
	
	public Map<String, Object> queryCropById(String crops_id);
	
	public void updateCrop(CropDto dto);
	
	public void deleteCrop(String crops_id);
	
	public JSONArray queryCropCombo();
	
}
