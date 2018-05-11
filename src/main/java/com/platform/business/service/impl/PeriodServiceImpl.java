package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.PeriodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.PeriodDto;
import com.platform.business.service.PeriodService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class PeriodServiceImpl extends BaseServiceImpl<PeriodDto> implements PeriodService {

	@Autowired
	private PeriodDao dao;
	
	@Override
	protected BaseDao<PeriodDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid dataGrid(PeriodDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryPeriod(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryPeriodCount(dto));
		
		return dg;
	}
	
	public JSONArray queryPeriodCombo() {
		
		List list = dao.queryPeriodCombo();
		
		return new JSONArray(list);
	}
	public JSONArray queryPeriodComboByCrops(String crops_id) {
		
		List list = dao.queryPeriodComboByCrops(crops_id);
		
		return new JSONArray(list);
	}

	@Override
	public void insertPeriod(PeriodDto dto) {

		dao.insertPeriod(dto);
	}

	@Override
	public Map<String, Object> queryPeriodById(String period_id) {

		return dao.queryPeriodById(period_id);
	}

	@Override
	public void updatePeriod(PeriodDto dto) {

		dao.updatePeriod(dto);
	}

	@Override
	public void deletePeriod(String period_id) {

		dao.deletePeriod(period_id);
	}
	
}
