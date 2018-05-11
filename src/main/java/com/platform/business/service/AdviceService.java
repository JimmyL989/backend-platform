package com.platform.business.service;

import java.util.Map;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.AdviceDto;
import com.platform.frame.service.BaseService;

public interface AdviceService extends BaseService<AdviceDto> {

	public DataGrid queryRelevant(AdviceDto dto, PageHelper ph);
	public DataGrid queryAnalysis(AdviceDto dto, PageHelper ph);
	public DataGrid queryFaming(AdviceDto dto, PageHelper ph);
	public DataGrid queryPlant(AdviceDto dto, PageHelper ph);
	public DataGrid queryDeficiency(AdviceDto dto, PageHelper ph);
	public DataGrid queryCultivation(AdviceDto dto, PageHelper ph);
	public DataGrid queryReference(AdviceDto dto, PageHelper ph);
	
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
