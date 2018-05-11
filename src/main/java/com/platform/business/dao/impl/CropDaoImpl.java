package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.CropDao;
import com.platform.business.dto.CropDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class CropDaoImpl extends BaseDaoImpl<CropDto> implements CropDao {

	@Override
	public List<Map<String, Object>> queryCrop(CropDto dto) {
		
		return this.selectList("queryCrop", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryCropCombo() {
		
		return this.selectList("queryCropCombo");
	}

	@Override
	public Long queryCropCount(CropDto dto) {

		return this.selectOne("queryCropCount", dto);
	}

	@Override
	public void insertCrop(CropDto dto) {

		this.insert("insertCrop", dto);
	}

	@Override
	public Map<String, Object> queryCropById(String crops_id) {

		return this.selectOne("queryCropById", crops_id);
	}

	@Override
	public void updateCrop(CropDto dto) {

		this.update("updateCrop", dto);
	}

	@Override
	public void deleteCrop(String crops_id) {

		this.delete("deleteCrop", crops_id);
	}

}
