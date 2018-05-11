package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.ProductDto;
import com.platform.frame.dao.BaseDao;

public interface ProductDao extends BaseDao<ProductDto> {

	public List<Map<String, Object>> queryProduct(ProductDto dto);
	public List<Map<String, Object>> queryRelevant(ProductDto dto);
	public List<Map<String, Object>> queryReference(ProductDto dto);
	public List<Map<String, Object>> queryAnalysis(ProductDto dto);
	public List<Map<String, Object>> queryFaming(ProductDto dto);
	public List<Map<String, Object>> queryCultivation(ProductDto dto);
	public List<Map<String, Object>> queryDeficiency(ProductDto dto);
	public List<Map<String, Object>> queryPlant(ProductDto dto);
	
	public List<Map<String, Object>> queryInstanceMonitor(ProductDto dto);

	public Long queryInstanceMonitorCount(ProductDto dto);
	
	public List<Map<String, Object>> queryForecast(ProductDto dto);
	
	public List<Map<String, Object>> queryProduct5days(ProductDto dto);

	public Long queryProductCount(ProductDto dto);
	
	public List<Map<String, Object>> queryPeriod(ProductDto dto);
	
	public Long queryPeriodCount(ProductDto dto);
	
	public List<Map<String, String>> queryRegionTree(ProductDto dto);
	
	public List<Map<String, String>> queryProductCombo();

	public List<Map<String, String>> queryProductComboNew();
	public List<Map<String, String>> queryProductComboNew_();
	
	public void insertInstance(ProductDto dto) throws Exception;
	
	public void updateInstance(ProductDto dto);
	
	public Map<String, Object> queryInstanceById(String instanceid);
	
	public void deleteInstance(String instance);
	
	public void toExaminInstance(ProductDto dto);
	
	public List getAdvice(String id);
	
	public List<Map<String, String>> querySensitive();

}
