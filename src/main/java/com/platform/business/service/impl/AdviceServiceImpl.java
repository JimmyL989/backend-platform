package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.AdviceDao;
import com.platform.business.dto.AdviceDto;
import com.platform.business.service.AdviceService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class AdviceServiceImpl extends BaseServiceImpl<AdviceDto> implements AdviceService {

	@Autowired
	private AdviceDao dao;
	
	@Override
	protected BaseDao<AdviceDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid queryRelevant(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryRelevant(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryRelevantCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryAnalysis(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryAnalysis(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryAnalysisCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryFaming(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryFaming(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryFamingCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryPlant(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryPlant(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryPlantCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryDeficiency(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryDeficiency(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryDeficiencyCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryCultivation(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryCultivation(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryCultivationCount(dto));
		
		return dg;
	}
	@Override
	public DataGrid queryReference(AdviceDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryReference(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryReferenceCount(dto));
		
		return dg;
	}
	
	@Override
	public void insertRelevant(AdviceDto dto) {

		dao.insertRelevant(dto);
	}
	@Override
	public void insertAnalysis(AdviceDto dto) {
		
		dao.insertAnalysis(dto);
	}
	@Override
	public void insertFaming(AdviceDto dto) {
		
		dao.insertFaming(dto);
	}
	@Override
	public void insertPlant(AdviceDto dto) {
		
		dao.insertPlant(dto);
	}
	@Override
	public void insertDeficiency(AdviceDto dto) {
		
		dao.insertDeficiency(dto);
	}
	@Override
	public void insertCultivation(AdviceDto dto) {
		
		dao.insertCultivation(dto);
	}
	@Override
	public void insertReference(AdviceDto dto) {
		
		dao.insertReference(dto);
	}

	
	@Override
	public Map<String, Object> queryRelevanById(String id) {

		return dao.queryRelevanById(id);
	}
	@Override
	public Map<String, Object> queryAnalysisById(String id) {
		
		return dao.queryAnalysisById(id);
	}
	@Override
	public Map<String, Object> queryFamingById(String id) {
		
		return dao.queryFamingById(id);
	}
	@Override
	public Map<String, Object> queryPlantById(String id) {
		
		return dao.queryPlantById(id);
	}
	@Override
	public Map<String, Object> queryDeficiencyById(String id) {
		
		return dao.queryDeficiencyById(id);
	}
	@Override
	public Map<String, Object> queryCultivationById(String id) {
		
		return dao.queryCultivationById(id);
	}
	@Override
	public Map<String, Object> queryReferenceById(String id) {
		
		return dao.queryReferenceById(id);
	}

	
	@Override
	public void updateRelevant(AdviceDto dto) {

		dao.updateRelevant(dto);
	}
	@Override
	public void updateAnalysis(AdviceDto dto) {
		
		dao.updateAnalysis(dto);
	}
	@Override
	public void updateFaming(AdviceDto dto) {
		
		dao.updateFaming(dto);
	}
	@Override
	public void updatePlant(AdviceDto dto) {
		
		dao.updatePlant(dto);
	}
	@Override
	public void updateDeficiency(AdviceDto dto) {
		
		dao.updateDeficiency(dto);
	}
	@Override
	public void updateCultivation(AdviceDto dto) {
		
		dao.updateCultivation(dto);
	}
	@Override
	public void updateReference(AdviceDto dto) {
		
		dao.updateReference(dto);
	}

	
	
	@Override
	public void deleteRelevant(String id) {

		dao.deleteRelevant(id);
	}
	@Override
	public void deleteAnalysis(String id) {
		
		dao.deleteAnalysis(id);
	}
	@Override
	public void deleteFaming(String id) {
		
		dao.deleteFaming(id);
	}
	@Override
	public void deletePlant(String id) {
		
		dao.deletePlant(id);
	}
	@Override
	public void deleteDeficiency(String id) {
		
		dao.deleteDeficiency(id);
	}
	@Override
	public void deleteCultivation(String id) {
		
		dao.deleteCultivation(id);
	}
	@Override
	public void deleteReference(String id) {
		
		dao.deleteReference(id);
	}
	
}
