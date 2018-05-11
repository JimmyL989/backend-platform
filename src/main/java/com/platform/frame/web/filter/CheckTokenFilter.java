package com.platform.frame.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.platform.authorization.model.LogonModel;
import com.platform.frame.config.CONFIG;
import com.platform.frame.util.HttpUtil;
import com.platform.frame.util.StringUtil;


/**
 * 
 * @author yang.li
 * @since 2014-05-14 16:58:55
 *
 */
public class CheckTokenFilter extends HttpServlet implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		String requestUri = hreq.getRequestURI();
		String contextPath = hreq.getContextPath();
		String url = requestUri.substring(contextPath.length());
		
		if (url.indexOf(CONFIG.APPCLIENT) > -1 || url.indexOf("/resources/") > -1 || "/authorization/controller/logon.do".contains(url)
				|| "/authorization/controller/logoff.do".contains(url)) {// 如果要访问的资源是不需要验证的
			filterChain.doFilter(request, response);
			return;
		}
		
		
		LogonModel sessionInfo = (LogonModel) ((HttpServletRequest) request).getSession().getAttribute(CONFIG.LOGON_USER);
		if (sessionInfo == null || sessionInfo.getUserid().equalsIgnoreCase("")) {// 如果没有登录或登录超时
			request.setAttribute("msg", "您还没有登录或登录已超时，请重新登录，然后再刷新本功能！");
			request.getRequestDispatcher("/frame/error/noSession.jsp").forward(request, response);
			return;
		}
		
		filterChain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
//	private MemberService memberService;
//	
//	@Override
//	public void init(FilterConfig config) throws ServletException {
//		// TODO Auto-generated method stub
//		ServletContext context = config.getServletContext();  
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
//		memberService = (MemberService) ctx.getBean("memberService");  
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		String currentURL = "";
//		String urlNoContextPath = "";
//		
//		HttpServletRequest hreq = (HttpServletRequest) request;
//		String ctxPath = hreq.getContextPath();
//		currentURL = hreq.getRequestURI();
//		urlNoContextPath = getTargetURL(ctxPath,currentURL);
//		// 如果不是客户端请求直接略过此filter
//		if (HttpUtil.isAppClient(urlNoContextPath)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		// 客户端请求不需要登录的连接集合
//		if (HttpUtil.isNoTokenPage(urlNoContextPath)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		if (StringUtil.isNullString(request.getParameter("id"))) {
//			HttpUtil.forwardToCode(response, CONFIG.APPCLIENT_NOT_LOGON_CODE);
//			return;
//		}
//		
//		int id = Integer.parseInt(request.getParameter("id"));
//		String token = request.getParameter("token");
//		if (StringUtil.isNullString(token)) {
//			
//			HttpUtil.forwardToCode(response, CONFIG.APPCLIENT_NOT_LOGON_CODE);
//			return;
//		}
//		Member member = new Member();
//		member.setId(id);
//		member.setTokenId(token);
//		if (memberService.queryCount(member) == 0) {
//			HttpUtil.forwardToCode(response, CONFIG.APPCLIENT_NOT_LOGON_CODE);
//			return;
//		}
//		
//		filterChain.doFilter(request, response);
//		return;
//		
//	}
//	
//	/**
//	 * 截取掉url中的ContextPath
//	 * 
//	 * @param ctxPath
//	 *            ContextPath
//	 * @param currentURL
//	 * @return 截取掉了ContextPath的URL
//	 */
//	private String getTargetURL(String ctxPath, String currentURL) {
//		int firstSlash = -1;
//		if (ctxPath != null && ctxPath.length() > 0) {
//			firstSlash = currentURL.indexOf("/", 1); // jump past the starting
//														// slash
//		}
//		String targetURL = currentURL;
//		if (firstSlash != -1) {
//			targetURL = currentURL.substring(firstSlash, currentURL.length());
//		}
//		return targetURL;
//	}

}
