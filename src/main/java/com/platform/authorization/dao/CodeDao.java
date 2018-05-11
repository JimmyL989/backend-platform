package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.CodeDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface CodeDao extends BaseDao<CodeDto> {
	
	public List<Map<String, String>> queryCodeList();

}
