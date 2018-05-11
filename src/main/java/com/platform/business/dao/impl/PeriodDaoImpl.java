package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.PeriodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.PeriodDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class PeriodDaoImpl extends BaseDaoImpl<PeriodDto> implements PeriodDao {

	@Override
	public List<Map<String, Object>> queryPeriod(PeriodDto dto) {
		
		return this.selectList("queryPeriod", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryPeriodCombo() {
		
		return this.selectList("queryPeriodCombo");
	}
	@Override
	public List<Map<String, Object>> queryPeriodComboByCrops(String crops_id) {
		
		return this.selectList("queryPeriodComboByCrops", crops_id);
	}

	@Override
	public Long queryPeriodCount(PeriodDto dto) {

		return this.selectOne("queryPeriodCount", dto);
	}

	@Override
	public void insertPeriod(PeriodDto dto) {

		this.insert("insertPeriod", dto);
	}

	@Override
	public Map<String, Object> queryPeriodById(String period_id) {

		return this.selectOne("queryPeriodById", period_id);
	}

	@Override
	public void updatePeriod(PeriodDto dto) {

		this.update("updatePeriod", dto);
	}

	@Override
	public void deletePeriod(String period_id) {

		this.delete("deletePeriod", period_id);
	}

}
