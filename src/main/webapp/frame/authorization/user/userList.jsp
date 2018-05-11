<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap isvalidMap = (TreeMap)map.get("isvalidMap");
%>
<head>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/editUser.do')}">
	<script type="text/javascript">
		$.canEdit = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/addUser.do')}">
	<script type="text/javascript">
		$.canAdd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/deleteUser.do')}">
	<script type="text/javascript">
		$.canDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/batchDeleteUser.do')}">
	<script type="text/javascript">
		$.canBatchDelete = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/resetPasswd.do')}">
	<script type="text/javascript">
		$.canResetPasswd = true;
	</script>
</c:if>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/frame/authorization/user/user_groupMapping.jsp')}">
	<script type="text/javascript">
		$.canUser_groupMapping = true;
	</script>
</c:if>
<title>用户管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/authorization/controller/UserController/dataGrid.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'sys_user_id',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'username',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				field : 'sys_user_id',
				title : '编号',
				width : 150,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'username',
				title : '用户名',
				width : 150,
				sortable : true
			},{
				field : 'usercode',
				title : '登录名',
				width : 60
			}, {
				field : 'isvalid',
				title : '帐号状态',
				width : 60,
				formatter:function(value,row,index){
					 
					return $("#"+value).val();
				}
			}, {
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
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
					if($.canEdit) {
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.sys_user_id, '${pageContext.request.contextPath}/resources/image/edit.gif');
					}
					 str += '&nbsp;';
					 if($.canUser_groupMapping) {
						str += $.formatString('<img onclick="grantFun(\'{0}\');" src="{1}" title="配置角色"/>', row.sys_user_id, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/wrench.png');
					 }
					str += '&nbsp;';
					if($.canDelete) {
						str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.sys_user_id, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
					}
					str += '&nbsp;';
					if($.canResetPasswd) {
						str += $.formatString('<img onclick="editPwdFun(\'{0}\');" src="{1}" title="重置密码"/>', row.sys_user_id, '${pageContext.request.contextPath}/resources/image/huitui.gif');
					}
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

	function editPwdFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].sys_user_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要重置当前用户密码？', function(b) {
			if(b){
				$.post('${pageContext.request.contextPath}/authorization/controller/UserController/resetPasswd.do', {
					sys_user_id : id
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

	function deleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].sys_user_id;
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
			if (b) {
				var currentUserId = '${sessionScope.logon_sysuser.userid}';/*当前登录用户的ID*/
				if (currentUserId != id) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/authorization/controller/UserController/deleteUser.do', {
						id : id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('提示', result.msg, 'info');
							dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
					}, 'JSON');
				} else {
					parent.$.messager.show({
						title : '提示',
						msg : '不可以删除自己！'
					});
				}
			}
		});
	}

	function batchDeleteFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					var currentUserId = '${sessionScope.logon_sysuser.userid}';/*当前登录用户的ID*/
					var flag = false;
					for ( var i = 0; i < rows.length; i++) {
						if (currentUserId != rows[i].sys_user_id) {
							ids.push(rows[i].sys_user_id);
						} else {
							flag = true;
						}
					}
					$.getJSON('${pageContext.request.contextPath}/authorization/controller/UserController/batchDeleteUser.do', {
						ids : ids.join(',')
					}, function(result) {
						if (result.success) {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						if (flag) {
							parent.$.messager.show({
								title : '提示',
								msg : '不可以删除自己！'
							});
						} else {
							parent.$.messager.alert('提示', result.msg, 'info');
						}
						parent.$.messager.progress('close');
					});
				}
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}

	function editFun(id) {
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].sys_user_id;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/authorization/controller/UserController/editPage.do?sys_user_id=' + id,
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
			title : '添加',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/frame/authorization/user/userAdd.jsp',
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

	/* function batchGrantFun() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			parent.$.modalDialog({
				title : '用户授权',
				width : 500,
				height : 300,
				href : '${pageContext.request.contextPath}/userController/grantPage?ids=' + ids.join(','),
				buttons : [ {
					text : '授权',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为授权成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler.find('#form');
						f.submit();
					}
				} ]
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要授权的记录！'
			});
		}
	}
*/
	function grantFun(id) {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		parent.$.modalDialog({
			title : '分配角色',
			width : 1000,
			height : 500,     
			href : '${pageContext.request.contextPath}/frame/authorization/user/user_groupMapping.jsp?id=' + id
		});
	} 
	
/* function grantFun(id) {
	dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	parent.$.modalDialog({
		title : '分配角色',
		width : 500,
		height : 300,      '${pageContext.request.contextPath}/authorization/controller/UserController/grantFun.do',
		href : '${pageContext.request.contextPath}/frame/authorization/user/user_groupMapping.jsp'
	});
} */ 

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
	<c:forEach items="<%=isvalidMap%>" var="isvalidMap">
		<input type="hidden" id="${ isvalidMap.key}" value="${ isvalidMap.value}" />
	</c:forEach>
<div id="win"></div>
	<div class="easyui-layout" data-options="fit : true,border : false">
 		<div data-options="region:'north',title:'查询条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm" method="post">
				<table class="table table-hover" style="display: none;">
					<tr>
						<th>登陆名</th>
						<td><input name="usercode" placeholder="" class="span2" /></td>
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
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/addUser.do')}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/batchDeleteUser.do')}">
			<a onclick="batchDeleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'delete'">批量删除</a>
		</c:if>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">重置</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls: 'attach',plain:true" onclick="Save_Excel($('#dataGrid'))" title="导出excel文件" style="float:right;"></a>
			
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/deleteUser.do')}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/editUser.do')}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
		<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/authorization/controller/UserController/resetPasswd.do')}">
			<div onclick="editPwdFun();" data-options="iconCls:'pencil_go'">重置密码</div>
		</c:if>
	</div>
</body>
</html>