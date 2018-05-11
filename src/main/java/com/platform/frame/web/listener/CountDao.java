package com.platform.frame.web.listener; 

import java.util.Map;

import org.apache.log4j.Logger;

import com.platform.frame.dao.impl.BaseDaoImpl;


/**
 * 计数器dao
 * 
 * @author yang.li
 */
public class CountDao extends BaseDaoImpl<CountDto> {
    private static Logger logger = Logger.getLogger(CountDao.class.getName());
    
    Count count = null;
	
    public CountDao() 
    {
//        try 
//        {
//            String sql = "select sys_count_id, all_count, today_count, yesterday_count, avg_count, max_count, today_date, max_date, create_date from sys_count";        
//            Map map = this.queryForMap(sql);
//            if (map!=null)
//            {
//            	Sys_countModel model = new Sys_countModel();
//            	SpringUtils.copyProperties(model,map);
//                String allCount = model.getAll_count()!=null?model.getAll_count():"0";
//                String todayCount = model.getToday_count()!=null?model.getToday_count():"0";
//                String yesCount = model.getYesterday_count()!=null?model.getYesterday_count():"0";
//                String avgCount = model.getAvg_count()!=null?model.getAvg_count():"0";
//                String maxCount = model.getMax_count()!=null?model.getMax_count():"0";
//                String todayDate = model.getToday_date()!=null?model.getToday_date():"";
//                String maxDate = model.getMax_date()!=null?model.getMax_date():"";
//                String createDate = model.getCreate_date()!=null?model.getCreate_date():DateUtil.getCurrentDate();
//                count = new Count(allCount,todayCount,yesCount,avgCount,
//                    maxCount, todayDate, maxDate, createDate);
//            }
//        }
//        catch (Exception e)
//        {
//            logger.error("CountDao 构造器异常：" +  e.getMessage());
//        }
    } 
	
	public Count getCount() 
    {           
		return count;
	}
	
	/**
	 * 更新数据库中的统计记录
	 * @param arg Count对象
	 */
	public final void updateCount(final Count arg) 
    {
//        String createDate = arg.getCreateDate();
//        String maxDate = arg.getMaxDate();
//        String todayDate = arg.getTodayDate();
//        String allCount = arg.getAllCount();	
//        String avgCount = arg.getAvgCount();	
//        String maxCount = arg.getMaxCount();	
//        String todayCount = arg.getTodayCount();
//        String yesCount = arg.getYesterdayCount();
//        
//        Sys_countModel model = new Sys_countModel();
//        model.setSys_count_id(CONFIG.SYS_COUNT_ID);
//        model.setCreate_date(createDate);
//        model.setMax_date(maxDate);
//        model.setToday_date(todayDate);
//        model.setAll_count(allCount);
//        model.setAvg_count(avgCount);
//        model.setMax_count(maxCount);
//        model.setToday_count(todayCount);
//        model.setYesterday_count(yesCount);
//        
//        try
//        {
//        	this.updateObject(model);
//        }
//        catch(Exception e) 
//        {
//            logger.error("updateCount()异常：" + e.getMessage());
//        }
	}
}
