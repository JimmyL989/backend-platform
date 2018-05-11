<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/editGroup.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/addGroup.do')}">
	<script type="text/javascript">
		$.canAdd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/deleteGroup.do')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/frame/authorization/group/group_userMapping.jsp')}">
	<script type="text/javascript">
		$.canGroup_userMapping = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/PrivController/allTree.do')}">
	<script type="text/javascript">
		$.canAllTree = true;
	</script>
</c:if>
<title>角色管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/authorization/controller/GroupController/dataGrid.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'sys_group_id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'groupname',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'sys_group_id',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'groupname',
				title : '角色名称',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'cdate',
				title : '创建时间',
				width : 150,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			}, {
				field : 'edate',
				title : '最后修改时间',
				width : 150,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			},  {
				field : 'memo',
				title : '备注',
				width : 60
			},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if($.canEdit)
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.sys_group_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
					str += '&nbsp;';
					if($.canGroup_userMapping)
						str += $.formatString('<img onclick="grantFun(\'{0}\');" src="{1}" title="配置人员"/>', row.sys_group_id, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/wrench.png'); 
					str += '&nbsp;';
					if($.canAllTree)
						str += $.formatString('<img onclick="grantPrivFun(\'{0}\');" src="{1}" title="配置资源"/>', row.sys_group_id, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/wrench_orange.png'); 
					str += '&nbsp;';
					if($.canDelete)
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.sys_group_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
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
			id = rows[0].sys_group_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前角色及其关联关系？', function(b) {
			if (b) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/authorization/controller/GroupController/deleteGroup.do', {
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
			id = rows[0].sys_group_id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑角色',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/authorization/controller/GroupController/editPage.do?sys_group_id=' + id,
			buttons : [ {
				text : '编辑',
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
			title : '添加角色',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/frame/authorization/group/groupAdd.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					f.submit();
				}
			} ]
		});
	}


	function grantFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
			title : '分配人员',
			width : 900,
			height : 500,     
			href : '${pageContext.request.contextPath}/frame/authorization/group/group_userMapping.jsp?id=' + id
		});
	} 
	
	function grantPrivFun(id){
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
			title : '分配资源',
			width : 500,
			height : 500,
			href : '${pageContext.request.contextPath}/authorization/controller/PrivController/group_privMapping.do?id=' + id,
			buttons : [ {
				text : '确定',
				handler : function() {
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
 		<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm" method="post">
				<table class="table table-hover" style="display: none;">
					<tr>
						<th>角色名称</th>
						<td><input name="groupname" placeholder="" class="span2" /></td>
					</tr>
					<!-- <tr>
						<th>创建时间</th>
						<td><input class="span2" name="createdatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="createdatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr>
					<tr>
						<th>最后修改时间</th>
						<td><input class="span2" name="modifydatetimeStart" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input class="span2" name="modifydatetimeEnd" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" /></td>
					</tr> -->
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div> 
	<div id="toolbar" style="display: none;">
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/addGroup.do')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">重置</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/deleteGroup.do')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/GroupController/editGroup.do')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>
</html>