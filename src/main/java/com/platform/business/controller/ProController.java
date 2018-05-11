package com.platform.business.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.ProDto;
import com.platform.business.model.ProModel;
import com.platform.business.service.ProService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 产品维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/ProController")
public class ProController extends BaseController {
	
	@Autowired
	private ProService service;
	
	@RequestMapping("/grantRegion.do")
	@ResponseBody
	public Json grantRegion(ProModel model) {
		Json j = new Json();
		ProDto dto = new ProDto();
		BeanUtils.copyProperties(dto, model);

		service.grantRegion(dto);
		
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	
	@RequestMapping("/allTree.do")
	@ResponseBody
	public List<Map<String, String>> allTree() {
		
		return service.queryTree();
	}
	
	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(ProModel model, PageHelper ph) {

		ProDto dto = new ProDto();
		BeanUtils.copyProperties(dto, model);

		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 添加产品
	 * 
	 * @return
	 */
	@RequestMapping("/addPro.do")
	@ResponseBody
	public Json add(ProModel model) {
		ProDto dto = new ProDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertPro(dto);

		return j;
	}
	
	/**
	 * 跳转到农作物修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String productid) {
		
		Map map = service.queryProById(productid);
		
		request.setAttribute("pro", map);
		
		return "business/pro/proEdit";
	}
	
	/**
	 * 修改产品
	 * 
	 * @return
	 */
	@RequestMapping("/editPro.do")
	@ResponseBody
	public Json editCrop(ProModel model) {
		ProDto dto = new ProDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updatePro(dto);

		return j;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePro.do")
	@ResponseBody
	public Json delete(String productid) {
		Json j = new Json();
		service.deletePro(productid);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 产品分配行政区划  查询产品下已分配行政区划
	 * 
	 * @param id
	 *            产品id
	 * @param request
	 * @return
	 */
	@RequestMapping("/pro_regionMapping.do")
	public String pro_regionMapping(String id, HttpServletRequest request) {
		
		String ids = service.queryRegions(id);

		request.setAttribute("ids", ids);
		request.setAttribute("id", id);
		
		return "business/pro/pro_regionMapping";
	}
	
}
