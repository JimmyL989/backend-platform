package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.DCPDao;
import com.platform.business.dto.DCPDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class DCPDaoImpl extends BaseDaoImpl<DCPDto> implements DCPDao {

	@Override
	public List<Map<String, Object>> dataGrid(DCPDto dto) {

		return this.selectList("dataGrid", dto);
	}

	@Override
	public Long queryUserCount(DCPDto dto) {

		return this.selectOne("queryUserCount", dto);
	}

	@Override
	public List<Map<String, Object>> queryDivision(String id) {

		return selectList("queryDivision", id);
	}

	@Override
	public List<Map<String, Object>> queryPeriod(DCPDto dto) {

		return this.selectList("queryPeriod", dto);
	}

	@Override
	public void insertDCP(DCPDto dto) {

		this.insert("insertDCP", dto);
	}

	@Override
	public void updateDCP(DCPDto dto) {

		this.update("updateDCP", dto);
	}

	@Override
	public Map<String, Object> queryPeriodById(DCPDto dto) {

		return this.selectOne("queryPeriod", dto);
	}

	@Override
	public void deleteDCP(String id) {

		this.delete("deleteDCP", id);
	}

	@Override
	public void batchDeleteDCP(DCPDto dto) {

		this.delete("batchDeleteDCP", dto);
	}
}
