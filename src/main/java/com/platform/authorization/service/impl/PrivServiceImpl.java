package com.platform.authorization.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.dao.PrivDao;
import com.platform.authorization.dto.PrivDto;
import com.platform.authorization.model.PrivModel;
import com.platform.authorization.service.PrivService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

/**
 * 
 * @author yang.li
 *
 */
@Service
public class PrivServiceImpl extends BaseServiceImpl<PrivDto> implements PrivService {
	
	@Autowired
	private PrivDao dao;

	@Override
	protected BaseDao<PrivDto> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Map<String, List<PrivModel>> queryTreeGrid(String userId) {
		
		List<PrivModel> resultList = new ArrayList<PrivModel>();
		List<Map<String, Object>> list;
		
		if ("1".equals(userId)) {
			// 超级管理员拥有所有权限
			list = dao.queryTreeGrid();
		} else {
			// 根据用户id检索该用户所拥有的权限 用于显示功能点管理配置树
			list = dao.queryTreeGrid(userId);
		}
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			PrivModel pm = new PrivModel();
			BeanUtils.copyProperties(pm, map);
			
			pm.setSys_privilege_id(String.valueOf(map.get("sys_privilege_id")));
			pm.setParentid(String.valueOf(map.get("parentid") == null ? "" : map.get("parentid")));
			pm.set_parentId(pm.getParentid());
			pm.setIconCls("1".equals(String.valueOf(map.get("menutype"))) ? "icon-infoIn" : "icon-submit");
			
			resultList.add(pm);
		}
		
		Map<String, List<PrivModel>> map = new HashMap<String, List<PrivModel>>();
		map.put("rows", resultList);
		
		return map;
	}
	
	@Override
	public List<Map<String, String>> queryTree(String userId) {
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<Map<String, Object>> list;
		// 超级管理员拥有所有权限
		if ("1".equals(userId)) {
			list = dao.queryTreeGrid();
			
		} else {
			list = dao.queryTreeGrid(userId);
		}
		
		for (int i = 0; i < list.size(); i++) {
			Map _map = new HashMap<String, String>();
			Map map = list.get(i);
			
			_map.put("_parentId", String.valueOf(map.get("parentid") == null ? "" : map.get("parentid")));
			_map.put("id", String.valueOf(map.get("sys_privilege_id")));
			_map.put("text", String.valueOf(map.get("name")));
			
			resultList.add(_map);
		}
		
		return resultList;

	}

	@Override
	public void insertPriv(PrivDto dto) {

		dao.insertPriv(dto);
		
	}

	@Override
	public void deletePriv(String id) {

		dao.deletePriv(id);
	}

	@Override
	public Map<String, Object> queryPrivById(String id) {

		return dao.queryPrivById(id);
	}

	@Override
	public void updatePriv(PrivDto dto) {

		dao.updatePriv(dto);
	}

	@Override
	public String queryPrivs(String sys_group_id) {

		List<Map<String, Object>> list = dao.queryPrivs(sys_group_id);
		String ids = "";
		for (int i = 0; i < list.size(); i++) {
			ids += String.valueOf(list.get(i).get("sys_privilege_id")) + ",";
		}
		if(ids.length() == 0)
			return "";
		return ids.substring(0, ids.length() - 1);
	}

	@Override
	public void grantPriv(PrivDto dto) {

		dao.deleteGrantPriv(dto.getSys_group_id());
		
		String ids = dto.getResourceIds();
		dto.setStatus("1");
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					dto.setSys_privilege_id(id);
					dao.insertGrantPriv(dto);
				}
			}
		}
		
		String ids_ = dto.getResourceIds_();
		dto.setStatus("0");
		if (ids_ != null && ids_.length() > 0) {
			for (String id : ids_.split(",")) {
				if (id != null) {
					dto.setSys_privilege_id(id);
					dao.insertGrantPriv(dto);
				}
			}
		}
	}

}
