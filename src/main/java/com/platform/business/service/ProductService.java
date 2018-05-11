package com.platform.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.ProductDto;
import com.platform.frame.service.BaseService;

public interface ProductService extends BaseService<ProductDto> {
	
	public DataGrid dataGrid(ProductDto dto, PageHelper ph);
	
	public DataGrid queryFaming(ProductDto dto);
	public DataGrid queryRelevant(ProductDto dto);
	public DataGrid queryReference(ProductDto dto);
	public DataGrid queryAnalysis(ProductDto dto);
	public DataGrid queryCultivation(ProductDto dto);
	public DataGrid queryDeficiency(ProductDto dto);
	public DataGrid queryPlant(ProductDto dto);
	
	public DataGrid queryInstanceMonitor(ProductDto dto, PageHelper ph);
	
	/**
	 * 查询未来预报
	 * @param dto
	 * @param ph
	 * @return
	 */
	public DataGrid queryForecast(ProductDto dto, HttpServletRequest request);

	public DataGrid queryProduct5days(ProductDto dto);

	public DataGrid queryPeriod(ProductDto dto);
	
	public JSONArray queryRegionTree(ProductDto dto);

	public JSONArray queryProductCombo();
	
	public JSONArray queryProductComboNew();
	public JSONArray queryProductComboNew_();
	
	public Json insertInstance(ProductDto dto, HttpSession session);
	
	public Object queryMessageBody(ProductDto dto);
	
	public Map<String, Object> queryInstanceById(String instanceid);
	
	public void deleteInstance(String instanceid);
	
	public void toExaminInstance(ProductDto dto);
	
	public List getAdvice(ProductDto dto);

}
