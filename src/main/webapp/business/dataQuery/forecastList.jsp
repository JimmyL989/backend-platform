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
			url : '${pageContext.request.contextPath}/business/controller/DataQueryController/queryForecast.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'urban_forecast_transfer_id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'play_time',
			sortOrder : 'desc',
			checkOnSelect : true,
			selectOnCheck : false,
			rownumbers : true,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'urban_forecast_transfer_id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name1',
				title : '省',
				width : 60
			},{
				field : 'id1',
				title : '省id',
				width : 60,
				hidden:true
			},{
				field : 'name2',
				title : '市',
				width : 60
			},{
				field : 'id2',
				title : '市id',
				width : 60,
				hidden:true
			},{
				field : 'name3',
				title : '县',
				width : 60
			},{
				field : 'id3',
				title : '县id',
				width : 60,
				hidden:true
			},{
				field : 'stationname',
				title : '站名',
				width : 60
			},{
				field : 'stationnum',
				title : '站id',
				width : 60,
				hidden:true
			}, {
				field : 'play_time',
				title : '报文时间',
				width : 130,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			},{
				field : 'time',
				title : '未来X小时',
				width : 60
			},{
				field : 'content',
				title : '预报内容',
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
	function Save_Excel1(){
		Save_Excel($('#dataGrid'));
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
							<input id="regionId2" name="regionId2" class="easyui-combobox" data-options="width:120, valueField: 'id',textField: 'text', 
								url: '${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=9999', 
								onSelect: function(rec){ var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 		$('#regionId').combobox('clear');
           				 		$('#regionId1').combobox('clear'); 
           				 		$('#regionId1').combobox('reload', url);  }" />   
						</td>
						
						<th>市</th>
						<td>
							<input id="regionId1" name="regionId1" class="easyui-combobox" data-options="valueField:'id',textField:'text', 
								onSelect: function(rec){ 
								var url ='${pageContext.request.contextPath}/business/controller/DCPController/getDivision.do?id=' + rec.id;
           				 		$('#regionId').combobox('clear');
           				 		$('#regionId').combobox('reload', url);  }" />   
						</td>
           				 
           				<th>县</th>
						<td>
           					<input id="regionId" name="regionId" class="easyui-combobox" data-options="valueField:'id',textField:'text'"/>
						</td>
						
						<th>站名</th>
						<td><input name="stationname" placeholder="" class="span2" /></td>
						
						<th>报文日期</th>
						<td><input class="dateSpan" name="play_time" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
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
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls: 'attach',plain:true" onclick="Save_Excel($('#dataGrid'))" title="导出excel文件" style="float:right;"></a>
	</div>

</body>
</html>