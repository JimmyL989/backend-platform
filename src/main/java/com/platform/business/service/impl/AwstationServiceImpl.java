package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.AwstationDao;
import com.platform.business.dto.AwstationDto;
import com.platform.business.dto.CropDto;
import com.platform.business.service.AwstationService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class AwstationServiceImpl extends BaseServiceImpl<AwstationDto> implements AwstationService {

	@Autowired
	private AwstationDao dao;
	
	@Override
	protected BaseDao<AwstationDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid dataGrid(AwstationDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryAwstation(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryAwstationCount(dto));
		
		return dg;
	}
	
	@Override
	public void insertAwstation(AwstationDto dto) {

		dao.insertAwstation(dto);
	}

	@Override
	public Map<String, Object> queryAwstationById(String id) {

		return dao.queryAwstationById(id);
	}

	@Override
	public void updateAwstation(AwstationDto dto) {

		dao.updateAwstation(dto);
	}

	@Override
	public void deleteAwstation(String id) {

		dao.deleteAwstation(id);
	}
	
}
