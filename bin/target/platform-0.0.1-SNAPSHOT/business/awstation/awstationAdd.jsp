<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/AwstationController/addAwstation.do',
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
	var productId = '';
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed">
				<tr>
					<th>站号</th>
					<td><input name="stationnum" type="text" placeholder="" class="easyui-validatebox span2" data-options="required:true" /></td>
					<th>站名</th>
					<td><input name="stationname" type="text" placeholder="" class="easyui-validatebox span2" data-options="required:true" /></td>
				</tr>
				<tr>
					<th width="50px">归属行政区划</th>
					<td width="100px" colspan="3">
					<select id="regionId" name="regionId" class="easyui-combotree" style="width:200px;" required="required" data-options="
					url: '${pageContext.request.contextPath}/business/controller/ProductController/queryRegionTree.do?productId=' + productId,
					onSelect: function(rec){ 
        				var tree = $(this).tree;  
       					var isLeaf = tree('isLeaf', rec.target);  
        				if (!isLeaf) {  
           					$('#regionId').combotree('clear');  
        				}  
					}"></select>  
				</tr>
			</table>
		</form>
	</div>
</div>