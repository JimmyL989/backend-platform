<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">
	var dataGrid;
	var dataGrid_;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/authorization/controller/UserController/noExistGroup.do?sys_group_id=' + "<%=request.getParameter("id")%>" + '&exist=0',
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
			}, {
				field : 'username',
				title : '用户名',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'usercode',
				title : '登录名',
				width : 60
			} ] ],
			onLoadSuccess : function() {
				$('#searchForm table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
		
		
		dataGrid_ = $('#dataGrid_').datagrid({
			url : '${pageContext.request.contextPath}/authorization/controller/UserController/noExistGroup.do?sys_group_id=' + "<%=request.getParameter("id")%>" + '&exist=1',
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
			}, {
				field : 'username',
				title : '用户名',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'usercode',
				title : '登录名',
				width : 60
			} ] ],
			onLoadSuccess : function() {
				$('#searchForm_ table').show();
				parent.$.messager.progress('close');

				$(this).datagrid('tooltip');
			}
		});
	});

	function assignUser() {
		var rows = dataGrid.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].sys_user_id);
			}
			$.getJSON('${pageContext.request.contextPath}/authorization/controller/GroupController/assignUser.do', { ids : ids.join(','), sys_group_id : "<%=request.getParameter("id")%>" }, 
					function(result) {
						if (result.success) {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
							dataGrid_.datagrid('load');
							dataGrid_.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						/* parent.$.messager.alert('提示', result.msg, 'info'); */
						parent.$.messager.progress('close');
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要分配的角色！'
			});
		}
	}
	
	function cancelUser() {
		var rows = dataGrid_.datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].sys_user_id);
			}
			$.getJSON('${pageContext.request.contextPath}/authorization/controller/GroupController/cancelUser.do', { ids : ids.join(','), sys_group_id : "<%=request.getParameter("id")%>" }, 
					function(result) {
						if (result.success) {
							dataGrid.datagrid('load');
							dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
							dataGrid_.datagrid('load');
							dataGrid_.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						}
						/* parent.$.messager.alert('提示', result.msg, 'info'); */
						parent.$.messager.progress('close');
			});
		} else {
			parent.$.messager.show({
				title : '提示',
				msg : '请勾选要分配的角色！'
			});
		}
	}

	function searchFun() {
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
	function searchFun_() {
		dataGrid_.datagrid('load', $.serializeObject($('#searchForm_')));
	}
	function cleanFun_() {
		$('#searchForm_ input').val('');
		dataGrid_.datagrid('load', {});
	}
</script>

<table border="0" cellpadding="0" cellspacing="0" width="98%" height=94%>
	<tr>
		<td valign="top" height="457" width="49%" align="left">
			<div style="height: 5%">未授权的用户</div>
			<div style="height: 10%">
				<form id="searchForm" method="post">
					<table class="table table-hover table-condensed"
						style="display: none;">
						<tr>
							<th>登录名</th>
							<td><input id="usercode" name="usercode"
								placeholder="可以模糊查询登录名" class="span2" /></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">过滤条件</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="height: 85%" >
				<table id="dataGrid" style="height: 100%"></table>
			</div>
		</td>
		<td width="2%" align="center"><br /> 
			<a onclick="assignUser()" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:false"/> <br /> 
			<a onclick="cancelUser()" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:false"/> <br /> 
		<td valign="top" height="457" width="49%">
			<div style="height: 5%">已授权的用户</div>
			<div style="height: 10%">
				<form id="searchForm_" method="post">
					<table class="table table-hover table-condensed"
						style="display: none;">
						<tr>
							<th>登录名</th>
							<td><input id="usercode" name="usercode"
								placeholder="可以模糊查询登录名" class="span2" /></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun_();">过滤条件</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun_();">清空条件</a></td>
						</tr>
					</table>
				</form>
			</div>
			<div style="height: 85%">
				<table id="dataGrid_" style="height: 100%"></table>
			</div>
		</td>
	</tr>
</table>