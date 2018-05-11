package com.platform.business.service;

import java.util.Map;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.AwstationDto;
import com.platform.frame.service.BaseService;

public interface AwstationService extends BaseService<AwstationDto> {

	public DataGrid dataGrid(AwstationDto dto, PageHelper ph);
	
	public void insertAwstation(AwstationDto dto);
	
	public Map<String, Object> queryAwstationById(String id);
	
	public void updateAwstation(AwstationDto dto);
	
	public void deleteAwstation(String id);
	
}
