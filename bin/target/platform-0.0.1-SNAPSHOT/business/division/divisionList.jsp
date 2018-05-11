<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="com.platform.frame.config.CONFIG" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
</script>
<title>行政区划管理</title>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid').treegrid({
			url : '${pageContext.request.contextPath}/business/controller/DivisionController/queryTreeGrid.do',
			idField : 'admin_region_id',
			treeField : 'admin_region_name',
			fit : true,
			fitColumns : true,
			border : false,
			rownumbers: true,
			animate:true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'admin_region_id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'admin_region_name',
				title : '资源名称',
				width : 200
			}, {
				field : 'action',
				title : '操作',
				width : 50,
				formatter : function(value, row, index) {
					var str = '';
					if(row.admin_region_id != 1){
							str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.admin_region_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
						str += '&nbsp;';
							str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.admin_region_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onContextMenu : function(e, row) {
				if(row.admin_region_id != 1){
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.admin_region_id);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
				}
			},
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
				$(this).treegrid('tooltip');
			}
		});
	});

	
	function deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			
			var cn = treeGrid.treegrid('getChildren', node.admin_region_id);
			
			if(cn != '') {
				parent.$.messager.alert('提示', '包含子节点，无法删除');
				return;
			}
			
			parent.$.messager.confirm('询问', '您是否要删除当前行政区划？', function(b) {
				if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/business/controller/DivisionController/deleteDivision.do', {
						id : node.admin_region_id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							treeGrid.treegrid('reload');
							parent.layout_west_tree.tree('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
				}
			});
		}
	}

	function editFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
				title : '编辑资源',
				width : 500,
				height : 300,
				href : '${pageContext.request.contextPath}/business/controller/DivisionController/editPage.do?admin_region_id=' + node.admin_region_id,
				buttons : [ {
					text : '保存',
					handler : function() {
						parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
		}
	}

	function addFun() {
		if(treeGrid.treegrid('getSelected') == null) {
			parent.$.messager.alert('提示','请选父节点');
			return;
		}
		var admin_region_id = treeGrid.treegrid('getSelected').admin_region_id;
		var admin_region_name = treeGrid.treegrid('getSelected').admin_region_name;
			
		parent.$.modalDialog({
			title : '添加资源',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/business/controller/DivisionController/addPage.do?parentid=' + admin_region_id + "&parentname=" + encodeURI(admin_region_name),
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}

	function redo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('expandAll', node.admin_region_id);
		} else {
			treeGrid.treegrid('expandAll');
		}
	}

	function undo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('collapseAll', node.admin_region_id);
		} else {
			treeGrid.treegrid('collapseAll');
		}
	}

</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
		<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
		<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
	
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>