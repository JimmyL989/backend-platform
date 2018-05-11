package com.platform.frame.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

import com.platform.frame.config.CONFIG;

/**
 * 
 * @author yang.li
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// 初始化全局配置
		CONFIG.getInstance();
		
		CONFIG.servletContext = servletContextEvent.getServletContext();
		
		/**功能1：自动加载配置信息*/
		String logconfig = servletContextEvent.getServletContext().getInitParameter("log4j-config");
		ResourcePatternResolver resourcePatternResolver = new ServletContextResourcePatternResolver(servletContextEvent.getServletContext());
		if (logconfig == null || logconfig.trim().equals(""))
		{
			logconfig = "/WEB-INF/classes/log4j-config.xml";
		}
		
		
		
		InitTool tool = new InitTool(CONFIG.servletContext);
		
		// 加载码表信息
		tool.loadCodeTable();
		
		System.out.println("InitListener - 开始加载权限配置信息......");
		tool.loadAllPrivilegeUrlsMap();
		System.out.println("InitListener - 权限配置数据缓存成功！");
		
		/**log4j初始化*/
		initLogger(resourcePatternResolver,logconfig);
	}
	
	protected void initLogger(ResourcePatternResolver resourcePatternResolver,String logConf)
	{
		try
		{
			DOMConfigurator.configure(resourcePatternResolver.getResource(logConf).getURL());
			System.out.println("InitListener - Log4j日志初始化成功！");
		}
		catch (Exception err)
		{
			System.out.println("InitListener - Log4j日志初始化异常： ");
			err.printStackTrace();
		}
	}

}
