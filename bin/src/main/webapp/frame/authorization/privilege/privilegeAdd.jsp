<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

	 $(function() {
		 
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/authorization/controller/PrivController/addPriv.do',
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
					parent.$.messager.alert('提示','保存成功');
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.layout_west_tree.tree('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
	}); 
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		 <form id="form" method="post">
		 <input name="parentid" type="hidden" value="${resource.parentid }"/>
		 <input name="sys_privilege_id" type="hidden" value="${resource.sys_privilege_id }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>上级资源</th>
					<td><input name="parentname" type="text" class="span2" value="${resource.parentname}" readonly="readonly"></td>
					<th>资源名称</th>
					<td><input name="name" type="text" placeholder="请输入资源名称" class="easyui-validatebox span2" data-options="required:true" value="${resource.name}"></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td colspan="3"><input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox span5" value="${resource.url}"></td>
				</tr>
				<tr>
					<th>资源类型</th>
					<td><select name="menutype" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<c:forEach items="${resourceTypeList}" var="resourceType">
								<option value="${resourceType.id}" <c:if test="${resourceType.id == resource.menutype}">selected="selected"</c:if>>${resourceType.name}</option>
							</c:forEach>
					</select></td>
					<th>排序</th>
					<td><input name="sort" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:true,min:1" value="${resource.sort}"></td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="memo" rows="" cols="" class="span5">${resource.memo}</textarea></td>
				</tr> 
			</table>
		</form> 
	</div>
</div>