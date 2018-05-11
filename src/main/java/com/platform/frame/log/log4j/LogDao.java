package com.platform.frame.log.log4j;

import java.util.List;

import com.platform.frame.dao.BaseDao;

/**
 * 
 * @author yang.li
 *
 */
public interface LogDao extends BaseDao<LogDto> {
	
	public void insertLogonLog(List list);
	
	/**
	 * 更新下线时间
	 * @param id
	 */
	public void updateLogonOffTime(LogDto dto);
	
	/**
	 * 更新所有用户下线时间 在服务器关闭时候
	 * @param dto
	 */
	public void updateAllLogonOffTime(LogDto dto);
	
	/**
	 * 插入sql操作日志
	 * @param dto
	 */
	public void insertSqlLog(List list);
	
	public void insertOperateLog(List list);

}
