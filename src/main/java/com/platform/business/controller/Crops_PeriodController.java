package com.platform.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.CropDto;
import com.platform.business.dto.Crops_PeriodDto;
import com.platform.business.dto.DCPDto;
import com.platform.business.model.CropModel;
import com.platform.business.model.Crops_PeriodModel;
import com.platform.business.model.DCPModel;
import com.platform.business.service.Crops_PeriodService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 农作物维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/Crops_PeriodController")
public class Crops_PeriodController extends BaseController {

	@Autowired
	private Crops_PeriodService service;

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(Crops_PeriodModel model, PageHelper ph) {

		Crops_PeriodDto dto = new Crops_PeriodDto();
		BeanUtils.copyProperties(dto, model);

		return service.dataGrid(dto, ph);
	}
	
	
	@RequestMapping("/queryPeriod.do")
	@ResponseBody
	public Json queryPeriod(Crops_PeriodModel model) {
		Json j = new Json();
		Crops_PeriodDto dto = new Crops_PeriodDto();
		BeanUtils.copyProperties(dto, model);
		Object list = service.queryPeriod(dto);
		
		j.setMsg("");
		j.setObj(list);
		j.setSuccess(true);
		if (list == null)
			j.setSuccess(false);
		return j;
	}
	
	/**
	 * 添加农作物
	 * 
	 * @return
	 */
	@RequestMapping("/addCrop.do")
	@ResponseBody
	public Json add(Crops_PeriodModel model) {
		Crops_PeriodDto dto = new Crops_PeriodDto();
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
		
		request.setAttribute("cp", map);
		
		return "business/crops_period/crops_periodEdit";
	}
	
	/**
	 * 修改农作物
	 * 
	 * @return
	 */
	@RequestMapping("/editCrop.do")
	@ResponseBody
	public Json editCrop(Crops_PeriodModel model) {
		Crops_PeriodDto dto = new Crops_PeriodDto();
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
