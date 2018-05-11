package com.platform.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.CropDto;
import com.platform.business.model.CropModel;
import com.platform.business.service.CropService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 农作物维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/CropController")
public class CropController extends BaseController {

	@Autowired
	private CropService service;

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(CropModel model, PageHelper ph) {

		CropDto dto = new CropDto();
		BeanUtils.copyProperties(dto, model);

		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 获取农作物下拉列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryCropCombo.do")
	@ResponseBody
	public JSONArray queryCropCombo() {
		
		return service.queryCropCombo();
	}
	
	/**
	 * 添加农作物
	 * 
	 * @return
	 */
	@RequestMapping("/addCrop.do")
	@ResponseBody
	public Json add(CropModel model) {
		CropDto dto = new CropDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertCrop(dto);

		return j;
	}
	
	/**
	 * 跳转到农作物修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String crops_id) {
		
		Map map = service.queryCropById(crops_id);
		
		request.setAttribute("crops", map);
		
		return "business/crop/cropEdit";
	}
	
	/**
	 * 修改农作物
	 * 
	 * @return
	 */
	@RequestMapping("/editCrop.do")
	@ResponseBody
	public Json editCrop(CropModel model) {
		CropDto dto = new CropDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateCrop(dto);

		return j;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCrop.do")
	@ResponseBody
	public Json delete(String crops_id) {
		Json j = new Json();
		service.deleteCrop(crops_id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
