package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.GroupDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface GroupDao extends BaseDao<GroupDto>{

	public List<Map<String, Object>> dataGrid(GroupDto dto);
	
	public Long queryGroupCount(GroupDto dto);
	
	public List<Map<String, Object>> noExistGroup(GroupDto dto);
	
	public Long noExistGroupCount(GroupDto dto);
	
	public void insertGroup(GroupDto dto);
	
	public Map<String, String> queryGroupById(String id);
	
	public void updateGroup(GroupDto dto);
	
	public void deleteGroup(String id);
	
	public void assignUser(GroupDto dto);
	
	public void cancelUser(GroupDto dto);
	
}
