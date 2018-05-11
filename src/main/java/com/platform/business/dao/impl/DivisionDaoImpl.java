package com.platform.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dto.PrivDto;
import com.platform.business.dao.DivisionDao;
import com.platform.business.dto.DivisionDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class DivisionDaoImpl extends BaseDaoImpl<DivisionDto> implements DivisionDao {

	@Override
	public List queryTreeGrid() {
		
		return this.selectList("queryTreeGrid");
	}
	
	/**
	 * 插入功能点
	 */
	@Override
	public void insertDivision(DivisionDto dto) throws Exception {

		this.insert("insertDivision", dto);
	}

	@Override
	public void deleteDivision(String id) {

		this.delete("deleteDivision", id);
	}
	
	@Override
	public Map<String, Object> queryDivisionById(String id) {

		return this.selectOne("queryDivisionById", id);
	}

	@Override
	public void updateDivision(DivisionDto dto) {

		this.update("updateDivision", dto);
	}

}
