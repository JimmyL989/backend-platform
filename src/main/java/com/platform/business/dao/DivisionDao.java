package com.platform.business.dao;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.DivisionDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface DivisionDao extends BaseDao<DivisionDto> {
	
	public List queryTreeGrid();

	public void insertDivision(DivisionDto dto) throws Exception ;
	
	public void deleteDivision(String id);
	
	public Map<String, Object> queryDivisionById(String id);
	
	public void updateDivision(DivisionDto dto);
	
}
