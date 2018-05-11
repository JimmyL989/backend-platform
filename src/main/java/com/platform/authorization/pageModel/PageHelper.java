package com.platform.authorization.pageModel;

/**
 * EasyUI 分页帮助类
 * @author yang.li
 * 
 */
public class PageHelper implements java.io.Serializable {

	private int page;// 当前页
	private int rows;// 每页显示记录数
	private String sort;// 排序字段
	private String order;// asc/desc
	
	private String sqlOrder = "";
	private String sqlLimit = "";
	
	private String before = "";
	private String after = "";
	
	public String getBefore() {
		int end = (page - 1) * rows + rows;
		String s = "select * from (select row_number() over ( " + getSqlOrder() + " ) temprownumber,* from ( select top " + end + " * from (";
		return s;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		
		int start = (page - 1) * rows;
		String s = ") t " + getSqlOrder() + "  ) tt )ttt where temprownumber> " + start;
		return s;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public String getSqlOrder() {
		String orderString = "";
		if (this.getSort() != null && this.getOrder() != null) {
			orderString = " order by " + this.getSort() + " " + this.getOrder();
		}
		return orderString;
	}

	public String getSqlLimit() {
		String limitString = "";
		int start = (page - 1) * rows;
		int end = start + rows;
		limitString = " limit " + start + ", " + end;
		return limitString;
	}

	public void setSqlLimit(String sqlLimit) {
		this.sqlLimit = sqlLimit;
	}

	public void setSqlOrder(String sqlOrder) {
		
		this.sqlOrder = sqlOrder;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
