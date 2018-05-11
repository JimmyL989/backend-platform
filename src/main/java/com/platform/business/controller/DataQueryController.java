package com.platform.business.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.business.dto.DataQueryDto;
import com.platform.business.model.DataQueryModel;
import com.platform.business.service.DataQueryService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 数据查询管理
 * 
 * @author LiYang
 * 
 */
@Controller
@RequestMapping("/business/controller/DataQueryController")
public class DataQueryController extends BaseController {

	@Autowired
	private DataQueryService service;

	/**
	 * 查询预报列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/queryForecast.do")
	@ResponseBody
	public DataGrid queryForecast(DataQueryModel model, PageHelper ph) {

		DataQueryDto dto = new DataQueryDto();
		BeanUtils.copyProperties(dto, model);

		return service.queryForecast(dto, ph);
	}
	
	/**
	 * 查询实况数据列表
	 * 
	 * @param model
	 * @param ph
	 * @return
	 */
	@RequestMapping("/queryLive.do")
	@ResponseBody
	public DataGrid queryLive(DataQueryModel model) {

		DataQueryDto dto = new DataQueryDto();
		BeanUtils.copyProperties(dto, model);

		return service.queryLive(dto);
	}
	
	@RequestMapping("/queryStationCombo.do")
	@ResponseBody
	public JSONArray queryStationCombo(String regionId) {
		
		return service.queryStationCombo(regionId);
	}
	
	@RequestMapping("/queryTypeCombo.do")
	@ResponseBody
	public JSONArray queryTypeCombo(String id) {
		
		return service.queryTypeCombo(id);
	}
	
	@RequestMapping("/downFile.do")
	public void downFile(String filePath, HttpServletResponse response) throws IOException {
		
		
		OutputStream o = response.getOutputStream();
		byte b[] = new byte[500];
		File fileLoad = new File(filePath);
		response.reset();

		response.setContentType("application/vnd.ms-excel");

		response.setHeader("content-disposition", "attachment; filename=data.xls");
		long fileLength = fileLoad.length();
		String length1 = String.valueOf(fileLength);
		response.setHeader("Content_Length", length1);
		FileInputStream in = new FileInputStream(fileLoad);
		int n;
		while ((n = in.read(b)) != -1) {
			o.write(b, 0, n);
		}

		in.close();
		o.close();
		fileLoad.delete();
	}
	
	@RequestMapping("/createFile.do")
	@ResponseBody
	public Json createFile(String data, HttpServletResponse response, HttpServletRequest request) throws IOException {
		
		Date date = new Date();
		String s = String.valueOf(date.getTime());
		String path = request.getSession().getServletContext().getRealPath("/") + s + ".xls";
		File file = new File(path);
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(data.getBytes("utf-8"));
		fos.close();
		
//		response.addHeader("Content-Disposition", "attachment;filename=" + "123");
//        response.addHeader("Content-Length", "" + "3");
//        response.setContentType("application/octet-stream");
        Json j = new Json();
//
//        
//                	OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//			toClient.write("123".getBytes());
//			toClient.flush();
//			toClient.close();
			j.setSuccess(true);
			j.setMsg(path);
			return j;
            
	}

}
