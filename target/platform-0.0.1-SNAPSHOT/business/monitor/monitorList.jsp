<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap examineStateMap = (TreeMap)map.get("examineStateMap");
TreeMap monitorStateMap = (TreeMap)map.get("monitorStateMap");
request.setAttribute("examineStateMap", examineStateMap);
request.setAttribute("monitorStateMap", monitorStateMap);
%>
<head>
<c:if test="${fn:contains(sessionScope._ALL_AUTH_URL_ID_, '/business/examin/examinList.jsp')}">
	<script type="text/javascript">
		$.canExamin = true;
	</script>
</c:if>
<title>产品实例监控</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/ProductController/queryInstanceMonitor.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			singleSelect : true,
			idField : 'Productid',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'Productid',
			sortOrder : 'desc',
			checkOnSelect : true,
			selectOnCheck : false,
			nowrap : true,
			frozenColumns : [ [ {
				field : 'Productid',
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
				hidden : true
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
				field : 'Productname',
				title : '产品名称',
				width : 90
			}, {
				field : 'sendstate',
				title : '状态',
				width : 60,
				hidden:true
			}, {
				field : 'instanceid',
				title : 'instanceid',
				width : 60,
				hidden:true
			},{
				field : 'x1',
				title : '未录入',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					 if(value == 1)
						return $.formatString('<img onclick="addFun(\'{0}\',\'{1}\');" src="{2}" title=""/>', row.Productid, row.Region_code, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
				}
			},{
				field : 'x2',
				title : '待提交',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					if(value == 1)
						return $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title=""/>', row.instanceid, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
				}
			},{
				field : 'x3',
				title : '待审批',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					if(value == 1)
						return $.formatString('<img onclick="editExaminFun(\'{0}\');" src="{1}" title=""/>', row.instanceid, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
				}
			},{
				field : 'x4',
				title : '被驳回',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					if(value == 1)
						return $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title=""/>', row.instanceid, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
				}
			},{
				field : 'x5',
				title : '审批通过',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					if(value == 1)
						return $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title=""/>', row.instanceid, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
				}
			},{
				field : 'x6',
				title : '已发送',
				width : 90,
				align:'center',
				formatter:function(value,row,index){
					if(value == 1)
						return $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title=""/>', row.instanceid, '${pageContext.request.contextPath}/resources/easyui_resource/style/images/extjs_icons/arrow/accept.png');
					return "";
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
				
			}
		});
	});
	
	function editExaminFun(id) {
	   
	   
		   if(!$.canExamin){
		   		parent.$.messager.alert('提示', '您无权限进行此操作，如有需要请联系管理员。', 'info');
		   		return;
		   }
	    
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		
		 parent.$.modalDialog({
			title : '编辑',
			width : 800,
			height : 470,
			modal : false,
			fit:true,
			maximizable : false,
			collapsible: true,
			href : '${pageContext.request.contextPath}/business/controller/ProductController/editExaminPage.do?instanceid=' + id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-1';
					f.submit();
				}
			},{
				text : '保存修改并审批通过',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '0';
					f.submit();
				}
			},{
				text : '保存修改并审批驳回',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#form');
					parent.$.modalDialog.sendstate = '-3';
					f.submit();
				}
			} ]
		});
	}
	
	function toExamin(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dataGrid.datagrid('getSelections');
			id = rows[0].Instanceid;
			if (rows[0].sendstate != '-2' && rows[0].sendstate != '-3') {
				parent.$.messager.alert('提示', '该状态无法提交', 'info');
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

	function editFun(id) {
	   
	    
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		
		
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
	function addFun(Productid, Region_code) {
	 	var myDate = new Date();
		if($('#created_time').val()	!= '' && myDate.format("yyyy-MM-dd") != $('#created_time').val()){
			parent.$.messager.alert('错误', '可能试图录入非当天产品！只允许录入当天产品实例！请重置日期框！', 'error');
			return;
		}
		parent.$.modalDialog({
			title : '添加',
			width : 900,
			height : 600,
			modal : false,
			fit:true,
			maximizable : true,
			collapsible: true,
			href : '${pageContext.request.contextPath}/business/product/productAdd.jsp?productid=' + Productid + '&region_code=' + Region_code,
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
		if ($("#searchForm input[name=sendstate]").val() == '-4') {
			$("#searchForm input[name=sendstate]").val('');
			$("#searchForm input[name=x1]").val('1');
		} else {
			$("#searchForm input[name=x1]").val('');
		}
		
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
				<input name="x1" type="hidden"/>
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
						
						<th>产品</th>
						<td>
							<input id="productId" name="productId" class="easyui-combobox" data-options="editable:false, valueField: 'Productid',textField: 'Product_name', 
								url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductComboNew_.do'"/>
						</td>
						<th>状态</th>
						<td>
							<select name="sendstate" class="easyui-combobox" data-options="width:120,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
								<c:forEach items="${monitorStateMap}" var="monitorStateMap">
									<option value="${monitorStateMap.key}">${monitorStateMap.value}</option>
								</c:forEach>
								<c:forEach items="${examineStateMap}" var="examineStateMap">
									<option value="${examineStateMap.key}">${examineStateMap.value}</option>
								</c:forEach>
							</select>
						</td>
						
						<th>日期</th>
						<td><input class="dateSpan" name="created_time" id="created_time" placeholder="点击选择日期" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
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