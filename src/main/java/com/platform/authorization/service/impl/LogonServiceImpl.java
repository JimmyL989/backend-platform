package com.platform.authorization.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.dao.LogonDao;
import com.platform.authorization.dto.LogonDto;
import com.platform.authorization.service.LogonService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.StringUtil;

/**
 * 
 * @author yang.li
 *
 */
@Service("logonService")
public class LogonServiceImpl extends BaseServiceImpl<LogonDto> implements LogonService {

	@Autowired
	private LogonDao dao;

	@Override
	protected BaseDao<LogonDto> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Map selectByCode(String userCode) {
		// TODO Auto-generated method stub
		return dao.selectByCode(userCode);
	}

	@Override
	public List queryforRootMenuUrlList(int rootId, String userId) {
		// 超级管理员拥有所有权限
		if ("1".equals(userId))
			return dao.queryforRootMenuUrlList(rootId);
		
		LogonDto dto = new LogonDto();
		dto.setRootid(String.valueOf(rootId));
		dto.setUserid(userId);
		
		return dao.queryforRootMenuUrlList(dto);
	}

	@Override
	public List<String> queryforAuthUrls(String userId) {
		
		List<Map<String, String>> list;
		List<String> rList = new ArrayList<String>();
		// 超级管理员拥有所有权限
		if ("1".equals(userId)) {
			list = dao.queryforAuthUrls();
		} else {
			list = dao.queryforAuthUrls(userId);
		}
			
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) != null && !StringUtil.isNullString(list.get(i).get("url")))
				rList.add(list.get(i).get("url"));
		}
		
		return rList;
	}

}
