package com.platform.business.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.ProductDto;
import com.platform.business.model.ProductModel;
import com.platform.business.service.ProductService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 产品实例维护
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/ProductController")
public class ProductController extends BaseController {

	@Autowired
	private ProductService service;
	
	
	@RequestMapping("/queryCultivation.do")
	@ResponseBody
	public DataGrid queryCultivation(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryCultivation(dto);
	}
	@RequestMapping("/queryDeficiency.do")
	@ResponseBody
	public DataGrid queryDeficiency(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryDeficiency(dto);
	}
	@RequestMapping("/queryPlant.do")
	@ResponseBody
	public DataGrid queryPlant(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryPlant(dto);
	}
	@RequestMapping("/queryRelevant.do")
	@ResponseBody
	public DataGrid queryRelevant(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryRelevant(dto);
	}
	@RequestMapping("/queryReference.do")
	@ResponseBody
	public DataGrid queryReference(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryReference(dto);
	}
	@RequestMapping("/queryFaming.do")
	@ResponseBody
	public DataGrid queryFaming(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryFaming(dto);
	}
	@RequestMapping("/queryAnalysis.do")
	@ResponseBody
	public DataGrid queryAnalysis(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryAnalysis(dto);
	}
	
	
	@RequestMapping("/getAdvice.do")
	@ResponseBody
	public Json getAdvice(ProductModel model) {
		Json j = new Json();
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		List list = service.getAdvice(dto);
		JSONArray ja = new JSONArray(list);
		
		j.setMsg("");
		j.setObj(ja);
		j.setSuccess(true);
		if (list == null || list.isEmpty())
			j.setSuccess(false);
		return j;
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
	public DataGrid dataGrid(ProductModel model, PageHelper ph) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 查询前5天产品列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryProduct5days.do")
	@ResponseBody
	public DataGrid queryProduct5days(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryProduct5days(dto);
	}
	
	/**
	 * 查询生长发育期列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/queryPeriod.do")
	@ResponseBody
	public DataGrid queryPeriod(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryPeriod(dto);
	}
	
	/**
	 * 获取行政区划下拉列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryRegionTree.do")
	@ResponseBody
	public JSONArray queryRegionTree(ProductModel model) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryRegionTree(dto);
	}
	
	/**
	 * 获取产品下拉列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryProductCombo.do")
	@ResponseBody
	public JSONArray queryProductCombo() {
		
		return service.queryProductCombo();
	}
	
	/**
	 * 获取在有效期内的产品下拉列表
	 * @return
	 */
	@RequestMapping("/queryProductComboNew.do")
	@ResponseBody
	public JSONArray queryProductComboNew() {
		
		return service.queryProductComboNew();
	}
	
	/**
	 * 获取在有效期内的产品下拉列表
	 * @return
	 */
	@RequestMapping("/queryProductComboNew_.do")
	@ResponseBody
	public JSONArray queryProductComboNew_() {
		
		return service.queryProductComboNew_();
	}
	
	/**
	 * 添加短信产品
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/addInstance.do")
	@ResponseBody
	public Json add(ProductModel model, HttpSession session) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.insertInstance(dto, session);
	}
	
	@RequestMapping("/queryMessageBody.do")
	@ResponseBody
	public Json queryMessageBody(ProductModel model) {
		Json j = new Json();
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		Object list = service.queryMessageBody(dto);
		
		j.setMsg("");
		j.setObj(list);
		j.setSuccess(true);
		if (list == null)
			j.setSuccess(false);
		return j;
	}
	
	/**
	 * 跳转到用户修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String instanceid) {
		
		Map map = service.queryInstanceById(instanceid);
		
		request.setAttribute("instance", map);
		
		return "business/product/productEdit";
	}
	
	@RequestMapping("/editExaminPage.do")
	public String editExaminPage(HttpServletRequest request, String instanceid) {
		
		Map map = service.queryInstanceById(instanceid);
		
		request.setAttribute("instance", map);
		
		return "business/examin/examinEdit1";
	}
	
	@RequestMapping("/deleteInstance.do")
	@ResponseBody
	public Json deleteInstance(String instanceid, HttpSession session) {
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		Json j = new Json();
			service.deleteInstance(instanceid);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/toExaminInstance.do")
	@ResponseBody
	public Json toExaminInstance(ProductModel model) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		Json j = new Json();
		service.toExaminInstance(dto);
		j.setMsg("提交成功！");
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/queryInstanceMonitor.do")
	@ResponseBody
	public DataGrid queryInstanceMonitor(ProductModel model, PageHelper ph) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryInstanceMonitor(dto, ph);
	}
	
	@RequestMapping("/queryForecast.do")
	@ResponseBody
	public DataGrid queryForecast(ProductModel model, HttpServletRequest request) {
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.queryForecast(dto, request);
	}
	
}
