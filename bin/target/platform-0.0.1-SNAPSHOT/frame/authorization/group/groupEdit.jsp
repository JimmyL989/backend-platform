<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/authorization/controller/GroupController/editGroup.do',
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
		<input name="sys_group_id" type="hidden" value="${ group.sys_group_id }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>角色名称</th>
					<td colspan="3"><input name="groupname" type="text" placeholder="请输入角色名" class="easyui-validatebox span2" data-options="required:true" value="${group.groupname }" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="memo" rows="" cols="" class="span5">${group.memo}</textarea></td>
				</tr> 
			</table>
		</form>
	</div>
</div>