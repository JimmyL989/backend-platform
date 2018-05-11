package com.platform.authorization.model;

/**
 * 
 * @author yang.li
 *
 */
public class PrivModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String sys_privilege_id = "";
	
	private String name = "";
	
	private String parentname = "";
	
	private String url = "";
	
	private String menutype = "";
	
	private String sort = "";
	
	private String parentid = "";
	
	private String _parentId = "";
	
	private String memo = "";
	
	private String cuserid = "";
	
	private String iconCls = "";
	
	// 类型对应的名称 0-菜单 1-按钮
	private String menuname = "";
	
	// 选中的资源ID集合
	private String resourceIds = "";
	
	// 半选中资源ID集合
	private String resourceIds_ = "";
	
	private String sys_group_id = "";

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getResourceIds_() {
		return resourceIds_;
	}

	public void setResourceIds_(String resourceIds_) {
		this.resourceIds_ = resourceIds_;
	}

	public String getSys_group_id() {
		return sys_group_id;
	}

	public void setSys_group_id(String sys_group_id) {
		this.sys_group_id = sys_group_id;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getCuserid() {
		return cuserid;
	}

	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getSys_privilege_id() {
		return sys_privilege_id;
	}

	public void setSys_privilege_id(String sys_privilege_id) {
		this.sys_privilege_id = sys_privilege_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
