package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.LogonDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface LogonDao extends BaseDao<LogonDto> {

	public Map<String, String> selectByCode(String userCode);
	
	public List queryforRootMenuUrlList(String userId);
	
	public List queryforRootMenuUrlList(int rootId);
	
	public List queryforRootMenuUrlList(LogonDto dto);
	
	public List<Map<String, String>> queryforAuthUrls();
	
	public List<Map<String, String>> queryforAuthUrls(String userId);
	
}
