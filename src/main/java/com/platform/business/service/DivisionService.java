package com.platform.business.service;

import java.util.List;
import java.util.Map;

import com.platform.business.dto.DivisionDto;
import com.platform.business.model.DivisionModel;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface DivisionService extends BaseService<DivisionDto> {
	
	public Map<String, List<DivisionModel>> queryTreeGrid();
	
	public void insertDivision(DivisionDto dto) throws Exception ;
	
	public void deleteDivision(String id);
	
	public Map<String, Object> queryDivisionById(String id);
	
	public void updateDivision(DivisionDto dto);
	
}
