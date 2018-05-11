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
import com.platform.business.dto.AwstationDto;
import com.platform.business.dto.CropDto;
import com.platform.business.model.AwstationModel;
import com.platform.business.model.CropModel;
import com.platform.business.service.AwstationService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 站点维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/AwstationController")
public class AwstationController extends BaseController {
	
	@Autowired
	private AwstationService service;

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(AwstationModel model, PageHelper ph) {

		AwstationDto dto = new AwstationDto();
		BeanUtils.copyProperties(dto, model);

		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 添加站点
	 * 
	 * @return
	 */
	@RequestMapping("/addAwstation.do")
	@ResponseBody
	public Json add(AwstationModel model) {
		AwstationDto dto = new AwstationDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertAwstation(dto);

		return j;
	}
	
	/**
	 * 跳转到站点修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String id) {
		
		Map map = service.queryAwstationById(id);
		
		request.setAttribute("awstations", map);
		
		return "business/awstation/awstationEdit";
	}
	
	/**
	 * 修改站点
	 * 
	 * @return
	 */
	@RequestMapping("/editAwstation.do")
	@ResponseBody
	public Json editAwstation(AwstationModel model) {
		AwstationDto dto = new AwstationDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateAwstation(dto);

		return j;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAwstation.do")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		service.deleteAwstation(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
