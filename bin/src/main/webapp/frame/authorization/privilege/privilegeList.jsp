<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="com.platform.frame.config.CONFIG" %>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
</script>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/editPriv.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/addPriv.do')}">
	<script type="text/javascript">
		$.canAdd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/deletePriv.do')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>


<title>资源管理</title>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid').treegrid({
			url : '${pageContext.request.contextPath}/authorization/controller/PrivController/queryTreeGrid.do',
			idField : 'sys_privilege_id',
			treeField : 'name',
			fit : true,
			fitColumns : true,
			border : false,
			rownumbers: true,
			animate:true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'sys_privilege_id',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '资源名称',
				width : 200
			}, {
				field : 'menuname',
				title : '类型',
				width : 150
			}, {
				field : 'url',
				title : '资源路径',
				width : 230
			}, {
				field : 'memo',
				title : '备注',
				width : 230
			}, {
				field : 'menutype',
				title : '资源类型ID',
				width : 150,
				hidden : true
			}, {
				field : 'action',
				title : '操作',
				width : 50,
				formatter : function(value, row, index) {
					var str = '';
					if(row.sys_privilege_id != 1){
						if ($.canEdit) {
							str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.sys_privilege_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
						}
						str += '&nbsp;';
						if($.canDelete) {
							str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.sys_privilege_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
						}
						
					}
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onContextMenu : function(e, row) {
				if(row.sys_privilege_id != 1){
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.sys_privilege_id);
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
			
			var cn = treeGrid.treegrid('getChildren', node.sys_privilege_id);
			
			if(cn != '') {
				parent.$.messager.alert('提示', '包含子节点，无法删除');
				return;
			}
			
			parent.$.messager.confirm('询问', '您是否要删除当前资源？', function(b) {
				if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/authorization/controller/PrivController/deletePriv.do', {
						id : node.sys_privilege_id
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
				href : '${pageContext.request.contextPath}/authorization/controller/PrivController/editPage.do?sys_privilege_id=' + node.sys_privilege_id,
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
		var sys_privilege_id = treeGrid.treegrid('getSelected').sys_privilege_id;
		var name = treeGrid.treegrid('getSelected').name;
			
		parent.$.modalDialog({
			title : '添加资源',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/authorization/controller/PrivController/addPage.do?parentid=' + sys_privilege_id + "&parentname=" + encodeURI(name),
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
			treeGrid.treegrid('expandAll', node.sys_privilege_id);
		} else {
			treeGrid.treegrid('expandAll');
		}
	}

	function undo() {
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			treeGrid.treegrid('collapseAll', node.sys_privilege_id);
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
	<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/addPriv.do')}">
		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
	</c:if>
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
		<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
		<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
	
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/addPriv.do')}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/deletePriv.do')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/editPriv.do')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>
</html>