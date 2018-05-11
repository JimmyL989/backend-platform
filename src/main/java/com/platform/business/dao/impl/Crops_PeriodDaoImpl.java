package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.Crops_PeriodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.Crops_PeriodDto;
import com.platform.business.dto.DCPDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class Crops_PeriodDaoImpl extends BaseDaoImpl<Crops_PeriodDto> implements Crops_PeriodDao {

	@Override
	public List<Map<String, Object>> queryCrop(Crops_PeriodDto dto) {
		
		return this.selectList("queryCrop", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryPeriod(Crops_PeriodDto dto) {

		return this.selectList("queryPeriod", dto);
	}
	
	@Override
	public Long queryCropCount(Crops_PeriodDto dto) {

		return this.selectOne("queryCropCount", dto);
	}

	@Override
	public void insertCrop(Crops_PeriodDto dto) {

		this.insert("insertCrop", dto);
	}

	@Override
	public Map<String, Object> queryCropById(String crops_id) {

		return this.selectOne("queryCropById", crops_id);
	}

	@Override
	public void updateCrop(Crops_PeriodDto dto) {

		this.update("updateCrop", dto);
	}

	@Override
	public void deleteCrop(String crops_id) {

		this.delete("deleteCrop", crops_id);
	}

}
