package com.platform.authorization.service;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.LogonDto;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface LogonService extends BaseService<LogonDto> {

	public Map<String, String> selectByCode(String userCode);
	
	public List queryforRootMenuUrlList(int rootId, String userId);
	
	public List<String> queryforAuthUrls(String userId);

}
