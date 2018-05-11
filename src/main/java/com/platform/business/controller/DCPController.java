package com.platform.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.DCPDto;
import com.platform.business.model.DCPModel;
import com.platform.business.service.DCPService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.StringUtil;
import com.platform.frame.web.controller.BaseController;

/**
 * 生长发育期分配管理
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/DCPController")
public class DCPController extends BaseController {
	
	@Autowired
	private DCPService service;

	/**
	 * 查询列表
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(DCPModel model, PageHelper ph) {
		
		DCPDto dto = new DCPDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.dataGrid(dto, ph);
	}
	
	
	/**
	 * 查询框的行政区划下拉框  省市县分开
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDivision.do")
	@ResponseBody
	public JSONArray getM(String id) {
		String parent_admin_region_id = "9999";
		if (!StringUtil.isNullString(id)) {
			parent_admin_region_id = id;
		}
		
		return service.queryDivision(parent_admin_region_id);
	}
	
	@RequestMapping("/queryPeriod.do")
	@ResponseBody
	public Json queryPeriod(DCPModel model) {
		Json j = new Json();
		DCPDto dto = new DCPDto();
		BeanUtils.copyProperties(dto, model);
		Object list = service.queryPeriod(dto);
		
		j.setMsg("");
		j.setObj(list);
		j.setSuccess(true);
		if (list == null)
			j.setSuccess(false);
		return j;
	}
	
	@RequestMapping("/addDCP.do")
	@ResponseBody
	public Json addDCP(DCPModel model) {
		Json j = new Json();
		DCPDto dto = new DCPDto();
		BeanUtils.copyProperties(dto, model);

		service.addDCP(dto);
		
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/deleteDCP.do")
	@ResponseBody
	public Json deleteDCP(String id) {
		Json j = new Json();
		
		service.deleteDCP(id);
		
		j.setMsg("删除成功");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, DCPModel model) {
		
		DCPDto dto = new DCPDto();
		BeanUtils.copyProperties(dto, model);
		
		Map map = service.queryPeriodById(dto);
		
		request.setAttribute("dcp", map);
		
		return "business/dcp/dcpEdit";
	}
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            ('0','1','2')
	 * @return
	 */
	@RequestMapping("/batchDeleteDCP.do")
	@ResponseBody
	public Json batchDelete(DCPModel model, HttpSession session) {
		DCPDto dto = new DCPDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		if (dto.getIds() != null && dto.getIds().length() > 0) {
			
			service.batchDeleteDCP(dto);
				
		}
		j.setMsg("批量删除成功！");
		j.setSuccess(true);
		return j;
	}
	
}
