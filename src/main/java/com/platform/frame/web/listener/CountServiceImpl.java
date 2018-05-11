package com.platform.frame.web.listener; 

import com.platform.frame.config.CONFIG;
import com.platform.frame.util.DateUtil;


/**
 * 计数器操作service
 * 
 * @author yang.li
 */
public class CountServiceImpl
{
	private static final String DEFAULT_COUNT = "0";
	private CountDao countDAO;
    private Count count = null;

	private static CountServiceImpl instance = null;

    private CountServiceImpl()
    {
        countDAO = new CountDao();
        this.count = countDAO.getCount();
    }
    
    /**
     * 获得CountServiceImpl实例
     * @return CountServiceImpl实例
     */
    public static CountServiceImpl getInstance()
    {
        if(instance==null)
        {
            instance = new CountServiceImpl();
        }
        return instance;
    }   
	
    /**
     * 获取Count对象
     * @param arg 是否更新数据库
     * @return Count对象
     */
	public final Count getCount(final boolean arg) 
    {
		if (count == null) 
        {
			count = createCount();
			updateCount(count);//更新计数
		}

		if (arg) 
        {
			updateCount(count);
		}

		return count;
	}

	/**
	 * 创建Count对象
	 * @return Count对象
	 */
	private Count createCount() 
    {
		String date = DateUtil.getCurrentDate();
		Count count = new Count();
		count.setAllCount(DEFAULT_COUNT);
        count.setTodayCount(DEFAULT_COUNT);
        count.setYesterdayCount(DEFAULT_COUNT);
		count.setAvgCount(DEFAULT_COUNT);
        count.setMaxCount(DEFAULT_COUNT);
		count.setCreateDate(date);
		count.setMaxDate(date);
		count.setTodayDate(date);
		count.setOnlineCount(DEFAULT_COUNT);

		return count;
	}

	/**
	 * 更新统计信息
	 * @param count
	 */
	private void updateCount(Count count) 
    {
		String now = DateUtil.getCurrentDate();

		String oldDate = count.getTodayDate();
        int nAllCount = Integer.parseInt(count.getAllCount()) + 1;
		count.setAllCount(Integer.toString(nAllCount)); //总访问量加1
		//set today count
		if (now.equals(oldDate)) 
        {
            int nTodayCount = Integer.parseInt(count.getTodayCount()) + 1;
			count.setTodayCount(Integer.toString(nTodayCount));//今日访问量加1
		} 
        else
        {
            count.setYesterdayCount(count.getTodayCount());
			count.setTodayCount("1");//今日访问量设为1
			count.setTodayDate(now);
		}

		if (Integer.parseInt(count.getTodayCount()) > Integer.parseInt(count.getMaxCount()))
        {
            //日最大访问量计算
			count.setMaxCount(count.getTodayCount());
			count.setMaxDate(count.getTodayDate());
		}

		int days = DateUtil.diffTwoDate(count.getTodayDate(),count.getCreateDate());
		days = (days == 0) ? 1 : days;

		int avgCount = (Integer.parseInt(count.getAllCount()) / days);//日平均访问量计算
		count.setAvgCount(Integer.toString(avgCount));
        int tempcount = Integer.parseInt(count.getTempCount());
        if(tempcount >= CONFIG.SYS_COUNT_BUFFER)
        {
            //如果临时访问量大于设定值，数据存入数据库
            countDAO.updateCount(count);
            count.setTempCount("0");
        }
	}
}
