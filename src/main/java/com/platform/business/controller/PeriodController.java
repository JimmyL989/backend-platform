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
import com.platform.business.dto.PeriodDto;
import com.platform.business.model.PeriodModel;
import com.platform.business.service.PeriodService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 生长发育期维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/PeriodController")
public class PeriodController extends BaseController {

	@Autowired
	private PeriodService service;

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(PeriodModel model, PageHelper ph) {

		PeriodDto dto = new PeriodDto();
		BeanUtils.copyProperties(dto, model);

		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 获取农作物下拉列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryPeriodCombo.do")
	@ResponseBody
	public JSONArray queryPeriodCombo() {
		
		return service.queryPeriodCombo();
	}
	@RequestMapping("/queryPeriodComboByCrops.do")
	@ResponseBody
	public JSONArray queryPeriodComboByCrops(String crops_id) {
		
		return service.queryPeriodComboByCrops(crops_id);
	}
	
	/**
	 * 添加生长发育期
	 * 
	 * @return
	 */
	@RequestMapping("/addPeriod.do")
	@ResponseBody
	public Json add(PeriodModel model) {
		PeriodDto dto = new PeriodDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertPeriod(dto);

		return j;
	}
	
	/**
	 * 跳转到生长发育期修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String period_id) {
		
		Map map = service.queryPeriodById(period_id);
		
		request.setAttribute("periods", map);
		
		return "business/period/periodEdit";
	}
	
	/**
	 * 修改生长发育期
	 * 
	 * @return
	 */
	@RequestMapping("/editPeriod.do")
	@ResponseBody
	public Json editPeriod(PeriodModel model) {
		PeriodDto dto = new PeriodDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updatePeriod(dto);

		return j;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePeriod.do")
	@ResponseBody
	public Json delete(String period_id) {
		Json j = new Json();
		service.deletePeriod(period_id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
