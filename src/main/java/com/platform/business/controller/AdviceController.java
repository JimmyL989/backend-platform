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
import com.platform.business.dto.AdviceDto;
import com.platform.business.model.AdviceModel;
import com.platform.business.service.AdviceService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 农事建议
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/AdviceController")
public class AdviceController extends BaseController {

	@Autowired
	private AdviceService service;

	/**
	 * 查询列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/queryRelevant.do")
	@ResponseBody
	public DataGrid queryRelevant(AdviceModel model, PageHelper ph) {

		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);

		return service.queryRelevant(dto, ph);
	}
	@RequestMapping("/queryAnalysis.do")
	@ResponseBody
	public DataGrid queryAnalysis(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryAnalysis(dto, ph);
	}
	@RequestMapping("/queryFaming.do")
	@ResponseBody
	public DataGrid queryFaming(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryFaming(dto, ph);
	}
	@RequestMapping("/queryPlant.do")
	@ResponseBody
	public DataGrid queryPlant(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryPlant(dto, ph);
	}
	@RequestMapping("/queryDeficiency.do")
	@ResponseBody
	public DataGrid queryDeficiency(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryDeficiency(dto, ph);
	}
	@RequestMapping("/queryCultivation.do")
	@ResponseBody
	public DataGrid queryCultivation(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryCultivation(dto, ph);
	}
	@RequestMapping("/queryReference.do")
	@ResponseBody
	public DataGrid queryReference(AdviceModel model, PageHelper ph) {
		
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryReference(dto, ph);
	}
	
	
	/**
	 * 添加农作物
	 * 
	 * @return
	 */
	@RequestMapping("/addRelevant.do")
	@ResponseBody
	public Json addRelevant(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertRelevant(dto);

		return j;
	}
	@RequestMapping("/addAnalysis.do")
	@ResponseBody
	public Json addAnalysis(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertAnalysis(dto);
		
		return j;
	}
	@RequestMapping("/addFaming.do")
	@ResponseBody
	public Json addFaming(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertFaming(dto);
		
		return j;
	}
	@RequestMapping("/addPlant.do")
	@ResponseBody
	public Json addPlant(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertPlant(dto);
		
		return j;
	}
	@RequestMapping("/addDeficiency.do")
	@ResponseBody
	public Json addDeficiency(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertDeficiency(dto);
		
		return j;
	}
	@RequestMapping("/addCultivation.do")
	@ResponseBody
	public Json addCultivation(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertCultivation(dto);
		
		return j;
	}
	@RequestMapping("/addReference.do")
	@ResponseBody
	public Json addReference(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		service.insertReference(dto);
		
		return j;
	}
	
	/**
	 * 跳转到农作物修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editRelevantPage.do")
	public String editRelevantPage(HttpServletRequest request, String id) {
		
		Map map = service.queryRelevanById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/relevant/relevantEdit";
	}
	@RequestMapping("/editAnalysisPage.do")
	public String editAnalysisPage(HttpServletRequest request, String id) {
		
		Map map = service.queryAnalysisById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/analysis/analysisEdit";
	}
	@RequestMapping("/editFamingPage.do")
	public String editFamingPage(HttpServletRequest request, String id) {
		
		Map map = service.queryFamingById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/faming/famingEdit";
	}
	@RequestMapping("/editPlantPage.do")
	public String editPlantPage(HttpServletRequest request, String id) {
		
		Map map = service.queryPlantById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/plant/plantEdit";
	}
	@RequestMapping("/editDeficiencyPage.do")
	public String editDeficiencyPage(HttpServletRequest request, String id) {
		
		Map map = service.queryDeficiencyById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/deficiency/deficiencyEdit";
	}
	@RequestMapping("/editCultivationPage.do")
	public String editCultivationPage(HttpServletRequest request, String id) {
		
		Map map = service.queryCultivationById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/cultivation/cultivationEdit";
	}
	@RequestMapping("/editReferencePage.do")
	public String editReferencePage(HttpServletRequest request, String id) {
		
		Map map = service.queryReferenceById(id);
		
		request.setAttribute("advice", map);
		
		return "business/advice/reference/referenceEdit";
	}
	
	
	
	/**
	 * 修改农作物
	 * 
	 * @return
	 */
	@RequestMapping("/editRelevant.do")
	@ResponseBody
	public Json editRelevant(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateRelevant(dto);

		return j;
	}
	@RequestMapping("/editAnalysis.do")
	@ResponseBody
	public Json editAnalysis(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateAnalysis(dto);
		
		return j;
	}
	@RequestMapping("/editFaming.do")
	@ResponseBody
	public Json editFaming(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateFaming(dto);
		
		return j;
	}
	@RequestMapping("/editPlant.do")
	@ResponseBody
	public Json editPlant(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updatePlant(dto);
		
		return j;
	}
	@RequestMapping("/editDeficiency.do")
	@ResponseBody
	public Json editDeficiency(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateDeficiency(dto);
		
		return j;
	}
	@RequestMapping("/editCultivation.do")
	@ResponseBody
	public Json editCultivation(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateCultivation(dto);
		
		return j;
	}
	@RequestMapping("/editReference.do")
	@ResponseBody
	public Json editReference(AdviceModel model) {
		AdviceDto dto = new AdviceDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		service.updateReference(dto);
		
		return j;
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRelevant.do")
	@ResponseBody
	public Json deleteRelevant(String id) {
		Json j = new Json();
		service.deleteRelevant(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deleteAnalysis.do")
	@ResponseBody
	public Json deleteAnalysis(String id) {
		Json j = new Json();
		service.deleteAnalysis(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deleteFaming.do")
	@ResponseBody
	public Json deleteFaming(String id) {
		Json j = new Json();
		service.deleteFaming(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deletePlant.do")
	@ResponseBody
	public Json deletePlant(String id) {
		Json j = new Json();
		service.deletePlant(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deleteDeficiency.do")
	@ResponseBody
	public Json deleteDeficiency(String id) {
		Json j = new Json();
		service.deleteDeficiency(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deleteCultivation.do")
	@ResponseBody
	public Json deleteCultivation(String id) {
		Json j = new Json();
		service.deleteCultivation(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	@RequestMapping("/deleteReference.do")
	@ResponseBody
	public Json deleteReference(String id) {
		Json j = new Json();
		service.deleteReference(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
