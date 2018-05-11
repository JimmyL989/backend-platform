package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.SysLogDto;
import com.platform.authorization.dto.UserDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface SysLogDao extends BaseDao<SysLogDto> {
	
	public List<Map<String, Object>> queryLogonLog(SysLogDto dto);

	public Long queryLogonLogCount(SysLogDto dto);
	
	public List<Map<String, Object>> queryOperateLog(SysLogDto dto);
	
	public Long queryOperateLogCount(SysLogDto dto);
	
	public List<Map<String, Object>> querySqlLog(SysLogDto dto);
	
	public Long querySqlLogCount(SysLogDto dto);

}
