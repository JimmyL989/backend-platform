package com.platform.frame.log.log4j;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.authorization.service.LogonService;
import com.platform.frame.dao.BaseDao;
import com.platform.frame.service.impl.BaseServiceImpl;


/**
 * 该类为log写入service实现类
 * @author yang.li
 */
@Service("FlushLogService")
public class FlushLogServiceImpl extends BaseServiceImpl<LogDto> implements FlushLogService {
	
	@Autowired
	private LogDao dao;
	/**
	 * 向数据库中写入logBuffer数组中的日志信息
	 * @param logBuffer Operatelog数组
	 */
	public void flushOperateLogBufferTA(List logBuffer) throws Exception
	{
		dao.insertOperateLog(logBuffer);
//		String preSql = CONFIG.OperateLog_Sql;
//		final int batchSize = 50;
//		int[] dataType = new int[] 
//		    {
//				Types.VARCHAR, 
//				Types.VARCHAR, 
//				Types.VARCHAR, 
//				Types.VARCHAR, 
//				Types.VARCHAR, 
//				Types.TIMESTAMP, 
//				Types.TIMESTAMP, 
//				Types.VARCHAR, 
//				Types.VARCHAR,
//				Types.VARCHAR,
//				Types.NUMERIC
//			};
//		ArrayList valList = new ArrayList();
//		String mac = "";
//		for (int i = 0; i < logBuffer.size(); i++)
//		{	
//			Operatelog tmp = (Operatelog)logBuffer.get(i);
//			Object[] var = new Object[11];
//			var[0] = tmp.getId();
//			var[1] = tmp.getUserid();
//			var[2] = tmp.getUserip();
//			var[3] = tmp.getOperate();
//			var[4] = tmp.getUrl();
//			var[5] = tmp.getStarttime();
//			var[6] = tmp.getEndtime();
//			var[7] = tmp.getModule();
//			var[8] = tmp.getDescription();
//			mac = tmp.getMacaddr();
//			if (mac!=null && mac.length()>100)
//			{
//				//mac长度限定在100
//				mac = mac.substring(0, 100);
//			}
//			var[9] = mac;
//			var[10] = tmp.getEndtime().getTime() - tmp.getStarttime().getTime();
//			valList.add(var);
//		}
//		this.getBaseDao().batchExcuteSql(preSql, batchSize, dataType, valList);
	}
	
	/**
	 * 向数据库中写入logBuffer数组中的sql操作日志信息
	 * @param logBuffer Sqllog数组
	 */
	public void flushSqlLogBufferTA(List logBuffer) throws Exception {
		
		dao.insertSqlLog(logBuffer);
	}

	public void flushLogonLogBufferTA(List logBuffer) throws Exception {

		dao.insertLogonLog(logBuffer);
	}

	/**
	 * 更新用户的下线时间
	 */
	public void updateLogonOffTime(String logonId) {
		try {
			
			LogDto dto = new LogDto();
			dto.setLogofftime(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
			dto.setLogonId(logonId);
			
			dao.updateLogonOffTime(dto);
			
		} catch (Exception ee) {
		}
	}

	/**
	 * 更新所有用户下线时间(web容器关闭或重启时)
	 */
	public void updateAllLogonOffTime() {
		LogDto dto = new LogDto();
		dto.setLogofftime(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
		dao.updateAllLogonOffTime(dto);
	}

	@Override
	protected BaseDao<LogDto> getBaseDao() {

		return dao;
	}
}
