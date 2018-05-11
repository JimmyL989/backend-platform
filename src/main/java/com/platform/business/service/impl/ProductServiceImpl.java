package com.platform.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dao.ProductDao;
import com.platform.business.dto.ProductDto;
import com.platform.business.service.ProductService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.TreeUtil;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductDto> implements ProductService {

	@Autowired
	private ProductDao dao;
	
	@Override
	protected BaseDao<ProductDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid dataGrid(ProductDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryProduct(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryProductCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid queryFaming(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryFaming(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryCultivation(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryCultivation(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryDeficiency(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryDeficiency(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryPlant(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryPlant(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryRelevant(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryRelevant(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryReference(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryReference(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	@Override
	public DataGrid queryAnalysis(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryAnalysis(dto);
		
		dg.setRows(list);
		
		return dg;
	}
	
	@Override
	public DataGrid queryInstanceMonitor(ProductDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryInstanceMonitor(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryInstanceMonitorCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid queryForecast(ProductDto dto, HttpServletRequest request) {
		
		DataGrid dg = new DataGrid();
		
		List<Map<String, Object>> list = dao.queryForecast(dto);
		
		for (int i = 0; i < list.size(); i++) {
			if ("48".equals(String.valueOf(list.get(i).get("time")))) {
				request.setAttribute("forecastContent", String.valueOf(list.get(i).get("content")));
				break;
			}
		}

		dg.setRows(list);
		
		return dg;
	}
	
	@Override
	public DataGrid queryProduct5days(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryProduct5days(dto);
		
		dg.setRows(list);
		
		return dg;
	}

	@Override
	public Object queryMessageBody(ProductDto dto) {

		List<Map<String, Object>> list = dao.queryProduct5days(dto);
		
		if (list == null || list.isEmpty()) 
			return null;
			
		dto.setSendstate(String.valueOf(list.get(0).get("sendstate")));
		dto.setContent(String.valueOf(list.get(0).get("content")));
		dto.setInstanceid(String.valueOf(list.get(0).get("Instanceid")));
		dto.setCuserid(String.valueOf(list.get(0).get("cuserid")));
		
		return dto;
	}
	
	@Override
	public DataGrid queryPeriod(ProductDto dto) {
		
		DataGrid dg = new DataGrid();
		
		List list = dao.queryPeriod(dto);
		
		dg.setRows(list);
		
		return dg;
	}

	@Override
	public JSONArray queryRegionTree(ProductDto dto) {
		
		List<Map<String, String>> list = dao.queryRegionTree(dto);
		JSONArray json = new JSONArray(TreeUtil.getList(list, "9999"));
		return json;
	}
	
	@Override
	public JSONArray queryProductCombo() {
		
		List list = dao.queryProductCombo();
		JSONArray json = new JSONArray(list);
		return json;
	}

	@Override
	public JSONArray queryProductComboNew() {
		
		List list = dao.queryProductComboNew();
		JSONArray json = new JSONArray(list);
		return json;
	}
	@Override
	public JSONArray queryProductComboNew_() {
		
		List list = dao.queryProductComboNew_();
		JSONArray json = new JSONArray(list);
		return json;
	}

	@Override
	public Json insertInstance(ProductDto dto, HttpSession session) {

		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		
		List<Map<String, String>> list = dao.querySensitive();
		
		for (int i = 0; i < list.size(); i++) {
			if(dto.getMessageBody().indexOf(list.get(i).get("words")) != -1) {
				j.setSuccess(false);
				j.setMsg("警告！存在敏感词汇：" + list.get(i).get("words"));
				return j;
			}
				
		}
		
		if (dto.getInstanceid() == null || "".equals(dto.getInstanceid())) {
			dto.setCuserid(userModel.getUserid());
			try {
				dao.insertInstance(dto);
			} catch (Exception e) {
				j.setSuccess(false);
				j.setMsg("产品实例已经存在！");
			}
		} else {
			dto.setEuserid(userModel.getUserid());
			dao.updateInstance(dto);
		}
		
		return j;

	}

	@Override
	public Map<String, Object> queryInstanceById(String instanceid) {

		return dao.queryInstanceById(instanceid);
	}

	@Override
	public void deleteInstance(String instanceid) {

		dao.deleteInstance(instanceid);
	}

	@Override
	public void toExaminInstance(ProductDto dto) {

		dao.toExaminInstance(dto);
	}

	@Override
	public List getAdvice(ProductDto dto) {

		return dao.getAdvice(dto.getProductId());
	}

}
