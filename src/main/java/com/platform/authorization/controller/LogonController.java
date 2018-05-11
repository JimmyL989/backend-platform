package com.platform.authorization.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.service.LogonService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.log.LogonLogger;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.HttpUtil;
import com.platform.frame.util.StringUtil;
import com.platform.frame.util.UUIDUtils;
import com.platform.frame.web.controller.BaseController;
import com.platform.frame.web.listener.CountListener;

/**
 * 登录管理
 * 
 * @author yang.li
 *
 */
@Controller
@RequestMapping("/authorization/controller")
public class LogonController extends BaseController{

	@Autowired
	private LogonService service;
	
	/**
	 * 登录
	 * 
	 * @param userModel
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/logon.do")
	public String logon(LogonModel userModel, HttpSession session, HttpServletRequest request) {
		
		String userCode = userModel.getUsercode();
		if(userCode == null || "".equals(userCode)) {
			request.setAttribute("message", "用户名不能为空");
			return "index";
		}
		String passwd = userModel.getPassword();
		if(passwd == null || "".equals(passwd)) {
			request.setAttribute("message", "密码不能为空");
			return "index";
		}
		String sessionId = session.getId();
		String logonId = UUIDUtils.create();
		
		String remoteIP = HttpUtil.getIpAddr(request);// ip地址
		
		Map map = service.selectByCode(userCode);
		LogonLogger logonLogger = new LogonLogger();
		
		// 1、验证用户是否存在
		if (map == null || map.isEmpty()) {
			logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "0", "用户不存在", sessionId);
			// 用户不存在
			request.setAttribute("message", "用户不存在");
			return "index";
		} else {
			String validFlag = null;
			String passwdDB = null;
			String userId = null;
			userModel = new LogonModel();
			BeanUtils.copyProperties(userModel, map);
			
			validFlag = userModel.getIsvalid();
			validFlag = validFlag != null ? validFlag.trim() : "0";
			passwdDB = userModel.getPassword();
			userId = userModel.getUserid();
			
			// 2、验证用户的有效性
			if (!validFlag.equals("1")) {
				if (validFlag.equals("0")) {
					// 登录用户已失效
					logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "0", "登录用户已失效", sessionId);
					request.setAttribute("message", "用户已失效");
					return "index";
				} else if (validFlag.equals("D")) {
					// 登录用户已被删除
					logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "0", "登录用户已被删除", sessionId);
					request.setAttribute("message", "用户已被删除");
					return "index";
				} else {
					// 登录用户非正常用户
					logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "0", "登录用户非正常用户", sessionId);
					request.setAttribute("message", "用户非正常用户");
					return "index";
				}
			}
			
			
			// 3、验证用户密码
			if (!StringUtil.MD5(passwd).equals(passwdDB)) {
				logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "0", "密码错误", sessionId);
				// 密码错误
				request.setAttribute("message", "密码错误");
				return "index";
			}
			
			// 登录用户对象放入session
			userModel.setLogonId(logonId);
			userModel.setRemoteIP(remoteIP);
			session.setAttribute(CONFIG.LOGON_USER, userModel);
			
			// 登录用户的第一级菜单放入session
			List<Map<String, Object>> rootMenuUrlList = service.queryforRootMenuUrlList(1, userId);
			
			if (rootMenuUrlList != null && !rootMenuUrlList.isEmpty()) {
				session.setAttribute(CONFIG.ROOT_MENU, rootMenuUrlList);
				session.setAttribute(CONFIG.ROOT_FLAG, rootMenuUrlList.get(rootMenuUrlList.size() - 1).get("sys_privilege_id"));
				session.setAttribute(CONFIG.ROOT_NAME, rootMenuUrlList.get(rootMenuUrlList.size() - 1).get("name"));
			} else {
				request.setAttribute("msg", "您没有访问任何资源的权限！<br/>请联系超管赋予您<br/>资源访问权限！");
				return "/frame/error/noSecurity";
			}
			
			// 登录用户的第二级菜单放入session
			List<Map<String, String>> secondMenuUrlList = null;
			Map secondMenuUrlMap = new HashMap();
			
			if (rootMenuUrlList != null) {
				for (int i = 0; i < rootMenuUrlList.size(); i++) {
					int rootId = Integer.parseInt(String.valueOf(rootMenuUrlList.get(i).get("sys_privilege_id")));
					secondMenuUrlList = service.queryforRootMenuUrlList(rootId, userId);
					secondMenuUrlMap.put(rootId, secondMenuUrlList);
					if (secondMenuUrlList != null) {
						for (int j = 0; j < secondMenuUrlList.size(); j++) {
							rootId = Integer.parseInt(String.valueOf(secondMenuUrlList.get(j).get("sys_privilege_id")));
							List thirdMenuUrlList = service.queryforRootMenuUrlList(rootId, userId);
							secondMenuUrlMap.put(rootId, thirdMenuUrlList);
						}

					}
				}
				session.setAttribute(CONFIG.SECOND_MENU, secondMenuUrlMap);
			}
			
			// 登录用户拥有权限访问的所有菜单和按钮url的字符串放入session
			List<String> authUrlsOfCurUser = service.queryforAuthUrls(userId);
			session.setAttribute(CONFIG.ALL_AUTH_URL_ID, authUrlsOfCurUser);

			
			logonLogger.log(logonId, userCode, remoteIP, "macs", new Date(System.currentTimeMillis()), "1", "", sessionId);
		}

		return "frame/main/main";
	}
	
	/**
	 * 切换权限树
	 * 
	 * @param session
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setRootId")
	public Json setRootId(HttpSession session, HttpServletRequest req) {
		Json j = new Json();
		String name = req.getParameter("name");
		String rootId = req.getParameter("rootid");
		session.setAttribute("_ROOT_FLAG_", rootId);
		j.setMsg(name);
		j.setSuccess(true);
		return j;
	}
	
	@ResponseBody
	@RequestMapping("/getOnLineCount.do")
	public Json getOnLineCount() {
		Json j = new Json();
		j.setMsg(String.valueOf(CountListener.getOnlineCount()));
		j.setSuccess(true);
		return j;
	}

	/**
	 * 注销
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/logoff.do")
	public String logoff(HttpSession session, HttpServletRequest request) {
		
		if (session.getAttribute(CONFIG.LOGON_USER) != null) {
			session.invalidate();
		}

		return "index";
	}
	
}
