package com.platform.authorization.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.dao.GroupDao;
import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.GroupService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.StringUtil;

/**
 * 
 * @author yang.li
 *
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupDto> implements GroupService {
	
	@Autowired
	private GroupDao dao;

	@Override
	protected BaseDao<GroupDto> getBaseDao() {

		return dao;
	}

	@Override
	public DataGrid dataGrid(GroupDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.dataGrid(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryGroupCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid noExistGroup(GroupDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.noExistGroup(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.noExistGroupCount(dto));
		
		return dg;
	}

	@Override
	public void insertGroup(GroupDto dto, HttpSession session) {

		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		dto.setCuserid(userModel.getUserid());
		
		dao.insertGroup(dto);
	}

	@Override
	public Map<String, String> queryGroupById(String id) {

		return dao.queryGroupById(id);
	}

	@Override
	public void updateGroup(GroupDto dto, HttpSession session) {
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		dto.setEuserid(userModel.getUserid());

		dao.updateGroup(dto);
	}

	@Override
	public void deleteGroup(String id) {

		dao.deleteGroup(id);
	}
	
	@Override
	public void assignUser(GroupDto dto) {

		dao.assignUser(dto);
	}

	@Override
	public void cancelUser(GroupDto dto) {

		dao.cancelUser(dto);
	}

}
