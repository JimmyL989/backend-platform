package com.platform.authorization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.dao.SysLogDao;
import com.platform.authorization.dto.SysLogDto;
import com.platform.authorization.dto.UserDto;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.SysLogService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;
import com.platform.frame.util.BeanUtils;

/**
 * 
 * @author yang.li
 *
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogDto> implements SysLogService {

	@Autowired
	private SysLogDao dao;
	
	@Override
	protected BaseDao<SysLogDto> getBaseDao() {

		return dao;
	}
	
	@Override
	public DataGrid doLogonLogQuery(SysLogDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryLogonLog(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryLogonLogCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid doOperateLogQuery(SysLogDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.queryOperateLog(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.queryOperateLogCount(dto));
		
		return dg;
	}
	
	@Override
	public DataGrid doSqlLogQuery(SysLogDto dto, PageHelper ph) {
		
		DataGrid dg = new DataGrid();
		BeanUtils.copyProperties(dto, ph);
		
		List list = dao.querySqlLog(dto);
		
		dg.setRows(list);
		dg.setTotal(dao.querySqlLogCount(dto));
		
		return dg;
	}

}
