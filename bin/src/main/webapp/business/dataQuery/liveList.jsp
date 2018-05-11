<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>预报查询</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/DataQueryController/queryLive.do',
			fit : true,
			fitColumns : true,
			border : false,
			checkOnSelect : false,
			selectOnCheck : false,
			singleSelect: true,
			rownumbers : true,
			nowrap : false,
			columns : [ [ {
				field : 'name',
				title : '要素',
				width : 60
			},{
				field : 'data',
				title : '数据',
				width : 260
			} ] ],
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll').datagrid('uncheckAll');
				$(this).datagrid('selectRow', rowIndex);
			}
		});
	});

	function searchFun() {
		var regionId2Boolean = validateCombobox($('#regionId2'));
		var regionId1Boolean = validateCombobox($('#regionId1'));
		var regionIdBoolean = validateCombobox($('#regionId'));
				
		if(regionId2Boolean && regionId1Boolean && regionIdBoolean){
			dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
		} else {
			parent.$.messager.alert('错误', '查询条件不合法！', 'error');
		}
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
 		<div data-options="region:'north',title:'过滤条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm" method="post">
				<table class="table table-hover mySearchTable">
					<tr>
						<th>省</th>
						<td>
							<input id="regionId2" name="regionId2" class="easyui-combobox" data-options="width:80, valueField: 'id',textField: 'text', 
								url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
								onSelect: function(rec){ var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 		$('#regionId').combobox('clear');
           				 		$('#regionId1').combobox('clear'); 
           				 		$('#regionId1').combobox('reload', url);  }" />   
						</td>
						
						<th>市</th>
						<td>
							<input id="regionId1" name="regionId1" class="easyui-combobox" data-options="width:80,valueField:'id',textField:'text', 
								onSelect: function(rec){ 
								var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 		$('#regionId').combobox('clear');
           				 		$('#regionId').combobox('reload', url);  }" />   
						</td>
           				 
           				<th>县</th>
						<td>
           					<input id="regionId" name="regionId" class="easyui-combobox" data-options="width:100,valueField:'id',textField:'text', 
								onSelect: function(rec){ 
								var url ='${pageContext.request.contextPath}/business/controller/DataQueryController/queryStationCombo.do?regionId=' + rec.id;
           				 		$('#station_id').combobox('clear');
           				 		$('#station_id').combobox('reload', url);  }"/>
						</td>
						
						<th>站名</th>
						<td><input id="station_id" name="station_id" class="easyui-validatebox easyui-combobox" data-options="width:100,required:true,valueField:'awstation_id',textField:'stationname',
							onLoadSuccess: function(param){
             					if (param.length > 0) {
                 					$('#station_id').combobox('select', param[0].awstation_id);
            					} 
							}, onSelect: function(rec) {
							    var url = '${pageContext.request.contextPath}/business/controller/DataQueryController/queryTypeCombo.do?id=' + rec.awstation_id;
								$('#data_id').combobox('clear');
           				 		$('#data_id').combobox('reload', url);
							}" /></td>
						
						<th>要素</th>
						<td><input id="data_id" name="data_id" class="easyui-validatebox easyui-combobox" data-options="required:true,valueField:'data_id',textField:'data_type',
							onSelect:function(rec){
								if('9' == rec.data_id){
									$('#pt').css('display', '');
								}else{
									$('#pt').css('display', 'none');
								}
							}" /></td>
						
						<th>时间</th>
						<td id='pt' style="display:none"><input class="easyui-validatebox dateSpan1" name="play_time1" data-options="required:true" placeholder="必须选择时间"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至</td>
						<td ><input class="dateSpan1" name="play_time" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div> 
	<div id="toolbar" style="display: none;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">重置</a>
	</div>

</body>
</html>