package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.ProductDao;
import com.platform.business.dto.ProductDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class ProductDaoImpl extends BaseDaoImpl<ProductDto> implements ProductDao {

	@Override
	public List<Map<String, Object>> queryProduct(ProductDto dto) {

		return this.selectList("queryProduct", dto);
	}
	@Override
	public List<Map<String, Object>> queryRelevant(ProductDto dto) {
		
		return this.selectList("queryRelevant", dto);
	}
	@Override
	public List<Map<String, Object>> queryReference(ProductDto dto) {
		
		return this.selectList("queryReference", dto);
	}
	@Override
	public List<Map<String, Object>> queryAnalysis(ProductDto dto) {
		
		return this.selectList("queryAnalysis", dto);
	}
	@Override
	public List<Map<String, Object>> queryFaming(ProductDto dto) {
		
		return this.selectList("queryFaming", dto);
	}
	@Override
	public List<Map<String, Object>> queryCultivation(ProductDto dto) {
		
		return this.selectList("queryCultivation", dto);
	}
	@Override
	public List<Map<String, Object>> queryDeficiency(ProductDto dto) {
		
		return this.selectList("queryDeficiency", dto);
	}
	@Override
	public List<Map<String, Object>> queryPlant(ProductDto dto) {
		
		return this.selectList("queryPlant", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryInstanceMonitor(ProductDto dto) {
		
		return this.selectList("queryInstanceMonitor", dto);
	}
	
	@Override
	public Long queryInstanceMonitorCount(ProductDto dto) {
		
		return this.selectOne("queryInstanceMonitorCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryForecast(ProductDto dto) {
		
		return this.selectList("queryForecast", dto);
	}
	
	@Override
	public Long queryProductCount(ProductDto dto) {
		
		return this.selectOne("queryProductCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryProduct5days(ProductDto dto) {
		
		return this.selectList("queryProduct5days", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryPeriod(ProductDto dto) {
		
		return this.selectList("queryPeriod", dto);
	}
	
	@Override
	public Long queryPeriodCount(ProductDto dto) {
		
		return this.selectOne("queryPeriodCount", dto);
	}

	@Override
	public List<Map<String, String>> queryRegionTree(ProductDto dto) {

		return this.selectList("queryRegionTree", dto);
	}
	
	@Override
	public List<Map<String, String>> queryProductCombo() {
		
		return this.selectList("queryProductCombo");
	}
	
	@Override
	public List<Map<String, String>> queryProductComboNew() {
		
		return this.selectList("queryProductComboNew");
	}
	@Override
	public List<Map<String, String>> queryProductComboNew_() {
		
		return this.selectList("queryProductComboNew_");
	}

	@Override
	public void insertInstance(ProductDto dto) throws Exception {

		this.insert("insertInstance", dto);
	}

	@Override
	public void updateInstance(ProductDto dto) {

		this.update("updateInstance", dto);
	}

	@Override
	public Map<String, Object> queryInstanceById(String instanceid) {

		return this.selectOne("queryInstanceById", instanceid);
	}

	@Override
	public void deleteInstance(String instance) {

		delete("deleteInstance", instance);
	}
	
	@Override
	public void toExaminInstance(ProductDto dto) {
		
		delete("toExaminInstance", dto);
	}

	@Override
	public List getAdvice(String id) {

		return this.selectList("getAdvice", id);
	}
	@Override
	public List<Map<String, String>> querySensitive() {

		return this.selectList("querySensitive");
	}
	
}
