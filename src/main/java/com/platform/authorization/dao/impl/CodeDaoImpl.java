package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.CodeDao;
import com.platform.authorization.dto.CodeDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class CodeDaoImpl extends BaseDaoImpl<CodeDto> implements CodeDao {
	
	@Override
	public List<Map<String, String>> queryCodeList() {

		return this.selectList("queryCodeList");
	}

}
