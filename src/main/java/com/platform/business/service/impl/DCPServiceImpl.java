package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.DCPDao;
import com.platform.business.dto.DCPDto;
import com.platform.business.dto.ProductDto;
import com.platform.business.service.DCPService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class DCPServiceImpl extends BaseServiceImpl<DCPDto> implements DCPService {

	@Autowired
	private DCPDao dao;
	
	@Override
	protected BaseDao<DCPDto> getBaseDao() {

		return dao;
	}

	@Override
	public DataGrid dataGrid(DCPDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.dataGrid(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryUserCount(dto));
		
		return dg;
	}

	@Override
	public JSONArray queryDivision(String id) {
		
		List list = dao.queryDivision(id);
		JSONArray json = new JSONArray(list);
		
		return json;
	}
	
	@Override
	public Object queryPeriod(DCPDto dto) {

		List<Map<String, Object>> list = dao.queryPeriod(dto);
		
		if (list == null || list.isEmpty()) 
			return null;
			
		dto.setStime(String.valueOf(list.get(0).get("stime")));
		dto.setEtime(String.valueOf(list.get(0).get("etime")));
		dto.setRelation_period_id(String.valueOf(list.get(0).get("relation_period_id")));
		
		return dto;
	}

	@Override
	public void addDCP(DCPDto dto) {

		if(dto.getRelation_period_id() == null || "".equals(dto.getRelation_period_id())) {
			dao.insertDCP(dto);
		} else {
			dao.updateDCP(dto);
		}
	}

	@Override
	public Map<String, Object> queryPeriodById(DCPDto dto) {

		return dao.queryPeriodById(dto);
	}

	@Override
	public void deleteDCP(String id) {

		dao.deleteDCP(id);
	}

	@Override
	public void batchDeleteDCP(DCPDto dto) {

		dao.batchDeleteDCP(dto);
	}
}
