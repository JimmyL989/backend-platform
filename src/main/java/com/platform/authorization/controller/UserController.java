package com.platform.authorization.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.authorization.dto.UserDto;
import com.platform.authorization.model.LogonModel;
import com.platform.authorization.model.UserModel;
import com.platform.authorization.pageModel.DataGrid;
import com.platform.authorization.pageModel.Json;
import com.platform.authorization.pageModel.PageHelper;
import com.platform.authorization.service.UserService;
import com.platform.frame.config.CONFIG;
import com.platform.frame.util.BeanUtils;
import com.platform.frame.util.StringUtil;
import com.platform.frame.web.controller.BaseController;

/**
 * 用户管理
 * 
 * @author yang.li
 *
 */
@Controller
@RequestMapping("/authorization/controller/UserController")
public class UserController extends BaseController {
	
	@Autowired
	private UserService service;
	
	/**
	 * 获取角色未分配的用户列表
	 * 
	 * @param id
	 * @param ph
	 * @return
	 */
	@RequestMapping("/noExistGroup.do")
	@ResponseBody
	public DataGrid noExistGroup(UserModel model, PageHelper ph) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dto, model);
		
		return service.noExistUser(dto, ph);
	}
	
	/**
	 * 获取用户数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid.do")
	@ResponseBody
	public DataGrid dataGrid(UserModel user, PageHelper ph) {
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dto, user);
		
		return service.dataGrid(dto, ph);
	}
	
	/**
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping("/addUser.do")
	@ResponseBody
	public Json add(UserModel user, HttpSession session) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dto, user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		try {
			
				// 新增用户
				boolean b = service.insertUser(dto, session);
				if(!b){
					j.setSuccess(false);
					j.setMsg("登录名重复");
				}
			
			j.setObj(user);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 修改用户
	 * 
	 * @return
	 */
	@RequestMapping("/editUser.do")
	@ResponseBody
	public Json editUser(UserModel user, HttpSession session) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dto, user);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("添加成功！");
		try {
			
				// 更新用户
				service.updateUser(dto, session);
			
			j.setObj(user);
		} catch (Exception e) {
			// e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 跳转到用户修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage.do")
	public String editPage(HttpServletRequest request, String sys_user_id) {
		
		Map map = service.queryUserById(sys_user_id);
		
		request.setAttribute("user", map);
		
		return "frame/authorization/user/userEdit";
	}
	
	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser.do")
	@ResponseBody
	public Json delete(String id, HttpSession session) {
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		Json j = new Json();
		if (id != null && !id.equalsIgnoreCase(userModel.getUserid())) {// 不能删除自己
			service.deleteUser(id);
		}
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 批量删除用户
	 * 
	 * @param ids
	 *            ('0','1','2')
	 * @return
	 */
	@RequestMapping("/batchDeleteUser.do")
	@ResponseBody
	public Json batchDelete(String ids, HttpSession session) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					this.delete(id, session);
				}
			}
		}
		j.setMsg("批量删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	
	/**
	 * 授权角色
	 * 
	 * @param ids
	 * @param sys_user_id
	 * @return
	 */
	@RequestMapping("/assignGroup.do")
	@ResponseBody
	public Json assignGroup(String ids, String sys_user_id) {
		Json j = new Json();
		if (ids != null && ids.length() > 0) {
			for (String id : ids.split(",")) {
				if (id != null) {
					UserDto dto = new UserDto();
					dto.setSys_group_id(id);
					dto.setSys_user_id(sys_user_id);
					service.assignGroup(dto);
				}
			}
		}
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 移除授权角色
	 * 
	 * @param ids
	 * @param sys_user_id
	 * @return
	 */
	@RequestMapping("/cancelGroup.do")
	@ResponseBody
	public Json cancelGroup(UserModel model) {
		
		Json j = new Json();
		UserDto dto = new UserDto();
		
		BeanUtils.copyProperties(dto, model);
		
		service.cancelGroup(dto);
		j.setMsg("");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 重置密码
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping("/resetPasswd.do")
	@ResponseBody
	public Json resetPasswd(UserModel user, HttpSession session) {
		Json j = new Json();
		
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(dto, user);
		
		service.updatePasswd(dto, session);
		
		j.setMsg("密码重置成功！");
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/grantFun.do")
	public String grantFun() {
		
		
		return "frame/authorization/user/user_groupMapping";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changePassword.do")
	public Json changePassword(String oldPwd, String pwd, HttpSession session) {
		Json j = new Json();
		LogonModel userModel = (LogonModel) session.getAttribute(CONFIG.LOGON_USER);
		
		Map map = service.queryUserById(userModel.getUserid());
		if (!StringUtil.MD5(oldPwd).equals(String.valueOf(map.get("password")))) {
			j.setSuccess(false);
			j.setMsg("原密码错误！");
			return j;
		}
		
		service.updatePasswd(pwd, session);
		
		j.setMsg("密码修改成功！");
		j.setSuccess(true);
		return j;
	}
}