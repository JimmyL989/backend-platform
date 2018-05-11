package com.platform.authorization.service;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.CodeDto;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface CodeService extends BaseService<CodeDto> {
	
	public List<Map<String, String>> queryCodeList();

}
