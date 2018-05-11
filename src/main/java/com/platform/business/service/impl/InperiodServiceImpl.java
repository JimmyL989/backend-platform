package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.InperiodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.InperiodDto;
import com.platform.business.service.InperiodService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class InperiodServiceImpl extends BaseServiceImpl<InperiodDto> implements InperiodService {

	@Autowired
	private InperiodDao dao;
	
	@Override
	protected BaseDao<InperiodDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid dataGrid(InperiodDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryCrop(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryCropCount(dto));
		
		return dg;
	}
	
	public JSONArray queryCropCombo() {
		
		List list = dao.queryCropCombo();
		
		return new JSONArray(list);
	}

	@Override
	public void insertCrop(CropDto dto) {

		dao.insertCrop(dto);
	}

	@Override
	public Map<String, Object> queryCropById(InperiodDto dto) {

		return dao.queryCropById(dto);
	}

	@Override
	public void updateCrop(InperiodDto dto) {

		dao.updateCrop(dto);
	}

	@Override
	public void deleteCrop(String crops_id) {

		dao.deleteCrop(crops_id);
	}
	
}
