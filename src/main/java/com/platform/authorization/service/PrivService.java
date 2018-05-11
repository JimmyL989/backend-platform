package com.platform.authorization.service;

import java.util.List;
import java.util.Map;

import com.platform.authorization.dto.PrivDto;
import com.platform.authorization.model.PrivModel;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface PrivService extends BaseService<PrivDto> {
	
	public Map<String, List<PrivModel>> queryTreeGrid(String userId);
	
	public List<Map<String, String>> queryTree(String userId);
	
	public void insertPriv(PrivDto dto);
	
	public void deletePriv(String id);
	
	public Map<String, Object> queryPrivById(String id);
	
	public void updatePriv(PrivDto dto);
	
	public String queryPrivs(String sys_group_id);
	
	public void grantPriv(PrivDto dto);

}
