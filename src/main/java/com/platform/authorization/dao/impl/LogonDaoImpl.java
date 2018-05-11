package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.LogonDao;
import com.platform.authorization.dto.LogonDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class LogonDaoImpl extends BaseDaoImpl<LogonDto> implements LogonDao {

	@Override
	public Map<String, String> selectByCode(String userCode) {

		return this.selectOne("selectByCode", userCode);
	}

	@Override
	public List queryforRootMenuUrlList(String userId) {

		return this.selectList("queryforRootMenuUrlList", userId);
	}

	@Override
	public List queryforRootMenuUrlList(int rootId) {

		return this.selectList("queryforRootMenuUrlListForAdmin", rootId);
	}

	@Override
	public List<Map<String, String>> queryforAuthUrls() {

		return this.selectList("queryforAuthUrlsForAdmin");
	}

	@Override
	public List queryforRootMenuUrlList(LogonDto dto) {
		
		
		return this.selectList("queryforRootMenuUrlList", dto);
	}

	@Override
	public List<Map<String, String>> queryforAuthUrls(String userId) {

		return this.selectList("queryforAuthUrls", userId);
	}

}
