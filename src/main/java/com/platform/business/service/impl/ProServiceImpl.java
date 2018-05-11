package com.platform.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.ProDao;
import com.platform.business.dto.ProDto;
import com.platform.business.service.ProService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

@Service
public class ProServiceImpl extends BaseServiceImpl<ProDto> implements ProService {

	@Autowired
	private ProDao dao;
	
	@Override
	protected BaseDao<ProDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public void grantRegion(ProDto dto) {

		dao.deleteGrantRegion(dto.getProductid());
		
		String ids = dto.getRegionIds();

		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					dto.setRegionid(id);
					dao.insertGrantRegion(dto);
				}
			}
		}
	}
	
	@Override
	public List<Map<String, String>> queryTree() {
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<Map<String, Object>> list;

		list = dao.queryTreeGrid();
		
		for (int i = 0; i < list.size(); i++) {
			Map _map = new HashMap<String, String>();
			Map map = list.get(i);
			
			_map.put("_parentId", String.valueOf(map.get("parent_admin_region_id") == null ? "" : map.get("parent_admin_region_id")));
			_map.put("id", String.valueOf(map.get("admin_region_id")));
			_map.put("text", String.valueOf(map.get("admin_region_name")));
			
			resultList.add(_map);
		}
		
		return resultList;

	}
	
	@Override
	public String queryRegions(String productid) {

		List<Map<String, Object>> list = dao.queryRegions(productid);
		String ids = "";
		for (int i = 0; i < list.size(); i++) {
			ids += String.valueOf(list.get(i).get("region_code")) + ",";
		}
		if(ids.length() == 0)
			return "";
		return ids.substring(0, ids.length() - 1);
	}

	@Override
	public DataGrid dataGrid(ProDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryPro(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryProCount(dto));
		
		return dg;
	}

	@Override
	public void insertPro(ProDto dto) {

		dao.insertPro(dto);
	}
	
	@Override
	public Map<String, Object> queryProById(String productid) {

		return dao.queryProById(productid);
	}

	@Override
	public void updatePro(ProDto dto) {

		dao.updatePro(dto);
	}

	@Override
	public void deletePro(String productid) {

		dao.deletePro(productid);
	}

}
