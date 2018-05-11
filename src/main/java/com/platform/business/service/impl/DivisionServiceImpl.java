package com.platform.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.business.dao.DivisionDao;
import com.platform.business.dto.DivisionDto;
import com.platform.business.model.DivisionModel;
import com.platform.business.service.DivisionService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

/**
 * 行政区划维护
 * @author yang.li
 *
 */
@Service
public class DivisionServiceImpl extends BaseServiceImpl<DivisionDto> implements DivisionService {
	
	@Autowired
	private DivisionDao dao;

	@Override
	protected BaseDao<DivisionDto> getBaseDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public Map<String, List<DivisionModel>> queryTreeGrid() {
		
		List<DivisionModel> resultList = new ArrayList<DivisionModel>();
		List<Map<String, Object>> list;

		list = dao.queryTreeGrid();
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			DivisionModel pm = new DivisionModel();
			BeanUtils.copyProperties(pm, map);

			pm.setAdmin_region_id(String.valueOf(map.get("admin_region_id")));
			pm.setParentid(String.valueOf("9999".equals(String.valueOf(map.get("parent_admin_region_id"))) ? "" : map.get("parent_admin_region_id")));
			pm.set_parentId(pm.getParentid());

			resultList.add(pm);
		}

		Map<String, List<DivisionModel>> map = new HashMap<String, List<DivisionModel>>();
		map.put("rows", resultList);

		return map;
	}
	
	@Override
	public void insertDivision(DivisionDto dto) throws Exception  {

		dao.insertDivision(dto);
		
	}

	@Override
	public void deleteDivision(String id) {

		dao.deleteDivision(id);
	}

	@Override
	public Map<String, Object> queryDivisionById(String id) {

		return dao.queryDivisionById(id);
	}

	@Override
	public void updateDivision(DivisionDto dto) {

		dao.updateDivision(dto);
	}

}
