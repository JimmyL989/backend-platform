<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap examineStateMap = (TreeMap)map.get("examineStateMap");
request.setAttribute("examineStateMap", examineStateMap);
%>

<head>
<title>产品实例管理</title>
<style type="text/css"> 
	.dialog-button { padding: 5px; text-align: left!important; } 
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/dataGrid.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			singleSelect : true,
			idField : 'Instanceid',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'Created_time',
			sortOrder : 'desc',
			checkOnSelect : true,
			selectOnCheck : false,
			nowrap : true,
			frozenColumns : [ [ {
				field : 'Instanceid',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'Region_code',
				title : '行政区划编码',
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
				field : 'Productid',
				title : '产品编码',
				width : 60,
				hidden:true
			},{
				field : 'Productname',
				title : '产品名称',
				width : 90
			}, {
				field : 'Period',
				title : '实例周期',
				width : 110,
				hidden:true
			}, {
				field : 'Content',
				title : '产品内容',
				width : 200
			}, {
				field : 'Created_time',
				title : '创建时间',
				width : 130,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			}, {
				field : 'cuserid',
				title : '创建人',
				width : 90,
				hidden:true
			}, {
				field : 'cusername',
				title : '创建人',
				width : 80
			}, {
				field : 'edate',
				title : '最后修改时间',
				width : 130,
				sortable : true,
				formatter:function(value,row,index){  
					if (value == "" || value == null)
						return "";
					var date = new Date(value);
					return date.format("yyyy-MM-dd hh:mm:ss");
                    }  
			}, {
				field : 'euserid',
				title : '最后修改人',
				width : 60,
				hidden:true
			}, {
				field : 'eusername',
				title : '最后修改人',
				width : 80
			}, {
				field : 'sendstate',
				title : '状态',
				width : 60,
				sortable : true,
				formatter:function(value,row,index){
					 
					return $("#"+value).val();
				}
			}, {
				field : 'action',
				title : '操作',
				width : 70,
				formatter : function(value, row, index) {
					var str = '';
						 str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑/查看"/>', row.Instanceid,row.sendstate, '${pageContext.request.contextPath}/resources/image/edit.gif');
					if (row.sendstate == '-2' || row.sendstate == '-3') {
						if(row.cuserid == '${sessionScope.logon_sysuser.userid}' || '${sessionScope.logon_sysuser.userid}' == '1') {
							str += '&nbsp;';
							str += $.formatString('<img onclick="toExamin(\'{0}\');" src="{1}" title="提交审批"/>', row.Instanceid, '${pageContext.request.contextPath}/resources/image/huitui.gif');
							str += '&nbsp;';
							str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.Instanceid, '${pageContext.request.contextPath}/resources/image/feizhi.gif');
						}
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
	
	

	function toExamin(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].Instanceid;
			if (rows[0].sendstate != '-2' && rows[0].sendstate != '-3') {
				parent.$.messager.alert('提示', '该状态无法提交', 'info');
				return;
			}
			if(rows[0].cuserid != '${sessionScope.logon_sysuser.userid}' && '${sessionScope.logon_sysuser.userid}' != '1') {
				parent.$.messager.alert('错误', '此短信非本人制作，无权提交。', 'error');
				return;
			}
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要提交审批当前产品实例？', function(b) {
			if (b) {
				var currentUserId = '${sessionScope.logon_sysuser.userid}';/*当前登录用户的ID*/
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/business/controller/ProductController/toExaminInstance.do', {
						instanceid : id, sendstate : '-1'
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
			id = rows[0].Instanceid;
			if (rows[0].sendstate != '-2' && rows[0].sendstate != '-3') {
				parent.$.messager.alert('提示', '该状态无法删除', 'info');
				return;
			}
			if(rows[0].cuserid != '${sessionScope.logon_sysuser.userid}' && '${sessionScope.logon_sysuser.userid}' != '1') {
				parent.$.messager.alert('错误', '此短信非本人制作，无权删除。', 'error');
				return;
			}
		} else {//点击操作里面的删除图标会触发这个
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('询问', '您是否要删除当前产品实例？', function(b) {
			if (b) {
				var currentUserId = '${sessionScope.logon_sysuser.userid}';/*当前登录用户的ID*/
					parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.post('${pageContext.request.contextPath}/business/controller/ProductController/deleteInstance.do', {
						instanceid : id
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

	function editFun(id, sendstate) {
	   
	    
		if (id == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].Instanceid;
			sendstate = rows[0].sendstate;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		
		
	/* 	 if(sendstate == -1 || sendstate == 0) {
	    	parent.$.messager.alert('提示', '该状态不允许被修改', 'info');
	    	return;
	    } */
		 parent.$.modalDialog({
			title : '编辑',
			width : 900,
			height : 600,
			modal : false,
			fit:true,
			maximizable : true,
			collapsible: true, 
			href : '${pageContext.request.contextPath}/business/controller/ProductController/editPage.do?instanceid=' + id,
			buttons : [ {
				
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-2';
					f.submit();
				}
			} ,{
				text : '保存并提交',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-1';
					f.submit();
				}
			} ]
		}); 
		
		 
	}
	function addFun() {
		parent.$.modalDialog({
			title : '添加',
			width : 900,
			height : 600,
			modal : false,
			fit:true,
			maximizable : true,
			collapsible: true,
			href : '${pageContext.request.contextPath}/business/product/productAdd.jsp',
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-2';
					f.submit();
				}
			} ,{
				text : '保存并提交',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-1';
					f.submit();
				}
			} ]
		});
	}

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
	<c:forEach items="<%=examineStateMap%>" var="examineStateMap">
		<input type="hidden" id="${ examineStateMap.key}" value="${ examineStateMap.value}" />
	</c:forEach>
<div id="win"></div>
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
           				 		$('#regionId1').combobox('reload', url); 
           				 		  }" />   
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
						
						<th>产品</th>
						<td>
							<input id="productId" name="productId" class="easyui-combobox" data-options="editable:false, valueField: 'Productid',textField: 'Product_name', 
								url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductComboNew_.do'"/>
						</td>
						<th>状态</th>
						<td>
							<select name="sendstate" class="easyui-combobox" data-options="width:120,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
								<c:forEach items="${examineStateMap}" var="examineStateMap">
									<option value="${examineStateMap.key}">${examineStateMap.value}</option>
								</c:forEach>
							</select>
						</td>
						
						<th>创建时间</th>
						<td><input class="dateSpan" name="created_time" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
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
			<div onclick="toExamin();" data-options="iconCls:'brick_go'">提交审批</div>
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑/查看</div>
	</div>
</body>
</html>