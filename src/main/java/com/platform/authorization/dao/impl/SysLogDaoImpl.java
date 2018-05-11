package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.SysLogDao;
import com.platform.authorization.dto.SysLogDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class SysLogDaoImpl extends BaseDaoImpl<SysLogDto> implements SysLogDao {

	@Override
	public List<Map<String, Object>> queryLogonLog(SysLogDto dto) {
	
		return this.selectList("queryLogonLog", dto, true);
	}

	@Override
	public Long queryLogonLogCount(SysLogDto dto) {
	
		return this.selectOne("queryLogonLogCount", dto, true);
	}
	
	@Override
	public List<Map<String, Object>> queryOperateLog(SysLogDto dto) {
		
		return this.selectList("queryOperateLog", dto, true);
	}
	
	@Override
	public Long queryOperateLogCount(SysLogDto dto) {
		
		return this.selectOne("queryOperateLogCount", dto, true);
	}
	
	@Override
	public List<Map<String, Object>> querySqlLog(SysLogDto dto) {
		
		return this.selectList("querySqlLog", dto, true);
	}
	
	@Override
	public Long querySqlLogCount(SysLogDto dto) {
		
		return this.selectOne("querySqlLogCount", dto, true);
	}

}
