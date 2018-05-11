package com.platform.authorization.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.authorization.dao.CodeDao;
import com.platform.authorization.dto.CodeDto;
import com.platform.authorization.service.CodeService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;

/**
 * 
 * @author yang.li
 *
 */
@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl<CodeDto> implements CodeService {
	
	@Autowired
	private CodeDao dao;

	@Override
	protected BaseDao<CodeDto> getBaseDao() {

		return dao;
	}

	@Override
	public List<Map<String, String>> queryCodeList() {

		return dao.queryCodeList();
	}

}
