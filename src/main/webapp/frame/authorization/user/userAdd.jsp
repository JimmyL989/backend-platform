<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap isvalidMap = (TreeMap)map.get("isvalidMap");
request.setAttribute("isvalidMap", isvalidMap);
%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/authorization/controller/UserController/addUser.do',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
		<input name="sys_user_id" type="hidden" value="${ user.sys_user_id }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>登陆名</th>
					<td><input name="usercode" type="text" placeholder="" class="span2" data-options="required:true" 
					<c:if test="${ user.usercode != null && user.usercode != '' }"> value="${user.usercode }" readonly="readonly"</c:if>  /></td>
					<th>用户名</th>
					<td><input name="username" type="text" placeholder="" class="easyui-validatebox span2" data-options="required:true" value="${user.username }"></td>
				</tr>
				<tr>
					<th>帐号状态</th>
					<td><select name="isvalid" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${isvalidMap}" var="isvalidMap">
								<option value="${isvalidMap.key}" 
								<c:if test="${(null == user.isvalid || '' == user.isvalid) && isvalidMap.key == 1}">
								selected="selected"
								</c:if>
								<c:if test="${isvalidMap.key == user.isvalid}">
								selected="selected"
								</c:if>
								>${isvalidMap.value}</option>
							</c:forEach>
					</select></td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</div>