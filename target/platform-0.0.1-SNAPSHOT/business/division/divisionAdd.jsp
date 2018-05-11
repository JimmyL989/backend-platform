<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

	 $(function() {
		 
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/DivisionController/addDivision.do',
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
					parent.$.modalDialog.handler.dialog('close');
				} else {
				
					parent.$.messager.alert('提示','站号重复，保存失败');
				}
			}
		});
	}); 
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		 <form id="form" method="post">
		 <input name="parentid" type="hidden" value="${resource.parentid }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>上级区划</th>
					<td colspan="3"><input name="parentname" type="text" class="span2" value="${resource.parentname}" readonly="readonly"></td>
				</tr>
				<tr>
					<th>区划ID</th>
					<td><input name="admin_region_id" type="text" class="easyui-validatebox span2" value="${resource.admin_region_id}" data-options="required:true"></td>
					<th>区划名称</th>
					<td><input name="admin_region_name" type="text" placeholder="" class="easyui-validatebox span2" data-options="required:true" value="${resource.admin_region_name}"></td>
				</tr>
			</table>
		</form> 
	</div>
</div>