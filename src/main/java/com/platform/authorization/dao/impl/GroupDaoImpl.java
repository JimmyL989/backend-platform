package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.GroupDao;
import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.dto.UserDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class GroupDaoImpl extends BaseDaoImpl<GroupDto> implements GroupDao {

	@Override
	public List<Map<String, Object>> dataGrid(GroupDto dto) {

		return this.selectList("dataGrid", dto, true);
	}

	@Override
	public Long queryGroupCount(GroupDto dto) {

		return this.selectOne("queryGroupCount", dto, true);
	}
	
	@Override
	public List<Map<String, Object>> noExistGroup(GroupDto dto) {

		return this.selectList("noExistGroup", dto, true);
	}

	@Override
	public Long noExistGroupCount(GroupDto dto) {

		return this.selectOne("noExistGroupCount", dto, true);
	}

	@Override
	public void insertGroup(GroupDto dto) {

		this.insert("insertGroup", dto, true);
	}

	@Override
	public Map<String, String> queryGroupById(String id) {

		return this.selectOne("queryGroupById", id);
	}

	@Override
	public void updateGroup(GroupDto dto) {

		this.update("updateGroup", dto, true);
	}

	@Override
	public void deleteGroup(String id) {

		this.delete("deleteGroup", id);
	}

	@Override
	public void assignUser(GroupDto dto) {

		this.insert("assignUser", dto);
	}

	@Override
	public void cancelUser(GroupDto dto) {
		
		this.delete("cancelUser", dto);
	}
	
}
