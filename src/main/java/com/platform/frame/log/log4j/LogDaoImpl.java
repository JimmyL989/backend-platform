package com.platform.frame.log.log4j;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<LogDto> implements LogDao {

	@Override
	public void insertLogonLog(List list) {

		sqlSessionTemplate.insert(getSqlName("insertLogonLog"), list);
	}

	@Override
	public void updateLogonOffTime(LogDto dto) {
		
		sqlSessionTemplate.update(getSqlName("updateLogonOffTime"), dto);
	}

	@Override
	public void updateAllLogonOffTime(LogDto dto) {
		
		sqlSessionTemplate.update(getSqlName("updateAllLogonOffTime"), dto);
	}

	@Override
	public void insertSqlLog(List list) {

		sqlSessionTemplate.insert(getSqlName("insertSqlLog"), list);
	}

	@Override
	public void insertOperateLog(List list) {

		sqlSessionTemplate.insert(getSqlName("insertOperateLog"), list);
	}

}
