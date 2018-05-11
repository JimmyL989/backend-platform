package com.platform.business.model;

/**
 * 
 * @author yang.li
 *
 */
public class DivisionModel implements java.io.Serializable {
	
	private static final long serialVersionUID = -9173933907265132992L;
	
	private String admin_region_id = "";
	
	private String admin_region_name = "";
	
	private String parentname = "";
	
	private String parent_admin_region_id = "";
	
	private String _parent_admin_region_id = "";
	
	private String parentid = "";
	
	private String _parentId = "";

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getAdmin_region_id() {
		return admin_region_id;
	}

	public void setAdmin_region_id(String admin_region_id) {
		this.admin_region_id = admin_region_id;
	}

	public String getAdmin_region_name() {
		return admin_region_name;
	}

	public void setAdmin_region_name(String admin_region_name) {
		this.admin_region_name = admin_region_name;
	}

	public String getParentname() {
		return parentname;
	}

	public void setParentname(String parentname) {
		this.parentname = parentname;
	}

	public String getParent_admin_region_id() {
		return parent_admin_region_id;
	}

	public void setParent_admin_region_id(String parent_admin_region_id) {
		this.parent_admin_region_id = parent_admin_region_id;
	}

	public String get_parent_admin_region_id() {
		return _parent_admin_region_id;
	}

	public void set_parent_admin_region_id(String _parent_admin_region_id) {
		this._parent_admin_region_id = _parent_admin_region_id;
	}
	
}
