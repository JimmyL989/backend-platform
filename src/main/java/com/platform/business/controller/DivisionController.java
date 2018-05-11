package com.platform.business.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.dto.PrivDto;
import com.platform.authorization.model.PrivModel;
import com.platform.authorization.pageModel.Json;
import com.platform.business.dto.DivisionDto;
import com.platform.business.model.DivisionModel;
import com.platform.business.service.DivisionService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.SystemUtil;
import com.platform.frame.web.controller.BaseController;

/**
 * 行政区划管理
 * 
 * @author yang.li
 *
 */
@Controller
@RequestMapping("/business/controller/DivisionController")
public class DivisionController extends BaseController {
	
	@Autowired
	private DivisionService service;
	
	/**
	 * 获得行政区划列表
	 * 
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryTreeGrid.do")
	@ResponseBody
	public Map<String, List<DivisionModel>> queryTreeGrid() {
		
		return service.queryTreeGrid();
	}
	
	/**
	 * 跳转到行政区划添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage.do")
	public String addPage(DivisionModel model, HttpServletRequest request) {
		
		try {
			model.setParentname(new String(model.getParentname().getBytes("iso-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("resource", model);
		
		return "business/division/divisionAdd";
	}
	
	/**
	 * 跳转到行政区划编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String admin_region_id) {

		Map _map = service.queryDivisionById(admin_region_id);
		request.setAttribute("resource", _map);
		return "business/division/divisionEdit";
	}
	
	/**
	 * 添加区划
	 * 
	 * @return
	 */
	@RequestMapping("/addDivision.do")
	@ResponseBody
	public Json addDivision(DivisionModel model, HttpSession session) {
		
		DivisionDto dto = new DivisionDto();
		
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		
		j.setSuccess(true);
		j.setMsg("添加成功！");
		try {
			service.insertDivision(dto);
		} catch (Exception e) {
			j.setMsg("添加失败！");
			j.setSuccess(false);
		}
		
		
		return j;
	}
	
	/**
	 * 修改区划
	 * 
	 * @return
	 */
	@RequestMapping("/editDivision.do")
	@ResponseBody
	public Json editDivision(DivisionModel model, HttpSession session) {
		
		DivisionDto dto = new DivisionDto();
		
		BeanUtils.copyProperties(dto, model);
		
		// 更新区划
		service.updateDivision(dto);

		Json j = new Json();
		
		
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}
	
	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteDivision.do")
	@ResponseBody
	public Json delete(String id, HttpSession session) {
		Json j = new Json();
		service.deleteDivision(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
}
