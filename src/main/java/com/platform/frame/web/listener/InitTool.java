package com.platform.frame.web.listener;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.authorization.model.LogonModel;
import com.platform.authorization.service.CodeService;
import com.platform.authorization.service.LogonService;
import com.platform.frame.config.CONFIG;

/**
 * 
 * @author yang.li
 *
 */
public class InitTool {
	
	private CodeService service;
	
	private LogonService logonService;
	
	public InitTool(ServletContext context) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
		service = (CodeService) ctx.getBean("codeService");  
		logonService = (LogonService) ctx.getBean("logonService");  
	}
	
	/**
	 * 加载系统所有权限信息到ServletContext中
	 * 
	 * @param request
	 * @return
	 */
	public void loadCodeTable() {
		try {
			System.out.println("InitListener - 开始读取码表数据信息......");
			/** 开始加载码表缓存数据 */
			List list = service.queryCodeList();
			Map map = new TreeMap();
			Map mapList = null;
			for (int i = 0; i < list.size(); i++) {
				Map dbMap = (Map) list.get(i);
				String type = dbMap.get("type").toString();
				if (map.containsKey(type)) {
					/** 如果类型已经加入到Map中，就继续向list中增加内容 */
					mapList = (Map) map.get(type);
					mapList.put(dbMap.get("code"), dbMap.get("name"));
					map.put(type, mapList);
				} else {
					/** 增加新的内容信息 */
					mapList = new TreeMap();
					mapList.put(dbMap.get("code"), dbMap.get("name"));
					map.put(type, mapList);
				}// else
			}// for(int i=0;i<list.size();i++)

			CONFIG.servletContext.setAttribute("CODE_TABLE_CONTENT", map);
			System.out.println("InitListener - 码表数据缓存成功,共计" + list.size() + "条记录！");

		} catch (Exception e) {
			System.out.println("InitListener - 加载码表缓存失败！");
			e.printStackTrace();
		}

	}
	
	/**
	 * 加载系统所有权限信息到ServletContext中
	 * @param context
	 */
	public void loadAllPrivilegeUrlsMap() {
		List<String> allUrlList = logonService.queryforAuthUrls("1");
		//系统中配置的所有菜单url和按钮url放入ServletContext(PrivilegeUrl对象Map)
		CONFIG.servletContext.setAttribute(CONFIG.ALL_URL, allUrlList);
	}
	
	/**
	 * 重新加载权限
	 * @param session
	 */
	public void loadUserPrivilegeUrlsMap(HttpSession session) {
		LogonModel user = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		// 登录用户拥有权限访问的所有菜单和按钮url的字符串放入session
		List<String> authUrlsOfCurUser = logonService.queryforAuthUrls(user.getUserid());
		session.setAttribute(CONFIG.ALL_AUTH_URL_ID, authUrlsOfCurUser);
	}
}
