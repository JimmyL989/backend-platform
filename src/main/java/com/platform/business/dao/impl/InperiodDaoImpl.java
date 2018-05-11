package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.business.dao.InperiodDao;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.InperiodDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

@Repository
public class InperiodDaoImpl extends BaseDaoImpl<InperiodDto> implements InperiodDao {

	@Override
	public List<Map<String, Object>> queryCrop(InperiodDto dto) {
		
		return this.selectList("queryCrop", dto);
	}
	
	@Override
	public List<Map<String, Object>> queryCropCombo() {
		
		return this.selectList("queryCropCombo");
	}

	@Override
	public Long queryCropCount(InperiodDto dto) {

		return this.selectOne("queryCropCount", dto);
	}

	@Override
	public void insertCrop(CropDto dto) {

		this.insert("insertCrop", dto);
	}

	@Override
	public Map<String, Object> queryCropById(InperiodDto dto) {

		return this.selectOne("queryCropById", dto);
	}

	@Override
	public void updateCrop(InperiodDto dto) {

		this.update("updateCrop", dto);
	}

	@Override
	public void deleteCrop(String crops_id) {

		this.delete("deleteCrop", crops_id);
	}

}
