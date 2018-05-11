<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<title>相关建议</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/AdviceController/queryPlant.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'advice_plant_id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'advice_plant_id',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : false,
			rownumbers : true,
			nowrap : true,
			frozenColumns : [ [ {
				field : 'advice_plant_id',
				title : '编号',
				width : 150,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'crops_id',
				title : '农作物名称',
				width : 150,
				hidden: true
			},{
				field : 'crops_name',
				title : '农作物名称',
				width : 110
			},{
				field : 'period_id',
				title : '农作物名称',
				width : 150,
				hidden: true
			},{
				field : 'period_name',
				title : '发育期',
				width : 150
			},{
				field : 'name',
				title : '病虫害',
				width : 150
			},{
				field : 'conditions',
				title : '病虫害发生条件',
				width : 150
			},{
				field : 'symptom',
				title : '病虫害发生症状',
				width : 150
			},{
				field : 'measures',
				title : '病虫害防治措施',
				width : 150
			},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.advice_plant_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
					str += '&nbsp;';
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.advice_plant_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
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
			id = rows[0].advice_plant_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前农作物及其关联关系？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/business/controller/AdviceController/deletePlant.do', {
						id : id
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
			id = rows[0].advice_plant_id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 600,
			height : 500,
			href : '${pageContext.request.contextPath}/business/controller/AdviceController/editPlantPage.do?id=' + id,
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
			width : 600,
			height : 500,
			href : '${pageContext.request.contextPath}/business/advice/plant/plantAdd.jsp',
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
						<th>农作物名称</th>
						<td colspan=""><input id="crops_id" name="crops_id" class="easyui-combobox" data-options="width:120, valueField: 'crops_id',textField: 'crops_name', 
								url: '${pageContext.request.contextPath}/business/controller/CropController/queryCropCombo.do'" />   </td>

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