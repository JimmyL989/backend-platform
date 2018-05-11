package com.platform.authorization.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.platform.authorization.dao.PrivDao;
import com.platform.authorization.dto.PrivDto;
import com.platform.frame.dao.impl.BaseDaoImpl;

/**
 * 
 * @author yang.li
 *
 */
@Repository
public class PrivDaoImpl extends BaseDaoImpl<PrivDto> implements PrivDao {

	@Override
	public List queryTreeGrid() {
		
		return this.selectList("queryTreeGridForAdmin");
	}
	
	@Override
	public List queryTreeGrid(String userId) {

		return this.selectList("queryTreeGrid", userId);
	}

	/**
	 * 插入功能点
	 */
	@Override
	public void insertPriv(PrivDto dto) {

		this.insert("insertPriv", dto, true);
	}

	@Override
	public void deletePriv(String id) {

		this.delete("deletePriv", id);
	}
	
	@Override
	public Map<String, Object> queryPrivById(String id) {

		return this.selectOne("queryPrivById", id);
	}

	@Override
	public void updatePriv(PrivDto dto) {

		this.update("updatePriv", dto, true);
	}

	@Override
	public List<Map<String, Object>> queryPrivs(String sys_group_id) {

		return this.selectList("queryPrivs", sys_group_id);
	}

	@Override
	public void deleteGrantPriv(String id) {

		this.delete("deleteGrantPriv", id);
	}

	@Override
	public void insertGrantPriv(PrivDto dto) {

		this.insert("insertGrantPriv", dto);
	}

}
