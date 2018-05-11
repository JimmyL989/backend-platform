package com.platform.authorization.service;


import java.util.Map;

import javax.servlet.http.HttpSession;

import com.platform.authorization.dto.UserDto;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface UserService extends BaseService<UserDto> {

	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	public DataGrid dataGrid(UserDto dto, PageHelper ph);
	
	public boolean insertUser(UserDto dto, HttpSession session);
	
	public void updateUser(UserDto dto, HttpSession session);
	
	public Map<String, String> queryUserById(String id);
	
	public void deleteUser(String id);
	
	public void updatePasswd(UserDto dto, HttpSession session);

	public void updatePasswd(String pwd, HttpSession session);
	
	public void assignGroup(UserDto dto);
	
	public void cancelGroup(UserDto dto);
	
	public DataGrid noExistUser(UserDto dto, PageHelper ph);
	
}
