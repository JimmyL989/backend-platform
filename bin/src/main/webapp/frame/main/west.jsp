<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.platform.frame.config.CONFIG"%>
<%@ page import="java.util.*"%>
<%
	String east_ctxPath_ = request.getContextPath();
	Map secondMap = (Map)request.getSession().getAttribute(CONFIG.SECOND_MENU);
%>
<script type="text/javascript">

	function addTab(url,text) {
		var iframe = '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:98%;"></iframe>';
		
		var t = $('#index_tabs');
		var opts = {
			title : text,
			closable : true,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
			parent.$.messager.progress('close');
		} else {
			t.tabs('add', opts);
		}
	}
</script>
<div class="easyui-accordion" data-options="fit:true,border:false">

	<%  
	int index = Integer.parseInt(session.getAttribute("_ROOT_FLAG_")+"");
	List secondList = (List)(secondMap.get(index));
	for (int i = 0; i < secondList.size(); i++) { 
	int parentid = Integer.parseInt(String.valueOf(((Map)secondList.get(i)).get("sys_privilege_id")));
	%>
	
	<div title="<%=String.valueOf(((Map)secondList.get(i)).get("name")) %>" style="padding: 5px;" data-options="border:false">
		<ul>
	<%
	List jList = (List)secondMap.get(parentid);
	if(jList!=null) {
		for(int q = 0; q < jList.size(); q++) {
		Map qMap = (Map)(jList).get(q);	      
	%>    
	                  
		<li style="margin-left:5px">
			<a onclick="javascript:addTab('<%=east_ctxPath_+qMap.get("url")%>','<%=qMap.get("name")%>');" href="#">
				<%=qMap.get("name")%>
			</a>
		</li>
	                  
	<%
		}
	}
	%>
		</ul>
		<div class="well well-small">
			<ul id="layout_west_tree"></ul>
		</div>
	</div>
	<%
		} 
	%>
	                  
</div>