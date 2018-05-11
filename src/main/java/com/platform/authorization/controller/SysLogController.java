package com.platform.authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.dto.SysLogDto;
import com.platform.authorization.model.SysLogModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.SysLogService;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 系统日志
 * 
 * @author yang.li
 * @since 2014-05-29 10:31:42
 *
 */
@Controller
@RequestMapping("/authorization/controller/SysLogController")
public class SysLogController extends BaseController {
	
	@Autowired
	private SysLogService service;
	
	
	@RequestMapping("/doLogonLogQuery.do")
	@ResponseBody
	public DataGrid doLogonLogQuery(SysLogModel model, PageHelper ph) {
		SysLogDto dto = new SysLogDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.doLogonLogQuery(dto, ph);
	}
	
	@RequestMapping("/doOperateLogQuery.do")
	@ResponseBody
	public DataGrid doOperateLogQuery(SysLogModel model, PageHelper ph) {
		SysLogDto dto = new SysLogDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.doOperateLogQuery(dto, ph);
	}
	
	@RequestMapping("/doSqlLogQuery.do")
	@ResponseBody
	public DataGrid doSqlLogQuery(SysLogModel model, PageHelper ph) {
		SysLogDto dto = new SysLogDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.doSqlLogQuery(dto, ph);
	}
}
