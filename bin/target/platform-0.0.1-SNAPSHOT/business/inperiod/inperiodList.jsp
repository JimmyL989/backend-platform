<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/frame/common/header.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<%
Map map = (TreeMap)application.getAttribute("CODE_TABLE_CONTENT");
TreeMap inperiodMap = (TreeMap)map.get("inperiodMap");
request.setAttribute("inperiodMap", inperiodMap);
%>
<head>
<title>农茬期配置</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '${pageContext.request.contextPath}/business/controller/InperiodController/dataGrid.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'productid',
			sortOrder : 'asc',
			checkOnSelect : true,
			selectOnCheck : false,
			rownumbers : true,
			nowrap : false,
			singleSelect : true,
			frozenColumns : [ [ {
				field : 'region_code',
				title : '编号',
				width : 150,
				hidden : true
			},{
				field : 'productid',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'Productname',
				title : '产品',
				width : 150
			},{
				field : 'name1',
				title : '省',
				width : 150
			},{
				field : 'name2',
				title : '市',
				width : 150
			},{
				field : 'name3',
				title : '县',
				width : 150
			}, {
				field : 'type',
				title : '状态',
				width : 160,
				formatter:function(value,row,index){
					 
					return $("#"+value).val();
				}
			},{
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '';
						str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row.region_code, row.productid, '${pageContext.request.contextPath}/resources/image/edit.gif');
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

	function editFun(region_code, productid) {
		if (region_code == undefined) {
			var rows = dataGrid.datagrid('getSelections');
			region_code = rows[0].region_code;
			productid = rows[0].productid;
		} else {
			dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.modalDialog({
			title : '编辑',
			width : 800,
			height : 500,
			href : '${pageContext.request.contextPath}/business/controller/InperiodController/editPage.do?regionId=' + region_code + '&productId=' + productid,
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
	<c:forEach items="<%=inperiodMap%>" var="inperiodMap">
		<input type="hidden" id="${ inperiodMap.key}" value="${ inperiodMap.value}" />
	</c:forEach>
	<div class="easyui-layout" data-options="fit : true,border : false">
 		<div data-options="region:'north',title:'过滤条件',border:false" style="height: 60px; overflow: hidden;">
			<form id="searchForm" method="post">
				<table class="table table-hover mySearchTable">
					<tr>
						<th>产品</th>
						<td>
							<input id="productId" name="productId" class="easyui-combobox" data-options="editable:false, valueField: 'Productid',textField: 'Product_name', 
								url: '${pageContext.request.contextPath}/business/controller/ProductController/queryProductComboNew_.do'"/>
						</td>
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
						
						<th>状态</th>
						<td>
							<select name="type" class="easyui-combobox" data-options="width:120,editable:false,panelHeight:'auto'">
									<option value="">全部</option>
								<c:forEach items="${inperiodMap}" var="inperiodMap">
									<option value="${inperiodMap.key}">${inperiodMap.value}</option>
								</c:forEach>
							</select>
						</td>
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

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
	</div>
</body>
</html>