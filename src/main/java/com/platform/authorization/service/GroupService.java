package com.platform.authorization.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface GroupService extends BaseService<GroupDto> {

	public DataGrid dataGrid(GroupDto dto, PageHelper ph);
	
	public DataGrid noExistGroup(GroupDto dto, PageHelper ph);
	
	public void insertGroup(GroupDto dto, HttpSession session);
	
	public Map<String, String> queryGroupById(String id);
	
	public void updateGroup(GroupDto dto, HttpSession session);
	
	public void deleteGroup(String id);
	
	public void assignUser(GroupDto dto);
	
	public void cancelUser(GroupDto dto);
	
}
