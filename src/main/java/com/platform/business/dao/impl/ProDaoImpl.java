package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.ProDao;
import com.platform.business.dto.ProDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class ProDaoImpl extends BaseDaoImpl<ProDto> implements ProDao {
	
	@Override
	public List queryTreeGrid() {
		
		return this.selectList("queryTreeGrid");
	}
	
	@Override
	public List<Map<String, Object>> queryRegions(String productid) {

		return this.selectList("queryRegions", productid);
	}
	
	@Override
	public List<Map<String, Object>> queryPro(ProDto dto) {
		
		return this.selectList("queryPro", dto);
	}

	@Override
	public Long queryProCount(ProDto dto) {

		return this.selectOne("queryProCount", dto);
	}

	@Override
	public void insertPro(ProDto dto) {

		this.insert("insertPro", dto);
	}

	@Override
	public Map<String, Object> queryProById(String productid) {

		return this.selectOne("queryProById", productid);
	}

	@Override
	public void updatePro(ProDto dto) {

		this.update("updatePro", dto);
	}

	@Override
	public void deletePro(String productid) {

		this.delete("deletePro", productid);
	}

	@Override
	public void deleteGrantRegion(String productid) {

		this.delete("deleteGrantRegion", productid);
	}

	@Override
	public void insertGrantRegion(ProDto dto) {

		this.insert("insertGrantRegion", dto);
	}
}
