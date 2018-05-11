package com.platform.authorization.service;

import com.platform.authorization.dto.SysLogDto;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.frame.service.BaseService;

/**
 * 
 * @author yang.li
 *
 */
public interface SysLogService extends BaseService<SysLogDto> {

	public DataGrid doLogonLogQuery(SysLogDto dto, PageHelper ph);
	
	public DataGrid doOperateLogQuery(SysLogDto dto, PageHelper ph);
	
	public DataGrid doSqlLogQuery(SysLogDto dto, PageHelper ph);
	
}
