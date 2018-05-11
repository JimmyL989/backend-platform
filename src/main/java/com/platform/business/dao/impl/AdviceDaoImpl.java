package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.AdviceDao;
import com.platform.business.dto.AdviceDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class AdviceDaoImpl extends BaseDaoImpl<AdviceDto> implements AdviceDao {

	@Override
	public List<Map<String, Object>> queryRelevant(AdviceDto dto) {
		
		return this.selectList("queryRelevant", dto);
	}
	
	@Override
	public Long queryRelevantCount(AdviceDto dto) {

		return this.selectOne("queryRelevantCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryAnalysis(AdviceDto dto) {
		
		return this.selectList("queryAnalysis", dto);
	}
	
	@Override
	public Long queryAnalysisCount(AdviceDto dto) {
		
		return this.selectOne("queryAnalysisCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryFaming(AdviceDto dto) {
		
		return this.selectList("queryFaming", dto);
	}
	
	@Override
	public Long queryFamingCount(AdviceDto dto) {
		
		return this.selectOne("queryFamingCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryPlant(AdviceDto dto) {
		
		return this.selectList("queryPlant", dto);
	}
	
	@Override
	public Long queryPlantCount(AdviceDto dto) {
		
		return this.selectOne("queryPlantCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryDeficiency(AdviceDto dto) {
		
		return this.selectList("queryDeficiency", dto);
	}
	
	@Override
	public Long queryDeficiencyCount(AdviceDto dto) {
		
		return this.selectOne("queryDeficiencyCount", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryCultivation(AdviceDto dto) {
		
		return this.selectList("queryCultivation", dto);
	}
	
	@Override
	public Long queryCultivationCount(AdviceDto dto) {
		
		return this.selectOne("queryCultivationCount", dto);
	}
	@Override
	public List<Map<String, Object>> queryReference(AdviceDto dto) {
		
		return this.selectList("queryReference", dto);
	}
	
	@Override
	public Long queryReferenceCount(AdviceDto dto) {
		
		return this.selectOne("queryReferenceCount", dto);
	}
	
	

	@Override
	public void insertRelevant(AdviceDto dto) {

		this.insert("insertRelevant", dto);
	}
	@Override
	public void insertAnalysis(AdviceDto dto) {
		
		this.insert("insertAnalysis", dto);
	}
	@Override
	public void insertFaming(AdviceDto dto) {
		
		this.insert("insertFaming", dto);
	}
	@Override
	public void insertPlant(AdviceDto dto) {
		
		this.insert("insertPlant", dto);
	}
	@Override
	public void insertDeficiency(AdviceDto dto) {
		
		this.insert("insertDeficiency", dto);
	}
	@Override
	public void insertCultivation(AdviceDto dto) {
		
		this.insert("insertCultivation", dto);
	}
	@Override
	public void insertReference(AdviceDto dto) {
		
		this.insert("insertReference", dto);
	}
	
	
	

	@Override
	public Map<String, Object> queryRelevanById(String id) {

		return this.selectOne("queryRelevanById", id);
	}
	@Override
	public Map<String, Object> queryAnalysisById(String id) {
		
		return this.selectOne("queryAnalysisById", id);
	}
	@Override
	public Map<String, Object> queryFamingById(String id) {
		
		return this.selectOne("queryFamingById", id);
	}
	@Override
	public Map<String, Object> queryPlantById(String id) {
		
		return this.selectOne("queryPlantById", id);
	}
	@Override
	public Map<String, Object> queryDeficiencyById(String id) {
		
		return this.selectOne("queryDeficiencyById", id);
	}
	@Override
	public Map<String, Object> queryCultivationById(String id) {
		
		return this.selectOne("queryCultivationById", id);
	}
	@Override
	public Map<String, Object> queryReferenceById(String id) {
		
		return this.selectOne("queryReferenceById", id);
	}
	
	

	@Override
	public void updateRelevant(AdviceDto dto) {

		this.update("updateRelevant", dto);
	}
	@Override
	public void updateAnalysis(AdviceDto dto) {
		
		this.update("updateAnalysis", dto);
	}
	@Override
	public void updateFaming(AdviceDto dto) {
		
		this.update("updateFaming", dto);
	}
	@Override
	public void updatePlant(AdviceDto dto) {
		
		this.update("updatePlant", dto);
	}
	@Override
	public void updateDeficiency(AdviceDto dto) {
		
		this.update("updateDeficiency", dto);
	}
	@Override
	public void updateCultivation(AdviceDto dto) {
		
		this.update("updateCultivation", dto);
	}
	@Override
	public void updateReference(AdviceDto dto) {
		
		this.update("updateReference", dto);
	}
	
	
	

	@Override
	public void deleteRelevant(String id) {

		this.delete("deleteRelevant", id);
	}
	@Override
	public void deleteAnalysis(String id) {
		
		this.delete("deleteAnalysis", id);
	}
	@Override
	public void deleteFaming(String id) {
		
		this.delete("deleteFaming", id);
	}
	@Override
	public void deletePlant(String id) {
		
		this.delete("deletePlant", id);
	}
	@Override
	public void deleteDeficiency(String id) {
		
		this.delete("deleteDeficiency", id);
	}
	@Override
	public void deleteCultivation(String id) {
		
		this.delete("deleteCultivation", id);
	}
	@Override
	public void deleteReference(String id) {
		
		this.delete("deleteReference", id);
	}

}
