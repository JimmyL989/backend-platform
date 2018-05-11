package com.platform.frame.log.log4j;

import java.util.List;

import com.platform.authorization.dto.LogonDto;
import com.platform.frame.service.BaseService;

/**
 * 该类为log写入service接口
 * @author yang.li
 */
public interface FlushLogService extends BaseService<LogDto> 
{
	public void flushOperateLogBufferTA(List logBuffer)throws Exception;
	public void flushSqlLogBufferTA(List logBuffer)throws Exception;
	public void flushLogonLogBufferTA(List logBuffer)throws Exception;
	public void updateLogonOffTime(String logonId);
	public void updateAllLogonOffTime();
}
