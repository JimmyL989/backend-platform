package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.Crops_PeriodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.Crops_PeriodDto;
import com.platform.business.dto.DCPDto;
import com.platform.business.service.Crops_PeriodService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class Crops_PeriodServiceImpl extends BaseServiceImpl<Crops_PeriodDto> implements Crops_PeriodService {

	@Autowired
	private Crops_PeriodDao dao;
	
	@Override
	protected BaseDao<Crops_PeriodDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid dataGrid(Crops_PeriodDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryCrop(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryCropCount(dto));
		
		return dg;
	}
	
	@Override
	public Object queryPeriod(Crops_PeriodDto dto) {

		List<Map<String, Object>> list = dao.queryPeriod(dto);
		
		if (list == null || list.isEmpty()) 
			return null;
			
		dto.setSdate(String.valueOf(list.get(0).get("sdate")));
		dto.setEdate(String.valueOf(list.get(0).get("edate")));
		dto.setCrops_period_id(String.valueOf(list.get(0).get("crops_period_id")));
		
		return dto;
	}
	
	@Override
	public void insertCrop(Crops_PeriodDto dto) {

		if(dto.getCrops_period_id() == null || "".equals(dto.getCrops_period_id())) {
			dao.insertCrop(dto);
		} else {
			dao.updateCrop(dto);
		}
		
	}

	@Override
	public Map<String, Object> queryCropById(String crops_id) {

		return dao.queryCropById(crops_id);
	}

	@Override
	public void updateCrop(Crops_PeriodDto dto) {

		dao.updateCrop(dto);
	}

	@Override
	public void deleteCrop(String crops_id) {

		dao.deleteCrop(crops_id);
	}
	
}
