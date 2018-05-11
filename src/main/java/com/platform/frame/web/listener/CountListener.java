package com.platform.frame.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.platform.authorization.model.LogonModel;
import com.platform.frame.config.CONFIG;
import com.platform.frame.log.log4j.FlushLogService;
import com.platform.frame.util.SpringUtils;
/**
 * 
 * @author yang.li
 *
 */
public class CountListener implements HttpSessionAttributeListener, ServletContextListener {
	
    private static int onlineCount = 0;
    private CountServiceImpl countService;

	/**
	 * server shutdown时，更新所有在线用户的下线时间
	 */
    @Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			FlushLogService flushLog = (FlushLogService) SpringUtils.getDataSource("FlushLogService");
			flushLog.updateAllLogonOffTime();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeAdded(final HttpSessionBindingEvent arg0) {
		if (this.attributeIsUser(arg0)) {
			init();
			countService.getCount(true);
			onlineCount++;
			Count count = countService.getCount(false);
			count.setOnlineCount(Integer.toString(onlineCount));
			count.setTempCount(count.getTempCount() + 1);
		}
	}

	@Override
	public void attributeRemoved(final HttpSessionBindingEvent arg0) {
		if (this.attributeIsUser(arg0) && onlineCount > 0) {
			onlineCount--;
			countService.getCount(false).setOnlineCount(Integer.toString(onlineCount));

			// 记录用户下线时间
			LogonModel user = (LogonModel) arg0.getValue();
			FlushLogService flushLog = (FlushLogService) SpringUtils.getDataSource("FlushLogService");
			flushLog.updateLogonOffTime(user.getLogonId());
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}
	
    /**
     * 判断属性是否为Sys_userModel
     * @param event
     * @return boolean
     */
    private boolean attributeIsUser(HttpSessionBindingEvent event) {
        String name = event.getName( );
        Object value = event.getValue( );
        return name.equals(CONFIG.LOGON_USER) && value instanceof com.platform.authorization.model.LogonModel;
    }
    
    private void init() {
        if (countService == null) {
            countService = CountServiceImpl.getInstance();
        }
    }
    
    public static int getOnlineCount() {
        return onlineCount;
    }

}
