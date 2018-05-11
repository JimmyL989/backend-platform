package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.AwstationDao;
import com.platform.business.dto.AwstationDto;
import com.platform.business.dto.CropDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class AwstationDaoImpl extends BaseDaoImpl<AwstationDto> implements AwstationDao {

	@Override
	public List<Map<String, Object>> queryAwstation(AwstationDto dto) {
		
		return this.selectList("queryAwstation", dto);
	}
	
	@Override
	public Long queryAwstationCount(AwstationDto dto) {

		return this.selectOne("queryAwstationCount", dto);
	}

	@Override
	public void insertAwstation(AwstationDto dto) {

		this.insert("insertAwstation", dto);
	}

	@Override
	public Map<String, Object> queryAwstationById(String id) {

		return this.selectOne("queryAwstationById", id);
	}

	@Override
	public void updateAwstation(AwstationDto dto) {

		this.update("updateAwstation", dto);
	}

	@Override
	public void deleteAwstation(String id) {

		this.delete("deleteAwstation", id);
	}

}
