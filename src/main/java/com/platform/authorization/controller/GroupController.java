package com.platform.authorization.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.dto.GroupDto;
import com.platform.authorization.model.GroupModel;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.GroupService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.web.controller.BaseController;

/**
 * 角色管理
 * 
 * @author yang.li
 * @since 2014-05-22 10:50:08
 *
 */
@Controller
@RequestMapping("/authorization/controller/GroupController")
public class GroupController extends BaseController {
	
	@Autowired
	private GroupService service;
	
	/**
	 * 获取用户未分配的角色列表
	 * 
	 * @param id
	 * @param ph
	 * @return
	 */
	@RequestMapping("/noExistGroup.do")
	@ResponseBody
	public DataGrid noExistGroup(GroupModel model, PageHelper ph) {
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.noExistGroup(dto, ph);
	}
	
	/**
	 * 授权用户
	 * 
	 * @param ids
	 * @param sys_user_id
	 * @return
	 */
	@RequestMapping("/assignUser.do")
	@ResponseBody
	public Json assignUser(String ids, String sys_group_id) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					GroupDto dto = new GroupDto();
					dto.setSys_group_id(sys_group_id);
					dto.setSys_user_id(id);
					service.assignUser(dto);
				}
			}
		}
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 移除授权用户
	 * 
	 * @param ids
	 * @param sys_user_id
	 * @return
	 */
	@RequestMapping("/cancelUser.do")
	@ResponseBody
	public Json cancelUser(GroupModel model) {
		
		Json j = new Json();
		GroupDto dto = new GroupDto();
		
		BeanUtils.copyProperties(dto, model);
		
		service.cancelUser(dto);
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 获取角色数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(GroupModel group, PageHelper ph) {
		
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(dto, group);
		
		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 添加群组
	 * 
	 * @return
	 */
	@RequestMapping("/addGroup.do")
	@ResponseBody
	public Json add(GroupModel group, HttpSession session) {
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(dto, group);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		try {
			
				// 新增群组
				service.insertGroup(dto, session);
			
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 修改群组
	 * 
	 * @return
	 */
	@RequestMapping("/editGroup.do")
	@ResponseBody
	public Json editGroup(GroupModel group, HttpSession session) {
		GroupDto dto = new GroupDto();
		BeanUtils.copyProperties(dto, group);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		try {
			
				// 更新群组
				service.updateGroup(dto, session);
			
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 跳转到角色修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String sys_group_id) {
		
		Map map = service.queryGroupById(sys_group_id);
		
		request.setAttribute("group", map);
		
		return "frame/authorization/group/groupEdit";
	}
	
	/**
	 * 删除群组
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteGroup.do")
	@ResponseBody
	public Json delete(String id, HttpSession session) {
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		Json j = new Json();
//		if (id != null && !id.equalsIgnoreCase(userModel.getUserid())) {// 不能删除自己
			service.deleteGroup(id);
//		}
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	

}
