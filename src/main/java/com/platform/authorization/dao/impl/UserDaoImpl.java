package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.UserDao;
import com.platform.authorization.dto.UserDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserDto> implements UserDao {

	@Override
	public List<Map<String, Object>> dataGrid(UserDto dto) {

		return this.selectList("dataGrid", dto, true);
	}

	@Override
	public Long queryUserCount(UserDto dto) {

		return this.selectOne("queryUserCount", dto, true);
	}

	@Override
	public void insertUser(UserDto dto) {

		this.insert("insertUser", dto, true);
	}

	@Override
	public Long validateUserCode(String usercode) {

		return this.selectOne("validateUserCode", usercode);
	}

	@Override
	public Map<String, String> queryUserById(String id) {

		return this.selectOne("queryUserById", id);
	}

	@Override
	public void updateUser(UserDto dto) {

		this.update("updateUser", dto, true);
	}

	@Override
	public void deleteUser(String id) {

		this.update("deleteUser", id);
	}

	@Override
	public void updatePasswd(UserDto dto) {

		this.update("updatePasswd", dto, true);
	}

	@Override
	public void assignGroup(UserDto dto) {

		this.insert("assignGroup", dto);
	}

	@Override
	public void cancelGroup(UserDto dto) {

		this.delete("cancelGroup", dto);
	}

	@Override
	public List<Map<String, Object>> noExistUser(UserDto dto) {

		return this.selectList("noExistUser", dto, true);
	}

	@Override
	public Long noExistUserCount(UserDto dto) {

		return this.selectOne("noExistUserCount", dto, true);
	}

}
