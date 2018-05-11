<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>作物发育时期配置</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/Crops_PeriodController/dataGrid.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'crops_period_id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'crops_id',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : false,
			singleSelect: true,
			rownumbers : true,
			nowrap : false,
			frozenColumns : [ [  {
				field : 'crops_period_id',
				title : '编号',
				width : 150,
				hidden: true
			},{
				field : 'crops_id',
				title : '编号',
				width : 150,
				hidden: true
			},{
				field : 'period_id',
				title : '编号',
				width : 150,
				hidden: true
			} ] ],
			columns : [ [ {
				field : 'crops_name',
				title : '农作物名称',
				width : 100
			},{
				field : 'period_name',
				title : '发育期',
				width : 250
			},{
				field : 'sdate',
				title : '开始时间',
				width : 80,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd");
                    }  
			},{
				field : 'edate',
				title : '结束时间',
				width : 80,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd");
                    }  
			},{
				field : 'action',
				title : '操作',
				width : 60,
				formatter : function(value, row, index) {
					var str = '';
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.crops_period_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
					str += '&nbsp;';
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.crops_period_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
					return str;
				}
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
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].crops_period_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前农作物及其关联关系？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/business/controller/Crops_PeriodController/deleteCrop.do', {
						crops_id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
			}
		});
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].crops_period_id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/business/controller/Crops_PeriodController/editPage.do?crops_id=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/business/crops_period/crops_periodAdd.jsp',
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
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
					<th width="80px">作物</th>
					<td width="80px">
					<input id="crops_id" name="crops_id" class="easyui-combobox" data-options="width:320, valueField: 'crops_id',textField: 'crops_name', 
								url: '${pageContext.request.contextPath}/business/controller/CropController/queryCropCombo.do'" />	
					</td>
					<th>生育时期</th>
					<td colspan="3"><input id="period_id" name="period_id" class="easyui-combobox" data-options="width:400, valueField: 'period_id',textField: 'period_name', 
								url: '${pageContext.request.contextPath}/business/controller/PeriodController/queryPeriodCombo.do'" />   </td>	
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div> 
	<div id="toolbar" style="display: none;">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">重置</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls: 'attach',plain:true" onclick="Save_Excel($('#dataGrid'))" title="导出excel文件" style="float:right;"></a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>