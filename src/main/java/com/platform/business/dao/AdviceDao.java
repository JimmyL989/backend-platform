package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.AdviceDto;
import com.platform.frame.dao.BaseDao;

public interface AdviceDao extends BaseDao<AdviceDto> {

	public List<Map<String, Object>> queryRelevant(AdviceDto dto);
	public Long queryRelevantCount(AdviceDto dto);
	public List<Map<String, Object>> queryAnalysis(AdviceDto dto);
	public Long queryAnalysisCount(AdviceDto dto);
	public List<Map<String, Object>> queryFaming(AdviceDto dto);
	public Long queryFamingCount(AdviceDto dto);
	public List<Map<String, Object>> queryPlant(AdviceDto dto);
	public Long queryPlantCount(AdviceDto dto);
	public List<Map<String, Object>> queryDeficiency(AdviceDto dto);
	public Long queryDeficiencyCount(AdviceDto dto);
	public List<Map<String, Object>> queryCultivation(AdviceDto dto);
	public Long queryCultivationCount(AdviceDto dto);
	public List<Map<String, Object>> queryReference(AdviceDto dto);
	public Long queryReferenceCount(AdviceDto dto);
	
	public void insertRelevant(AdviceDto dto);
	public void insertAnalysis(AdviceDto dto);
	public void insertFaming(AdviceDto dto);
	public void insertPlant(AdviceDto dto);
	public void insertDeficiency(AdviceDto dto);
	public void insertCultivation(AdviceDto dto);
	public void insertReference(AdviceDto dto);
	
	public Map<String, Object> queryRelevanById(String id);
	public Map<String, Object> queryAnalysisById(String id);
	public Map<String, Object> queryFamingById(String id);
	public Map<String, Object> queryPlantById(String id);
	public Map<String, Object> queryDeficiencyById(String id);
	public Map<String, Object> queryCultivationById(String id);
	public Map<String, Object> queryReferenceById(String id);
	
	public void updateRelevant(AdviceDto dto);
	public void updateAnalysis(AdviceDto dto);
	public void updateFaming(AdviceDto dto);
	public void updatePlant(AdviceDto dto);
	public void updateDeficiency(AdviceDto dto);
	public void updateCultivation(AdviceDto dto);
	public void updateReference(AdviceDto dto);
	
	public void deleteRelevant(String id);
	public void deleteAnalysis(String id);
	public void deleteFaming(String id);
	public void deletePlant(String id);
	public void deleteDeficiency(String id);
	public void deleteCultivation(String id);
	public void deleteReference(String id);
	
}
