package com.platform.business.service;

import java.util.List;
import java.util.Map;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.ProDto;
import com.platform.frame.service.BaseService;

public interface ProService extends BaseService<ProDto> {

	public DataGrid dataGrid(ProDto dto, PageHelper ph);
	
	public void insertPro(ProDto dto);
	
	public Map<String, Object> queryProById(String productid);
	
	public void updatePro(ProDto dto);

	public void deletePro(String productid);
	
	public String queryRegions(String productid);
	
	public List<Map<String, String>> queryTree();
	
	public void grantRegion(ProDto dto);
	
}
