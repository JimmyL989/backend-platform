package com.platform.frame.web.interceptors;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.platform.authorization.model.LogonModel;
import com.platform.frame.config.CONFIG;
import com.platform.frame.log.OperateLogger;
import com.platform.frame.util.SessionUtil;
import com.platform.frame.util.UUIDUtils;
import com.platform.frame.util.UserSessionUtil;

/**
 * 权限拦截器
 * 
 * @author yang.li
 * 
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;// 不需要拦截的资源
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		if(url.indexOf("/authorization/controller/getOnLineCount.do") != -1)
			return;
		
		String id = UUIDUtils.create();
		
		// 请求处理结束时间
		Date etime = new Date(System.currentTimeMillis());
		
		Date stime = (Date) request.getSession().getAttribute("stime");
		if(stime == null)
			return;

		// 记录操作日志
		OperateLogger ologger = new OperateLogger();
		
		LogonModel user = SessionUtil.getLogonUser(request);
		
		ologger.log(id, user.getUsercode(), user.getRemoteIP(), user.getMacAddress(), null, url, stime, etime, null, null, String.valueOf(etime.getTime() - stime.getTime()));
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		request.getSession().removeAttribute("stime");
		
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		if (url.indexOf(CONFIG.APPCLIENT) > -1 || url.indexOf("/resources/") > -1 || excludeUrls.contains(url)) {// 如果要访问的资源是不需要验证的
			return true;
		}

		LogonModel sessionInfo = (LogonModel) request.getSession().getAttribute(CONFIG.LOGON_USER);
		if (sessionInfo == null || sessionInfo.getUserid().equalsIgnoreCase("")) {// 如果没有登录或登录超时
			request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			request.getRequestDispatcher("/frame/error/noSession.jsp").forward(request, response);
			return false;
		}
		
		// 设置用户信息 供log使用
		UserSessionUtil.setUser(sessionInfo);
		
		// 请求处理开始时间
		Date stime = new Date(System.currentTimeMillis());
		request.getSession().setAttribute("stime", stime);
		
		// 访问url不存在权限控制范围内
		List<String> allUrl = (List<String>) request.getSession().getServletContext().getAttribute(CONFIG.ALL_URL);
		if (!allUrl.contains(url)) {
			return true;
		}
		
		List<String> urlList = (List<String>) request.getSession().getAttribute(CONFIG.ALL_AUTH_URL_ID);
		if (!urlList.contains(url)) {// 如果当前用户没有访问此资源的权限
			request.setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
			request.getRequestDispatcher("/frame/error/noSecurity.jsp").forward(request, response);
			return false;
		}
		

		return true;
	}
}
