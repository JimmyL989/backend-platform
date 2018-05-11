package com.platform.authorization.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.dto.PrivDto;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.model.PrivModel;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.service.PrivService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.SystemUtil;
import com.platform.frame.web.controller.BaseController;

/**
 * 资源管理
 * 
 * @author yang.li
 *
 */
@Controller
@RequestMapping("/authorization/controller/PrivController")
public class PrivController extends BaseController {
	
	@Autowired
	private PrivService service;
	
	/**
	 * 角色分配资源 查询角色下已分配资源
	 * 
	 * @param id
	 *            角色id
	 * @param request
	 * @return
	 */
	@RequestMapping("/group_privMapping.do")
	public String group_privMapping(String id, HttpServletRequest request) {
		
		String ids = service.queryPrivs(id);

		request.setAttribute("ids", ids);
		request.setAttribute("id", id);
		
		return "frame/authorization/group/group_privMapping";
	}
	
	@RequestMapping("/allTree.do")
	@ResponseBody
	public List<Map<String, String>> allTree(String sys_group_id, HttpSession session, HttpServletRequest request) {
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		return service.queryTree(userModel.getUserid());
	}
	
	
	/**
	 * 获得资源列表
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/queryTreeGrid.do")
	@ResponseBody
	public Map<String, List<PrivModel>> queryTreeGrid(HttpSession session) {
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);

		return service.queryTreeGrid(userModel.getUserid());
	}
	
	/**
	 * 跳转到资源添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/addPage.do")
	public String addPage(PrivModel model, HttpServletRequest request) {
		
		// 暂时模拟码表
		List resourceTypeList = new ArrayList();
		Map map = new HashMap();
		map.put("id", "0");
		map.put("name", "菜单");
		resourceTypeList.add(map);
		map = new HashMap();
		map.put("id", "1");
		map.put("name", "按钮");
		resourceTypeList.add(map);
		
		request.setAttribute("resourceTypeList", resourceTypeList);
		
		try {
			model.setParentname(new String(model.getParentname().getBytes("iso-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("resource", model);
		
		return "frame/authorization/privilege/privilegeAdd";
	}
	
	/**
	 * 跳转到资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String sys_privilege_id) {

		// 暂时模拟码表
		List resourceTypeList = new ArrayList();
		Map map = new HashMap();
		map.put("id", "0");
		map.put("name", "菜单");
		resourceTypeList.add(map);
		map = new HashMap();
		map.put("id", "1");
		map.put("name", "按钮");
		resourceTypeList.add(map);

		request.setAttribute("resourceTypeList", resourceTypeList);
		
		Map _map = service.queryPrivById(sys_privilege_id);
		request.setAttribute("resource", _map);
		return "frame/authorization/privilege/privilegeEdit";
	}
	
	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/addPriv.do")
	@ResponseBody
	public Json addPriv(PrivModel model, HttpSession session) {
		
		PrivDto dto = new PrivDto();
		
		BeanUtils.copyProperties(dto, model);
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		// 新增资源
		dto.setCuserid(userModel.getUserid());
		service.insertPriv(dto);
		
		Json j = new Json();
		
		SystemUtil.reloadPrivilege(session);
		// 针对管理员的权限重载
		SystemUtil.loadUserPrivilegeUrlsMap(session);
		
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}
	
	/**
	 * 修改资源
	 * 
	 * @return
	 */
	@RequestMapping("/editPriv.do")
	@ResponseBody
	public Json editPriv(PrivModel model, HttpSession session) {
		
		PrivDto dto = new PrivDto();
		
		BeanUtils.copyProperties(dto, model);
		
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		// 更新资源
		dto.setEuserid(userModel.getUserid());
		service.updatePriv(dto);

		Json j = new Json();
		
		SystemUtil.reloadPrivilege(session);
		// 针对管理员的权限重载
		SystemUtil.loadUserPrivilegeUrlsMap(session);
		
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}
	
	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePriv.do")
	@ResponseBody
	public Json delete(String id, HttpSession session) {
		Json j = new Json();
		service.deletePriv(id);
		SystemUtil.reloadPrivilege(session);
		// 针对管理员的权限重载
		SystemUtil.loadUserPrivilegeUrlsMap(session);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 分配角色权限
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/grantPriv.do")
	@ResponseBody
	public Json grantPriv(PrivModel model, HttpSession session) {
		Json j = new Json();
		PrivDto dto = new PrivDto();
		BeanUtils.copyProperties(dto, model);

		service.grantPriv(dto);
		
		SystemUtil.loadUserPrivilegeUrlsMap(session);
		
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}

}
