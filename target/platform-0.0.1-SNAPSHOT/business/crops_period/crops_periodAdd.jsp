<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

	 $(function() {
		 
		parent.$.messager.progress('close');

		$('#form').form({
			url : '${pageContext.request.contextPath}/business/controller/Crops_PeriodController/addCrop.do',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					//parent.$.modalDialog.handler.dialog('close');
					checkDCP($('#crops_id').combotree('getValue'), $('#period_id').combobox('getValue'));
				}
			}
		});
		
		
		
	}); 
	
	function checkDCPByPeriod(period_id) {
		var isValid = $('#form').form('validate');
		if (!isValid) {
			return;
		}
		
		var crops_id = $('#crops_id').combotree('getValue');
		
		checkDCP(crops_id, period_id);
	}
	
	function checkDCPByRegion(regionId) {
		$('#period_id').combobox('clear');
	/* alert(1);
		var isValid = $('#form').form('validate');
		alert(isValid);
		if (!isValid) {
			return;
		}
		
		var period_id = $('#period_id').combobox('getValue');
		
		checkDCP(regionId, period_id); */
	}
	
	
	function checkDCP(crops_id, period_id) {
	
			$.post('${pageContext.request.contextPath}/business/controller/Crops_PeriodController/queryPeriod.do', {
						crops_id : crops_id, period_id : period_id
					}, function(result) {
						if (result.success) {
							//parent.$.messager.alert('提示', result.msg, 'info');
							$("#form input[name=crops_period_id]").val(result.obj.crops_period_id);
							
							$('#sdate').val(result.obj.sdate);
							$('#edate').val(result.obj.edate);
								
						} else {
							
							$('#sdate').val('');
							$('#edate').val('');
							$("#form input[name=crops_period_id]").val('');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		 <form id="form" method="post">
		 <input name="crops_period_id" type="hidden"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th width="">作物</th>
					<td width="">
					<input id="crops_id" name="crops_id" class="easyui-combobox" data-options="required:true,width:320, valueField: 'crops_id',textField: 'crops_name', 
								url: '${pageContext.request.contextPath}/business/controller/CropController/queryCropCombo.do'" />	
					</td>
				</tr>
				<tr>
					<th>生育时期</th>
					<td colspan="3"><input id="period_id" name="period_id" class="easyui-combobox" data-options="required:true,width:400, valueField: 'period_id',textField: 'period_name', 
								url: '${pageContext.request.contextPath}/business/controller/PeriodController/queryPeriodCombo.do', onSelect : function(rec){
									checkDCPByPeriod(rec.period_id);
								}" />   </td>				
				</tr>
				<tr>
					<th>范 围</th>
					<td colspan="3">
					<input class="easyui-validatebox" style="width: 300 px" id="sdate" name="sdate" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'edate\')}'})" readonly="readonly" />
					至
					<input style="width: 300 px" class="easyui-validatebox" id="edate" name="edate" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sdate\')}'})" readonly="readonly" />   </td>
				</tr>
			</table>
		</form> 
	</div>
</div>