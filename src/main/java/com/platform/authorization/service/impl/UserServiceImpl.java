package com.platform.authorization.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.dao.UserDao;
import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.UserService;
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
public class UserServiceImpl extends BaseServiceImpl<UserDto> implements UserService{
	
	@Autowired
	private UserDao dao;

	@Override
	protected BaseDao<UserDto> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public DataGrid dataGrid(UserDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.dataGrid(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryUserCount(dto));
		
		return dg;
	}

	@Override
	public boolean insertUser(UserDto dto, HttpSession session) {
		
		if(0 != dao.validateUserCode(dto.getUsercode()))
			return false;
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		dto.setCuserid(userModel.getUserid());
		dto.setPassword(StringUtil.MD5(CONFIG.DEFAULT_PASSWD));
		
		dao.insertUser(dto);
		return true;
	}

	@Override
	public Map<String, String> queryUserById(String id) {

		return dao.queryUserById(id);
	}

	@Override
	public void updateUser(UserDto dto, HttpSession session) {

		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		dto.setEuserid(userModel.getUserid());
		
		dao.updateUser(dto);
	}

	@Override
	public void deleteUser(String id) {

		dao.deleteUser(id);
	}

	@Override
	public void updatePasswd(UserDto dto, HttpSession session) {

		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		dto.setEuserid(userModel.getUserid());
		dto.setPassword(StringUtil.MD5(CONFIG.DEFAULT_PASSWD));
		
		dao.updatePasswd(dto);
	}
	
	@Override
	public void updatePasswd(String pwd, HttpSession session) {
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		UserDto dto = new UserDto();
		dto.setEuserid(userModel.getUserid());
		dto.setSys_user_id(userModel.getUserid());
		dto.setPassword(StringUtil.MD5(pwd));
		
		dao.updatePasswd(dto);
	}

	@Override
	public void assignGroup(UserDto dto) {

		dao.assignGroup(dto);
	}

	@Override
	public void cancelGroup(UserDto dto) {

		dao.cancelGroup(dto);
	}
	
	@Override
	public DataGrid noExistUser(UserDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.noExistUser(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.noExistUserCount(dto));
		
		return dg;
	}

}
