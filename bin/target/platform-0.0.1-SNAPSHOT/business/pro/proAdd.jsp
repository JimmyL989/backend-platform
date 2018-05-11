<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/ProController/addPro.do',
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
			<table class="table table-hover table-condensed">
				<tr>
					<th>产品名称</th>
					<td colspan="3"><input name="product_name" type="text" placeholder="" class="easyui-validatebox span2" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>农作物</th>
					<td colspan="3"><input id="crop" name="crop" class="easyui-combobox" data-options="required:true,width:120, valueField: 'crops_id',textField: 'crops_name', 
								url: '${pageContext.request.contextPath}/business/controller/CropController/queryCropCombo.do'" />   </td>
				</tr>
				<tr>
					<th>有效范围</th>
					<td colspan="3">
					<input class="easyui-validatebox" style="width: 300 px" id="effect_time" data-options="required:true" name="effect_time" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'expire_time\')}'})" readonly="readonly" />
					至
					<input style="width: 300 px" class="easyui-validatebox" id="expire_time" data-options="required:true" name="expire_time" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'effect_time\')}'})" readonly="readonly" />   </td>
				</tr>
			</table>
		</form>
	</div>
</div>