package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.dto.UserDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface UserDao extends BaseDao<UserDto> {
	
	public List<Map<String, Object>> dataGrid(UserDto dto);
	
	public Long queryUserCount(UserDto dto);
	
	public void insertUser(UserDto dto);
	
	public void updateUser(UserDto dto);
	
	public Long validateUserCode(String usercode);
	
	public Map<String, String> queryUserById(String id);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(String id);
	
	public void updatePasswd(UserDto dto);
	
	public void assignGroup(UserDto dto);
	
	public void cancelGroup(UserDto dto);
	
	public List<Map<String, Object>> noExistUser(UserDto dto);
	
	public Long noExistUserCount(UserDto dto);

}
