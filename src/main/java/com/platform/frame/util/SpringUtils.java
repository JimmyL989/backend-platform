package com.platform.frame.util;

import org.springframework.web.context.ContextLoader;

/**
 * 
 * @author yang.li
 *
 */
public class SpringUtils {

	/**
	 * 取得相应的dataSource实例
	 * @param dataSourceName
	 * @return 相应的dataSource实例
	 */
	public static Object getDataSource(String dataSourceName) {
		return ContextLoader.getCurrentWebApplicationContext().getBean(dataSourceName);
	}
}
