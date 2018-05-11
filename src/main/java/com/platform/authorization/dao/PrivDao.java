package com.platform.authorization.dao;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.PrivDto;
import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface PrivDao extends BaseDao<PrivDto> {
	
	public List queryTreeGrid();

	public List queryTreeGrid(String userId);
	
	public void insertPriv(PrivDto dto);
	
	public void deletePriv(String id);
	
	public Map<String, Object> queryPrivById(String id);
	
	public void updatePriv(PrivDto dto);
	
	public List<Map<String, Object>> queryPrivs(String sys_group_id);
	
	public void deleteGrantPriv(String id);
	
	public void insertGrantPriv(PrivDto dto);

}
